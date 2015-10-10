/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.model;

import com.riverboat.model.User;
import com.riverboat.customer.AmazonCustomer;

/**
 * Rivertboat User object for use across all products (Amazon, Ebay...etc)
 * @author D'Mita Levy
 */
public class RiverBoatUser extends User {


    public AmazonCustomer amazon;
  
    public RiverBoatUser(){
        super();
        amazon = new AmazonCustomer(true);
    }

    /**
     * Amazon Customer class that represents this users amazon info
     * @return amazon 
     */
    public AmazonCustomer getAmazon() {
        return amazon;
    }

    /**
     * Amazon Customer class that represents this users amazon info
     * @param amazon 
     */
    public void setAmazon(AmazonCustomer amazon) {
        this.amazon = amazon;
    }
    
    public void displayCustomer()
    {
        System.out.println(this.getAccount()+" "+this.amazon.getAmazonSecretKey());
    }

   
    
    
    
}