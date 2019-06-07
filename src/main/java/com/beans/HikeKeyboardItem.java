package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TabbedPaneBean", propOrder = { "tabs" })
@XmlRootElement(name = "root")
public class HikeKeyboardItem extends Root {

	private List<HikeKeyboardItemBean> items;

	public HikeKeyboardItem() {
		super(0, "Success");
	}

	public HikeKeyboardItem(List<HikeKeyboardItemBean> items) {
		super(0, "Success");
		this.items = items;
	}

	public List<HikeKeyboardItemBean> getItems() {
		return items;
	}

	public void setItems(List<HikeKeyboardItemBean> items) {
		this.items = items;
	}
}
