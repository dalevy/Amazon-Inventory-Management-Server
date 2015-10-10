/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.util;

import com.riverboat.amazon.reports.AmazonInventoryReportRecord;
import java.util.Random;
import java.util.StringTokenizer;

/**
 * Generates unique product id for each platform
 * Id's are of the form:
 *      EntryPoint(where it was entered) - Platform Specific Id - Random Time Interval - Category
 *      Example:
 *          Amazon Item : B00E4MS1FQ    Shoes    9:17 AM
 *          RID: AMZ - S1FQ - 6982 - Shoes
 * 
 *          Customer Item: 
 * 
 * @author D.Levy
 */
public class RiverBoatProductIdGenerator {
    
    public String generateAmazonRID(AmazonInventoryReportRecord record){
        
        StringBuilder id = new StringBuilder();
        // Entry point where this item was first entered
        id.append("AMZ");
       
        id.append("-");
        
        // Last 4 
        for(int i = record.product.ASIN.length()-4; i < record.product.ASIN.length(); i++)
            id.append(record.product.ASIN.charAt(i));
        
        id.append("-");
        
        // Random function of time
        
        long seeda = System.nanoTime();
        long seedb = System.currentTimeMillis();
        long seedc = System.nanoTime();
        
        Random number = new Random(seeda+seedb*seedc);
        id.append(number.nextInt(10000));
        
        id.append("-");
        
        // Product Category
        StringTokenizer st = new StringTokenizer(record.product.ProductGroup," ");
        id.append(st.nextToken());
        
        return id.toString();
    }
}
