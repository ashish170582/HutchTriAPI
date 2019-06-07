package com.beans;

import javax.xml.bind.annotation.XmlType;

import com.app.beans.Images;

@XmlType(name = "", propOrder = { "rcode", "title", "albumid", "albumtitle", "artistid", "artist", "genreid", "genre",
		"filesize", "duration", "stream", "image", "uimage" })
public class DedicationData {

	private int dedicationid;
	private String username;
	private String reqdt;
	private String msg;
	private String rcode;
	private String title;
	private int albumid;
	private String albumtitle;
	private int artistid;
	private String artist;
	private int genreid;
	private String genre;
	private long filesize;
	private long duration;
	private String stream;
	private String image;
	private String uimage;
	private Images images;

	public DedicationData() {
	}

	public DedicationData(int dedicationid, String username, String reqdt, String msg, String rcode, String title,
			int albumid, String albumtitle, int artistid, String artist, int genreid, String genre, long filesize,
			long duration, String stream, String image, String uimage) {
		this.dedicationid = dedicationid;
		this.username = username;
		this.reqdt = reqdt;
		this.msg = msg;
		this.rcode = rcode;
		this.title = title;
		this.albumid = albumid;
		this.albumtitle = albumtitle;
		this.artistid = artistid;
		this.artist = artist;
		this.genreid = genreid;
		this.genre = genre;
		this.filesize = filesize;
		this.duration = duration;
		this.stream = stream;
		this.image = image;
		this.uimage = uimage;
		this.images = new Images(image);
	}

	public int getDedicationid() {
		return dedicationid;
	}

	public void setDedicationid(int dedicationid) {
		this.dedicationid = dedicationid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getReqdt() {
		return reqdt;
	}

	public void setReqdt(String reqdt) {
		this.reqdt = reqdt;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAlbumid() {
		return albumid;
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

	public int getArtistid() {
		return artistid;
	}

	public void setArtistid(int artistid) {
		this.artistid = artistid;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

}