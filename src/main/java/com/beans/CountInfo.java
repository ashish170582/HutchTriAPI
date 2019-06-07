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

@XmlType(name = "countinfo", propOrder = { "countinfo" })

@XmlRootElement(name = "root")
public class CountInfo extends Root {
	private CountInfoData countinfo;

	public CountInfo() {
		super(0, "Success");
	}

	public CountInfo(CountInfoData countinfo) {
		super(0, "Success");
		this.countinfo = countinfo;
	}

	public CountInfoData getCountinfo() {
		return countinfo;
	}

	public void setCountinfo(CountInfoData countinfo) {
		this.countinfo = countinfo;
	}

}
