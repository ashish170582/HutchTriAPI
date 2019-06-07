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
public class ConfigurationValue {
	private int banner_ads;
	private int audio_ads;
	private int favourite;
	private int dedication;
	private int share;
	private int crbt;
	private int offline_limit;
	private int offline_daily;
	private int offline_total;
	private int download;
	private int stream_interrupt;
	private int stream_limit;
	private int stream_preview;
	private int stream_daily;
	private int stream_total;
	private int stream_track;

	public ConfigurationValue(int banner_ads, int audio_ads, int favourite, int dedication, int share, int crbt,
			int offline_limit, int offline_daily, int offline_total, int download, int stream_interrupt,
			int stream_limit, int stream_preview, int stream_daily, int stream_total, int stream_track) {
		this.banner_ads = banner_ads;
		this.audio_ads = audio_ads;
		this.favourite = favourite;
		this.dedication = dedication;
		this.share = share;
		this.crbt = crbt;
		this.offline_limit = offline_limit;
		this.offline_daily = offline_daily;
		this.offline_total = offline_total;
		this.download = download;
		this.stream_interrupt = stream_interrupt;
		this.stream_limit = stream_limit;
		this.stream_preview = stream_preview;
		this.stream_daily = stream_daily;
		this.stream_total = stream_total;
		this.stream_track = stream_track;
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

	public int getOffline_limit() {
		return offline_limit;
	}

	public void setOffline_limit(int offline_limit) {
		this.offline_limit = offline_limit;
	}

	public int getOffline_daily() {
		return offline_daily;
	}

	public void setOffline_daily(int offline_daily) {
		this.offline_daily = offline_daily;
	}

	public int getOffline_total() {
		return offline_total;
	}

	public void setOffline_total(int offline_total) {
		this.offline_total = offline_total;
	}

	public int getDownload() {
		return download;
	}

	public void setDownload(int download) {
		this.download = download;
	}

	public int getStream_interrupt() {
		return stream_interrupt;
	}

	public void setStream_interrupt(int stream_interrupt) {
		this.stream_interrupt = stream_interrupt;
	}

	public int getStream_limit() {
		return stream_limit;
	}

	public void setStream_limit(int stream_limit) {
		this.stream_limit = stream_limit;
	}

	public int getStream_preview() {
		return stream_preview;
	}

	public void setStream_preview(int stream_preview) {
		this.stream_preview = stream_preview;
	}

	public int getStream_daily() {
		return stream_daily;
	}

	public void setStream_daily(int stream_daily) {
		this.stream_daily = stream_daily;
	}

	public int getStream_total() {
		return stream_total;
	}

	public void setStream_total(int stream_total) {
		this.stream_total = stream_total;
	}

	public int getStream_track() {
		return stream_track;
	}

	public void setStream_track(int stream_track) {
		this.stream_track = stream_track;
	}

}
