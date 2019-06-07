package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ArtistList", propOrder = { "artist" })

@XmlRootElement(name = "root")
public class GenreArtists extends Root {

	private int resultCount;
	GenreBean genre;
	private List<ArtistData> artist;

	public GenreArtists() {
	}

	public GenreArtists(GenreBean genre, List<ArtistData> artist) {
		super(0, "Success");
		this.resultCount = artist.size();
		this.genre = genre;
		this.artist = artist;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<ArtistData> getArtist() {
		return artist;
	}

	public void setArtist(List<ArtistData> artist) {
		this.artist = artist;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}

	public GenreBean getGenre() {
		return genre;
	}

}
