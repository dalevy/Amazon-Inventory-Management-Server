/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.amazon.reports;

import com.amazonservices.mws.client.MwsEndpoints;
import com.amazonservices.mws.sellers.MarketplaceWebServiceSellers;
import com.riverboat.model.AmazonModel;
import com.riverboat.model.IRiverBoatUser;
import com.riverboat.model.RiverBoatUser;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.security.DigestOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Idirekt
 * @param <GetReportListRequest>
 */
public class AmazonReports<GetReportListRequest> extends AmazonModel implements IRiverBoatUser {

    
    MarketplaceWebServiceSellers client;
    BufferedOutputStream bout;
    DigestOutputStream dout;
    ByteArrayOutputStream barrayout;
    
    //Special implementation: see setCSV Method
    String lastCSV;
    
    
    
    
    public AmazonReports(){
        
    }
    
    /**
     * Request the list of reports available to this seller
     */
    public void getReportList(){
        
        GetReportListRequest request = new GetReportListRequest();
        request.setMerchant(sellerID);
        
          try {

            GetReportListRequest response = client.getReportList(request);


            System.out.println ("GetReportList Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    GetReportListResponse");
            System.out.println();
            if (response.isSetGetReportListResult()) {
                System.out.print("        GetReportListResult");
                System.out.println();
                GetReportListResult  getReportListResult = response.getGetReportListResult();
                if (getReportListResult.isSetNextToken()) {
                    System.out.print("            NextToken");
                    System.out.println();
                    System.out.print("                " + getReportListResult.getNextToken());
                    System.out.println();
                }
                if (getReportListResult.isSetHasNext()) {
                    System.out.print("            HasNext");
                    System.out.println();
                    System.out.print("                " + getReportListResult.isHasNext());
                    System.out.println();
                }
                java.util.List<ReportInfo> reportInfoListList = getReportListResult.getReportInfoList();
                for (ReportInfo reportInfoList : reportInfoListList) {
                    System.out.print("            ReportInfoList");
                    System.out.println();
                    if (reportInfoList.isSetReportId()) {
                        System.out.print("                ReportId");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getReportId());
                        System.out.println();
                    }
                    if (reportInfoList.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getReportType());
                        System.out.println();
                    }
                    if (reportInfoList.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getReportRequestId());
                        System.out.println();
                    }
                    if (reportInfoList.isSetAvailableDate()) {
                        System.out.print("                AvailableDate");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getAvailableDate());
                        System.out.println();
                    }
                    if (reportInfoList.isSetAcknowledged()) {
                        System.out.print("                Acknowledged");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.isAcknowledged());
                        System.out.println();
                    }
                    if (reportInfoList.isSetAcknowledgedDate()) {
                        System.out.print("                AcknowledgedDate");
                        System.out.println();
                        System.out.print("                    " + reportInfoList.getAcknowledgedDate());
                        System.out.println();
                    }
                }
            } 
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();


        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
        
    }
       
    
    /**
     * Checks which reports are ready and returns a boolean value if a specific report is ready
     * @param requestIdList
     * @param generatedId
     * @return 
     */
    public boolean getReportRequestList(List<String> requestIdList, List<String> generatedId)
    {
        boolean complete = false; //found the report

        //List of report Id's to look for
        IdList list = new IdList();
            list.setId(requestIdList);
        
        GetReportRequestListRequest request = new GetReportRequestListRequest();
        request.setMerchant( user.amazon.getSellerID() );
        request.setReportRequestIdList(list);
        
         try {

            GetReportRequestListResponse response = client.getReportRequestList(request);


            System.out.println ("GetReportRequestList Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    GetReportRequestListResponse");
            System.out.println();
            if (response.isSetGetReportRequestListResult()) {
                System.out.print("        GetReportRequestListResult");
                System.out.println();
                GetReportRequestListResult  getReportRequestListResult = response.getGetReportRequestListResult();
                if (getReportRequestListResult.isSetNextToken()) {
                    System.out.print("            NextToken");
                    System.out.println();
                    System.out.print("                " + getReportRequestListResult.getNextToken());
                    System.out.println();
                }
                if (getReportRequestListResult.isSetHasNext()) {
                    System.out.print("            HasNext");
                    System.out.println();
                    System.out.print("                " + getReportRequestListResult.isHasNext());
                    System.out.println();
                }
                java.util.List<ReportRequestInfo> reportRequestInfoList = getReportRequestListResult.getReportRequestInfoList();
                for (ReportRequestInfo reportRequestInfo : reportRequestInfoList) {
                    System.out.print("            ReportRequestInfo");
                    System.out.println();
                    if (reportRequestInfo.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportRequestId());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportType());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetStartDate()) {
                        System.out.print("                StartDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getStartDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetEndDate()) {
                        System.out.print("                EndDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getEndDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetCompletedDate()) {
                        System.out.print("                CompletedDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getCompletedDate());
                        System.out.println();
                    }                    
                    if (reportRequestInfo.isSetReportProcessingStatus()) {
                        System.out.print("                ReportProcessingStatus");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportProcessingStatus());
                        System.out.println();
                        
                        //Check if the request report is done
                        if(reportRequestInfo.getReportProcessingStatus().equalsIgnoreCase("_DONE_"))
                        {
                            
                             System.out.print("                GeneratedReportId");
                             System.out.println();
                             System.out.print("                    " + reportRequestInfo.getGeneratedReportId());
                             System.out.println();
                            //Look for the specific report
                            if(requestIdList.get(0).equalsIgnoreCase(reportRequestInfo.getReportRequestId()))
                            {
                                complete = true;
                                generatedId.add(reportRequestInfo.getGeneratedReportId());
                            }
                        }
                        
                    }
                }
            } 
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();


        } catch (MarketplaceWebServiceException ex) {

            System.out.println("Caught Exception: " + ex.getMessage());
            System.out.println("Response Status Code: " + ex.getStatusCode());
            System.out.println("Error Code: " + ex.getErrorCode());
            System.out.println("Error Type: " + ex.getErrorType());
            System.out.println("Request ID: " + ex.getRequestId());
            System.out.print("XML: " + ex.getXML());
            System.out.println("ResponseHeaderMetadata: " + ex.getResponseHeaderMetadata());
        }
        
        
         return complete;
         
    }//end getReportRequestList
    
    public AmazonReportsRecordBuilder getCompletedReport(String id){
       
        //testing CSV
        String CSV ="";
        AmazonReportsRecordBuilder records = null;
        
    try{  
        
       // OutputStream requestoutputstream = new FileOutputStream("C:\\Users\\Idirekt\\Desktop\\The Dump\\Test.txt");
        OutputStream requestoutputstream = new ByteArrayOutputStream();
        GetReportRequest request = new GetReportRequest();
        request.setMerchant(sellerID);
        request.setReportId(id);
       
        request.setReportOutputStream(requestoutputstream);
        
 
            
            GetReportResponse response = client.getReport(request);
            
            System.out.println ("GetReport Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    GetReportResponse");
            System.out.println();
            System.out.print("    GetReportResult");
            System.out.println();
            System.out.print("            MD5Checksum");
            System.out.println();
            System.out.print("                " + response.getGetReportResult().getMD5Checksum());
            System.out.println();
            if (response.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = response.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();

            System.out.println("Report");
            System.out.println ("=============================================================================");
            System.out.println();
            //Stream Object is here
           // System.out.println( request.getReportOutputStream().toString() );
            //Testing CSV Parser
            setCSV(request.getReportOutputStream().toString());
            System.out.println(getCSV());
            
            records = new AmazonReportsRecordBuilder(( ByteArrayOutputStream)request.getReportOutputStream());
            records.extrapolate();
            System.out.println(response.getResponseHeaderMetadata());
            System.out.println();
            
            
        }catch(MarketplaceWebServiceException e){
             
            System.out.println("Caught Exception: " + e.getMessage());
            System.out.println("Response Status Code: " + e.getStatusCode());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Type: " + e.getErrorType());
            System.out.println("Request ID: " + e.getRequestId());
            System.out.println("XML: " + e.getXML());
            System.out.println("ResponseHeaderMetadata: " + e.getResponseHeaderMetadata());
            
            e.printStackTrace();
        }
    
        return records;
            
        
    }


    
    
    /* Gets report based on the enumerated ReportType object 
    * and returns the report ID
    */
    public String requestReport(String type){
        
        String reportRequestID ="";
        
        RequestReportRequest reportRequest = new RequestReportRequest();
        reportRequest.setMerchant(sellerID);
        reportRequest.setReportType(type);


        try{
            
          RequestReportResponse requestReportResponse = client.requestReport(reportRequest);
          
            System.out.println ("RequestReport Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    RequestReportResponse");
            System.out.println();
            if (requestReportResponse.isSetRequestReportResult()) {
                System.out.print("        RequestReportResult");
                System.out.println();
                RequestReportResult  requestReportResult = requestReportResponse.getRequestReportResult();
                if (requestReportResult.isSetReportRequestInfo()) {
                    System.out.print("            ReportRequestInfo");
                    System.out.println();
                    ReportRequestInfo  reportRequestInfo = requestReportResult.getReportRequestInfo();
                    if (reportRequestInfo.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportRequestId());
                        System.out.println();
                        reportRequestID = reportRequestInfo.getReportRequestId();
                    }
                    if (reportRequestInfo.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportType());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetStartDate()) {
                        System.out.print("                StartDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getStartDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetEndDate()) {
                        System.out.print("                EndDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getEndDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetReportProcessingStatus()) {
                        System.out.print("                ReportProcessingStatus");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportProcessingStatus());
                        System.out.println();
                    }
                } 
            } 
            if (requestReportResponse.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = requestReportResponse.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();
            System.out.println(requestReportResponse.getResponseHeaderMetadata());
            System.out.println();
            
            GetReportRequestListRequest requestList = new GetReportRequestListRequest();
            requestList.setMerchant(sellerID);
            
                 GetReportRequestListResponse responseList = client.getReportRequestList(requestList);
             


            System.out.println ("GetReportRequestList Action Response");
            System.out.println ("=============================================================================");
            System.out.println ();

            System.out.print("    GetReportRequestListResponse");
            System.out.println();
            if (responseList.isSetGetReportRequestListResult()) {
                System.out.print("        GetReportRequestListResult");
                System.out.println();
                GetReportRequestListResult  getReportRequestListResult = responseList.getGetReportRequestListResult();
                if (getReportRequestListResult.isSetNextToken()) {
                    System.out.print("            NextToken");
                    System.out.println();
                    System.out.print("                " + getReportRequestListResult.getNextToken());
                    System.out.println();
                }
                if (getReportRequestListResult.isSetHasNext()) {
                    System.out.print("            HasNext");
                    System.out.println();
                    System.out.print("                " + getReportRequestListResult.isHasNext());
                    System.out.println();
                }
                java.util.List<ReportRequestInfo> reportRequestInfoList = getReportRequestListResult.getReportRequestInfoList();
                for (ReportRequestInfo reportRequestInfo : reportRequestInfoList) {
                    System.out.print("            ReportRequestInfo");
                    System.out.println();
                    if (reportRequestInfo.isSetReportRequestId()) {
                        System.out.print("                ReportRequestId");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportRequestId());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetReportType()) {
                        System.out.print("                ReportType");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportType());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetStartDate()) {
                        System.out.print("                StartDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getStartDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetEndDate()) {
                        System.out.print("                EndDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getEndDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetSubmittedDate()) {
                        System.out.print("                SubmittedDate");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getSubmittedDate());
                        System.out.println();
                    }
                    if (reportRequestInfo.isSetReportProcessingStatus()) {
                        System.out.print("                ReportProcessingStatus");
                        System.out.println();
                        System.out.print("                    " + reportRequestInfo.getReportProcessingStatus());
                        System.out.println();
                    }
                }
            } 
            if (responseList.isSetResponseMetadata()) {
                System.out.print("        ResponseMetadata");
                System.out.println();
                ResponseMetadata  responseMetadata = responseList.getResponseMetadata();
                if (responseMetadata.isSetRequestId()) {
                    System.out.print("            RequestId");
                    System.out.println();
                    System.out.print("                " + responseMetadata.getRequestId());
                    System.out.println();
                }
            } 
            System.out.println();
            System.out.println(responseList.getResponseHeaderMetadata());
            System.out.println();


        }catch(MarketplaceWebServiceException e){
           
            System.out.println("Caught Exception: " + e.getMessage());
            System.out.println("Response Status Code: " + e.getStatusCode());
            System.out.println("Error Code: " + e.getErrorCode());
            System.out.println("Error Type: " + e.getErrorType());
            System.out.println("Request ID: " + e.getRequestId());
            System.out.print("XML: " + e.getXML());
            System.out.println("ResponseHeaderMetadata: " + e.getResponseHeaderMetadata());
                   
       }
        
        return reportRequestID;
                
    }
     
    @Override
    public void setUser(RiverBoatUser user){
        this.user = user;
        this.awsAccessKeyID = user.amazon.getAmazonAccessKey();
        this.awsSecretKeyID = user.amazon.getAmazonSecretKey();
        this.appVersion = user.amazon.getApplicationVersion();
        this.appName = user.amazon.getApplicationName();
        this.sellerID = user.amazon.getSellerID();
        this.endpoint = MwsEndpoints.NA_PROD.toString();
        this.marketplaceID = user.amazon.getMarketplaceID();
        clientSetup();
        
    }
    
     public void clientSetup(){
       
        MarketplaceWebServiceConfig config  = new MarketplaceWebServiceConfig();
        config.setServiceURL(endpoint);
                client = new MarketplaceWebServiceClient(awsAccessKeyID, awsSecretKeyID,appName,appVersion,config);
    }
     
     
     /**
    * Reports that return CSV files are saved here in the order they are requested
    * That is, a method that is called and creates a report - once completed sets this value
    * the next method resets it and so forth so this will always only have the last report generated by this class.
    */
     public void setCSV(String csv)
     {
         lastCSV = csv;
     }
     
     
    public String getFullInventoryList(){
        
      return  requestReport(AmazonReportType.Open_Listings_Report);
    }
    
    public String getMerchantlistings(){
        return requestReport(AmazonReportType.Merchant_Listings_Lite_Report);
    }
     
     public String getCSV()
     {
         return lastCSV;
     }
   
}
