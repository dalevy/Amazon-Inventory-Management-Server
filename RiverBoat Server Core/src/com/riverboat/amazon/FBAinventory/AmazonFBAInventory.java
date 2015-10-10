/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.FBAinventory;


import com.amazonservices.mws.FulfillmentInventory._2010_10_01.FBAInventoryServiceMWSConfig;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.FBAInventoryServiceMWSException;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.GetServiceStatusRequest;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.GetServiceStatusResponse;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyRequest;
import com.amazonservices.mws.FulfillmentInventory._2010_10_01.model.ListInventorySupplyResponse;
import com.amazonservices.mws.client.MwsEndpoints;
import com.riverboat.model.AmazonModel;
import com.riverboat.model.IRiverBoatUser;
import com.riverboat.model.RiverBoatUser;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 *
 * @author Idirekt
 */
public class AmazonFBAInventory extends AmazonModel implements IRiverBoatUser{

    FBAInventoryServiceMWSClient client;
   // AmazonInventoryXMLParser parser;
    XMLGregorianCalendar xmlDate;
    DatatypeFactory factory;
    
    public AmazonFBAInventory(){
        //  parser = new AmazonInventoryXMLParser();
        try{
            factory = DatatypeFactory.newInstance();
        }catch(DatatypeConfigurationException e){
            
        }
    }
    
    
   public void ListInventorySupply(){
       
       xmlDate = factory.newXMLGregorianCalendar();
       xmlDate.setDay(user.calendar.day - 1);
       xmlDate.setMonth(user.calendar.month);
       xmlDate.setYear(user.calendar.year);
       xmlDate.setHour(0);
       xmlDate.setMinute(0);
       xmlDate.setSecond(0);
       
       ListInventorySupplyRequest supplyRequest = new ListInventorySupplyRequest();
       supplyRequest.setMarketplace(marketplaceID);
       supplyRequest.setSellerId(sellerID);
       supplyRequest.setQueryStartDateTime(xmlDate);
       supplyRequest.setResponseGroup("Detail");
       
       
       try{
            ListInventorySupplyResponse supplyResponse = client.listInventorySupply(supplyRequest);
            System.out.println("Inventory Request "+supplyRequest.toString());
            System.out.println(supplyResponse.toXML());
       }catch(FBAInventoryServiceMWSException e ){
           e.printStackTrace();
       }
   }
   
   public String getServiceStatus(){
       
       GetServiceStatusRequest statusRequest = new GetServiceStatusRequest();
       statusRequest.setSellerId(sellerID);
       
       
          
       try{
              GetServiceStatusResponse response = client.getServiceStatus(statusRequest);
              System.out.printf(response.toXML());
       }catch(FBAInventoryServiceMWSException e ){
           e.printStackTrace();
           System.out.println("Error Code "+e.getErrorCode());
           System.out.println("Error Type "+e.getErrorType());
           System.out.println("Error Message "+e.getMessage());
           System.out.println("Request ID "+e.getRequestId());
           System.out.println("Status Code "+e.getStatusCode());
           System.out.println("XML "+e.getXML());

           
       }
      
       return "good";
   }
    
    
   @Override
    public void setUser(RiverBoatUser user){
        this.user = user;
        this.awsAccessKeyID = user.amazon.getAmazonAccessKey();
        this.awsSecretKeyID = user.amazon.getAmazonSecretKey();
        this.appVersion = user.amazon.getApplicationVersion();
        this.appName = user.amazon.getApplicationName();
        this.sellerID = user.amazon.getSellerID();
        this.email = user.getEmail();
        this.endpoint = MwsEndpoints.NA_PROD.toString();
        this.marketplaceID = user.amazon.getMarketplaceID();
        clientSetup();
    }
    
       public void clientSetup(){
       System.out.print("Setup");
       FBAInventoryServiceMWSConfig config  = new FBAInventoryServiceMWSConfig();
       config.setServiceURL("https://mws.amazonservices.com/FulfillmentInventory/2010-10-01");
                client = new  FBAInventoryServiceMWSClient(awsAccessKeyID, awsSecretKeyID,appName,appVersion,config);
    }
}
