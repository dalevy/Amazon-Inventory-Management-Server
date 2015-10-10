/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.manager;

import java.sql.Timestamp;

/**
 * An individual task that needs to be executed
 * @author DMita Levy
 * 
 */



public class ServerTask {
    
    private String taskNumber;
    private String account;
    private String platform;
    private String type;
    private String id;
    private String time;
    private Timestamp timeStamp;
    
   
   //Full Amazon Inventory Update: 
    public static String AMAZON_FULL_UPDATE = "AMAZON_FULL_UPDATE";
    
    public static String AMAZON_ACTIVE_LISTING_REQUEST="AMAZON_ACTIVE_LISTING_REPORT";
    
    public static String AMAZON_MERCHANT_LISTINGS_LITE_REPORT ="AMAZON_MERCHANT_LISTINGS_LITE_REPORT";
    
    /*********
     * List of possible statuses
     */
    
    public static String AVAILABLE = "AVAILABLE";
    public static String LOCKED = "LOCKED";
    public static String FAILURE = "FAILURE";
    
    
    public ServerTask(String taskNumber, String account, String platform, String type, String id, Timestamp timeStamp)
    {
        this.taskNumber = taskNumber;
        this.account = account;
        this.platform = platform;
        this.type = type;
        this.id =id;
        this.timeStamp = timeStamp;
        time = timeStamp.toString();
    }

    /**
     * @return the taskNumber
     */
    public String getTaskNumber() {
        return taskNumber;
    }

    /**
     * @param taskNumber the taskNumber to set
     */
    public void setTaskNumber(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * @return the account
     */
    public String getAccount() {
        return account;
    }

    /**
     * @param account the account to set
     */
    public void setAccount(String account) {
        this.account = account;
    }

    /**
     * @return the platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform the platform to set
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return the timeStamp
     */
    public Timestamp getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(Timestamp timeStamp) {
        this.timeStamp = timeStamp;
    }
}
