package com.beans;

import beans.config.AudioQualityConfiguration;
import beans.config.Configuration;
import beans.config.Visibility;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumData", propOrder = { "countryinfo", "appversion", "visibility", "configuration", "tabs",
		"discover" })
@XmlRootElement(name = "root")
public class CountryAppVersionInfo extends Root {

	private CountryInfoData countryinfo;
	private AppVersionInfoData appversion;
	private Visibility visibility;
	private Configuration configuration;
	AudioQualityConfiguration audioQualityConfiguration;
	Root paymentMethod;
	List<LeftMenuTitle> leftMenuButtons;
	OptScreenConfig optScreenConfig;

	public CountryAppVersionInfo() {
	}

	@SuppressWarnings("unchecked")
	public CountryAppVersionInfo(Object obj1, Object obj2, Object obj3, Object obj4, Object obj7, Object obj8,
			Object obj9, Object obj10) {
		super(0, "Success");
		if (obj1 instanceof CountryInfoData) {
			CountryInfoData countryinfo = (CountryInfoData) obj1;
			this.countryinfo = countryinfo;
		}
		if (obj2 instanceof AppVersionInfoData) {
			AppVersionInfoData appversion = (AppVersionInfoData) obj2;
			this.appversion = appversion;
		}
		if (obj3 instanceof Visibility) {
			Visibility visibility = (Visibility) obj3;
			this.visibility = visibility;
		}
		if (obj4 instanceof Configuration) {
			Configuration configuration = (Configuration) obj4;
			this.configuration = configuration;
		}

		if (obj7 instanceof AudioQualityConfiguration) {
			this.audioQualityConfiguration = (AudioQualityConfiguration) obj7;
		}
		if (obj8 instanceof Root) {
			this.paymentMethod = (Root) obj8;
		} else {
			this.paymentMethod = (PaymentMethods) obj8;
		}

		if (obj9 instanceof Root) {
			this.leftMenuButtons = (List<LeftMenuTitle>) obj9;
		} else {
			this.leftMenuButtons = (List<LeftMenuTitle>) obj9;
		}
		if (obj10 instanceof OptScreenConfig) {
			this.optScreenConfig = (OptScreenConfig) obj10;
		} else {
			this.optScreenConfig = (OptScreenConfig) obj10;
		}

	}

	public void setPaymentMethod(Root paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public void setOptScreenConfig(OptScreenConfig optScreenConfig) {
		this.optScreenConfig = optScreenConfig;
	}

	public OptScreenConfig getOptScreenConfig() {
		return optScreenConfig;
	}

	public CountryInfoData getCountryinfo() {
		return countryinfo;
	}

	public void setCountryinfo(CountryInfoData countryinfo) {
		this.countryinfo = countryinfo;
	}

	public AppVersionInfoData getAppversion() {
		return appversion;
	}

	public void setAppversion(AppVersionInfoData appversion) {
		this.appversion = appversion;
	}

	public Visibility getVisibility() {
		return visibility;
	}

	public void setVisibility(Visibility visibility) {
		this.visibility = visibility;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public AudioQualityConfiguration getAudioQualityConfiguration() {
		return audioQualityConfiguration;
	}

	public void setAudioQualityConfiguration(AudioQualityConfiguration audioQualityConfiguration) {
		this.audioQualityConfiguration = audioQualityConfiguration;
	}

	public Root getPaymentMethod() {
		return paymentMethod;
	}

	public List<LeftMenuTitle> getLeftMenuButtons() {
		return leftMenuButtons;
	}

	public void setLeftMenuButtons(List<LeftMenuTitle> leftMenuButtons) {
		this.leftMenuButtons = leftMenuButtons;
	}

	/*
	 * public SignUpViaConfigurationBean getSignUpViaConfiguration() { return
	 * signUpViaConfiguration; }
	 */

}
