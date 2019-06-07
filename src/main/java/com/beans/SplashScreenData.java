package com.beans;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "splashurl", "splashversion", "logourl", "logoversion" })
public class SplashScreenData {

	private String splashurl;
	private String splashversion;
	private String logourl;
	private String logoversion;

	public SplashScreenData() {
	}

	public SplashScreenData(String splashurl, String splashversion, String logourl, String logoversion) {
		this.splashurl = splashurl;
		this.splashversion = splashversion;
		this.logourl = logourl;
		this.logoversion = logoversion;
	}

	public String getSplashurl() {
		return splashurl;
	}

	public void setSplashurl(String splashurl) {
		this.splashurl = splashurl;
	}

	public String getSplashversion() {
		return splashversion;
	}

	public void setSplashversion(String splashversion) {
		this.splashversion = splashversion;
	}

	public String getLogourl() {
		return logourl;
	}

	public void setLogourl(String logourl) {
		this.logourl = logourl;
	}

	public String getLogoversion() {
		return logoversion;
	}

	public void setLogoversion(String logoversion) {
		this.logoversion = logoversion;
	}

}
