/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.tasks;

import com.riverboat.server.config.PathNames;
import com.riverboat.server.manager.ServerTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Creates a new task and updates the task list database
 * @author D.Levy
 */
public class RiverBoatServerUpdateTaskList {
    
    Connection dbConnection;
    private String taskNumber;
    private String account;
    private String platform;
    private String type;
    private String id;
    private String time;
    private String status;
    
    public RiverBoatServerUpdateTaskList()
    {
        
    }
    
    public RiverBoatServerUpdateTaskList(String account, String platform, String type, String id, String status)
    {
        this.account = account;
        this.platform = platform;
        this.type = type;
        this.id = id;
        this.status = status;
        createNewTask(account, platform, type, id, status);
    }
    
    /**
     * Updates the status of an individual task based on the ServerTask class static variables
     * @param task
     * @param status 
     */
    public void updateTaskStatus(ServerTask task, String status)
    {
          PreparedStatement preparedStatement;
        
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_SERVERTASKLIST);
        
        String insertStatement ="UPDATE "+db.toString()+"SET status = ? WHERE task = ?";
        
        try{
            
                 //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           System.out.println("Connected to Database. Accessing Table"+db.toString());
           //Prepare statement
           preparedStatement = dbConnection.prepareStatement(insertStatement);
           
           preparedStatement.setString(1, status);
           preparedStatement.setString(2,task.getTaskNumber());

           
           //Execute
           preparedStatement.executeUpdate(insertStatement);
            
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            
        }
    }
    
    /**
     * Creates a new Task and adds it to the Database task list.
     * @param account
     * @param platform
     * @param type
     * @param id
     * @param status 
     */
    public final void createNewTask(String account, String platform, String type, String id, String status)
    {
         PreparedStatement preparedStatement;
        
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_SERVERTASKLIST);
        
        String insertStatement ="INSERT INTO "+db.toString()+"(account,platform,type,id,status) VALUES (?,?,?,?,?)";
        
        try{
            
                 //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           System.out.println("Connected to Database. Accessing Table"+db.toString());
           //Prepare statement
           preparedStatement = dbConnection.prepareStatement(insertStatement);
           
           preparedStatement.setString(1,account);
           preparedStatement.setString(2, platform);
           preparedStatement.setString(3, type);
           preparedStatement.setString(4, id);
           preparedStatement.setString(5, status);
           
           preparedStatement.executeUpdate(insertStatement);
           
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            
        }
    }
    
    /**
     * Delete a task from the Databases task list
     * @param task 
     */
    public void deleteTask(ServerTask task)
    {
        PreparedStatement preparedStatement;
        
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_SERVERTASKLIST);
        
        String insertStatement ="DELETE FROM "+db.toString()+" WHERE task = ?";
        
        try{
            
                 //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           System.out.println("Connected to Database. Accessing Table"+db.toString());
           //Prepare statement
           preparedStatement = dbConnection.prepareStatement(insertStatement);
           
           preparedStatement.setString(1,task.getTaskNumber());

         
           //Execute
           preparedStatement.executeUpdate(insertStatement);
            
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally{
            
        }
    }

    /**
     * @return the taskNumber
     */
    public String getTaskNumber() {
        return taskNumber;
    }

    /**
     * @param taskNumber the taskNumber to set
     */
    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
