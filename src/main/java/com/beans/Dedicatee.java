package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DedicateeList", propOrder = { "dedicatee" })

@XmlRootElement(name = "root")
public class Dedicatee extends Root {
	private List<DedicateeData> dedicatee;

	public Dedicatee() {
	}

	public Dedicatee(List<DedicateeData> dedicatee) {
		super(0, "Success");
		this.dedicatee = dedicatee;
	}

	public List<DedicateeData> getDedicatee() {
		return dedicatee;
	}

	public void setDedicatee(List<DedicateeData> dedicatee) {
		this.dedicatee = dedicatee;
	}

}
