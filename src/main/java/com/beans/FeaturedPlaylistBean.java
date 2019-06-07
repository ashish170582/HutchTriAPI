package com.beans;

import javax.xml.bind.annotation.XmlType;

import com.app.beans.Images;

@XmlType(name = "", propOrder = { "playlistId", "playlistName", "playlistDescription", "playlistImageUrl" })

public class FeaturedPlaylistBean {

	private int playlistId;
	private String playlistName;
	private String playlistDescription;
	private String playlistImageUrl;
	private int trackCount;
	private Images images;

	public FeaturedPlaylistBean() {
	}

	public FeaturedPlaylistBean(int playlistId, String playlistName, String playlistDescription,
			String playlistImageUrl, int trackCount) {
		this.playlistId = playlistId;
		this.playlistName = playlistName;
		this.playlistDescription = playlistDescription;
		this.playlistImageUrl = playlistImageUrl;
		this.trackCount = trackCount;
		this.images = new Images(playlistImageUrl);
	}

	public int getPlaylistId() {
		return playlistId;
	}

	public void setPlaylistId(int playlistId) {
		this.playlistId = playlistId;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public String getPlaylistDescription() {
		return playlistDescription;
	}

	public void setPlaylistDescription(String playlistDescription) {
		this.playlistDescription = playlistDescription;
	}

	public String getPlaylistImageUrl() {
		return playlistImageUrl;
	}

	public void setPlaylistImageUrl(String playlistImageUrl) {
		this.playlistImageUrl = playlistImageUrl;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

}
