package com.beans;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonProperty;

public class LeftMenuTitle implements Serializable {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private String title;
	private String url;
	private Boolean isExternal;
	private String popupText;
	private String popupTitle;

	public LeftMenuTitle() {
		// TODO Auto-generated constructor stub
	}

	public LeftMenuTitle(String title, String url, Boolean isExternal, String popupText, String popupTitle) {
		super();
		this.title = title;
		this.url = url;
		this.isExternal = isExternal;
		this.popupText = popupText;
		this.popupTitle = popupTitle;
	}

	public String getPopupTitle() {
		return popupTitle;
	}

	public void setPopupTitle(String popupTitle) {
		this.popupTitle = popupTitle;
	}

	public String getPopupText() {
		return popupText;
	}

	public void setPopupText(String popupText) {
		this.popupText = popupText;
	}

	public void setIsExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty(value = "isExternal")
	public Boolean isExternal() {
		return isExternal;
	}

	public void setExternal(Boolean isExternal) {
		this.isExternal = isExternal;
	}

	public Boolean getIsExternal() {
		return isExternal;
	}

	@Override
	public String toString() {
		return "LeftMenuTitle [title=" + title + ", url=" + url + ", isExternal=" + isExternal + "]";
	}
}
