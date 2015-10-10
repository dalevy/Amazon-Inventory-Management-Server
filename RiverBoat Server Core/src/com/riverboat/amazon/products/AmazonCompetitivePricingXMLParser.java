/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.products;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 *
 * @author Idirekt
 */
public class AmazonCompetitivePricingXMLParser {

    
        XMLReader reader;
        AmazonProductsCompetitivePricingXMLHandler handler;
        String xmlSource;
        InputSource inputSource;
        
   public AmazonCompetitivePricingXMLParser(){
            
       try{
             reader = XMLReaderFactory.createXMLReader();  
             handler = new AmazonProductsCompetitivePricingXMLHandler();
             reader.setContentHandler(handler);
             
       }catch(SAXException e){
           
           e.printStackTrace();
       }
                
            
   }
   
   public void setXMLSource(String xml){
    
       inputSource = new InputSource(new StringReader(xml));
       
   }
   
   public void parseXML(){
      
       try{
            reader.parse(inputSource);
       }catch(IOException e){
           
           e.printStackTrace();
           
       }catch(SAXException e){
           
           e.printStackTrace();
       }
   }
   
   public ArrayList<AmazonCompetitivePricingXMLReport> getReportList(){
       return handler.getReportList();
   }
}
