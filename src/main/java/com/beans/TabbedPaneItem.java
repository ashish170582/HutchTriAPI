package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TabbedPaneBean", propOrder = { "tabs" })
@XmlRootElement(name = "root")
public class TabbedPaneItem extends Root {

	private List<TabbedPaneItemBean> tabItems;

	public TabbedPaneItem() {
		super(0, "Success");
	}

	public TabbedPaneItem(List<TabbedPaneItemBean> tabItems) {
		super(0, "Success");
		this.tabItems = tabItems;
	}

	public List<TabbedPaneItemBean> getTabItems() {
		return tabItems;
	}

	public void setTabItems(List<TabbedPaneItemBean> tabItems) {
		this.tabItems = tabItems;
	}
}
