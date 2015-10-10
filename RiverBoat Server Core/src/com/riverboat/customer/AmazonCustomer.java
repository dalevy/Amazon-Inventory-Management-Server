/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.customer;

/**
 *
 * @author Midnite
 */
public class AmazonCustomer {
    
    
   private String awsAccessKeyID;
   private String awsSecretKeyID;
   private String appName;
   private String appVersion;
   private String sellerID;
   private String marketplaceID;
   private boolean isCustomer; //Denotes whether the customer is an Amazon customer or not
   
   public AmazonCustomer(boolean isAmazon)
   {
       isCustomer = isAmazon;
   }
   
    public void setSellerID(String sellerID){
        this.sellerID = sellerID;
    }
    
    public void setMarketplace(String marketplaceID){
        this.marketplaceID = marketplaceID;
    }  
    
    public void setAmazonAccessKey(String accessKeyID){
       this.awsAccessKeyID = accessKeyID;
    }
    
    public void setAmazonSecretKey(String secretKeyID){
        this.awsSecretKeyID = secretKeyID;
    }
    
     public void setApplicationVersion(String appVersion){
       this.appVersion = appVersion;
    }
    
    public void setApplicationName(String appName){
        this.appName= appName;
    }
    
    public String getApplicationName(){
        return appName;
    }
    
    public String getApplicationVersion(){
        return appVersion;
    }
    
    public String getAmazonAccessKey(){
       return awsAccessKeyID;
    }
    
    public String getAmazonSecretKey(){
       return awsSecretKeyID;
    }
    
    public String getSellerID(){
        return sellerID;
    }
    
    public String getMarketplaceID(){
        return marketplaceID;
    }
    
   
   public void setAccess(boolean isAmazon)
   {
       isCustomer = isAmazon;
   }
   
   public boolean getAccess(boolean isAmazon)
   {
       return isCustomer;
   }
}
