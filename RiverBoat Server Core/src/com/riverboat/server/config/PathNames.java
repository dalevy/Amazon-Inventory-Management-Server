/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.riverboat.server.config;

/**
 *
 * @author D.Levy
 * 5/2/2014
 * This class has file path names for loading/locating different views;
 * as well other configuration constants necessary to run the server application
 * 
 */
public interface PathNames {

    
    String APP_VERSION ="Server .01";
    String APP_NAME ="Riverboat";
    
    
    //Path to all views
    String ROOT_VIEW = "com/riverboat/view/RootView.fxml";
    String ServerDashBoardView = "com/riverboat/server/view/ServerDashBoardView.fxml";
    String ManualUpdateView = "com/riverboat/server/view/ManualUpdateView.fxml";
    String TaskListView = "com/riverboat/server/view/TaskListView.fxml";
    
    //Database Info
    String DB_URL = "";
    String DB_NAME = "";
    String DB_SERVERTASKLIST = "";
    String DB_AMAZONCUSTOMERS = "amazoncustomers";
    String DB_CUSTOERS = "customers";
    String USERNAME = "";
    String PASSWORD = "!";
    
    //Riverboat Servlets
    String LOGIN_SERVLET = "";
}
