/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

/**
 *
 * @author Rajat.kumar
 */
public class SubscribedPackage {

    private int packageId;
    private String packageName;
    private String packagePrice;
    private String packageValidityPeriod;
    private String packageStartDate;
    private String packageEndDate;
    private String androidToken;
    private String iosToken;
    private boolean optScreenVisibility;
    public SubscribedPackage(int packageId, String packageName, String packagePrice, String packageValidityPeriod, String packageStartDate, String packageEndDate, String androidToken, String iosToken,boolean optScreenVisibility) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packagePrice = packagePrice;
        this.packageValidityPeriod = packageValidityPeriod;
        this.packageStartDate = packageStartDate;
        this.packageEndDate = packageEndDate;
        this.androidToken = androidToken;
        this.iosToken = iosToken;
        this.optScreenVisibility=optScreenVisibility;
    }

    public boolean isOptScreenVisibility() {
		return optScreenVisibility;
	}
	public void setOptScreenVisibility(boolean optScreenVisibility) {
		this.optScreenVisibility = optScreenVisibility;
	}


	public int getPackageId() {
        return packageId;
    }

    public void setPackageId(int packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageValidityPeriod() {
        return packageValidityPeriod;
    }

    public void setPackageValidityPeriod(String packageValidityPeriod) {
        this.packageValidityPeriod = packageValidityPeriod;
    }

    public String getPackageStartDate() {
        if (packageStartDate == null) {
            return "";
        } else if (packageStartDate.equalsIgnoreCase("null")) {
            return "";
        } else if (packageStartDate.equalsIgnoreCase("(null(")) {
            return "";
        } else {
            return packageStartDate;
        }
    }

    public void setPackageStartDate(String packageStartDate) {
        this.packageStartDate = packageStartDate;
    }

    public String getPackageEndDate() {
        if (packageEndDate == null) {
            return "";
        } else if (packageEndDate.equalsIgnoreCase("null")) {
            return "";
        } else if (packageEndDate.equalsIgnoreCase("(null(")) {
            return "";
        } else {
            return packageEndDate;
        }
    }

    public void setPackageEndDate(String packageEndDate) {
        this.packageEndDate = packageEndDate;
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }

    public String getIosToken() {
        return iosToken;
    }

    public void setIosToken(String iosToken) {
        this.iosToken = iosToken;
    }

}
