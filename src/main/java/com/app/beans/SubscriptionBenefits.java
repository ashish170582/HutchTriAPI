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
public class SubscriptionBenefits {

    private String casualGeneralLine;
    private String liteGeneralLine;
    private String premiumGeneralLine;
    private String benefitsHeading;    
    private String benefits;
    private String pageHeader;
    private String packHeader;
    private String benefitHeader;
    public SubscriptionBenefits() {
    }


    public SubscriptionBenefits(String casualGeneralLine,
			String liteGeneralLine, String premiumGeneralLine,
			String benefitsHeading, String benefits, String pageHeader,
			String packHeader, String benefitHeader) {
		super();
		this.casualGeneralLine = casualGeneralLine;
		this.liteGeneralLine = liteGeneralLine;
		this.premiumGeneralLine = premiumGeneralLine;
		this.benefitsHeading = benefitsHeading;
		this.benefits = benefits;
		this.pageHeader = pageHeader;
		this.packHeader = packHeader;
		this.benefitHeader = benefitHeader;
	}


	public String getCasualGeneralLine() {
        return casualGeneralLine;
    }

    public String getPageHeader() {
		return pageHeader;
	}


	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}


	public String getPackHeader() {
		return packHeader;
	}


	public void setPackHeader(String packHeader) {
		this.packHeader = packHeader;
	}


	public String getBenefitHeader() {
		return benefitHeader;
	}


	public void setBenefitHeader(String benefitHeader) {
		this.benefitHeader = benefitHeader;
	}


	public void setCasualGeneralLine(String casualGeneralLine) {
        this.casualGeneralLine = casualGeneralLine;
    }

    public String getLiteGeneralLine() {
        return liteGeneralLine;
    }

    public void setLiteGeneralLine(String liteGeneralLine) {
        this.liteGeneralLine = liteGeneralLine;
    }

    public String getPremiumGeneralLine() {
        return premiumGeneralLine;
    }

    public void setPremiumGeneralLine(String premiumGeneralLine) {
        this.premiumGeneralLine = premiumGeneralLine;
    }
   

    public String getBenefitsHeading() {
        return benefitsHeading;
    }

    public void setBenefitsHeading(String benefitsHeading) {
        this.benefitsHeading = benefitsHeading;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }
}
