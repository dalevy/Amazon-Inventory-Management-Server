/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.util;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Idirekt
 */
public class RiverBoatCalendar {

    public int month;
    public int day;
    public int year;
    Calendar calendar;
    
    
    public RiverBoatCalendar(){
        calendar = new GregorianCalendar();
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
    }
    
    public int getMonth(){
      return this.month;
    }
    
    public int getDay(){
      return this.day;
    }
    
    public int getYear(){
        return this.year;
    }
    
            
}
