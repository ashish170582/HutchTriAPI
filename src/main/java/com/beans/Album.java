package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumData", propOrder = { "album", "tracks" })
@XmlRootElement(name = "root")
public class Album extends Root {

	private AlbumData album;
	private List<Tracks> tracks;

	public Album() {
	}

	public Album(AlbumData album, List<Tracks> tracks) {
		super(0, "Success");
		this.album = album;
		this.tracks = tracks;
	}

	public AlbumData getAlbum() {
		return album;
	}

	public void setAlbum(AlbumData album) {
		this.album = album;
	}

	public List<Tracks> getTracks() {
		return tracks;
	}

	public void setTracks(List<Tracks> tracks) {
		this.tracks = tracks;
	}

}
