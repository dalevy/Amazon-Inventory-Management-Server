/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.manager;

import com.riverboat.server.config.RiverBoatServerConfig;
import com.riverboat.server.tasks.RiverBoatServerRetrieveTaskList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author DMita Levy
 */
public class RiverBoatServerManager{
    
    RiverBoatServerRetrieveTaskList taskUpdater;
    RiverBoatInventoryManager inventoryManager;
    RiverBoatServerTaskManager taskManager;
    RiverBoatServerConfig config;
    
    //Task list downloaded from server
    LinkedList<ServerTask> taskList;
    

    
    //Executor service thread pool for application dependent classes
    ExecutorService executor;
    
    final int POOL_SIZE = 5;
    
   public RiverBoatServerManager()
   {
       executor = Executors.newFixedThreadPool(POOL_SIZE);
       config = new RiverBoatServerConfig();
   }
   
   /**
    * Initializes all objects and begins task updates
    */
   public void start()
   {
      
       System.out.println("\n------------Starting Server Manager----------");
       System.out.println("--- Serial: "+RiverBoatServerConfig.serial);
       
     /*       
       taskManager = new RiverBoatServerTaskManager();
              System.out.println(this.getClass().getCanonicalName()+"\n\t--Starting-- "+taskManager.getClass().getCanonicalName());
             */
      
       inventoryManager = new RiverBoatInventoryManager();
              System.out.println(this.getClass().getCanonicalName()+"\n\t--Starting-- "+inventoryManager.getClass().getCanonicalName());

       
       //Start inventory updates
     //  executor.execute(taskManager);
       executor.execute(inventoryManager);
      
   }

   
   
   

  
   
}
