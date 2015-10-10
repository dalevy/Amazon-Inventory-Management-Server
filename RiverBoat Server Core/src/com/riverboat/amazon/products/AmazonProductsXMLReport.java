/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverboat.amazon.products;

import com.riverboat.model.Reports;

/**
 * Product report that mirrors the XML data returned by Amazon
 * @author D'Mita Levy
 */
public class AmazonProductsXMLReport extends Reports{

    
    //Variables that mirror elements from the GetMatchingProductForId API
    public String ASIN = "-";
    public String Brand = "-";
    public String Feature = "-";
    public String HeightUnitsinches="-";
    public String LengthUnitsinches="-";
    public String WidthUnitsinches="-";
    public String WeightUnitspounds="-";
    public String PackageHeightUnitsinches="-";
    public String PackageLengthUnitsinches="-";
    public String PackageWidthUnitsinches="-";
    public String PackageWeightUnitspounds="-";
    public String IsAdultProduct = "-";
    public String Label = "-";
    public String ListPrice = "-";
    public String CurrencyCode = "-";
    public String Manufacturer = "-";
    public String PartNumber = "-";
    public String ProductGroup = "-";
    public String ProductTypeName = "-";
    public String URL = "-";
    public String Studio = "-";
    public String Title = "-";
    public String Rank = "-";
    public String Color = "-";
    
    //Variables that are generated or added to by other API
    public String Price = "-";
    public String ShippingPrice ="-";
    public String LandedPrice ="-";
    
    
    boolean reportComplete;
    
    /**
     * Create an XML Report with only the id number of the item - this represents
     * an item that is unknown to the services
     * @param unknownId 
     */
    public AmazonProductsXMLReport(String unknownId)
    {
        ASIN = Brand = Title = Manufacturer = "Unknown Item";
    }
    //Default constructor
    public AmazonProductsXMLReport() {

    }

    public void setASIN(String param) {
        this.ASIN = param;
    }

    public void setBrand(String param) {
        this.Brand = param;
    }

    public void setFeature(String param) {
        this.Feature += param;
    }

    public void setHeight_Units_inches(String param) {// check this
        this.HeightUnitsinches = param;
    }

    public void setLength_Units_inches(String param) {
        this.LengthUnitsinches = param;
    }

    public void setWidth_Units_inches(String param) {
        this.WidthUnitsinches = param;
    }

    public void setWeight_Units_pounds(String param) {
        this.WeightUnitspounds = param;
    }

    public void setIsAdultProduct(String param) {
        this.IsAdultProduct = param;
    }

    public void setLabel(String param) {
        this.Label = param;
    }

    public void setAmount(String param) {
        this.ListPrice = param;
    }

    public void setCurrencyCode(String param) {
        this.CurrencyCode = param;
    }

    public void setManufacturer(String param) {
        this.Manufacturer = param;
    }

    public void setPartNumber(String param) {
        this.PartNumber = param;
    }

    public void setProductGroup(String param) {
        this.ProductGroup = param;
    }

    public void setProductTypeName(String param) {
        this.ProductTypeName = param;
    }

    public void setURL(String param) {
        this.URL = param;
    }

    public void setStudio(String param) {
        this.Studio = param;
    }

    public void setTitle(String param) {
        this.Title = param;
    }

    public void setRank(String param) {
        this.Rank = param;
    }

    public void setColor(String param) {
        this.Color = param;
    }
    public void setPackageHeightUnitsinches(String param){
        this.PackageHeightUnitsinches = param;
    }
    public void setPackageLengthUnitsinches(String param){
        this.PackageLengthUnitsinches = param;
    }
    public void setPackageWidthUnitsinches(String param){
        this.PackageWidthUnitsinches = param;
    }
    public void setPackageWeightUnitspounds(String param){
        this.PackageWeightUnitspounds = param;
    }
    public void setPrice(String param){
        
        this.Price = param;
        
        if(ListPrice.equalsIgnoreCase(""))
            ListPrice = Price;
        
    }
    public void setShippingCost(String param){
        
        if(param.equalsIgnoreCase("0.00")){
            ShippingPrice = "FREE";
        }else{
            ShippingPrice = param;
        }
        
        
    }
    public void setLandedPrice(String param){
        this.LandedPrice = param;
    }
    
    


    public String getASIN() {
        return ASIN;
    }

    public String getBrand() {
        return Brand;
    }

    public String getFeature() {
        return Feature;
    }

    public String getHeight_Units_inches() {// check this
        return HeightUnitsinches;
    }

    public String getLength_Units_inches() {
        return LengthUnitsinches;
    }

    public String getWidth_Units_inches() {
        return WidthUnitsinches;
    }

    public String getWeight_Units_pounds() {
        return WeightUnitspounds;
    }

    public String getIsAdultProduct() {
        return IsAdultProduct;
    }

    public String getLabel() {
        return Label;
    }

    public String getAmount() {
        return ListPrice;
    }

    public String getCurrencyCode() {
        return CurrencyCode;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getPartNumber() {
        return PartNumber;
    }

    public String getProductGroup() {
        return ProductGroup;
    }

    public String getProductTypeName() {
        return ProductTypeName;
    }

    public String getURL() {
        return URL;
    }

    public String getStudio() {
        return Studio;
    }

    public String getTitle() {
        return Title;
    }

    public String getRank() {
        return Rank;
    }

    public String getColor() {
        return Color;
    }
    
    public String getPackageHeightUnitsinches(){
        return PackageHeightUnitsinches;
    }
    public String getPackageLengthUnitsinches(){
        return PackageLengthUnitsinches;
    }
    public String getPackageWidthUnitsinches(){
        return PackageWidthUnitsinches;
    }
    public String getPackageWeightUnitspounds(){
        return PackageWeightUnitspounds;
    }
    public String getPrice(){
        return Price;
    }
    public String getShippingPrice(){
        return ShippingPrice;
    }
    public String getLandedPrice(){
        return LandedPrice;
    }
    


    public void displayAllVariables() {

        System.out.println("ASIN: " + ASIN);
        System.out.println("BRAND: " + Brand);
        System.out.println("Feature: " + Feature);
        System.out.println("Heigth: " + HeightUnitsinches);
        System.out.println("Length: " + LengthUnitsinches);
        System.out.println("Width: " + WidthUnitsinches);
        System.out.println("Weight: " + WeightUnitspounds);
        System.out.println("IsAdult: " + IsAdultProduct);
        System.out.println("List Price: " + ListPrice);
        System.out.println("Currency: " + CurrencyCode);
        System.out.println("Manufacturer: " + Manufacturer);
        System.out.println("PartNumber: " + PartNumber);
        System.out.println("ProductGroup: " + ProductGroup);
        System.out.println("ProductType: " + ProductTypeName);
        System.out.println("URL: " + URL);
        System.out.println("Studio: " + Studio);
        System.out.println("Title: " + Title);
        System.out.println("Rank: " + Rank);
        System.out.println("Color: " + Color);
        System.out.println("Package Height: "+PackageHeightUnitsinches);
        System.out.println("Package Length: "+PackageLengthUnitsinches);
        System.out.println("Package Width: "+PackageWidthUnitsinches);
        System.out.println("Package Weight: "+ PackageWeightUnitspounds);
        System.out.println("Price :"+Price);
        System.out.println("Shipping Price: "+ShippingPrice);
        System.out.println("Total Price: "+LandedPrice);
        System.out.println("\n");

    }
}
