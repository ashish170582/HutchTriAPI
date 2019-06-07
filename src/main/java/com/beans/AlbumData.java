package com.beans;

import javax.xml.bind.annotation.XmlType;

import com.app.beans.Images;

@XmlType(name = "", propOrder = { "albumid", "ivrMMNumber", "albumtitle", "artist", "rating", "tracks", "image" })
public class AlbumData {

	private int albumid;
	private String ivrMMNumber;
	private String albumtitle;
	private String artist;
	private int rating;
	private int tracks;
	private String image;
	private Images images;

	public AlbumData() {
	}

	public AlbumData(int albumid, String ivrMMNumber, String albumtitle, String artist, int rating, int tracks,
			String image) {
		super();
		this.albumid = albumid;
		this.ivrMMNumber = ivrMMNumber;
		this.albumtitle = albumtitle;
		this.artist = artist;
		this.rating = rating;
		this.tracks = tracks;
		this.image = image;
		this.images = new Images(image);
	}

	public int getAlbumid() {
		return albumid;
	}

	public String getIvrMMNumber() {
		return ivrMMNumber;
	}

	public void setIvrMMNumber(String ivrMMNumber) {
		this.ivrMMNumber = ivrMMNumber;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public String getAlbumtitle() {
		return albumtitle;
	}

	public void setAlbumtitle(String albumtitle) {
		this.albumtitle = albumtitle;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public int getTracks() {
		return tracks;
	}

	public void setTracks(int tracks) {
		this.tracks = tracks;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

}
