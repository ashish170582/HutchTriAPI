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
public class DeviceInformationData {

	private DeviceInformation root ;


	public DeviceInformationData() {
	}


	public DeviceInformationData(DeviceInformation root) {
		super();
		this.root = root;
	}


	public DeviceInformation getRoot() {
		return root;
	}


	public void setRoot(DeviceInformation root) {
		this.root = root;
	}


	@Override
	public String toString() {
		return "DeviceInformationData [root=" + root + "]";
	}

}
