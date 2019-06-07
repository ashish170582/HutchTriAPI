/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

/**
 *
 * @author Rajat.kumar
 */
public class TabbedPaneItemBean {
	private int tabId;
	private int itemSequenceId;
	private int itemTypeId;
	private String resourceId;
	private String resourceTitle;
	private String resourceDescription;
	private String resourceImageUrl;
	private String bannerUrl;
	private String clickUrl;

	public TabbedPaneItemBean() {
	}

	public TabbedPaneItemBean(int tabId, int itemSequenceId, int itemTypeId, String resourceId, String resourceTitle,
			String resourceDescription, String resourceImageUrl, String bannerUrl, String clickUrl) {
		this.tabId = tabId;
		this.itemSequenceId = itemSequenceId;
		this.itemTypeId = itemTypeId;
		this.resourceId = resourceId;
		this.resourceTitle = resourceTitle;
		this.resourceDescription = resourceDescription;
		this.resourceImageUrl = resourceImageUrl;
		this.bannerUrl = bannerUrl;
		this.clickUrl = clickUrl;
	}

	public int getTabId() {
		return tabId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public int getItemSequenceId() {
		return itemSequenceId;
	}

	public void setItemSequenceId(int itemSequenceId) {
		this.itemSequenceId = itemSequenceId;
	}

	public int getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getResourceTitle() {
		return resourceTitle;
	}

	public void setResourceTitle(String resourceTitle) {
		this.resourceTitle = resourceTitle;
	}

	public String getResourceDescription() {
		return resourceDescription;
	}

	public void setResourceDescription(String resourceDescription) {
		this.resourceDescription = resourceDescription;
	}

	public String getResourceImageUrl() {
		return resourceImageUrl;
	}

	public void setResourceImageUrl(String resourceImageUrl) {
		this.resourceImageUrl = resourceImageUrl;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

}
