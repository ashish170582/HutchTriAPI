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
public class VisibilityValue {
	private int banner_ads;
	private int audio_ads;
	private int favourite;
	private int dedication;
	private int share;
	private int crbt;
	private int offline;
	private int download;

	public VisibilityValue(int banner_ads, int audio_ads, int favourite, int dedication, int share, int crbt,
			int offline, int download) {
		this.banner_ads = banner_ads;
		this.audio_ads = audio_ads;
		this.favourite = favourite;
		this.dedication = dedication;
		this.share = share;
		this.crbt = crbt;
		this.offline = offline;
		this.download = download;
	}

	public int getBanner_ads() {
		return banner_ads;
	}

	public void setBanner_ads(int banner_ads) {
		this.banner_ads = banner_ads;
	}

	public int getAudio_ads() {
		return audio_ads;
	}

	public void setAudio_ads(int audio_ads) {
		this.audio_ads = audio_ads;
	}

	public int getFavourite() {
		return favourite;
	}

	public void setFavourite(int favourite) {
		this.favourite = favourite;
	}

	public int getDedication() {
		return dedication;
	}

	public void setDedication(int dedication) {
		this.dedication = dedication;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getCrbt() {
		return crbt;
	}

	public void setCrbt(int crbt) {
		this.crbt = crbt;
	}

	public int getOffline() {
		return offline;
	}

	public void setOffline(int offline) {
		this.offline = offline;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}
}
