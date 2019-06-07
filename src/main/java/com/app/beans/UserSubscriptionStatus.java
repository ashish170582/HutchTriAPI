/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

/**
 *
 * @author ashish.vishwakarma
 */
public class UserSubscriptionStatus {
    
    private int status;
    private String statusMessage;
//    private String title;
//    private String leftMenueTitle;
//    private int alertType;
//    private String button;
//    private String alertAction;

    public UserSubscriptionStatus() {
    }
    
    
     public UserSubscriptionStatus(int status, String statusMessage ) {
        this.status = status;
        this.statusMessage = statusMessage;
      
    }
    
    
    
    
    

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    
}
