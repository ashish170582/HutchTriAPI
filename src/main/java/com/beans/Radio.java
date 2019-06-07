package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FeaturedPlaylist", propOrder = { "featuredPlaylist" })

@XmlRootElement(name = "root")
public class Radio extends Root {
	private int resultCount;
	private List<RadioBean> radio;

	public Radio() {
	}

	public Radio(List<RadioBean> radio) {
		super(0, "Success");
		this.resultCount = radio.size();
		this.radio = radio;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<RadioBean> getRadio() {
		return radio;
	}

	public void setRadio(List<RadioBean> radio) {
		this.radio = radio;
	}

}
