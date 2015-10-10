/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.core;

import com.riverboat.server.config.RiverBoatServerConfig;
import com.riverboat.server.manager.RiverBoatInventoryManager;
import com.riverboat.server.tasks.RiverBoatServerRetrieveTaskList;

/**
 * List of assets that are available and necessary to all the applications.
 * An implementing class must use the Assets interface to implement this class
 * @author D. Levy
 */
public class RiverBoatServerAssets {
    
    private RiverBoatServerRetrieveTaskList taskListRetreiver;
    private RiverBoatInventoryManager inventoryManager;
    private RiverBoatServerConfig config;
    
    public RiverBoatServerAssets()
    {
        
    }

    /**
     * Returns the server task list retrieval object
     * @return the taskList
     */
    public RiverBoatServerRetrieveTaskList getTaskListRetreiver() {
        return taskListRetreiver;
    }
    /**
     * @param taskList the taskList to set
     */
    public void setTaskList(RiverBoatServerRetrieveTaskList taskList) {
        this.taskListRetreiver = taskList;
    }

    /**
     * @return the inventoryManager
     */
    public RiverBoatInventoryManager getInventoryManager() {
        return inventoryManager;
    }

    /**
     * @param inventoryManager the inventoryManager to set
     */
    public void setInventoryManager(RiverBoatInventoryManager inventoryManager) {
        this.inventoryManager = inventoryManager;
    }

    /**
     * @return the config
     */
    public RiverBoatServerConfig getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(RiverBoatServerConfig config) {
        this.config = config;
    }
    
    
}
