package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GenreList", propOrder = { "mobileoperators" })

@XmlRootElement(name = "root")
public class MobileOperator extends Root {
	private List<MobileOperatorData> mobileoperators;

	public MobileOperator() {
	}

	public MobileOperator(List<MobileOperatorData> mobileoperators) {
		super(0, "Success");
		this.mobileoperators = mobileoperators;
	}

	public List<MobileOperatorData> getMobileoperators() {
		return mobileoperators;
	}

	public void setMobileoperators(List<MobileOperatorData> mobileoperators) {
		this.mobileoperators = mobileoperators;
	}

}
