/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverboat.customer;

/**
 * User object for sync across many e-commerce (Amazon, Ebay, Rakatuuen, etc)
 * 
 * @author Maria Presa 5/3/2014
 */
public class RiverBoatUserAccess {

    private boolean Amazon;
    private boolean Ebay;
    private boolean Rakatuuen;

    public boolean isAmazon() {
        return Amazon;
    }

    public void setAmazon(boolean Amazon) {
        this.Amazon = Amazon;
    }

    public boolean isEbay() {
        return Ebay;
    }

    public void setEbay(boolean Ebay) {
        this.Ebay = Ebay;
    }

    public boolean isRakatuuen() {
        return Rakatuuen;
    }

    public void setRakatuuen(boolean Rakatuuen) {
        this.Rakatuuen = Rakatuuen;
    }
}
