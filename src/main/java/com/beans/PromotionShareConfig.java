/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
public class PromotionShareConfig extends Root  {
    private int id;
    private String bannerUrl;
    private String title;
    private String msg;
    private String tncTitle;
    private String tncMessage;
    private String shareMessage;

    public PromotionShareConfig() {
    	super(0, "Success");
    }

	public PromotionShareConfig(int id, String bannerUrl, String title,
			String msg, String tncTitle, String tncMessage, String shareMessage) {
		super(0, "Success");
		this.id = id;
		this.bannerUrl = bannerUrl;
		this.title = title;
		this.msg = msg;
		this.tncTitle = tncTitle;
		this.tncMessage = tncMessage;
		this.shareMessage = shareMessage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getTncTitle() {
		return tncTitle;
	}

	public void setTncTitle(String tncTitle) {
		this.tncTitle = tncTitle;
	}

	public String getTncMessage() {
		return tncMessage;
	}

	public void setTncMessage(String tncMessage) {
		this.tncMessage = tncMessage;
	}

	public String getShareMessage() {
		return shareMessage;
	}

	public void setShareMessage(String shareMessage) {
		this.shareMessage = shareMessage;
	}

	@Override
	public String toString() {
		return "PromotionShareConfig [id=" + id + ", bannerUrl=" + bannerUrl
				+ ", title=" + title + ", msg=" + msg + ", tncTitle="
				+ tncTitle + ", tncMessage=" + tncMessage + ", shareMessage="
				+ shareMessage + "]";
	}

}
