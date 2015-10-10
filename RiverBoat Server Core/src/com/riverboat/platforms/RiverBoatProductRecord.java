/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.platforms;

import com.riverboat.amazon.products.AmazonProductsXMLReport;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.TableCell;

/**
 * 
/**Holds a single product record from any of the marketplaces
 * @author DMita Levy
 */
public class RiverBoatProductRecord {
    
    private AmazonProductsXMLReport amazonRecord;
    private PlatformType platform;
    
    //For display purposes inside of Tablevie columns expecting user callback options
    private TableCell object;
    
    /**The first scanned item shall determine the number of duplicates
    /*For instance if this is the first scanned item and a subsequent is scanned
    *the list will retrieve the original (this), update its count, and use the count to
    *generate a new key for the next scanned item ex.  Item xxxxxx, xxxxx-1 xxxxx-2**/
    private int duplicates =0;
    
    private int count = 1;
    
    private String record_code;
    
    
    //These fields are created for the purpose of exposing them to the calling View element
    private String platform_id;
    private String product_code;
    private String platform_name;
    private String product_id;
    private String product_name;
    private String product_description;
    private String product_manufacturer;
    private String product_quantity;
    private String product_thumbnail;
    private BooleanProperty selected;
    
    public RiverBoatProductRecord(AmazonProductsXMLReport product)
    {
        amazonRecord = product;
        platform = PlatformType.AMAZON;
        platform_name = "Amazon";
        product_id = product.ASIN;
        product_name = product.Title;
        product_description = product.Feature;
        product_manufacturer = product.Manufacturer;
        product_quantity = String.valueOf(count);
        product_thumbnail = product.URL;
        selected = new SimpleBooleanProperty(true);
        
    }
    
    public RiverBoatProductRecord(AmazonProductsXMLReport product, String productCode)
    {
        product_code = productCode;
        amazonRecord = product;
        platform = PlatformType.AMAZON;
        platform_name = "Amazon";
        product_id = product.ASIN;
        product_name = product.Title;
        product_description = product.Feature;
        product_manufacturer = product.Manufacturer;
        product_thumbnail = product.URL;
        product_quantity = String.valueOf(count);

    }
    
    

    /**
     * @return the amazonRecord
     */
    public AmazonProductsXMLReport getAmazonRecord() {
        return amazonRecord;
    }

    /**
     * @param amazonRecord the amazonRecord to set
     */
    public void setAmazonRecord(AmazonProductsXMLReport amazonRecord) {
        this.amazonRecord = amazonRecord;
    }

    /**
     * @return the current
     */
    public PlatformType getPlatform() {
        return platform;
    }

    /**
     * @param current the current to set
     */
    public void setPlatform(PlatformType current) {
        this.platform = current;
    }

    /**
     * @return the platform_id
     */
    public String getPlatform_id() {
        return platform_id;
    }

    /**
     * @param platform_id the platform_id to set
     */
    public void setPlatform_id(String platform_id) {
        this.platform_id = platform_id;
    }

    /**
     * @return the platform_name
     */
    public String getPlatform_name() {
        return platform_name;
    }

    /**
     * @param platform_name the platform_name to set
     */
    public void setPlatform_name(String platform_name) {
        this.platform_name = platform_name;
    }

    /**
     * @return the product_id
     */
    public String getProduct_id() {
        return product_id;
    }

    /**
     * @param product_id the product_id to set
     */
    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    /**
     * @return the product_name
     */
    public String getProduct_name() {
        return product_name;
    }

    /**
     * @param product_name the product_name to set
     */
    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    /**
     * @return the product_description
     */
    public String getProduct_description() {
        return product_description;
    }

    /**
     * @param product_description the product_description to set
     */
    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    /**
     * @return the product_manufacturer
     */
    public String getProduct_manufacturer() {
        return product_manufacturer;
    }

    /**
     * @param product_manufacturer the product_manufacturer to set
     */
    public void setProduct_manufacturer(String product_manufacturer) {
        this.product_manufacturer = product_manufacturer;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    public void increaseCount()
    {
        count++;
        product_quantity = String.valueOf(count);
    }
    
    public void increseDuplicate()
    {
        duplicates++;
    }
    
    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
        product_quantity = String.valueOf(count);

    }

    /**
     * @return the duplicates
     */
    public int getDuplicates() {
        return duplicates;
    }

    /**
     * @param duplicates the duplicates to set
     */
    public void setDuplicates(int duplicates) {
        this.duplicates = duplicates;
    }

    /**
     * @return the product_code
     */
    public String getProduct_code() {
        return product_code;
    }

    /**
     * @param product_code the product_code to set
     */
    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }
    
    public TableCell getElement()
    {
        return object;
    }
    
    public void setElement(TableCell e)
    {
        object = e;
    }
    
  

    /**
     * @return the product_quantity
     */
    public String getProduct_quantity() {
        return product_quantity;
    }

    /**
     * @param product_quantity the product_quantity to set
     */
    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    /**
     * @return the record_code
     */
    public String getRecord_code() {
        return record_code;
    }

    /**
     * @param record_code the record_code to set
     */
    public void setRecord_code(String record_code) {
        this.record_code = record_code;
    }

    /**
     * @return the selected
     */
    public BooleanProperty selectedProperty() {
        return selected;
    }
    
    public boolean isSelected()
    {
        return selected.get();
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    /**
     * @return the product_thumbnail
     */
    public String getProduct_thumbnail() {
        return product_thumbnail;
    }

    /**
     * @param product_thumbnail the product_thumbnail to set
     */
    public void setProduct_thumbnail(String product_thumbnail) {
        this.product_thumbnail = product_thumbnail;
    }

   
}
