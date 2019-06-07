package com.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "ad_id", "ad_type", "banner_url", "banner_type", "redirect_url", "resource_id" })
@XmlRootElement(name = "root")
public class AdvertisementData {
	private int ad_id;
	private String ad_type;
	private String banner_url;
	private String banner_type;
	private String redirect_url;
	private int resource_id;
	private String resource_title;
	private String resource_img;

	public AdvertisementData() {
	}

	public String getResource_title() {
		return resource_title;
	}

	public AdvertisementData(int ad_id, String ad_type, String banner_url, String banner_type, String redirect_url,
			int resource_id, String resource_title, String resource_img) {
		this.ad_id = ad_id;
		this.ad_type = ad_type;
		this.banner_url = banner_url;
		this.banner_type = banner_type;
		this.redirect_url = redirect_url;
		this.resource_id = resource_id;
		this.resource_title = resource_title;
		this.resource_img = resource_img;
	}

	public void setResource_title(String resource_title) {
		this.resource_title = resource_title;
	}

	public String getResource_img() {
		return resource_img;
	}

	public void setResource_img(String resource_img) {
		this.resource_img = resource_img;
	}

	public int getAd_id() {
		return ad_id;
	}

	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_type() {
		return ad_type;
	}

	public void setAd_type(String ad_type) {
		this.ad_type = ad_type;
	}

	public String getBanner_url() {
		return banner_url;
	}

	public void setBanner_url(String banner_url) {
		this.banner_url = banner_url;
	}

	public String getBanner_type() {
		return banner_type;
	}

	public void setBanner_type(String banner_type) {
		this.banner_type = banner_type;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public void setRedirect_url(String redirect_url) {
		this.redirect_url = redirect_url;
	}

	public int getResource_id() {
		return resource_id;
	}

	public void setResource_id(int resource_id) {
		this.resource_id = resource_id;
	}

}
