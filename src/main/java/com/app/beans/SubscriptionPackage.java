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
public class SubscriptionPackage {

    private int packageId;
    private String packageName;
    private String packageDescription;
    private String priceInfo;
    private String subscriptionAmount;    
    private String renewalAmount;
    private String packageValidityPeriod;
    private int packageValidityInDays;
    private String currencySign;
    private String redirectUrl;
    //List<PaymentMethod> paymentMethodList;

    public SubscriptionPackage() {
    }

    public SubscriptionPackage(int packageId, String packageName, String packageDescription,String priceInfo, String subscriptionAmount, String renewalAmount, String packageValidityPeriod, int packageValidityInDays, String currencySign,String redirectUrl ) {
        this.packageId = packageId;
        this.packageName = packageName;
        this.packageDescription = packageDescription;
        this.priceInfo=priceInfo;
        this.subscriptionAmount = subscriptionAmount;
        this.renewalAmount = renewalAmount;
        this.packageValidityPeriod = packageValidityPeriod;
        this.packageValidityInDays = packageValidityInDays;
        this.currencySign = currencySign;
        this.redirectUrl=redirectUrl;
     //   this.paymentMethodList=paymentMethodList;
    }

    public String getRedirectUrl() {
		return redirectUrl;
	}
    public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
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

    public String getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(String subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
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
