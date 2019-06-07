package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumListType", propOrder = { "album" })

@XmlRootElement(name = "root")
public class GenreAlbums extends Root {

	private int resultCount;
	GenreBean genre;
	private List<AlbumData> album;

	public GenreAlbums() {
	}

	public GenreAlbums(GenreBean genre, List<AlbumData> album) {
		super(0, "Success");
		this.resultCount = album.size();
		this.genre = genre;
		this.album = album;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<AlbumData> getAlbum() {
		return album;
	}

	public void setAlbum(List<AlbumData> album) {
		this.album = album;
	}

	public GenreBean getGenre() {
		return genre;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}
}
