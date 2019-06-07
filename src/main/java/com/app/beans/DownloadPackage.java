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
public class DownloadPackage {

    private int packageId;
    private String packageName;
    private String packageDescription;
    private String priceInfo;
    private String downloadAmount;    
    private String renewalAmount;
    private String packageValidityPeriod;
    private int packageValidityInDays;
    private String currencySign;
    

    public DownloadPackage() {
    }

    public DownloadPackage(int packageId, String packageName, String packageDescription,String priceInfo, String downloadAmount, String renewalAmount, String packageValidityPeriod, int packageValidityInDays, String currencySign) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageDescription = packageDescription;
        this.priceInfo=priceInfo;
        this.downloadAmount = downloadAmount;
        this.renewalAmount = renewalAmount;
        this.packageValidityPeriod = packageValidityPeriod;
        this.packageValidityInDays = packageValidityInDays;
        this.currencySign = currencySign;
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

    public String getPackageDescription() {
        return packageDescription;
    }

    public void setPackageDescription(String packageDescription) {
        this.packageDescription = packageDescription;
    }

    public String getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(String priceInfo) {
        this.priceInfo = priceInfo;
    }

    public String getDownloadAmount() {
        return downloadAmount;
    }

    public void setDownloadAmount(String downloadAmount) {
        this.downloadAmount = downloadAmount;
    }
    
    public String getRenewalAmount() {
        return renewalAmount;
    }

    public void setRenewalAmount(String renewalAmount) {
        this.renewalAmount = renewalAmount;
    }

    public String getPackageValidityPeriod() {
        return packageValidityPeriod;
    }

    public void setPackageValidityPeriod(String packageValidityPeriod) {
        this.packageValidityPeriod = packageValidityPeriod;
    }

    public int getPackageValidityInDays() {
        return packageValidityInDays;
    }

    public void setPackageValidityInDays(int packageValidityInDays) {
        this.packageValidityInDays = packageValidityInDays;
    }

    public String getCurrencySign() {
        return currencySign;
    }

    public void setCurrencySign(String currencySign) {
        this.currencySign = currencySign;
    }  

}
