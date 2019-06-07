/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author ashish.vishwakarma
 */

@XmlType(name = "signupdesion", propOrder = { "signupdesion" })

@XmlRootElement(name = "root")
public class SignUpDesignObject extends Root {

	private List<SignUpDesign> listSingnUp;

	public SignUpDesignObject() {
	}

	public List<SignUpDesign> getListSingnUp() {
		return listSingnUp;
	}

	public void setListSingnUp(List<SignUpDesign> listSingnUp) {
		this.listSingnUp = listSingnUp;
	}

}
