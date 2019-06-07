/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.beans;

import java.util.Arrays;

/**
 *
 * @author Rajat.kumar
 */

public class ContainerItemBean {
	private String itemId;
	private String itemSequenceId;
	private String itemTypeId;
	private String resourceId;
	private String resourceTitle;
	private String resourceDescription;
	private String resourceImageUrl;
	private String clickUrl;
	private Boolean isCrbtAvailable;
	private Boolean isKaraokeAvailable;
	private String karaokeLang[]; 

	public ContainerItemBean() {
	}

	public ContainerItemBean(String itemId, String itemSequenceId, String itemTypeId, String resourceId,
			String resourceTitle, String resourceDescription, String resourceImageUrl, String clickUrl,
			Boolean isCrbtAvailable, Boolean isKaraokeAvailable, String[] karaokeLang) {
		super();
		this.itemId = itemId;
		this.itemSequenceId = itemSequenceId;
		this.itemTypeId = itemTypeId;
		this.resourceId = resourceId;
		this.resourceTitle = resourceTitle;
		this.resourceDescription = resourceDescription;
		this.resourceImageUrl = resourceImageUrl;
		this.clickUrl = clickUrl;
		this.isCrbtAvailable = isCrbtAvailable;
		this.isKaraokeAvailable = isKaraokeAvailable;
		this.karaokeLang = karaokeLang;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemSequenceId() {
		return itemSequenceId;
	}

	public void setItemSequenceId(String itemSequenceId) {
		this.itemSequenceId = itemSequenceId;
	}

	public String getItemTypeId() {
		return itemTypeId;
	}

	public void setItemTypeId(String itemTypeId) {
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

	public String getClickUrl() {
		return clickUrl;
	}

	public void setClickUrl(String clickUrl) {
		this.clickUrl = clickUrl;
	}

	public Boolean getIsCrbtAvailable() {
		return isCrbtAvailable;
	}

	public void setIsCrbtAvailable(Boolean isCrbtAvailable) {
		this.isCrbtAvailable = isCrbtAvailable;
	}

	public Boolean getIsKaraokeAvailable() {
		return isKaraokeAvailable;
	}

	public void setIsKaraokeAvailable(Boolean isKaraokeAvailable) {
		this.isKaraokeAvailable = isKaraokeAvailable;
	}

	public String[] getKaraokeLang() {
		return karaokeLang;
	}

	public void setKaraokeLang(String[] karaokeLang) {
		this.karaokeLang = karaokeLang;
	}

	@Override
	public String toString() {
		return "ContainerItemBean [itemId=" + itemId + ", itemSequenceId=" + itemSequenceId + ", itemTypeId="
				+ itemTypeId + ", resourceId=" + resourceId + ", resourceTitle=" + resourceTitle
				+ ", resourceDescription=" + resourceDescription + ", resourceImageUrl=" + resourceImageUrl
				+ ", clickUrl=" + clickUrl + ", isCrbtAvailable=" + isCrbtAvailable + ", isKaraokeAvailable="
				+ isKaraokeAvailable + ", karaokeLang=" + Arrays.toString(karaokeLang) + "]";
	}


}
