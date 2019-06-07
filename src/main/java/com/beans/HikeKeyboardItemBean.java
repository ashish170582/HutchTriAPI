/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

import com.app.beans.Images;

/**
 *
 * @author Rajat.kumar
 */
public class HikeKeyboardItemBean {
	private int itemSequenceId;
	private int itemTypeId;
	private String resourceId;
	private String resourceTitle;
	private String resourceDescription;
	private String resourceImageUrl;
	private String shareMessage;
	private Images images;

	public HikeKeyboardItemBean() {
	}

	public HikeKeyboardItemBean(int itemSequenceId, int itemTypeId, String resourceId, String resourceTitle,
			String resourceDescription, String resourceImageUrl, String shareMessage) {
		this.itemSequenceId = itemSequenceId;
		this.itemTypeId = itemTypeId;
		this.resourceId = resourceId;
		this.resourceTitle = resourceTitle;
		this.resourceDescription = resourceDescription;
		this.resourceImageUrl = resourceImageUrl;
		this.shareMessage = shareMessage;
		this.images = new Images(resourceImageUrl);
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

	public String getShareMessage() {
		return shareMessage;
	}

	public void setShareMessage(String shareMessage) {
		this.shareMessage = shareMessage;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}
}
