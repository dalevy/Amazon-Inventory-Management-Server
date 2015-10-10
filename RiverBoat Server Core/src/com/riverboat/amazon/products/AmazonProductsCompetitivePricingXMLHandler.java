/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.products;

import com.riverboat.util.IRiverBoatXMLHandler;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Idirekt
 */
public class AmazonProductsCompetitivePricingXMLHandler extends DefaultHandler implements IRiverBoatXMLHandler {
    
    AmazonCompetitivePricingXMLReport report;
    ArrayList<AmazonCompetitivePricingXMLReport> reportList;
    private boolean a_ASIN =false;
    private boolean a_Amount = false;  //there are 3 of these, Landed, List, and Shipping
    
    private boolean reportComplete =false;
    
    private int priceCounter = 0;
    

    
      
    @Override
    public void startDocument(){
         reportComplete = false;
         reportList = new ArrayList<>();
         report = new AmazonCompetitivePricingXMLReport();
    
    }
    @Override
    public void endDocument(){
        System.out.println("[ "+this.toString()+" ]    Finished Parsing Product Record");
        reportComplete = true;
    }
    
     @Override
    public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts){
    
           
         if (qName.equalsIgnoreCase("ASIN")){
                a_ASIN= true;
          }
         
         if(qName.equalsIgnoreCase("Amount")){
               a_Amount = true;
               priceCounter++;
         }
         
    }
    
     @Override
    public void endElement(String namespaceURI, String localName, String qName){
        //After the last element of the record is encountered add the record to the list and start a new one
        if(qName.equalsIgnoreCase("GetCompetitivePricingForASINResult")){
            reportList.add(report);
            report = new AmazonCompetitivePricingXMLReport();
            priceCounter = 0;
            
        }
    }
    
       
    @Override
    public void characters(char[] ch, int start, int length){
   
        if (a_ASIN){
            a_ASIN= false;
            report.setASIN(new String(ch,start,length));         
             
        }
        
        if(a_Amount){
            
            if(priceCounter == 1)
              report.setLandedPrice(new String(ch,start,length));
            
            if(priceCounter == 2)
              report.setListPrice(new String(ch,start,length));
            
            if(priceCounter == 3)
              report.setShippingCost(new String(ch,start,length));
            
            a_Amount = false;
            
        } 
        
        
        
    }
    
    @Override
    public ArrayList getReportList() {
        return this.reportList;
    }
    
    public boolean getReportStatus(){
        return reportComplete;
    }
    
}
