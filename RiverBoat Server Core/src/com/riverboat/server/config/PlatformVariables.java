/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.config;

/**
 * Platform specific variables needed to interoperate with the different
 * E-Commerce API's
 * @author D.Levy
 */
public interface PlatformVariables {
    
    /****************************
     * Amazon Products
    ***************************/
    
    
    //GetMatchingProductForId Max Throttle Ratio   
    //Maximum possible efficent throttle is 5 with a restore of 5 every second
    int getMatchingProductForId_max_throttle = 5;
    long getMatchingProductForId_restore_rate =1000;
    
    /*****************************
     * Amazon Reports
     ****************************/ 
    
    //GetReportRequestList Throttle Ratio
    //Maximum possible efficient throttle is 45 seconds with a restore rate of 1 ever 45 seconds
    long getReportRequestList_max_throttle = 45000;
}
