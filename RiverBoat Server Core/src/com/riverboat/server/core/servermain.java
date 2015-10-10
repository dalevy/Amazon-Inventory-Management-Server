package com.riverboat.server.core;

import com.riverboat.server.manager.RiverBoatServerManager;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dlevy
 */
public class servermain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RiverBoatServerManager manager = new RiverBoatServerManager();
            manager.start();
    }
    
}
