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
public class Visibility {
	private VisibilityValue casual;
	private VisibilityValue lite;
	private VisibilityValue premium;

	public Visibility(VisibilityValue casual, VisibilityValue lite, VisibilityValue premium) {
		this.casual = casual;
		this.lite = lite;
		this.premium = premium;
	}

	public VisibilityValue getCasual() {
		return casual;
	}

	public void setCasual(VisibilityValue casual) {
		this.casual = casual;
	}

	public VisibilityValue getLite() {
		return lite;
	}

	public void setLite(VisibilityValue lite) {
		this.lite = lite;
	}

	public VisibilityValue getPremium() {
		return premium;
	}

	public void setPremium(VisibilityValue premium) {
		this.premium = premium;
	}
}
