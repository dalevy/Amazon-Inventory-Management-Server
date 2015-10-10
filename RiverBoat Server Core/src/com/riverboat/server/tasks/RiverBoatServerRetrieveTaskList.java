/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.tasks;

import com.riverboat.server.config.PathNames;
import com.riverboat.server.core.IRiverBoatServerAssets;
import com.riverboat.server.core.RiverBoatServerAssets;
import com.riverboat.server.manager.ServerTask;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Retrieves the list of tasks that are available to this server and provides
 * methods to update, query, and modify the task list
 * @author DMita Levy
 */
public class RiverBoatServerRetrieveTaskList implements IRiverBoatServerAssets{
    
    //Terminates this thread if true
    public  boolean terminate;
    public  boolean synchronizing;
    
    public  ObservableList<ServerTask> obsRepList;
    RiverBoatServerAssets assets;
    public  LinkedList<ServerTask> taskList;
    
    //default update ever 30 seconds
    long interval = 30000;
            
    public RiverBoatServerRetrieveTaskList()
    {
       taskList = new LinkedList<>();
       obsRepList = FXCollections.observableArrayList();

    }

    public RiverBoatServerRetrieveTaskList(long updateInterval)
    {   
        this();
        interval = updateInterval;
    }
    
    /**
     * Retreives the task list from the server
     * @return 
     */
    public LinkedList<ServerTask> retrieveTaskList()
    {
        taskList.clear();
        Connection dbConnection;
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(PathNames.DB_SERVERTASKLIST);
        
        Statement statement = null;
        
        String query = "SELECT * FROM "+db.toString()+" WHERE status ='AVAILABLE'";
        
        
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
           
           
           
           //build a list of server tasks
           while(rs.next()){
              taskList.add(
                      new ServerTask(rs.getString("task"),
                      rs.getString("account"),
                      rs.getString("platform"), 
                      rs.getString("type"), 
                      rs.getString("id"), 
                      rs.getTimestamp("time")));
                      
              
           }
           
           
            rs.close();
            statement.close();
            dbConnection.close();  
            
            //Create a list so the views can have access to it
            buildObservableList();
            
     }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            
     }finally{
       
        
       }
        
        return taskList;
    }
    
    /**
     * Builds an observable list of server tasks so that other views can use them
     */
    
    private void buildObservableList()
    {

       obsRepList.clear();
       for(ServerTask t: taskList)
       {
           StringBuilder sb = new StringBuilder();
           sb.append(t.getTaskNumber());
           sb.append(t.getAccount());
           sb.append(t.getPlatform());
           sb.append(t.getType());
           sb.append(t.getTime());
           
           System.out.println("\tAdding Server Task: "+sb.toString());
           
           
           obsRepList.add(t);
       }
       
       
    }
    
    
    
    /**
     * @return the terminate
     */
    public boolean isTerminate() {
        return terminate;
    }

    /**
     * @param terminate the terminate to set
     */
    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }

    /**
     * @return the synchronizing
     */
    public boolean isSynchronizing() {
        return synchronizing;
    }

    /**
     * @param synchronizing the synchronizing to set
     */
    public void setSynchronizing(boolean synchronizing) {
        this.synchronizing = synchronizing;
    }

    @Override
    public void setAssets(RiverBoatServerAssets ass) {
        assets = ass;
    }
    
}
