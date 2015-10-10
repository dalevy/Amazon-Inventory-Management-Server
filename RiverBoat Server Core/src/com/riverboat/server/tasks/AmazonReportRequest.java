/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.tasks;

import com.riverboat.amazon.reports.AmazonReportType;
import com.riverboat.amazon.reports.AmazonReports;
import com.riverboat.model.RiverBoatUser;
import com.riverboat.server.config.PathNames;
import com.riverboat.server.config.PlatformVariables;
import com.riverboat.server.manager.ServerTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Requests an Amazon Report, assigns the customer account the correct Report Id,
 * Type, and Time and then updates the server task list with the generated report Id and request
 * @author D.Levy 
 * 5-25-2014
 */
public class AmazonReportRequest {
    
    RiverBoatUser user;
    String reportType;
    AmazonReports amazon_reports;
    Connection dbConnection;
    RiverBoatServerUpdateTaskList taskUpdate;
    //Time in millis to wait to get a new update, +1000 (1 second) to allow maximum efficient lossless restoration
    final long REQUESTTIMER = PlatformVariables.getReportRequestList_max_throttle +1000;
    
    //Current throttle ratio for getmatchingProductForId operation, max -1 to allow efficient lossless restoration
    final int throttle_ratio = PlatformVariables.getMatchingProductForId_max_throttle - 1;
    
    //Secondary method of instantiation get account, build user, complete report request
    public AmazonReportRequest(String account, String reportType)
    {
       RiverBoatServerRetrieve ret = new RiverBoatServerRetrieve(); 
       this.user = ret.getCustomer(Integer.parseInt(account));
       this.reportType = reportType;
       requestReport();
    }
    
    //Prefered method of instantiation -- receive a user and report Type and complete the task
    public AmazonReportRequest(RiverBoatUser user, String reportType)
    {
        this.user = user;
        this.reportType = reportType;
        requestReport();
    }
    
    /**
    * Requests an amazon Report based on Report Type when object is created
    */
    public final void requestReport()
    {   
        if(user == null) throw new IllegalArgumentException("RiverBoat: User cannot be null");
       
        String serverTaskType = null;
        
        //Set to failure on initialization so that the server will know it could not complete the request
        
        String requestId = "FAILURE";
        
        //Instantiate the user
        amazon_reports = new AmazonReports();
            amazon_reports.setUser(user);
        
            
        //Verify the report type and complete action
        if(reportType.equalsIgnoreCase(AmazonReportType.Merchant_Listings_Lite_Report))
        {
            requestId = amazon_reports.getMerchantlistings();
            serverTaskType = ServerTask.AMAZON_MERCHANT_LISTINGS_LITE_REPORT;
        }
        
        
        
        //Update the server with the report Id and releveant information for this task
        taskUpdate = new RiverBoatServerUpdateTaskList(user.getAccount(), "amazon", serverTaskType, requestId,ServerTask.AVAILABLE);
        //updateTaskList(user.getAccount(),"amazon", serverTaskType, requestId);
       
    }
    
    /**
     * Connects to the database and updates the server task list with the user account
     * that is affected by this task, the platform (Amazon,Ebay,Rakatuuen, etc), and the time
     * stamp
     * @param account
     * @param platform
     * @param platformId
     * @param taskType
     */
    public void updateTaskList(String account, String platform, String taskType, String platformId)
    {
          PreparedStatement preparedStatement= null;
        
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append("servertasks");
        
        String insertStatement = "INSERT INTO "+db.toString()
				+ "(account, platform, type, id) VALUES"
				+ "(?,?,?,?)";
        
    try{
            
            //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           System.out.println("Connected to Database. Accessing Table"+db.toString());
           //Prepare statement
           preparedStatement = dbConnection.prepareStatement(insertStatement);
           
           //Add values to the statement
           preparedStatement.setString(1,account);
           preparedStatement.setString(2,platform);
           preparedStatement.setString(3,taskType);
           preparedStatement.setString(4,platformId);


           
           //Execute statement
           preparedStatement.executeUpdate();
           
     }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            
     }finally{
             if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException logOrIgnore) { logOrIgnore.printStackTrace();}
             if (dbConnection != null) try { dbConnection.close(); } catch (SQLException logOrIgnore) {logOrIgnore.printStackTrace();}
       }
    }
    
    public void setUser(RiverBoatUser user)
    {
        this.user = user;
    }
    
    public void setReportType(String reportType)
    {
        this.reportType = reportType;
    }
            
    
    
    
}
