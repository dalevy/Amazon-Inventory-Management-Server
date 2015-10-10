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
public class AmazonTaxReport {
    
    private String Order_ID="";
    private String Order_Date="";
    private String Shipment_ID="";
    private String Shipment_Date="";
    private String Tax_Calculated_Date="";
    private String Posted_Date="";
    private String Marketplace="";
    private String Merchant_ID="";
    private String Fulfillment="";
    private String ASIN="";
    private String SKU="";
    private String Transaction_Type="";
    private String Product_Tax_Code="";
    private String Quantity="";
    private String Currency="";
    private String Display_Price="";
    private String TaxExclusive_Selling_Price="";
    private String Total_Tax="";
    private String Ship_From_City="";
    private String Ship_From_State="";
    private String Ship_From_Country="";
    private String Ship_From_Postal_Code="";
    private String Ship_From_Tax_Location_Code="";
    private String Ship_To_City="";
    private String Ship_To_State="";
    private String Ship_To_Country="";
    private String Ship_To_Postal_Code="";
    private String Ship_To_Location_Code="";
    private String Taxed_Location_Code="";
    private String Tax_Address_Role="";
    private String Jurisdiction_Level="";
    private String Jurisdiction_Name="";
    private String Tax_Amount="";
    private String Taxed_Jurisdiction_Tax_Rate="";
    private String Tax_Type="";
    private String Tax_Calculation_Reason_Code="";
    private String NonTaxable_Amount="";
    private String Taxable_Amount="";
    
    private String csv;
    private String output;
    
    public AmazonTaxReport(){
        
        
    }
    
    
     
	public String getOrder_ID() {
		return Order_ID;
	}
	public void setOrder_ID(String order_ID) {
		Order_ID = order_ID;
	}
	public String getOrder_Date() {
		return Order_Date;
	}
	public void setOrder_Date(String order_Date) {
		Order_Date = order_Date;
	}
	public String getShipment_ID() {
		return Shipment_ID;
	}
	public void setShipment_ID(String shipment_ID) {
		Shipment_ID = shipment_ID;
	}
	public String getShipment_Date() {
		return Shipment_Date;
	}
	public void setShipment_Date(String shipment_Date) {
		Shipment_Date = shipment_Date;
	}
	public String getTax_Calculated_Date() {
		return Tax_Calculated_Date;
	}
	public void setTax_Calculated_Date(String tax_Calculated_Date) {
		Tax_Calculated_Date = tax_Calculated_Date;
	}
	public String getPosted_Date() {
		return Posted_Date;
	}
	public void setPosted_Date(String posted_Date) {
		Posted_Date = posted_Date;
	}
	public String getMarketplace() {
		return Marketplace;
	}
	public void setMarketplace(String marketplace) {
		Marketplace = marketplace;
	}
	public String getMerchant_ID() {
		return Merchant_ID;
	}
	public void setMerchant_ID(String merchant_ID) {
		Merchant_ID = merchant_ID;
	}
	public String getFulfillment() {
		return Fulfillment;
	}
	public void setFulfillment(String fulfillment) {
		Fulfillment = fulfillment;
	}
	public String getASIN() {
		return ASIN;
	}
	public void setASIN(String aSIN) {
		ASIN = aSIN;
	}
	public String getSKU() {
		return SKU;
	}
	public void setSKU(String sKU) {
		SKU = sKU;
	}
	public String getTransaction_Type() {
		return Transaction_Type;
	}
	public void setTransaction_Type(String transaction_Type) {
		Transaction_Type = transaction_Type;
	}
	public String getProduct_Tax_Code() {
		return Product_Tax_Code;
	}
	public void setProduct_Tax_Code(String product_Tax_Code) {
		Product_Tax_Code = product_Tax_Code;
	}
	public String getQuantity() {
		return Quantity;
	}
	public void setQuantity(String quantity) {
		Quantity = quantity;
	}
	public String getCurrency() {
		return Currency;
	}
	public void setCurrency(String currency) {
		Currency = currency;
	}
	public String getDisplay_Price() {
		return Display_Price;
	}
	public void setDisplay_Price(String display_Price) {
		Display_Price = display_Price;
	}
	public String getTaxExclusive_Selling_Price() {
		return TaxExclusive_Selling_Price;
	}
	public void setTaxExclusive_Selling_Price(String taxExclusive_Selling_Price) {
		TaxExclusive_Selling_Price = taxExclusive_Selling_Price;
	}
	public String getTotal_Tax() {
		return Total_Tax;
	}
	public void setTotal_Tax(String total_Tax) {
		Total_Tax = total_Tax;
	}
	public String getShip_From_City() {
		return Ship_From_City;
	}
	public void setShip_From_City(String ship_From_City) {
		Ship_From_City = ship_From_City;
	}
	public String getShip_From_State() {
		return Ship_From_State;
	}
	public void setShip_From_State(String ship_From_State) {
		Ship_From_State = ship_From_State;
	}
	public String getShip_From_Country() {
		return Ship_From_Country;
	}
	public void setShip_From_Country(String ship_From_Country) {
		Ship_From_Country = ship_From_Country;
	}
	public String getShip_From_Postal_Code() {
		return Ship_From_Postal_Code;
	}
	public void setShip_From_Postal_Code(String ship_From_Postal_Code) {
		Ship_From_Postal_Code = ship_From_Postal_Code;
	}
	public String getShip_From_Tax_Location_Code() {
		return Ship_From_Tax_Location_Code;
	}
	public void setShip_From_Tax_Location_Code(String ship_From_Tax_Location_Code) {
		Ship_From_Tax_Location_Code = ship_From_Tax_Location_Code;
	}
	public String getShip_To_City() {
		return Ship_To_City;
	}
	public void setShip_To_City(String ship_To_City) {
		Ship_To_City = ship_To_City;
	}
	public String getShip_To_State() {
		return Ship_To_State;
	}
	public void setShip_To_State(String ship_To_State) {
		Ship_To_State = ship_To_State;
	}
	public String getShip_To_Country() {
		return Ship_To_Country;
	}
	public void setShip_To_Country(String ship_To_Country) {
		Ship_To_Country = ship_To_Country;
	}
	public String getShip_To_Postal_Code() {
		return Ship_To_Postal_Code;
	}
	public void setShip_To_Postal_Code(String ship_To_Postal_Code) {
		Ship_To_Postal_Code = ship_To_Postal_Code;
	}
	public String getShip_To_Location_Code() {
		return Ship_To_Location_Code;
	}
	public void setShip_To_Location_Code(String ship_To_Location_Code) {
		Ship_To_Location_Code = ship_To_Location_Code;
	}
	public String getTaxed_Location_Code() {
		return Taxed_Location_Code;
	}
	public void setTaxed_Location_Code(String taxed_Location_Code) {
		Taxed_Location_Code = taxed_Location_Code;
	}
	public String getTax_Address_Role() {
		return Tax_Address_Role;
	}
	public void setTax_Address_Role(String tax_Address_Role) {
		Tax_Address_Role = tax_Address_Role;
	}
	public String getJurisdiction_Level() {
		return Jurisdiction_Level;
	}
	public void setJurisdiction_Level(String jurisdiction_Level) {
		Jurisdiction_Level = jurisdiction_Level;
	}
	public String getJurisdiction_Name() {
		return Jurisdiction_Name;
	}
	public void setJurisdiction_Name(String jurisdiction_Name) {
		Jurisdiction_Name = jurisdiction_Name;
	}
	public String getTax_Amount() {
		return Tax_Amount;
	}
	public void setTax_Amount(String tax_Amount) {
		Tax_Amount = tax_Amount;
	}
	public String getTaxed_Jurisdiction_Tax_Rate() {
		return Taxed_Jurisdiction_Tax_Rate;
	}
	public void setTaxed_Jurisdiction_Tax_Rate(String taxed_Jurisdiction_Tax_Rate) {
		Taxed_Jurisdiction_Tax_Rate = taxed_Jurisdiction_Tax_Rate;
	}
	public String getTax_Type() {
		return Tax_Type;
	}
	public void setTax_Type(String tax_Type) {
		Tax_Type = tax_Type;
	}
	public String getTax_Calculation_Reason_Code() {
		return Tax_Calculation_Reason_Code;
	}
	public void setTax_Calculation_Reason_Code(String tax_Calculation_Reason_Code) {
		Tax_Calculation_Reason_Code = tax_Calculation_Reason_Code;
	}
	public String getNonTaxable_Amount() {
		return NonTaxable_Amount;
	}
	public void setNonTaxable_Amount(String nonTaxable_Amount) {
		NonTaxable_Amount = nonTaxable_Amount;
	}
	public String getTaxable_Amount() {
		return Taxable_Amount;
	}
	public void setTaxable_Amount(String taxable_Amount) {
		Taxable_Amount = taxable_Amount;
	}
}
