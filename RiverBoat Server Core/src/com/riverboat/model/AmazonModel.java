/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.model;

import com.riverboat.model.RiverBoatUser;

/**
 *
 * @author Idirekt
 */
public abstract class AmazonModel {

    protected   String endpoint;
    protected   String awsAccessKeyID;
    protected   String awsSecretKeyID;
    protected   String appVersion;
    protected   String appName;
    protected   String sellerID;
    protected   String email;
    protected   String marketplaceID;
    protected   RiverBoatUser user;
    
    public String getEndpoint(){
        return endpoint;
    }
    
    public String getAccessKey(){
        return awsAccessKeyID;
    }
    
    public String getSecretKey(){
        return awsSecretKeyID;
    }
    public String getAppVersion(){
        return appVersion;
    }
    public String getAppName(){
         return appName;
    }
    public String getSellerID(){
        return sellerID;
    }
    public String getEmail(){
        return email;
    }
    public String getMarketplace(){
        return marketplaceID;
    }
   
    
    
    
}  
