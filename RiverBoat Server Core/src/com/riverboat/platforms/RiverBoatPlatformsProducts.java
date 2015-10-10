/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.platforms;

import com.riverboat.amazon.products.AmazonProducts;
import com.riverboat.model.RiverBoatUser;

/**
 * Encapsulates all the e-commerce marketplace platforms product classes into one class so that
 * they can be queried all at once if necessary.
 * @author dlevy
 */
public class RiverBoatPlatformsProducts {
    
    RiverBoatUser user;
    AmazonProducts amazon;
    
    
    public RiverBoatPlatformsProducts(RiverBoatUser user1)
    {
        this.user = user1;
        amazon = new AmazonProducts();
        amazon.setUser(user);
    }
    
 

 
    public RiverBoatReports lookup(String productNumber)
    {
        //Create a report object to hold the result of item lookup performed by each 
        //e-commerce platform api
        RiverBoatReports lookupResults = new RiverBoatReports();
        
        //Get the an amazon product report and store it in 
        lookupResults.setAmzProductsXMLReports(amazon.getMatchingProductForUnknownId(productNumber));
          
        return lookupResults;
    }
    
    
    
}
