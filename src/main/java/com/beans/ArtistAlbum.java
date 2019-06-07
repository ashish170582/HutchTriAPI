package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumData", propOrder = { "album", "tracks" })
@XmlRootElement(name = "root")
public class ArtistAlbum extends Root {

	private int resultCount;
	private ArtistData artist;
	private List<AlbumData> album;

	public ArtistAlbum() {
	}

	public ArtistAlbum(ArtistData artist, List<AlbumData> album) {
		super(0, "Success");
		this.resultCount = album.size();
		this.artist = artist;
		this.album = album;
	}

	public ArtistData getArtist() {
		return artist;
	}

	public void setArtist(ArtistData artist) {
		this.artist = artist;
	}

	public List<AlbumData> getAlbum() {
		return album;
	}

	public void setAlbum(List<AlbumData> album) {
		this.album = album;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
