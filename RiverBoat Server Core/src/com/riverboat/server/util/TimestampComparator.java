/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Compares two time stamp objects and generates difference values for their 
 * respective times.
 * @author DMita Levy
 */
public class TimestampComparator {
    
    //Timestamps
    Timestamp current;
    Timestamp task;
    
    //Conversion class used to add and subtract times
    LocalDateTime currentDateTime;
    LocalDateTime currentTaskTime;
    
    private int days;
    private int months;
    private int years;
    private int minutes;
    private int weeks;
    
    /**
     * Compares two timestamps to determine when the last update 
     * @param taskTime
     * @return 
     */
    public boolean compareTime(Timestamp taskTime)
    {
        //Assumes the tasks time stamp that is passed to the server is within range
        //Within range meaning the year of the tasks timestamp is not greater than the current year
        boolean withinRange = true;
        
        task = taskTime;
        
        //Get the current time for this application;
        current = new Timestamp(System.currentTimeMillis());
        
        //turn the current time into Date Time
         currentDateTime = current.toLocalDateTime();
        //turn the task time into Date Time
         currentTaskTime = task.toLocalDateTime();
         
         //Find the difference in days, months, weeks, and years of the current time and the task time
         setDays(currentDateTime.getDayOfMonth() - currentTaskTime.getDayOfMonth());
         setMonths(currentDateTime.getMonthValue() - currentTaskTime.getMonthValue());
         setYears(currentDateTime.getYear() - currentTaskTime.getYear());
         setMinutes(currentDateTime.getMinute() - currentTaskTime.getMinute());
         
         
         //Verify this task is not a year old
         if(currentDateTime.getYear() - currentTaskTime.getYear() != 0)
             withinRange = false;
         
         return withinRange;
         
    }

    /**
     * @return the days
     */
    public int getDays() {
        return days;
    }

    /**
     * @param days the days to set
     */
    public void setDays(int days) {
        this.days = days;
    }

    /**
     * @return the months
     */
    public int getMonths() {
        return months;
    }

    /**
     * @param months the months to set
     */
    public void setMonths(int months) {
        this.months = months;
    }

    /**
     * @return the years
     */
    public int getYears() {
        return years;
    }

    /**
     * @param years the years to set
     */
    public void setYears(int years) {
        this.years = years;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param minutes the minutes to set
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * @return the weeks
     */
    public int getWeeks() {
        return weeks;
    }

    /**
     * @param weeks the weeks to set
     */
    public void setWeeks(int weeks) {
        this.weeks = weeks;
    }

}
