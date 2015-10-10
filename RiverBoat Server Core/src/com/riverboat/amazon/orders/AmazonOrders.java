/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.orders;

import com.riverboat.model.AmazonModel;
import com.amazonservices.mws.client.MwsEndpoints;
import com.amazonservices.mws.orders.MarketplaceWebServiceOrdersClient;
import com.amazonservices.mws.orders.MarketplaceWebServiceOrdersConfig;
import com.amazonservices.mws.orders.model.ListOrdersByNextTokenRequest;
import com.amazonservices.mws.orders.model.ListOrdersByNextTokenResponse;
import com.amazonservices.mws.orders.model.ListOrdersRequest;
import com.amazonservices.mws.orders.model.OrderStatusEnum;
import com.amazonservices.mws.orders.model.OrderStatusList;
import com.amazonservices.mws.orders.model.ListOrdersResponse;
import com.amazonservices.mws.orders.model.MarketplaceIdList;
import com.amazonservices.mws.sellers.MarketplaceWebServiceSellers;
import com.riverboat.model.IRiverBoatUser;
import com.riverboat.model.RiverBoatUser;
import com.riverboat.util.AmazonOrdersXMLParser;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *
 * @author Idirekt
 * @param <ListOrdersRequest>
 * @param <OrderStatusEnum>
 * @param <OrderStatusList>
 * @param <MarketplaceIdList>
 */
public class AmazonOrders<ListOrdersRequest, OrderStatusEnum, OrderStatusList, MarketplaceIdList> extends AmazonModel implements IRiverBoatUser, Runnable {
   
 
    MarketplaceWebServiceSellers client;
    ListOrdersRequest request;
    AmazonOrdersXMLParser parser;
    XMLGregorianCalendar xmlDate;
    DatatypeFactory factory;
    
    //Used as persistent models to update the views
    ArrayList<AmazonOrdersXMLReport> pending;
    ArrayList<AmazonOrdersXMLReport> shipped;
    ArrayList<AmazonOrdersXMLReport> unshipped;
    
    //Milliseconds per minute for update thread
    private final long MILLI_MINUTE = 60000;
   
    /*******************************************
    *Adjust the number of minutes between updates
    * Request quota for this API:
    * The ListOrders and ListOrdersByNextToken operations together share 
    * a maximum request quota of six and a restore rate of one request every minute
    */
    private int UPDATE_RATE = 7;
    
    private int totalPending;
    private int totalUnshipped;
    
    
    //Used to offset the created after criteria 
    private final int WEEK_OFFSET = 1;
   
    public AmazonOrders(){
        
        parser = new AmazonOrdersXMLParser();
        try{
            factory = DatatypeFactory.newInstance();
        }catch(DatatypeConfigurationException e){
            
        }
    }
    
    
        
    //Starts continous updates for each of the fields of the orders view
    public void update(){
        
        pending = listPending();
        shipped =  listShipped();
        unshipped = listUnShipped();
        System.out.println("[ "+this.toString()+" ]    Updating Orders Model");
    }
    
  
    /************************************************
     * Gets shipped items based on an offset. Current day - WEEK_OFFSET;
     * Returns an XML Order list string;
     * @return 
     */ 
    public ArrayList<AmazonOrdersXMLReport> listShipped(){
           
            xmlDate = factory.newXMLGregorianCalendar();
            xmlDate.setDay(user.calendar.day);
            xmlDate.setMonth(1);
            xmlDate.setYear(user.calendar.year);
            xmlDate.setHour(12);
            xmlDate.setMinute(0);
            xmlDate.setSecond(0);
  
        
            List<OrderStatusEnum> enumStatusList = new ArrayList();
            enumStatusList.add(OrderStatusEnum.SHIPPED);
        
            List<String> marketplaceList = new ArrayList();
            marketplaceList.add(marketplaceID);
        
            OrderStatusList statusList = new OrderStatusList();
            statusList.setStatus(enumStatusList);
        
             //it is possible to do this dynamically with code - retrieve marketplace ID's
             MarketplaceIdList marketplaces = new MarketplaceIdList();
             marketplaces.setId(marketplaceList);
        
             ListOrdersRequest statusRequest = new ListOrdersRequest();
             statusRequest.setOrderStatus(statusList);
             statusRequest.setSellerId(sellerID);
             statusRequest.setMarketplaceId(marketplaces);
             statusRequest.setCreatedAfter(xmlDate);
             
        
             ListOrdersResponse response = client.listOrders(statusRequest);
             
             
             //System.out.println(response.toXML());
             parser.setXMLSource(response.toXML());
             parser.parseXML();
             ArrayList<AmazonOrdersXMLReport> list = parser.getReportList();
      /*       ArrayList<AmazonOrdersXMLReport> temp1 = list;
             
             System.out.print("+++++++Is NEXT TOKEN SET++++++++++"+temp1.get(0).getNextTokenIsSet());
             
             //If there is a NexToken keep retrieving orders
             while(temp1.get(0).getNextTokenIsSet())
             {
                ArrayList<AmazonOrdersXMLReport> temp2 = listOrdersByNextToken(temp1.get(0).getNextToken());
                
                for (AmazonOrdersXMLReport record: temp2){
                    list.add(record);
                }
                
                temp1 = temp2;
                
             } */

             
             
        /*     int count = 0;
        
           for(int i = 0; i < list.size(); i++){

           System.out.println("["+count+"] "+list.get(i).getAmazonOrderId());
           System.out.println("Name  --" +list.get(i).getName());
           System.out.println("Amount  --"+ list.get(i).getAmount());
           System.out.println("Address  --"+ list.get(i).getAddressLine1());
           System.out.println("Shipped Date -- "+list.get(i).getPurchaseDate());


            count++;
                   
            
        } */
              
                 return list;
        
    }
    
    
    public ArrayList<AmazonOrdersXMLReport> listUnShipped(){
            xmlDate = factory.newXMLGregorianCalendar();
            xmlDate.setDay(user.calendar.day - WEEK_OFFSET);
            xmlDate.setMonth(1);
            xmlDate.setYear(user.calendar.year);
            xmlDate.setHour(12);
            xmlDate.setMinute(0);
            xmlDate.setSecond(0);
            
  
        
            List<OrderStatusEnum> enumStatusList = new ArrayList();
            enumStatusList.add(OrderStatusEnum.UNSHIPPED);
            enumStatusList.add(OrderStatusEnum.PARTIALLY_SHIPPED);
        
            List<String> marketplaceList = new ArrayList();
            marketplaceList.add(marketplaceID);
        
            OrderStatusList statusList = new OrderStatusList();
            statusList.setStatus(enumStatusList);
        
             //it is possible to do this dynamically with code - retrieve marketplace ID's
             MarketplaceIdList marketplaces = new MarketplaceIdList();
             marketplaces.setId(marketplaceList);
        
             ListOrdersRequest statusRequest = new ListOrdersRequest();
             statusRequest.setOrderStatus(statusList);
             statusRequest.setSellerId(sellerID);
             statusRequest.setMarketplaceId(marketplaces);
             statusRequest.setCreatedAfter(xmlDate);
             
        
             ListOrdersResponse response = client.listOrders(statusRequest);
             
             
             //System.out.println(response.toXML());
             parser.setXMLSource(response.toXML());
             parser.parseXML();
             ArrayList<AmazonOrdersXMLReport> list = parser.getReportList();
            
             //Count for minipanels
             totalUnshipped = list.size();
             
             return list;
    }
    
    public ArrayList<AmazonOrdersXMLReport> listPending(){
        
            xmlDate = factory.newXMLGregorianCalendar();
            xmlDate.setDay(user.calendar.day - WEEK_OFFSET);
            xmlDate.setMonth(1);
            xmlDate.setYear(user.calendar.year);
            xmlDate.setHour(12);
            xmlDate.setMinute(0);
            xmlDate.setSecond(0);
            
  
        
            List<OrderStatusEnum> enumStatusList = new ArrayList();
            enumStatusList.add(OrderStatusEnum.PENDING);

        
            List<String> marketplaceList = new ArrayList();
            marketplaceList.add(marketplaceID);
        
            OrderStatusList statusList = new OrderStatusList();
            statusList.setStatus(enumStatusList);
        
             //it is possible to do this dynamically with code - retrieve marketplace ID's
             MarketplaceIdList marketplaces = new MarketplaceIdList();
             marketplaces.setId(marketplaceList);
        
             ListOrdersRequest statusRequest = new ListOrdersRequest();
             statusRequest.setOrderStatus(statusList);
             statusRequest.setSellerId(sellerID);
             statusRequest.setMarketplaceId(marketplaces);
             statusRequest.setCreatedAfter(xmlDate);
             
        
             ListOrdersResponse response = client.listOrders(statusRequest);
             
             
            // System.out.println(response.toXML());
             parser.setXMLSource(response.toXML());
             parser.parseXML();
                  

            ArrayList<AmazonOrdersXMLReport> list = parser.getReportList();
            
            
            totalPending = list.size();
            return list;
    }
    
    public ArrayList<AmazonOrdersXMLReport> listOrdersByNextToken(String token){
        
        ListOrdersByNextTokenRequest nextTokenRequest = new ListOrdersByNextTokenRequest();
        nextTokenRequest.setNextToken(token);
        nextTokenRequest.setSellerId(sellerID);
        
        ListOrdersByNextTokenResponse response =  client.listOrdersByNextToken(nextTokenRequest);
        System.out.println("================================Next Token ===============================\n");
        System.out.println(token);
        parser.setXMLSource(response.toXML());
        parser.parseXML();
        return parser.getReportList();
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
       // update();
    }
    
    public void clientSetup(){
       
        MarketplaceWebServiceOrdersConfig config  = new MarketplaceWebServiceOrdersConfig();
        config.setServiceURL(endpoint);
                client = new MarketplaceWebServiceOrdersClient(awsAccessKeyID, awsSecretKeyID,appName,appVersion,config);
    }
   
    public int getTotalUnshipped(){
        return totalUnshipped;
    }
    public int getTotalPending(){
        return totalPending;
    }
    public ArrayList<AmazonOrdersXMLReport> getShippedReport(){
        return this.shipped;
    }  
    public ArrayList<AmazonOrdersXMLReport> getPendingReport(){
        return this.pending;
    }
    public ArrayList<AmazonOrdersXMLReport> getUnshippedReport(){
        return this.unshipped;
    }
    @Override
    public void run() {
    
      try{
            while(true)
            {
                update(); 
                Thread.sleep(MILLI_MINUTE*UPDATE_RATE);
            }
      }catch(InterruptedException e){
          
      }
    }

    
    

}
