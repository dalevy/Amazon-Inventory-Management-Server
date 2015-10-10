/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.manager;

import com.riverboat.server.core.IRiverBoatServerAssets;
import com.riverboat.server.core.RiverBoatServerAssets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author D.Levy
 */
public class RiverBoatInventoryManager implements Runnable{
    
    ExecutorService executor;
    AmazonInventoryManager amazon;
    
    int POOL_SIZE = 3;
            
    
    public RiverBoatInventoryManager()
    {
        executor = Executors.newFixedThreadPool(POOL_SIZE);
        amazon = new AmazonInventoryManager();
        
    }

    public void startUpdates()
    {
        executor.execute(amazon);
             System.out.println(this.getClass().getCanonicalName()+"\n\t--Starting-- "+amazon.getClass().getCanonicalName());

           
    }
    
    
    @Override
    public void run() {
        
        startUpdates();
    }
    
}
