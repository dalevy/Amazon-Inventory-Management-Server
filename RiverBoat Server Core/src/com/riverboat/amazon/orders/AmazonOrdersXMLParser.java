package com.riverboat.amazon.orders;


import com.riverboat.util.*;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Idirekt
 */
public class AmazonOrdersXMLParser {

        XMLReader reader;
        AmazonOrdersXMLHandler handler;
        String xmlSource;
        InputSource inputSource;
        
   public AmazonOrdersXMLParser(){
            
       try{
             reader = XMLReaderFactory.createXMLReader();  
             handler = new AmazonOrdersXMLHandler();
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
   
   public ArrayList<AmazonOrdersXMLReport> getReportList(){
       return handler.getReportList();
   }
    
}
