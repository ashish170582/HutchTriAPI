package com.app.beans;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.beans.Root;
import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class SubscriptionPackageList extends Root {
    int subscriptionTrialStatus;
    List<SubscriptionPackage> subscriptionPackage;
    SubscriptionBenefits subscriptionBenefits;

    public SubscriptionPackageList() {
        super(0, "Success");
    }
    
    public SubscriptionPackageList(List<SubscriptionPackage> subscriptionPackage,int subscriptionTrialStatus,SubscriptionBenefits subscriptionBenefits) {
        super(0, "Success");
        this.subscriptionPackage = subscriptionPackage;
        this.subscriptionTrialStatus=subscriptionTrialStatus;
        this.subscriptionBenefits = subscriptionBenefits;
    }


    
    public SubscriptionBenefits getSubscriptionBenefits() {
		return subscriptionBenefits;
	}


	public void setSubscriptionBenefits(
			SubscriptionBenefits subscriptionBenefits) {
		this.subscriptionBenefits = subscriptionBenefits;
	}
    public List<SubscriptionPackage> getSubscriptionPackage() {
        return subscriptionPackage;
    }

    public void setSubscriptionPackage(List<SubscriptionPackage> subscriptionPackage) {
        this.subscriptionPackage = subscriptionPackage;
    } 

    public int getSubscriptionTrialStatus() {
        return subscriptionTrialStatus;
    }

    public void setSubscriptionTrialStatus(int subscriptionTrialStatus) {
        this.subscriptionTrialStatus = subscriptionTrialStatus;
    }
    
}
