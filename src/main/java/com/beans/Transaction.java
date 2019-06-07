/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Rajat.kumar
 */

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Transaction", propOrder = { "transaction" })

@XmlRootElement(name = "root")
public class Transaction extends Root {
	String xid;

	public Transaction() {
	}

	public Transaction(int countryid, int source, String msisdn) {
		super(0, "Success");
		this.xid = countryid + "_" + source + "_" + msisdn + "_"
				+ new SimpleDateFormat("MMddHHmmss").format(new Date());
	}

	public String getXid() {
		return xid;
	}

	public void setXid(String xid) {
		this.xid = xid;
	}

}
