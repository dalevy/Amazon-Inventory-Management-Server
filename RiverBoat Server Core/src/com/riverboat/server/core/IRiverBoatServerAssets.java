/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.core;

/**
 *
 * @author D.Levy
 */
public interface IRiverBoatServerAssets {
    
    
    /**
     * All classes requesting access to global assets created by the application must implement this interface
     * @param ass 
     */
    public void setAssets(RiverBoatServerAssets ass);
}
