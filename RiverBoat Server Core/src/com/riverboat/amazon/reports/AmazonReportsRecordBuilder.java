/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.reports;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Extrapolates tab-delimited report data from report response byte array.
 * 
 * @author D'MIta Levy
 */
public class AmazonReportsRecordBuilder {

    ByteArrayOutputStream sourceReport;
     ArrayList<AmazonInventoryReportRecord> recordsList;
    byte[] report;
    
    //Default contructor
    public AmazonReportsRecordBuilder(){
        
    }
    public AmazonReportsRecordBuilder(ByteArrayOutputStream sourceReport){
        
        byte[] tempreport = sourceReport.toByteArray();
        this.report = tempreport;
        
    }
    
    
    public void extrapolate(){
            
           String value="";
           String sku="";
           String asin="";
           String price="";
           String quantity="";
           
           recordsList = new ArrayList<>();
           //When a tab (char == 9) is encountered, increments to 
           //denote the next column
           int column = 1;
        
        for (byte b: report)
        {
            switch(b)
            {
               //Encountered a tab
                case 9:
                    if(column == 1)
                    {
                        sku = value;
                        column++;
                        value="";
                    }else if(column == 2){
                        asin = value;
                        column++;
                        value="";
                    }else if(column == 3){
                        price = value;
                        column++;
                        value="";
                    }
                break;
                //Encountered CR end of line    
                case 13:
                        quantity = value;
                        column++;
                        value="";
                  break;
               //Ecountered NL
                case 10:
                  recordsList.add(new AmazonInventoryReportRecord(sku, asin, price, quantity));
                  column = 1;
                  value ="";
                  break;
                default:
                    value+=(char)b;
                break;
                
            }//end switch
 
        }//end for loop
        
        
       /* Output Checker
        
        for(int i = 0; i < recordsList.size(); i++)
        {
            System.out.println(
               recordsList.get(i).getSku()+"\t"+recordsList.get(i).getAsin()+"\t"+recordsList.get(i).getPrice()+"\t"+recordsList.get(i).getQuantity());
        }
      */
        
    }
   
    
    
    
    public ByteArrayOutputStream getOriginalReport(){ 
        return sourceReport;
    }
    
    
    public  ArrayList<AmazonInventoryReportRecord> getRecordList(){
        return recordsList;
    }
    
}
