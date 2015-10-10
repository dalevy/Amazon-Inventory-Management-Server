/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.tasks;

import com.riverboat.amazon.products.AmazonProducts;
import com.riverboat.amazon.products.AmazonProductsXMLReport;
import com.riverboat.amazon.reports.AmazonInventoryReportRecord;
import com.riverboat.amazon.reports.AmazonReports;
import com.riverboat.model.RiverBoatUser;
import com.riverboat.server.config.PathNames;
import com.riverboat.server.config.PlatformVariables;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author D.Levy
 * Represents inventory management tasks such as update, manual add, manual delete.
 */
public class RiverBoatServerInventoryUpdate implements Runnable{
    
    RiverBoatUser user;
    AmazonReports amazon_reports;
    AmazonProducts amazon_products;
    Connection dbConnection;
    
    //Time in millis to wait to get a new update, +1000 (1 second) to allow maximum efficient lossless restoration
    final long REQUESTTIMER = PlatformVariables.getReportRequestList_max_throttle +1000;
    
    //Current throttle ratio for getmatchingProductForId operation, max -1 to allow efficient lossless restoration
    final int throttle_ratio = PlatformVariables.getMatchingProductForId_max_throttle - 1;


    /***
     * Pulls inventory and fully updates customer tables
     * @return successful 
     */
    
    private void fullUpdate()
    {
        boolean isReady = false; //report is not ready
        String requestId; //holds the value of a report request Id
        List<String> generatedReportId = null; //once report is generated holds the Id's
        
        int totalInventory;
        int totalXML;
        
        List<String> idList; //list of report id's to check for
        
       
        //Get Full Inventory report from Amazon
        amazon_reports = new AmazonReports();
            amazon_reports.setUser(user);
       
          
        //Request a Full Inventory report from Amazon (Open Listings Report)
        requestId = amazon_reports.getFullInventoryList();
            //add request to list of requests
            idList = new ArrayList<>();
            idList.add(requestId);
       
        generatedReportId = new ArrayList<>();
    
     
      //check if the report is ready 
      while(!isReady)
      {
          isReady = amazon_reports.getReportRequestList(idList, generatedReportId);
          waitThread(REQUESTTIMER);
                
      }
      
      //The report is ready, get a list of Records
      
          if(generatedReportId == null) throw new IllegalArgumentException("RiverBoat:Generated Report Id Parameter Cannot be Null");
          ArrayList<AmazonInventoryReportRecord> completeReport = amazon_reports.getCompletedReport(generatedReportId.get(0)).getRecordList();
            completeReport.remove(0); //Get Rid of Column Headers
            
          amazon_products = new AmazonProducts();
          amazon_products.setUser(user);
          
          totalInventory = completeReport.size();
          
             int count = 0;
             int index = 0;
             ArrayList<String> asinList = new ArrayList<>(); //list of asins to look up
             
             //Holds reports for the convenience sake of combining them with Inventory Record Report
             AmazonProductsXMLReport[] xmlReports = new AmazonProductsXMLReport[completeReport.size()];
             
             /**
             * Go through each record and get additional info for the inventory items
             * The current restore rate for GetMatchingProductForId is 5 every 1 second
             * Therefore, 4 (throttle_ratio) will be sent to be safe that there are additional requests
             * available or operations such as customer item scan look up.
             * 5-23-2014
             */

             
             Iterator<AmazonInventoryReportRecord> itr = completeReport.iterator();
             
             while(itr.hasNext())
             {
                 //Only want to add throttle_ratio number items to look up
                 if(count < throttle_ratio)
                 {
                     asinList.add(itr.next().getAsin());
                     count++;
                 }
                 //Look up trottle_ratio number items
                 if(count == throttle_ratio || !itr.hasNext())
                 {
                   //Get this list of products' info and add to product report list
                   for(AmazonProductsXMLReport report: amazon_products.getMatchingProductForId(asinList, AmazonProducts.TYPE_ASIN))
                   {
                       xmlReports[index] = report;
                       index++;
                   }
                   
                   count = 0;
                   asinList.clear();
                   waitThread(PlatformVariables.getMatchingProductForId_restore_rate); //wait to avoid throttling
                 }
                    
             }
             
             
             index = 0;
             for(AmazonInventoryReportRecord record: completeReport)
             {
                 record.setProduct(xmlReports[index]);
                 record.displayRecord();
                 index++;
             }
             

             
             for(AmazonProductsXMLReport r: xmlReports)
                 System.out.println(r.ASIN+"\t"+r.Title+"\t"+r.Manufacturer+"\t"+r.Brand);
                 
                 
        batchInsert(completeReport);
        
        
    }// end fullUpdate
    
    public void batchInsert(ArrayList<AmazonInventoryReportRecord> records)
    {
        PreparedStatement preparedStatement= null;
        
        StringBuilder db = new StringBuilder();
        db.append(PathNames.DB_NAME);
        db.append(".");
        db.append(user.getAccount());
        db.append("amazoninventory");
        
        String insertStatement = "REPLACE INTO "+db.toString()
				+ "(asin, sku, quantity, price, brand, manufacturer, adult, feature, height, "
                                + "weight, width, length, amount, currency, color, part_number, product_group, product_type, "
                                + "studio, rank, package_height, package_width, package_weight, list_price, shipping_price, url, title, rid) VALUES"
				+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try{
            
            //Register JDBC Driver
           Class.forName("com.mysql.jdbc.Driver");
           
           //Open Connections
           System.out.println("Openining Database Connection to "+PathNames.DB_URL);
           dbConnection = DriverManager.getConnection(PathNames.DB_URL,PathNames.USERNAME,PathNames.PASSWORD);
           
           System.out.println("Connected to Database. Accessing Table"+db.toString());
           //Prepare statement
           preparedStatement = dbConnection.prepareStatement(insertStatement);
           
           //dbConnection.setAutoCommit(false);
           
           int count = 0;
           for(AmazonInventoryReportRecord r: records)
           {          
                preparedStatement.setString(1, r.getAsin());
                preparedStatement.setString(2, r.getSku());
                preparedStatement.setInt (3, Integer.parseInt(r.getQuantity())); 
                preparedStatement.setString(4, r.getPrice());
                preparedStatement.setString(5, r.product.Brand);
                preparedStatement.setString(6, r.product.Manufacturer);
                preparedStatement.setString(7, r.product.IsAdultProduct);
                preparedStatement.setString(8, r.product.Feature);
                preparedStatement.setString(9, r.product.HeightUnitsinches);
                preparedStatement.setString(10, r.product.WeightUnitspounds);
                preparedStatement.setString(11, r.product.WidthUnitsinches);
                preparedStatement.setString(12, r.product.LengthUnitsinches);
                preparedStatement.setString(13, r.product.ListPrice);
                preparedStatement.setString(14, r.product.CurrencyCode);
                preparedStatement.setString(15, r.product.Color);
                preparedStatement.setString(16, r.product.PartNumber);
                preparedStatement.setString(17, r.product.ProductGroup);
                preparedStatement.setString(18, r.product.ProductTypeName);
                preparedStatement.setString(19, r.product.Studio);
                preparedStatement.setString(20, r.product.Rank);
                preparedStatement.setString(21, r.product.PackageHeightUnitsinches);
                preparedStatement.setString(22, r.product.PackageWidthUnitsinches);
                preparedStatement.setString(23, r.product.PackageWeightUnitspounds);
                preparedStatement.setString(24, r.product.Price);
                preparedStatement.setString(25, r.product.ShippingPrice);
                preparedStatement.setString(26, r.product.URL);
                preparedStatement.setString(27, r.product.Title);
                preparedStatement.setString(28, r.getRid());
                
                count++;
                System.out.println(count+" -- Adding "+r.getAsin()+" "+r.product.Title+" "+r.product.Manufacturer);
                preparedStatement.addBatch();
                if((count+1)%100 == 0){
                    System.out.println("Executing Batch!");
                    preparedStatement.executeBatch();
                }
                
           }//end for loop
           preparedStatement.executeBatch();
            
        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
            
        }finally{
             if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException logOrIgnore) { logOrIgnore.printStackTrace();}
             if (dbConnection != null) try { dbConnection.close(); } catch (SQLException logOrIgnore) {logOrIgnore.printStackTrace();}
        }
   }
        
  
    
    private void waitThread(long time)
    {
          try{
              
              //Wait before making next request
              Thread.sleep(time);
          
          }catch(InterruptedException e){
              
          }
    }

    @Override
    public void run() {
        
        fullUpdate();
    }
    
    
    //Constructor    
    public RiverBoatServerInventoryUpdate(RiverBoatUser user)
    {
        this.user = user;
       
    }
       
    
}
