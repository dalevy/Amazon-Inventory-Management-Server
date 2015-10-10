/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.platforms;

import com.riverboat.amazon.products.AmazonProductsXMLReport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dlevy
 */
public class RiverBoatReports {
    
    private ArrayList<AmazonProductsXMLReport> amzProductsXMLReportList;
    private final List<RiverBoatProductRecord> productList;
    
    //Determines if this report is set
    private boolean AmazonProductsXMLReportComplete;
    
    
    public RiverBoatReports()
    {
        productList = new ArrayList<>();
    }

    /**
     * @return the amzProductsXMLReport
     */
    public ArrayList<AmazonProductsXMLReport> getAmzProductsXMLReports() {
              
        return amzProductsXMLReportList;
    }

    /**
     * @param amzProductsXMLReports the amzProductsXMLReports to set
     */
    public void setAmzProductsXMLReports(ArrayList<AmazonProductsXMLReport> amzProductsXMLReports) {
        for(AmazonProductsXMLReport report: amzProductsXMLReports)
        {
            getProductList().add(new RiverBoatProductRecord(report));
        }
        AmazonProductsXMLReportComplete = true;
    }
    
    

    /**
     * @return the AmazonProductsXMLReportComplete
     */
    public boolean isAmazonProductsXMLReportComplete() {
        return AmazonProductsXMLReportComplete;
    }

    /**
     * @param AmazonProductsXMLReportComplete the AmazonProductsXMLReportComplete to set
     */
    public void setAmazonProductsXMLReportComplete(boolean AmazonProductsXMLReportComplete) {
        this.AmazonProductsXMLReportComplete = AmazonProductsXMLReportComplete;
    }

    /**
     * @return the productList
     */
    public List<RiverBoatProductRecord> getProductList() {
        return productList;
    }
    
}
