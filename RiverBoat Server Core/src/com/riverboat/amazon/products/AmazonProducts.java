/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.products;

import com.amazonservices.mws.client.MwsEndpoints;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsClient;
import com.amazonservices.mws.products.MarketplaceWebServiceProductsConfig;
import com.amazonservices.mws.products.model.ASINListType;
import com.amazonservices.mws.products.model.GetCompetitivePricingForASINRequest;
import com.amazonservices.mws.products.model.GetCompetitivePricingForASINResponse;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINRequest;
import com.amazonservices.mws.products.model.GetLowestOfferListingsForASINResponse;
import com.amazonservices.mws.products.model.GetMatchingProductForIdRequest;
import com.amazonservices.mws.products.model.GetMatchingProductRequest;
import com.amazonservices.mws.products.model.GetMatchingProductResponse;
import com.amazonservices.mws.products.model.GetMatchingProductForIdResponse;
import com.amazonservices.mws.products.model.IdListType;
import com.riverboat.model.AmazonModel;
import com.riverboat.model.IRiverBoatUser;
import com.riverboat.model.RiverBoatUser;
import com.riverboat.util.ProductCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author D'Mita Levy
 */
public class AmazonProducts extends AmazonModel implements IRiverBoatUser {
    
    MarketplaceWebServiceProductsClient client;
    
    public static final String TYPE_ASIN ="ASIN";
    public static final String TYPE_UPC ="UPC";
    public static final String TYPE_SKU ="SellerSKU";
    public static final String TYPE_ISBN ="ISBN";
    public static final String TYPE_GCID ="GCID";
    public static final String TYPE_EAN ="EAN";
    public static final String TYPE_JAN ="JAN";

    //NOTE: XMLGRID.NET is a good place to view XML structure and validate
    
    
    public AmazonProducts()
    {
        
    }

    /**
     * Accepts an unknown product number, determines whether it is ASIN, UPC, SKU, ISBN, GCID, EAN, JAN
     * and returns a singleton XML Report list that represents the found product. If the product is not found a
     * unknownId report is generated.
     * @param unknownId
     * @return 
     */
    public ArrayList<AmazonProductsXMLReport> getMatchingProductForUnknownId(String unknownId)
    {
        //Get the type of this id
        String type = determineTypeId(unknownId);
        ArrayList<String> itemList = new ArrayList<>();
        
        //Item code could not be found, return Unknown Item report
        if(type.equalsIgnoreCase(ProductCode.TYPE_UNKNOWN))
        {
            ArrayList<AmazonProductsXMLReport> list = new ArrayList<>();
            list.add(new AmazonProductsXMLReport(unknownId));
            
            return list;                   
        }
        
        
        itemList.add(unknownId);
        //Item type found, generate report
        return getMatchingProductForId(itemList, type);
    }
    
    /**
     * Determines what kind of product code has been passed ASIN, UPC, SKU, etc.
     * @param unknownId
     * @return 
     */
    public String determineTypeId(String unknownId)
    {
        String type = ProductCode.TYPE_UNKNOWN;
        
        //Check for ISBN
        if(ProductCode.isISBN(unknownId) )
            return  ProductCode.TYPE_ISBN;
        
        //Check for ASIN
        else if (ProductCode.isASIN(unknownId))
           return ProductCode.TYPE_ASIN;
        
        //check for UPC
        else if (ProductCode.isUPC(unknownId))
            return ProductCode.TYPE_UPC;
        
        return type;
                
    }
    
    /**
     * Assumes the user knows the type, ASIN, UPC, SKU of the product Id that is being passed, looks the item(s)
     * up and returns a XML Product report.
     * @param items
     * @param type
     * @return 
     */
    public ArrayList<AmazonProductsXMLReport> getMatchingProductForId(ArrayList<String> items, String type){
        
        IdListType itemList = new IdListType();
        itemList.setId(items);
        
        //Request object, returns a list of items that match type and id
        GetMatchingProductForIdRequest matchingProductIdRequest = new GetMatchingProductForIdRequest();
        matchingProductIdRequest.setMarketplaceId(marketplaceID);
        matchingProductIdRequest.setSellerId(sellerID);
        matchingProductIdRequest.setIdType(type);
        matchingProductIdRequest.setIdList(itemList);
        
        GetMatchingProductForIdResponse matchingProductIdresponse = client.getMatchingProductForId(matchingProductIdRequest);
        
        AmazonProductsXMLParser parser = new AmazonProductsXMLParser();
        parser.setXMLSource(matchingProductIdresponse.toXML());
        System.out.println("\n\n"+matchingProductIdresponse.toXML()+"\n\n");
                System.out.println("\n\n"+matchingProductIdresponse.toJSON()+"\n\n");

        parser.parseXML();
        ArrayList<AmazonProductsXMLReport> reportList = parser.getReportList();
        
            
           /*The next section of code generates pricing/shipping data
            *
            */
        
            ArrayList<String> asins = new ArrayList<>();
            
            
             //Get all the asisn from the Products report and add them to an asin list
            for(AmazonProductsXMLReport r: reportList)
            {
                asins.add(r.getASIN());
            }
            
            //Generate additional pricing info, shipping, list, landed price etc
            HashMap<String,AmazonCompetitivePricingXMLReport> pricingList = getCompetitivePricingForASIN(asins);
            
            //Add the pricing info to the products that were generated by the Products report
            for(AmazonProductsXMLReport r: reportList)
            {
                 AmazonCompetitivePricingXMLReport pricingReport = pricingList.get(r.getASIN());
                 r.setLandedPrice(pricingReport.getLandedPrice());
                 r.setShippingCost(pricingReport.getShippingCost());
                 r.setPrice(pricingReport.getListPrice());
                 
                 r.displayAllVariables();
                 
                 
                 
            }
        
            System.out.println("----------------------------------------------");
            //System.out.println(matchingProductIdresponse.toXML());

            return reportList;
        

    }
    
       
    /*****************************************************
     * Returns the competitive price and shipping price for a product
     * @param asins 
     * @return  
     */
    public HashMap<String, AmazonCompetitivePricingXMLReport> getCompetitivePricingForASIN(ArrayList<String> asins){
        
        //List to be returned
        HashMap<String, AmazonCompetitivePricingXMLReport> list = new HashMap<>();
          
        //Creat a list of ASIN to send to server 
        List<String> productList = asins;
            ASINListType asinList = new ASINListType();
            asinList.setASIN(productList);
        
        
         //Request object, returns competitive pricing    
        GetCompetitivePricingForASINRequest competitivePricingRequest = new GetCompetitivePricingForASINRequest();
        competitivePricingRequest.setMarketplaceId(marketplaceID);
        competitivePricingRequest.setSellerId(sellerID);
        competitivePricingRequest.setASINList(asinList);
        
        GetCompetitivePricingForASINResponse competitivePricingResponse = client.getCompetitivePricingForASIN(competitivePricingRequest);
        
        //Parse the XML response
        AmazonCompetitivePricingXMLParser parser = new AmazonCompetitivePricingXMLParser();
        parser.setXMLSource(competitivePricingResponse.toXML());
        parser.parseXML();
        
       ArrayList<AmazonCompetitivePricingXMLReport> reportList = parser.getReportList();
       
       //Generate a Hash Map withe ASIN: Object
       for(AmazonCompetitivePricingXMLReport r: reportList)
       {
           list.put(r.getASIN(), r);
           
           //System.out.println("ASIN "+r.getASIN()+" Shipping"+r.getShippingCost()+" Landed Price"+r.getLandedPrice()+" List Price"+r.getListPrice());

       }
       
                   // System.out.println(competitivePricingResponse.toXML());
       
       return list;

    }
    
    
    public void getLowestOfferListingsForASIN(ArrayList<String> asins){
        
        //Creat a list of ASIN to send to server 
        List<String> productList = asins;
            ASINListType asinList = new ASINListType();
            asinList.setASIN(productList);
         
        
        GetLowestOfferListingsForASINRequest lowestOfferRequest = new GetLowestOfferListingsForASINRequest();
        lowestOfferRequest.setASINList(asinList);
        lowestOfferRequest.setMarketplaceId(marketplaceID);
        lowestOfferRequest.setSellerId(sellerID);
        
        GetLowestOfferListingsForASINResponse lowestOfferResponse = client.getLowestOfferListingsForASIN(lowestOfferRequest);
       System.out.print(lowestOfferResponse.toXML());
        
    }
 
    /*************************************************
     * This method has been deprecated and the 
     * GetmatchingProductForId method should be used
     * @param asins 
     * @return String
     */
    public String getMatchingProductLegacy(ArrayList<String> asins){
        
        
        //Creat a list of ASIN to send to server 
        List<String> productList = asins;
            ASINListType asinList = new ASINListType();
            asinList.setASIN(productList);
        
         //Submit request
        GetMatchingProductRequest matchingProductRequest = new GetMatchingProductRequest();
        matchingProductRequest.setSellerId(sellerID);
        matchingProductRequest.setASINList(asinList);
        matchingProductRequest.setMarketplaceId(marketplaceID);
        
        GetMatchingProductResponse matchingProductResponse = client.getMatchingProduct(matchingProductRequest);
        return matchingProductResponse.toXML();
    }
    

  @Override
    public void setUser(RiverBoatUser user){
        
        if(user == null) throw new IllegalArgumentException("Amazon Products: User cannot be null");    
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
    
     private  void clientSetup(){
       
        MarketplaceWebServiceProductsConfig config  = new MarketplaceWebServiceProductsConfig();
        config.setServiceURL(endpoint);
                client = new MarketplaceWebServiceProductsClient(awsAccessKeyID, awsSecretKeyID,appName,appVersion,config);
    }

}
