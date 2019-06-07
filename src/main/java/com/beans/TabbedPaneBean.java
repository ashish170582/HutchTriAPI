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
public class TabbedPaneBean {
	private int tabId;
	private int tabSequenceId;
	private int tabTypeId;
	private String tabTitle;

	public TabbedPaneBean() {
	}

	public TabbedPaneBean(int tabId, int tabSequenceId, int tabTypeId, String tabTitle) {
		this.tabId = tabId;
		this.tabSequenceId = tabSequenceId;
		this.tabTypeId = tabTypeId;
		this.tabTitle = tabTitle;
	}

	public int getTabId() {
		return tabId;
	}

	public void setTabId(int tabId) {
		this.tabId = tabId;
	}

	public int getTabTypeId() {
		return tabTypeId;
	}

	public void setTabTypeId(int tabTypeId) {
		this.tabTypeId = tabTypeId;
	}

	public String getTabTitle() {
		return tabTitle;
	}

	public void setTabTitle(String tabTitle) {
		this.tabTitle = tabTitle;
	}

	public int getTabSequenceId() {
		return tabSequenceId;
	}

	public void setTabSequenceId(int tabSequenceId) {
		this.tabSequenceId = tabSequenceId;
	}
}
