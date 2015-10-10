package com.riverboat.amazon.orders;


import com.riverboat.model.IRiverBoatUser;
import com.riverboat.model.RiverBoatUser;
import com.riverboat.util.IRiverBoatXMLHandler;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  XML Handler for AmazonOrders class. the a_value variables represent fields
 *  in an List order and Orders API response.
 * 
 *  ex. <Orders>
             <Order>
                <AmazonOrderId>108-5750714-4838661</AmazonOrderId>
                <PurchaseDate>2013-12-17T20:57:00Z</PurchaseDate>
                <LastUpdateDate>2013-12-17T21:48:32Z</LastUpdateDate>
                <OrderStatus>Shipped</OrderStatus>
                <FulfillmentChannel>MFN</FulfillmentChannel>
                <SalesChannel>Amazon.com</SalesChannel>
                            .
                            .
                            .
 * 
 * @author D'Mita Levy
 */
public class AmazonOrdersXMLHandler extends DefaultHandler implements IRiverBoatUser{
    
    AmazonOrdersXMLReport report;
    ArrayList<AmazonOrdersXMLReport> reportList;
    boolean a_AmazonOrderId = false;
    boolean a_PurchaseDate = false;
    boolean a_LastUpdateDate = false;
    boolean a_FullfillmentChannel = false;
    boolean a_ShipServiceLevel = false;
    boolean a_Name = false;
    boolean a_AddressLine1 = false;
    boolean a_AddressLine2 = false;
    boolean a_City = false;
    boolean a_StateOrRegion = false;
    boolean a_PostalCode = false;
    boolean a_CountryCode = false;
    boolean a_Phone = false;
    boolean a_CurrencyCode = false;
    boolean a_Amount = false;
    boolean a_NumberOfItemsShipped = false;
    boolean a_NumberOfItemsUnshipped = false;
    boolean a_PaymentMethod = false;
    boolean a_MarketplaceId = false;
    boolean a_BuyerEmail = false;
    boolean a_BuyerName = false;
    boolean a_ShipmentServiceLevelCategory = false;
    boolean a_ShippedByAmazonTFM = false;
    boolean a_OrderType = false;
    boolean a_EarliestShipDate = false;
    boolean a_LatestShipDate = false;
    boolean a_RequestId = false;
    boolean a_NextToken = false;
    boolean reportComplete;


    
    @Override
    public void startDocument(){
         reportComplete = false;
         reportList = new ArrayList();
         report = new AmazonOrdersXMLReport();
    
    }
    @Override
    public void endDocument(){
         System.out.println("[ "+this.toString()+" ]    Finished Parsing Order Record");
        reportComplete = true;
    }
   
    
    @Override
    public void startElement(String nameSpaceURI, String localName, String qName, Attributes atts){
       
        if(qName.equalsIgnoreCase("RequestId"))
            a_RequestId = true;

        if(qName.equalsIgnoreCase("AmazonOrderId"))
            a_AmazonOrderId = true;
        
        if(qName.equalsIgnoreCase("PurchaseDate"))
            a_PurchaseDate = true;
              
        if(qName.equalsIgnoreCase("LastUpdateDate"))
            a_LastUpdateDate = true;
             
        if(qName.equalsIgnoreCase("ShipServiceLevel"))
            a_FullfillmentChannel =true;
              
        if(qName.equalsIgnoreCase("ShipServiceLevel"))
            a_ShipServiceLevel =true;
             
        if(qName.equalsIgnoreCase("Name"))
            a_Name =true;
            
        if(qName.equalsIgnoreCase("AddressLine1"))
            a_AddressLine1 =true;
        
        if(qName.equalsIgnoreCase("AddressLine1"))
            a_AddressLine2 =true;
                  
        if(qName.equalsIgnoreCase("City"))
            a_City =true;
               
        if(qName.equalsIgnoreCase("StateOrRegion"))
            a_StateOrRegion =true;
                 
        if(qName.equalsIgnoreCase("PostalCode"))
            a_PostalCode =true;
             
        if(qName.equalsIgnoreCase("CountryCode"))
            a_CountryCode =true;
            
        if(qName.equalsIgnoreCase("Phone"))
            a_Phone =true;
             
        if(qName.equalsIgnoreCase("CurrencyCode"))
            a_CurrencyCode =true;
             
        if(qName.equalsIgnoreCase("Amount"))
            a_Amount =true;
             
        if(qName.equalsIgnoreCase("NumberOfItemsShipped"))
            a_NumberOfItemsShipped =true;
               
        if(qName.equalsIgnoreCase("NumberOfItemsUnshipped"))
            a_NumberOfItemsUnshipped =true;
             
        if(qName.equalsIgnoreCase("PaymentMethod"))
            a_PaymentMethod =true;
              
        if(qName.equalsIgnoreCase("MarketplaceId"))
            a_MarketplaceId =true;
              
        if(qName.equalsIgnoreCase("BuyerEmail"))
            a_BuyerEmail =true;
             
        if(qName.equalsIgnoreCase("BuyerName"))
            a_BuyerName =true;
              
        if(qName.equalsIgnoreCase("ShipmentServiceLevelCategory"))
            a_ShipmentServiceLevelCategory =true;
             
        if(qName.equalsIgnoreCase("ShipppedByAmazonTFM"))
            a_ShippedByAmazonTFM =true;
             
        if(qName.equalsIgnoreCase("OrderType"))
            a_OrderType =true;
            
        if(qName.equalsIgnoreCase("EarliestShipDate"))
            a_EarliestShipDate =true;
             
        if(qName.equalsIgnoreCase("LatestShipDate"))
            a_LatestShipDate =true;
        
        if(qName.equalsIgnoreCase("NextToken"))
            a_NextToken =true;
  
}
    
    
    @Override
    public void endElement(String namespaceURI, String localName, String qName){

        if(qName.equalsIgnoreCase("Order")){
            reportList.add(report);
            report = new AmazonOrdersXMLReport();
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length){
        
            
        if(a_AmazonOrderId){
            report.setAmazonOrderId(new String(ch, start, length));
           a_AmazonOrderId = false;
        }

        if(a_PurchaseDate){
            report.setPurchaseDate(new String(ch, start, length));
            a_PurchaseDate = false;
        }
        
        if(a_LastUpdateDate){
            report.setLastUpdateDate(new String(ch, start, length));
            a_LastUpdateDate = false;
        }
        
        if(a_FullfillmentChannel){
            report.setFullfilmentChannel(new String(ch, start, length));
            a_FullfillmentChannel =false;
            
            }
         
        if(a_ShipServiceLevel){
            report.setShipServiceLevel(new String(ch, start, length));
            a_ShipServiceLevel =false;
            }
        
        if(a_Name){
            report.setName(new String(ch, start, length));
            a_Name =false;
            }
       
        if(a_AddressLine1){
            report.setAddressLine1(new String(ch, start, length));
            a_AddressLine1 =false;
            }
        
        if(a_AddressLine2){
            report.setAddressLine2(new String(ch, start, length));
            a_AddressLine2 =false;
            }
            
        if(a_City){
            report.setCity(new String(ch, start, length));
            a_City =false;
            }
        
        if(a_StateOrRegion){
            report.setStateOrRegion(new String(ch, start, length));
            a_StateOrRegion =false;
            }
           
        if(a_PostalCode){
            report.setPostalCode(new String(ch, start, length));
            a_PostalCode =false;
            }
        
        if(a_CountryCode){
            report.setCountryCode(new String(ch, start, length));
            a_CountryCode =false;
            }
       
        if(a_Phone){
            report.setPhone(new String(ch, start, length));
            a_Phone =false;
            }
        
        if(a_CurrencyCode){
            report.setCurrencyCode(new String(ch, start, length));
            a_CurrencyCode =false;
            }
        
        if(a_Amount){
            report.setAmount(new String(ch, start, length));
            a_Amount =false;
            }
        
        if(a_NumberOfItemsShipped){
            report.setNumberOfItemsShipped(new String(ch, start, length));
            a_NumberOfItemsShipped =false;
            }
          
        if(a_NumberOfItemsUnshipped){
            report.setNumberOfItemsUnshipped(new String(ch, start, length));
            a_NumberOfItemsUnshipped =false;
            }
        
        if(a_PaymentMethod){
            report.setPaymentMethod(new String(ch, start, length));
            a_PaymentMethod =false;
            }
        
        if(a_MarketplaceId){
            report.setMarketplaceId(new String(ch, start, length));
            a_MarketplaceId =false;
            }
        
        if(a_BuyerEmail){
            report.setBuyerEmail(new String(ch, start, length));
            a_BuyerEmail =false;
            }
        
        if(a_BuyerName){
            report.setBuyerName(new String(ch, start, length));
            a_BuyerName =false;
        }
        
        if(a_ShipmentServiceLevelCategory){
            report.setShipmentServiceLevelCategory(new String(ch, start, length));
            a_ShipmentServiceLevelCategory =false;
            }
        
        if(a_ShippedByAmazonTFM){
            report.setShippedByAmazonTFM(new String(ch, start, length));
            a_ShippedByAmazonTFM =false;
            }
        
        if(a_OrderType){
            report.setOrderType(new String(ch, start, length));
            a_OrderType =false;
            }
        
        if(a_EarliestShipDate){
            report.setEarliestShipDate(new String(ch, start, length));
            a_EarliestShipDate =false;
            }
        
        if(a_LatestShipDate){
            report.setLatestShipDate(new String(ch, start, length));
            a_LatestShipDate =false;
            }
        
        if(a_NextToken){
            report.setNextToken(new String(ch, start, length));
            report.setNextTokenIsSet(true);
            a_NextToken = false;
            }
       

    }
    
    @Override
    public  ArrayList<AmazonOrdersXMLReport> getReportList(){
        return reportList;
    }
    
    public boolean getReportStatus(){
        return reportComplete;
    }
	@Override
	public void setUser(RiverBoatUser user) {
		// TODO Auto-generated method stub
		
	}
    
    
    
    
}
