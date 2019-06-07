/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "splash", propOrder = { "splash" })

@XmlRootElement(name = "root")
public class Splash extends Root {
	private SplashScreenData splash;

	public Splash() {
		super(0, "Success");
	}

	public Splash(SplashScreenData splash) {
		super(0, "Success");
		this.splash = splash;
	}

	public SplashScreenData getSplash() {
		return splash;
	}

	public void setSplash(SplashScreenData splash) {
		this.splash = splash;
	}

}
