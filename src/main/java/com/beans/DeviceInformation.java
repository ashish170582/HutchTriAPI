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
public class DeviceInformation {

	private String applicationVersion = "";
	private String operatingSystem = "";
	private String operatingSystemVersion = "";
	private String device = "";
	private String deviceModel = "";
	private String deviceId = "";
	private String devicePin = "";

	public DeviceInformation() {
	}

	public DeviceInformation(String applicationVersion, String operatingSystem, String operatingSystemVersion,
			String device, String deviceModel, String deviceId, String devicePin) {
		if (applicationVersion == null || applicationVersion.equalsIgnoreCase("-")) {
			this.applicationVersion = "1.0.0";
		} else {
			this.applicationVersion = applicationVersion;
		}
		this.operatingSystem = operatingSystem;
		this.operatingSystemVersion = operatingSystemVersion;
		this.device = device;
		this.deviceModel = deviceModel;
		this.deviceId = deviceId;
		this.devicePin = devicePin;
		if (this.device == null) {
			this.device = "mobile";
		}
	}

	public String getApplicationVersion() {
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion) {
		if (applicationVersion != null) {
			this.applicationVersion = applicationVersion;
		}
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		if (operatingSystem != null) {
			this.operatingSystem = operatingSystem;
		} else {
			this.operatingSystem = "Unknown";
		}

	}

	public String getOperatingSystemVersion() {
		return operatingSystemVersion;
	}

	public void setOperatingSystemVersion(String operatingSystemVersion) {
		if (operatingSystemVersion != null) {
			this.operatingSystemVersion = operatingSystemVersion;
		} else {
			this.operatingSystemVersion = "0.0.0";
		}
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		if (device != null) {
			this.device = device;
		} else {
			this.device = "Unknown";
		}
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		if (deviceModel != null) {
			this.deviceModel = deviceModel;
		} else {
			this.deviceModel = "Unknown";
		}
	}

	@SuppressWarnings("finally")
	public String getDeviceId() {
		try {
			if (deviceId.length() > 0 && deviceId.length() < 50) {
				return this.deviceId;
			} else if (deviceId.length() > 50) {
				this.deviceId = deviceId.substring(0, 50);
			} else {
				this.deviceId = "";
			}
		} catch (Exception e) {
			deviceId = "";
		} finally {
			return deviceId;
		}

	}

	public void setDeviceId(String deviceId) {
		if (deviceId != null) {
			this.deviceId = deviceId;
		} else {
			this.deviceId = "NotAvailable";
		}
	}

	@SuppressWarnings("finally")
	public String getDevicePin() {
		try {
			if (devicePin.length() > 0 && devicePin.length() < 350) {
				return this.devicePin;
			} else if (devicePin.length() > 350) {
				this.devicePin = devicePin.substring(0, 350);
			} else {
				this.devicePin = "NotAvailable";
			}
		} catch (Exception e) {
			devicePin = "NotAvailable";
		} finally {
			return devicePin;
		}
	}

	public void setDevicePin(String devicePin) {
		if (devicePin != null) {
			this.devicePin = devicePin;
		} else {
			this.devicePin = "NotAvailable";
		}
	}

	@Override
	public String toString() {
		return "DeviceInformation [applicationVersion=" + applicationVersion + ", operatingSystem=" + operatingSystem
				+ ", operatingSystemVersion=" + operatingSystemVersion + ", device=" + device + ", deviceModel="
				+ deviceModel + ", deviceId=" + deviceId + ", devicePin=" + devicePin + "]";
	}

}
