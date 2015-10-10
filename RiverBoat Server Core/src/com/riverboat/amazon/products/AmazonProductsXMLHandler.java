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
public class AmazonProductsXMLHandler extends DefaultHandler implements IRiverBoatXMLHandler {

    AmazonProductsXMLReport report;
    ArrayList<AmazonProductsXMLReport> reportList;
    private boolean a_ASIN= false;
    private boolean a_ns2_Brand= false;
    private boolean a_ns2_Feature= false;
    private boolean a_ns2_Height_Units_inches= false;
    private boolean a_ns2_Length_Units_inches= false;
    private boolean a_ns2_Width_Units_inches= false;
    private boolean a_ns2_Weight_Units_pounds= false;
    private boolean a_ns2_IsAdultProduct= false;
    private boolean a_ns2_Label= false;
    private boolean a_ns2_Amount= false;
    private boolean a_ns2_CurrencyCode= false;
    private boolean a_ns2_Manufacturer= false;
    private boolean a_ns2_PartNumber= false;
    private boolean a_ns2_ProductGroup= false;
    private boolean a_ns2_ProductTypeName= false;
    private boolean a_ns2_URL= false;
    private boolean a_ns2_Studio= false;
    private boolean a_ns2_Title= false;
    private boolean reportComplete;
    private boolean a_Rank = false;
    private boolean a_Color = false;
    
    
    //Counts the parameter number to set the correct dimensions, Item, Packaging etc.
    int heightCounter = 0;
    int widthCounter = 0;
    int lengthCounter = 0;
    int weightCounter = 0;
    int asinCounter = 0;
    int featureCounter = 0;
    int amountCounter = 0;
    
    //Used as a flag since there are duplicate values, will only get the first value per product
    boolean isFirstASIN = true;
    boolean isFirstRank = true;
    
    /**************************************************************************
    *Inner elements - used to find inner elements to scope duplicate parameters
    * 
    */
        boolean a_PackageDimensions = false;

    
    @Override
    public void startDocument(){
         reportComplete = false;
         reportList = new ArrayList();
         report = new AmazonProductsXMLReport();
    
    }
    @Override
    public void endDocument(){
        System.out.println("[ "+this.toString()+" ]    Finished Parsing Product Record");
        reportComplete = true;
    }
   
    
    @Override
    public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts){
       
        
       if (qName.equalsIgnoreCase("ASIN")){
            if(isFirstASIN) 
                a_ASIN= true;
       }
       
        if(qName.equalsIgnoreCase("ns2:Brand"))
             a_ns2_Brand= true;
        
        if(qName.equalsIgnoreCase("ns2:Feature")){
             featureCounter++;
             a_ns2_Feature= true;
        }
        
        if(qName.equalsIgnoreCase("ns2:Height")) {
             a_ns2_Height_Units_inches= true;
                heightCounter++;
        }
        if(qName.equalsIgnoreCase("ns2:Length")) {
             a_ns2_Length_Units_inches= true;
                lengthCounter++;
        }
        
        if(qName.equalsIgnoreCase("ns2:Width")) {
             a_ns2_Width_Units_inches= true;
                widthCounter++;
        }
        
        if(qName.equalsIgnoreCase("ns2:Weight")){
            a_ns2_Weight_Units_pounds= true;
                weightCounter++;
        }
        
        if(qName.equalsIgnoreCase("ns2:IsAdultProduct")) 
            a_ns2_IsAdultProduct= true;
        
        if(qName.equalsIgnoreCase("ns2:Label")) 
             a_ns2_Label= true;
        
        if(qName.equalsIgnoreCase("ns2:Amount")){ 
             a_ns2_Amount= true;
                amountCounter++;
        }
        
        if(qName.equalsIgnoreCase("ns2:CurrencyCode")) 
             a_ns2_CurrencyCode= true;
        
        if(qName.equalsIgnoreCase("ns2:Manufacturer")) 
             a_ns2_Manufacturer= true;
        
        if(qName.equalsIgnoreCase("ns2:PartNumber")) 
             a_ns2_PartNumber= true;
        
        if(qName.equalsIgnoreCase("ns2:ProductGroup")) 
             a_ns2_ProductGroup= true;
        
        if(qName.equalsIgnoreCase("ns2:ProductTypeName")) 
            a_ns2_ProductTypeName= true;
        
        if(qName.equalsIgnoreCase("ns2:URL")) 
            a_ns2_URL= true;
        
        if(qName.equalsIgnoreCase("ns2:Studio")) 
            a_ns2_Studio= true;
        
        if(qName.equalsIgnoreCase("ns2:Title")) 
            a_ns2_Title= true;
        
        if(qName.equalsIgnoreCase("Rank")){
            if(isFirstRank)
                 a_Rank= true;
        }
        
        if(qName.equalsIgnoreCase("ns2:Color")) 
            a_Color= true;
        

        
    
        
        
}
    
    
    @Override
    public void endElement(String namespaceURI, String localName, String qName){
        //After the last element of the record is encountered add the record to the list and start a new one
        if(qName.equalsIgnoreCase("Product")){
            reportList.add(report);
            report = new AmazonProductsXMLReport();
            isFirstASIN = true;
            isFirstRank = true;
            heightCounter = 0;
            widthCounter = 0;
            lengthCounter = 0;
            weightCounter = 0;
            featureCounter = 0;
            amountCounter = 0;

            
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length){
        //System.out.println("Height "+a_ns2_Height_Units_inches);
        //System.out.println(new String(ch,start,length));
       
        
      /*  if(a_AmazonOrderId){
            report.setAmazonOrderId(new String(ch, start, length));
           a_AmazonOrderId = false;
        
        }*/
        
        if (a_ASIN && isFirstASIN){
            a_ASIN= false;
            report.setASIN(new String(ch,start,length));         
             isFirstASIN = false;
             
        }

        if(a_ns2_Brand){
             report.setBrand(new String(ch,start,length));
             a_ns2_Brand= false;
        }
        
        if(a_ns2_Feature){
             report.setFeature(new String(ch,start,length));
             a_ns2_Feature= false;
        }
        
        if(a_ns2_Height_Units_inches) {
             if(heightCounter == 1)
             report.setHeight_Units_inches(new String(ch,start,length));
            
            if(heightCounter == 2)
             report.setPackageHeightUnitsinches(new String(ch,start,length));
            
                a_ns2_Height_Units_inches= false;
        }
        
        if(a_ns2_Length_Units_inches) {
             if(lengthCounter == 1)
             report.setLength_Units_inches(new String(ch,start,length));
            
            if(lengthCounter == 2)
             report.setPackageLengthUnitsinches(new String(ch,start,length));
            
                a_ns2_Length_Units_inches= false;
        }
        
        if(a_ns2_Width_Units_inches) {
            
            if(widthCounter == 1)
             report.setWidth_Units_inches(new String(ch,start,length));
            
            if(widthCounter == 2)
             report.setPackageWidthUnitsinches(new String(ch,start,length));
            
                 a_ns2_Width_Units_inches= false;
        }
        
        if(a_ns2_Weight_Units_pounds) {
             if(weightCounter == 1)
             report.setWeight_Units_pounds(new String(ch,start,length));
            
            if(weightCounter == 2)
             report.setPackageWeightUnitspounds(new String(ch,start,length));
            
                a_ns2_Weight_Units_pounds= false;
        }
        
        if(a_ns2_IsAdultProduct) {
            report.setIsAdultProduct(new String(ch,start,length));
            a_ns2_IsAdultProduct= false;
        }
        
        if(a_ns2_Label){
             report.setLabel(new String(ch,start,length));
             a_ns2_Label= false;
        }
        
        if(a_ns2_Amount) {
            if(amountCounter == 1)
             report.setAmount(new String(ch,start,length));
            
                a_ns2_Amount= false;
        }
        
        if(a_ns2_CurrencyCode) {
             report.setCurrencyCode(new String(ch,start,length));
             a_ns2_CurrencyCode= false;
        
        }
        
        if(a_ns2_Manufacturer) {
             report.setManufacturer(new String(ch,start,length));
             a_ns2_Manufacturer= false;
        }
        
        if(a_ns2_PartNumber) {
             report.setPartNumber(new String(ch,start,length));
             a_ns2_PartNumber= false;
        }
        
        if(a_ns2_ProductGroup) {
             report.setProductGroup(new String(ch,start,length));
             a_ns2_ProductGroup= false;
        }
        
        if(a_ns2_ProductTypeName) {
             report.setProductTypeName(new String(ch,start,length));
             a_ns2_ProductTypeName= false;
        }
        
        if(a_ns2_URL) {
            report.setURL(new String(ch,start,length));
            a_ns2_URL= false;
        }
        
        if(a_ns2_Studio) {
            report.setStudio(new String(ch,start,length));
            a_ns2_Studio= false;
        }
        
        if(a_ns2_Title) {
             report.setTitle(new String(ch,start,length));
             a_ns2_Title= false;
        }
        
        if(a_Rank &&isFirstRank) {
             report.setRank(new String(ch,start,length));
             a_Rank= false;
             isFirstRank = false;

        }
        
       if(a_Color) {
             report.setColor(new String(ch,start,length));
             a_Color= false;
        }
    
    

    }
    
    @Override
    public  ArrayList<AmazonProductsXMLReport> getReportList(){
        return reportList;
    }
    
    public boolean getReportStatus(){
        return reportComplete;
    }
    
    
}
