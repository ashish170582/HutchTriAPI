/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;


/**
 *
 * @author ashish.vishwakarma
 */

public class SignUpDesign {
	private String msg;
	private String msgSW;
	private String imgURL;

	public SignUpDesign(String msg, String msgSW, String imgURL) {
		this.msg = msg;
		this.msgSW = msgSW;
		this.imgURL = imgURL;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgSW() {
		return msgSW;
	}

	public void setMsgSW(String msgSW) {
		this.msgSW = msgSW;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

}
