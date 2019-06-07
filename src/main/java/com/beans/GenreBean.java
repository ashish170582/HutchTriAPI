package com.beans;

import javax.xml.bind.annotation.XmlType;

import com.app.beans.Images;

@XmlType(name = "", propOrder = { "genreid", "genre" })

public class GenreBean {
	private int genreid;
	private String genre;
	private String image;
	private Images images;

	public GenreBean() {
	}

	public GenreBean(int genreid, String genre, String image) {
		super();
		this.genreid = genreid;
		this.genre = genre;
		this.image = image;
		this.images = new Images(image);

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
