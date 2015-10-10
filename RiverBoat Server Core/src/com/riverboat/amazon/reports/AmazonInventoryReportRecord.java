/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.reports;

import com.riverboat.amazon.products.AmazonProductsXMLReport;
import com.riverboat.model.Reports;
import com.riverboat.util.RiverBoatProductIdGenerator;

/**
 * Holds and individual record from the Tab delimited array provided by the
 * Amazon Recrods Report API
 * @author D'Mita Levy
 */
public class AmazonInventoryReportRecord extends Reports{

    private final String sku;
    private final String asin;
    private final String price;
    private final String quantity;
    private String rid;
    
    //Holds information parsed from Amazon product search for this ASIN
    public AmazonProductsXMLReport product;
    
    //True if this report is complete (has an xml report)
    boolean complete;
    
    
     public AmazonInventoryReportRecord(String sku, String asin, String price, String quantity){
         
         this.sku = sku;
         this.asin = asin;
         this.price = price;
         this.quantity = quantity;
         product = new AmazonProductsXMLReport();
     }
     

     
     public void setProduct(AmazonProductsXMLReport report)
     {
         if(!report.ASIN.equalsIgnoreCase(asin)) throw new IllegalArgumentException("RiverBoat: ASIN's do not match");
         if(report!=null){
         product = report;
         complete = true;
         generateRID();
         }
     }
     
     public boolean isComplete()
     {
         return complete;
     }
     
     public AmazonProductsXMLReport getXMLReport()
     {
         return product;
     }
     
     public void displayRecord()
     {
         System.out.println(getAsin()+"\t"+getSku()+"\t"+getPrice()+"\t"+getQuantity()+"\t"+product.Title+"\t"+product.Manufacturer);
     }
     
     public void generateRID()
     {
        RiverBoatProductIdGenerator idGen = new RiverBoatProductIdGenerator();
        rid = idGen.generateAmazonRID(this);
     }

    /**
     * @return the sku
     */
    public String getSku() {
        return sku;
    }

    /**
     * @return the asin
     */
    public String getAsin() {
        return asin;
    }

    /**
     * @return the price
     */
    public String getPrice() {
        return price;
    }

    /**
     * @return the quantity
     */
    public String getQuantity() {
        return quantity;
    }

    /**
     * @return the rid
     */
    public String getRid() {
        return rid;
    }

    /**
     * @param rid the rid to set
     */
    public void setRid(String rid) {
        this.rid = rid;
    }
     
 
}
