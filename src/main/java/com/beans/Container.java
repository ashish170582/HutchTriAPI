package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Container", propOrder = { "containers" })
@XmlRootElement(name = "root")
public class Container extends Root {

	private List<ContainerBean> containerList;

	public Container() {
		super(0, "Success");
	}

	public Container(List<ContainerBean> containerList) {
		super(0, "Success");
		this.containerList = containerList;
	}

	public List<ContainerBean> getContainerList() {
		return containerList;
	}

	public void setContainerList(List<ContainerBean> containerList) {
		this.containerList = containerList;
	}

}
