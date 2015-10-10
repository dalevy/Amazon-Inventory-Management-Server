/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.orders;

/**
 *
 * @author Idirekt
 */
public class AmazonOrdersThread implements Runnable {

    
    @Override
    public void run() {
     try{
        while(true){
            
            System.out.print("Hello World!");
            Thread.sleep(5000);
        }
     }catch(Exception e){
         
     }
    }

/*    @Override
    protected Object call() throws Exception {
        
 
        
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            
        }
        return null;
        
    }*/

}


