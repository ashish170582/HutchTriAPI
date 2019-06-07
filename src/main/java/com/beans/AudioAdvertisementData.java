package com.beans;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "ad_url", "duration" })
@XmlRootElement(name = "root")
public class AudioAdvertisementData {

	private String ad_url;
	private int duration;

	public String getAd_url() {
		return ad_url;
	}

	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public AudioAdvertisementData(String ad_url, int duration) {
		this.ad_url = ad_url;
		this.duration = duration;
	}
}
