/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.manager;

import com.riverboat.server.core.IRiverBoatServerAssets;
import com.riverboat.server.core.RiverBoatServerAssets;
import com.riverboat.server.tasks.RiverBoatServerRetrieveTaskList;
import java.util.LinkedList;

/**
 * Assigns tasks to different classes based on server task list
 * @author D.Levy
 */
public class RiverBoatServerTaskManager implements Runnable, IRiverBoatServerAssets {

    RiverBoatServerAssets assets;
    LinkedList<ServerTask> taskList;
    ServerTask currentTask;
    RiverBoatServerRetrieveTaskList taskListRetriever;
    
    public RiverBoatServerTaskManager()
    {
        taskListRetriever = new RiverBoatServerRetrieveTaskList();
    }
    
    
    
    public void beginTaskUpdates()
    {
        
        //Get the current task list
        taskList = taskListRetriever.retrieveTaskList();
        
        //Delegate each task
        for(ServerTask c: taskList)
        {
            currentTask = c;
            delegateTask(currentTask);
        }
         
    }
    
    @Override
    public void setAssets(RiverBoatServerAssets ass) {
        assets = ass;
        taskListRetriever.setAssets(ass);
    }

    @Override
    public void run() {
        
        beginTaskUpdates();
    }
    
    public void delegateTask(ServerTask task)
    {
        
        
            if(task.getType().equalsIgnoreCase(ServerTask.AMAZON_ACTIVE_LISTING_REQUEST))
                return;
            if(task.getType().equalsIgnoreCase(ServerTask.AMAZON_MERCHANT_LISTINGS_LITE_REPORT))
                return;
            if(task.getType().equalsIgnoreCase(ServerTask.AMAZON_FULL_UPDATE))
              ;
    }
    
    
   
    
}
