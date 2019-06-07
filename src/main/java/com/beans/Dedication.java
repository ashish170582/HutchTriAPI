package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Dedication", propOrder = { "dedication" })

@XmlRootElement(name = "root")
public class Dedication extends Root {
	private List<DedicationData> dedication;

	public Dedication() {
		super(0, "Success");
	}

	public Dedication(int code, String msg, List<DedicationData> dedication) {
		super(code, msg);
		this.dedication = dedication;
	}

	public List<DedicationData> getDedication() {
		return dedication;
	}

	public void setDedication(List<DedicationData> dedication) {
		this.dedication = dedication;
	}
}
