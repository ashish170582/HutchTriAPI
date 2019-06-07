/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.config;

public class AudioQualityConfiguration {
	private String ssDomainName; // "vgabr.scontentzone.com";(Voda_GHANA)
	private AudioQuality auto;
	private AudioQuality high;
	private AudioQuality medium;
	private AudioQuality low;

	public AudioQualityConfiguration(AudioQuality auto, AudioQuality high, AudioQuality medium, AudioQuality low) {
		this.auto = auto;
		this.high = high;
		this.medium = medium;
		this.low = low;
	}

	public String getSsDomainName() {
		return ssDomainName;
	}

	public void setSsDomainName(String ssDomainName) {
		this.ssDomainName = ssDomainName;
	}

	public AudioQuality getAuto() {
		return auto;
	}

	public void setAuto(AudioQuality auto) {
		this.auto = auto;
	}

	public AudioQuality getHigh() {
		return high;
	}

	public void setHigh(AudioQuality high) {
		this.high = high;
	}

	public AudioQuality getMedium() {
		return medium;
	}

	public void setMedium(AudioQuality medium) {
		this.medium = medium;
	}

	public AudioQuality getLow() {
		return low;
	}

	public void setLow(AudioQuality low) {
		this.low = low;
	}

}
