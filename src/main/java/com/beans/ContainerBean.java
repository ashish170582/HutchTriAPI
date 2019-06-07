/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class ContainerBean {
	private String containerId;
	private String containerSequenceId;
	private String containerTypeId;
	private String containerTitle;
	private String containerSeeAll;
	private String containerItemListTypeId;
	private List<ContainerItemBean> containerItemList;

	public ContainerBean() {
	}

	public ContainerBean(int containerId, int containerSequenceId, int containerTypeId, String containerTitle,
			int containerSeeAll, int containerItemListTypeId, List<ContainerItemBean> containerItemList) {
		this.containerId = String.valueOf(containerId);
		this.containerSequenceId = String.valueOf(containerSequenceId);
		this.containerTypeId = String.valueOf(containerTypeId);
		this.containerTitle = containerTitle;
		this.containerSeeAll = String.valueOf(containerSeeAll);
		this.containerItemListTypeId = String.valueOf(containerItemListTypeId);
		this.containerItemList = containerItemList;
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(int containerId) {
		this.containerId = String.valueOf(containerId);
	}

	public String getContainerSequenceId() {
		return String.valueOf(containerSequenceId);
	}

	public void setContainerSequenceId(int containerSequenceId) {
		this.containerSequenceId = String.valueOf(containerSequenceId);
	}

	public String getContainerTypeId() {
		return containerTypeId;
	}

	public void setContainerTypeId(int containerTypeId) {
		this.containerTypeId = String.valueOf(containerTypeId);
	}

	public String getContainerTitle() {
		return containerTitle;
	}

	public void setContainerTitle(String containerTitle) {
		this.containerTitle = containerTitle;
	}

	public String getContainerSeeAll() {
		return containerSeeAll;
	}

	public void setContainerSeeAll(int containerSeeAll) {
		this.containerSeeAll = String.valueOf(containerSeeAll);
	}

	public String getContainerItemListTypeId() {
		return containerItemListTypeId;
	}

	public void setContainerItemListTypeId(int containerItemListTypeId) {
		this.containerItemListTypeId = String.valueOf(containerItemListTypeId);
	}

	public List<ContainerItemBean> getContainerItemList() {
		return containerItemList;
	}

	public void setContainerItemList(List<ContainerItemBean> containerItemList) {
		this.containerItemList = containerItemList;
	}
}
