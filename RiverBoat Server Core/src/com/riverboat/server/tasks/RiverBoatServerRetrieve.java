/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.tasks;


import com.riverboat.model.RiverBoatUser;
import com.riverboat.server.config.PathNames;
import java.sql.*;

/**
 *
 * @author D.Levy
 * Retrieves records from server
 */
public class RiverBoatServerRetrieve {
    
    Connection dbConnection;
    
    private String url;
    private String username;
    private String password;
    private RiverBoatUser user;
    
   public RiverBoatServerRetrieve()
   {
       
   }
    
   public RiverBoatServerRetrieve(String url, String username, String password)
   {
       this.url = url;
       this.username = username;
       this.password = password;
   }
   
   
   public RiverBoatUser getCustomer(int account)
   {
      
       Statement statement = null;
       user = new RiverBoatUser();
       try{
          
           //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+url);
           dbConnection = DriverManager.getConnection(url,username,password);
           
           //Execute a query
           statement = dbConnection.createStatement();
           
           String query;
            query = "SELECT * FROM (SELECT * FROM "+PathNames.DB_NAME+"."+PathNames.DB_CUSTOERS+" WHERE customers.account ="+account+") AS c1, riverboatdb.amazoncustomers AS a1 WHERE c1.account = a1.account";
           
            ResultSet rs = statement.executeQuery(query);
            
            while(rs.next())
            {
                user.setAccount(String.valueOf(rs.getInt("account")));
                user.setCompany(rs.getString("company"));
                user.setEmail(rs.getString("email"));
                user.setFirst(rs.getString("first"));
                user.setLast(rs.getString("last"));
                user.setAddress(rs.getString("address"));
                user.setPhone(rs.getString("phone"));
                user.amazon.setApplicationName(PathNames.APP_NAME);
                user.amazon.setApplicationVersion(PathNames.APP_VERSION);
                user.amazon.setAmazonAccessKey(rs.getString("access_key"));
                user.amazon.setMarketplace(rs.getString("marketplace"));
                user.amazon.setSellerID(rs.getString("merchant_id"));
                user.amazon.setAmazonSecretKey(rs.getString("secret_key"));
              
            }
            
            rs.close();
            statement.close();
            dbConnection.close();
            
       }catch(SQLException |ClassNotFoundException e){
           
       }finally{
           try{
               if(statement != null)
               {
                   statement.close();
                   dbConnection.close();
               }
           }catch(SQLException e2){
               
           }
               
              
       }
       
       return user;
   }
}
