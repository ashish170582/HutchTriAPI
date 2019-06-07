/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class PurchaseList extends Root {
    List<Purchase> purchase;

    public PurchaseList() {
        super(0, "Success");
    }
    
    public PurchaseList(List<Purchase> purchase) {
        super(0, "Success");
        this.purchase = purchase;
    }

    public List<Purchase> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<Purchase> purchase) {
        this.purchase = purchase;
    }

  

}
