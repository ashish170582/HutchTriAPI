package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TabbedPaneBean", propOrder = { "tabs" })
@XmlRootElement(name = "root")
public class TabbedPane {

	private List<TabbedPaneBean> tabs;

	public TabbedPane() {
	}

	public TabbedPane(List<TabbedPaneBean> tabs) {
		this.tabs = tabs;
	}

	public List<TabbedPaneBean> getTabs() {
		return tabs;
	}

	public void setTabs(List<TabbedPaneBean> tabs) {
		this.tabs = tabs;
	}

}
