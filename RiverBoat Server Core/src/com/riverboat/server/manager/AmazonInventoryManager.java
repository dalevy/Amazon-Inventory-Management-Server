/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.manager;

import com.riverboat.server.config.PathNames;
import com.riverboat.server.config.RiverBoatServerConfig;
import com.riverboat.server.util.TimestampComparator;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

/**
 *
 * @author DMita Levy
 */
public class AmazonInventoryManager implements Runnable{
    
    //Compares the current server time with the timestamp
    TimestampComparator tscomp;
    //Updates to inventory will be scheduled every 15 minutes
    public static int UPDATE_INTERVAL = 15;
    
    
    public AmazonInventoryManager()
    {
        tscomp = new TimestampComparator();
    }
    
    public void updateRecords()
    {
        
        Connection dbConnection;
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_AMAZONCUSTOMERS);
        
        Statement statement = null;
        
        String query = "SELECT * FROM "+db.toString()+" WHERE status ='ACTIVE' and update_status ='READY' OR update_type ='NEVER' and status ='ACTIVE'";
        
        
    try{
            
            //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println(this.getClass().getCanonicalName());
           System.out.println("\t\nOpenining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           //create statement
           statement = dbConnection.createStatement();       
           System.out.println("\tConnected to Database. Accessing Table"+db.toString());
           System.out.println("\tSending Query:\n"+query);
           
           //execute
           ResultSet rs = statement.executeQuery(query);
           
           //Represents the current customer row
           AmazonDatabaseCustomer current = new AmazonDatabaseCustomer();
                      
           //Get each customer and update if necessary
           while(rs.next()){
               current.account=(rs.getString("account"));
               current.accessKey=(rs.getString("access_key"));
               current.updateType = (rs.getString("update_type"));
               current.updateStatus = (rs.getString("update_status"));
               current.lastUpdate =(rs.getString("last_update"));
               current.merchantId =(rs.getString("merchant_id"));
               current.marketplace=(rs.getString("marketplace"));
               current.secretKey = (rs.getString("secret_key"));
               current.timestamp=(rs.getTimestamp("last_update"));
               
               System.out.println(this.getClass().getCanonicalName()+"\n--Updating Customer--\n"+current.getCustomer());
               
               //Updat ethe current customer record
               updateCustomer(current);
              
           }
           
           
            rs.close();
            statement.close();
            dbConnection.close();  
            
     }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            
     }finally{
       
        
       }
        
        
    }
    
    /**
     * Updates an individual Amazon customer record for inventory update
     * @param cust 
     */
    public void updateCustomer(AmazonDatabaseCustomer cust)
    {
        
        if(cust.updateStatus.equalsIgnoreCase("NEVER"))
        {
            lockCustomer(cust);
            
            if(verifyLock(cust))
            {
                System.out.println("Lock Verified");
                System.exit(0);
            }
            return;
        }
        
        if(tscomp.compareTime(cust.timestamp));
        
        //If it has been more than 15 minutes since this record was updated
        if(tscomp.getMinutes() > UPDATE_INTERVAL)
        {
            //lock the customer so no other managers will try to update the record
            lockCustomer(cust);
            
            //Check if any other managers are looking at this record
            if(verifyLock(cust))
            {
                //Lock verified for this customer -- customer  is locked to this server serial
                //update the customer
                
            }
    
            
        }
            
            
    }
    
    /**
     * Verifies if the serial number in the config is 
     * @param cust
     * @return 
     */
    public boolean verifyLock(AmazonDatabaseCustomer cust)
    {
       boolean verified = false;
        
         Connection dbConnection;
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_AMAZONCUSTOMERS);
        
        Statement statement = null;
        
        System.out.println(this.getClass().getCanonicalName()+"\n--Verifying Customer Lock--\n"+cust.getCustomer());
        String query = "SELECT * FROM "+db.toString()+" WHERE account ='"+cust.account+"'";
        System.out.println(query);

        try{
            
            //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           //create statement
           statement = dbConnection.createStatement();       
           System.out.println("\tConnected to Database. Accessing Table"+db.toString());
           System.out.println("\tSending Query:\n"+query);
           
           //execute
           ResultSet rs = statement.executeQuery(query);
           
           //Inner class that Represents the current customer row
           AmazonDatabaseCustomer current = new AmazonDatabaseCustomer();
           
           //Used to compare time stamps from the previous update
           
           //Get each customer and update if necessary
           while(rs.next()){
               
               //Get this servers serial number
                String serial = RiverBoatServerConfig.serial;
              
                //Check if the lock on the account matches this servers serial number
                if(serial.equalsIgnoreCase(rs.getString("update_status")))
                    verified = true;
           }
           
           
            rs.close();
            statement.close();
            dbConnection.close();  
            
     }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            
     }
  
        return verified;
    }
    
    /**
     * "Locks" customer record so that other Managers will not perform updates
     * A lock is a the serial number of this server application taken from the server config
     * By "locking" the record it is not in the READY statea and will be ignored
     * @param cust
     * @return 
     */
    public boolean lockCustomer(AmazonDatabaseCustomer cust)
    {
        boolean success = false;
              
        Connection dbConnection = null;
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_AMAZONCUSTOMERS);
        
        Statement statement = null;
        PreparedStatement preparedStatement = null;
                
        System.out.println(this.getClass().getCanonicalName()+"\n--Locking Customer--\n"+cust.getCustomer());
        
        String insertStatement = "UPDATE "+db.toString()
				+ " SET update_status='"+RiverBoatServerConfig.serial+"'"
                                + " WHERE account ='"+cust.account+"'";
        
    try{
            
            //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           System.out.println("Connected to Database. Accessing Table"+db.toString());
           //Prepare statement
           statement = dbConnection.prepareStatement(insertStatement);
           
           //Execute Update
           statement.executeUpdate(insertStatement);  

           
     }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            
     }finally{
             if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException logOrIgnore) { logOrIgnore.printStackTrace();}
             if (dbConnection != null) try { dbConnection.close(); } catch (SQLException logOrIgnore) {logOrIgnore.printStackTrace();}
       }
        
        
       return success; 
        
    }
    

    @Override
    public void run() {

        updateRecords();
    }
    
    
    public class AmazonDatabaseCustomer{
        
       public String account;
       public String lastUpdate;
       public String merchantId;
       public String marketplace;
       public String accessKey;
       public String secretKey;
       public String updateType;
       public String updateStatus;
       public Timestamp timestamp;
       
       public String getCustomer()
       {
           StringBuilder sb = new StringBuilder();
           sb.append("Account: ");
           sb.append(account);
           sb.append("\nLast Update: ");
           sb.append(lastUpdate);
           sb.append("\nUpdate Type: ");
           sb.append(updateType);
           sb.append("\nUpdate Status: ");
           sb.append(updateStatus);
           sb.append("\nMerchant Id: ");
           sb.append(merchantId);
           sb.append("\nMarketplace: ");
           sb.append(marketplace);
           sb.append("\nAccess Key: ");
           sb.append(accessKey);
           sb.append("\nSecret Key: ");
           sb.append(secretKey);
           
           return sb.toString();
           
       }
    }
    
}
