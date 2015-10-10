package com.riverboat.amazon.orders;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * Creates an Amazon Order customer record will all the parameters that are returned by
 * XML Parsing
 * @author D'Mita Levy
 */
public class AmazonOrdersXMLReport {
    
    
    
    private String AmazonOrderId = "";
    private String PurchaseDate = "";
    private String LastUpdateDate = "";
    private String FullfillmentChannel = "";
    private String ShipServiceLevel = "";
    private String Name = "";
    private String AddressLine = "";
    private String AddressLine2 = "";
    private String City = "";
    private String StateOrRegion = "";
    private String PostalCode = "";
    private String CountryCode = "";
    private String Phone = "";
    private String CurrencyCode = "";
    private String Amount = "";
    private String NumberOfItemsShipped = "";
    private String NumberOfItemsUnshipped = "";
    private String PaymentMethod = "";
    private String MarketplaceId = "";
    private String BuyerEmail = "";
    private String BuyerName = "";
    private String ShipmentServiceLevelCategory = "";
    private String ShippedByAmazonTFM = "";
    private String OrderType = "";
    private String EarliestShipDate = "";
    private String LatestShipDate = "";
    private String NextToken = "";
    private boolean NextTokenSet = false;
    
    //possible future changes to compensate for international orders
    private final String USD_CURRENCY ="$ ";
    
    //Default Constructor
    public AmazonOrdersXMLReport(){
        
    }
  
    public void setAmazonOrderId(String param){
         this.AmazonOrderId = param;
    }     
    public void setPurchaseDate(String param){
         this.PurchaseDate = param;
    }
    public void setLastUpdateDate(String param){
         this.LastUpdateDate = param;
    }
    public void setFullfilmentChannel(String param){
         this.FullfillmentChannel = param;
    }
    public void setShipServiceLevel(String param){
         this.ShipServiceLevel = param;
    }
    public void setName(String param){
         this.Name = param;
    }
    public void setAddressLine1(String param){
         this.AddressLine = param;
    }
    
     public void setAddressLine2(String param){
         this.AddressLine2 = param;
    }
    
    public void setCity(String param){
         this.City = param;
    }
    public void setStateOrRegion(String param){
         this.StateOrRegion = param;
    }
    public void setPostalCode(String param){
         this.PostalCode = param;
    }
    public void setCountryCode(String param){
         this.CountryCode = param;
    }
    public void setPhone(String param){
         this.Phone = param;
    }
    public void setCurrencyCode(String param){
         this.CurrencyCode = param;
    }
    public void setAmount(String param){
         this.Amount = param;
    }
    public void setNumberOfItemsShipped(String param){
         this.NumberOfItemsShipped = param;
    }
    public void setNumberOfItemsUnshipped(String param){
         this.NumberOfItemsUnshipped = param;
    }
    public void setPaymentMethod(String param){
         this.PaymentMethod = param;
    }
    public void setMarketplaceId(String param){
         this.MarketplaceId = param;
    }
    public void setBuyerEmail(String param){
         this.BuyerEmail = param;
    }
    public void setBuyerName(String param){
         this.BuyerName = param;
    }
    public void setShipmentServiceLevelCategory(String param){
         this.ShipmentServiceLevelCategory = param;
    }
    public void setShippedByAmazonTFM(String param){
         this.ShippedByAmazonTFM = param;
    }
    public void setOrderType(String param){
         this.OrderType = param;
    }
    public void setEarliestShipDate(String param){
         this.EarliestShipDate = param;
    }
    public void setLatestShipDate(String param){
         this.LatestShipDate = param;
    }  
    
    public void setNextToken(String param){
        this.NextToken = param;
    }
    
    public void setNextTokenIsSet(boolean param){
        this.NextTokenSet = param;
    }
    
    
    public String getAmazonOrderId(){
         return AmazonOrderId;
    }     
    public String getPurchaseDate(){
         return PurchaseDate;
    }
    public String getLastUpdateDate(){
         return LastUpdateDate;
    }
    public String getFullfilmentChannel(){
         return FullfillmentChannel;
    }
    public String getShipServiceLevel(){
         return ShipServiceLevel;
    }
    public String getName(){
         return Name;
    }
    public String getAddressLine1(){
         return AddressLine;
    }
    public String getAddressLine2(){
         return AddressLine2;
    }
    public String getCity(){
         return City;
    }
    public String getStateOrRegion(){
         return StateOrRegion;
    }
    public String getPostalCode(){
         return PostalCode;
    }
    public String getCountryCode(){
         return CountryCode;
    }
    public String getPhone(){
         return Phone;
    }
    public String getCurrencyCode(){
         return CurrencyCode;
    }
    
    //Value will need to be updated for International orders
    public String getAmount(){
         return USD_CURRENCY + Amount;
    }
    public String getNumberOfItemsShipped(){
         return NumberOfItemsShipped;
    }
    public String getNumberOfItemsUnshipped(){
         return NumberOfItemsUnshipped;
    }
    public String getPaymentMethod(){
         return PaymentMethod;
    }
    public String getMarketplaceId(){
         return MarketplaceId;
    }
    public String getBuyerEmail(){
         return BuyerEmail;
    }
    public String getBuyerName(){
         return BuyerName;
    }
    public String getShipmentServiceLevelCategory(){
         return ShipmentServiceLevelCategory;
    }
    public String getShippedByAmazonTFM(){
         return ShippedByAmazonTFM;
    }
    public String getOrderType(){
         return OrderType;
    }
    public String getEarliestShipDate(){
         return EarliestShipDate;
    }
    public String getLatestShpDate(){
         return LatestShipDate;
    }
    
    //returns the value of NextToken
    public String getNextToken(){
        return NextToken;
    }
    
    //Checks if NextToken is set in the response
    public boolean getNextTokenIsSet(){
        return NextTokenSet;
    }
    
    /* Below are methods formatted for output
    
    */
    public String getAddress(){
        String formattedAddress = AddressLine +" "+AddressLine2+" "+StateOrRegion+" "+PostalCode; 
        
        return formattedAddress.toUpperCase();
    }

    
    
    
    
    
    
  
    
}
