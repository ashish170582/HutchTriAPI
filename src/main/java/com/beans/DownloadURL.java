/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Download", propOrder = { "dwldurl" })
@XmlRootElement(name = "root")
public class DownloadURL extends Root {

	private int withAd;
	private String audioAd;
	private String url;

	public DownloadURL() {

	}

	public DownloadURL(int withAd, String url) {
		this.withAd = withAd;
		this.url = url;
	}

	public int getWithAd() {
		return withAd;
	}

	public void setWithAd(int withAd) {
		this.withAd = withAd;
	}

	public String getAudioAd() {
		return audioAd;
	}

	public void setAudioAd(String audioAd) {
		this.audioAd = audioAd;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
