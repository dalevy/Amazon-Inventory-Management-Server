/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.reports;

import au.com.bytecode.opencsv.CSVReader;
import java.io.CharArrayReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Idirekt
 */
public class AmazonTaxReportsParser {
    
    private String csv;
    private ArrayList<AmazonTaxReport> reportList;
    

    public AmazonTaxReportsParser(String csv){
        
        this.csv = csv;
    }
    
    
    public void generateReport()
    {
    
       try{

            //load CSV into a Reader
            char[] csvchars  = csv.toCharArray();
            
            CSVReader reader = new CSVReader(new CharArrayReader(csvchars));
            String [] nextLine;
            int record = 0;
            AmazonTaxReport report;
            reportList = new ArrayList<>();
            
            while ((nextLine = reader.readNext()) != null) 
            {
                
                report = new AmazonTaxReport();
                
                if(record > 0)
                {
                    report.setOrder_ID(nextLine[0]);
                    report.setOrder_Date(nextLine[1]);
                    report.setShipment_Date(nextLine[3]);
                    report.setMarketplace(nextLine[6]);
                    report.setASIN(nextLine[9]);
                    report.setSKU(nextLine[10]);
                    report.setTransaction_Type(nextLine[11]);
                    report.setQuantity(nextLine[13]);
                    report.setCurrency(nextLine[14]);
                    report.setDisplay_Price(nextLine[15]);
                    report.setTaxExclusive_Selling_Price(nextLine[16]);
                    report.setTotal_Tax(nextLine[17]);
                    report.setShip_To_City(nextLine[23]);
                    report.setShip_From_State(nextLine[24]);
                    report.setShip_To_Country(nextLine[25]);
                    report.setShip_To_Postal_Code(nextLine[26]);
                    if(record == 2){
                        System.out.print(nextLine[0]);
                    }
                 }
                record++;
                    reportList.add(report);
             }//end while loop
            
        }catch(FileNotFoundException e){
            
        }catch(IOException e){
            
        }
   }//end generateReport()
    
    
   public ArrayList<AmazonTaxReport> getReport(){
       
       return reportList;
   }

}
      
      
      

