/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.config;

/**
 *
 * @author Rajat.kumar
 */
public class Configuration {
	private ConfigurationValue casual;
	private ConfigurationValue lite;
	private ConfigurationValue premium;

	public Configuration(ConfigurationValue casual, ConfigurationValue lite, ConfigurationValue premium) {
		this.casual = casual;
		this.lite = lite;
		this.premium = premium;
	}

	public ConfigurationValue getCasual() {
		return casual;
	}

	public void setCasual(ConfigurationValue casual) {
		this.casual = casual;
	}

	public ConfigurationValue getLite() {
		return lite;
	}

	public void setLite(ConfigurationValue lite) {
		this.lite = lite;
	}

	public ConfigurationValue getPremium() {
		return premium;
	}

	public void setPremium(ConfigurationValue premium) {
		this.premium = premium;
	}

}
