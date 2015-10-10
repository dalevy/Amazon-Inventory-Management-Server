/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.products;

import com.riverboat.model.Reports;

/**
 * Competitive Pricing object
 * @author D'Mita Levy
 */
public class AmazonCompetitivePricingXMLReport extends Reports {

    private String ASIN = "";
    private String ShippingCost = "";
    private String LandedPrice = "";
    private String ListPrice ="";
    
    
    
    public AmazonCompetitivePricingXMLReport(){
        
    }
    
    public String getASIN(){
        return ASIN;
    }
    public String getShippingCost(){
        return ShippingCost;
    }
    public String getLandedPrice(){
        return LandedPrice;
    }
    public String getListPrice(){
        return ListPrice;
    }
    
    public void setASIN(String param){
        this.ASIN = param;
    }
    public void setShippingCost(String param){
        this.ShippingCost = param;
    }
    public void setLandedPrice(String param){
        this.LandedPrice = param;
    }
    public void setListPrice(String param){
        this.ListPrice = param;
    }
    
    
    
}
