/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.riverboat.model;

import com.riverboat.server.util.RiverBoatCalendar;

/**
 * **********************************************************************
 * Superclass for all Sync user objects - regardless of what product they
 * subscribe to (Amazon, Ebay, Shop Crazy etc.) -- This is our customer specific info
 *
 * @author D'Mita Levy 5/2/2014
 */
public abstract class User {

    private String loginPassword;
    private String customerName;
    private String first;
    private String last;
    private String accountNumber;
    private String companyName;
    private String email;
    private String address;
    private String phone;
    public RiverBoatCalendar calendar;

    public User() {

        calendar = new RiverBoatCalendar();
    }

    public void setAccount(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String customerName) {
        this.customerName = customerName;
    }

    public void setPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setCompany(String companyName) {
        this.companyName = companyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public String getCompany() {
        return this.companyName;
    }

    public String getAccount() {
        return accountNumber;
    }

    /**
     * @return the first
     */
    public String getFirst() {
        return first;
    }

    /**
     * @param first the first to set
     */
    public void setFirst(String first) {
        this.first = first;
    }

    /**
     * @return the last
     */
    public String getLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(String last) {
        this.last = last;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

   
}
