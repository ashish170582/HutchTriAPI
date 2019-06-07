package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@XmlType(name = "ContainerBean", propOrder = { "containers" })
@XmlRootElement(name = "root")
@JsonInclude(Include.NON_NULL)
public class AllItem extends Root {

	private List<ContainerItemBean> allItemList;

	public AllItem() {
		super(0, "Success");
	}

	public AllItem(int code, String message, List<ContainerItemBean> allItemList) {
		super(code, message);
		this.allItemList = allItemList;
	}

	public AllItem(List<ContainerItemBean> allItemList) {
		super(0, "Success");
		this.allItemList = allItemList;
	}

	public List<ContainerItemBean> getAllItemList() {
		return allItemList;
	}

	public void setAllItemList(List<ContainerItemBean> allItemList) {
		this.allItemList = allItemList;
	}

}
