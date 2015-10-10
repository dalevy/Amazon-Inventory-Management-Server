/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.reports;

/**
 *
 * @author Idirekt
 */
public interface AmazonReportType {
    
    /****************************************************************
     * Tab-delimited flat file open listings report that contains the S
     * KU, ASIN, Price, and Quantity fields. For Marketplace and Seller Central sellers.
     * --("Inventory Report")
     */
    String Open_Listings_Report = "_GET_FLAT_FILE_OPEN_LISTINGS_DATA_";
    
    
      /****************************************************************
     * Tab-delimited flat file detailed active listings report. 
     * For Marketplace and Seller Central sellers.
     */
    String Merchant_Listings_Report  = "_GET_MERCHANT_LISTINGS_DATA_";
    
      /****************************************************************
     * Tab-delimited flat file active listings report that contains only the SKU, ASIN, Price, 
     * and Quantity fields for items that have a quantity greater than zero. 
     * For Marketplace and Seller Central sellers.
     * --("Active Listings Report")
     */
    String Merchant_Listings_Lite_Report ="_GET_MERCHANT_LISTINGS_DATA_LITE_";
    
      /****************************************************************
      * Tab-delimited flat file active listings report that contains only the SKU and
      * Quantity fields for items that have a quantity greater than zero. 
      * For Marketplace and Seller Central sellers.
     */
    String Merchant_Listings_Liter_Report = "_GET_MERCHANT_LISTINGS_DATA_LITER_";
    
    
      /****************************************************************
     * Tab-delimited flat file sold listings report that contains items 
     * sold on Amazon's retail website. For Marketplace and Seller Central sellers.
     */
    String Sold_Listings_Report = "_GET_CONVERGED_FLAT_FILE_SOLD_LISTINGS_DATA_";
    
      /****************************************************************
     * Tab-delimited flat file canceled listings report. 
     * For Marketplace and Seller Central sellers.
     */
    String Canceled_Listings_Report ="_GET_MERCHANT_CANCELLED_LISTINGS_DATA_";
    
      /****************************************************************
     * Tab-delimited flat file quality listing report that contains the 
     * following fields: sku, product-name, asin, field-name, alert-type, current-value, 
     * last-updated, alert-name, and status. For Marketplace and Seller Central sellers.
     */
    String Qaulity_Listing_Report ="_GET_MERCHANT_LISTINGS_DEFECT_DATA_";
}
