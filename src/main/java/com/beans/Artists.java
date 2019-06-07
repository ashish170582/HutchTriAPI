package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "ArtistList", propOrder = { "artist" })

@XmlRootElement(name = "root")
public class Artists extends Root {

	private int resultCount;
	private List<ArtistData> artist;

	public Artists() {
	}

	public Artists(List<ArtistData> artist) {
		super(0, "Success");
		this.resultCount = artist.size();
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

}
