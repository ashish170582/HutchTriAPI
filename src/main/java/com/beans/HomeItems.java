package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "HomeItems", propOrder = { "homeitems" })

@XmlRootElement(name = "root")
public class HomeItems extends Root {
	private List<HomeItemData> homeitems;

	public HomeItems() {
	}

	public HomeItems(List<HomeItemData> homeitems) {
		super(0, "Success");
		this.homeitems = homeitems;
	}

	public List<HomeItemData> getHomeitems() {
		return homeitems;
	}

	public void setHomeitems(List<HomeItemData> homeitems) {
		this.homeitems = homeitems;
	}

}
