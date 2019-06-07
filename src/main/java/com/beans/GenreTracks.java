package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = { "tracks" })

@XmlRootElement(name = "root")
public class GenreTracks extends Root {

	private int resultCount;
	GenreBean genre;
	private List<TrackData> tracks;

	public GenreTracks() {
	}

	public GenreTracks(GenreBean genre, List<TrackData> tracks) {
		super(0, "Success");
		this.resultCount = tracks.size();
		this.genre = genre;
		this.tracks = tracks;
	}

	public GenreTracks(int code, String msg, List<TrackData> tracks) {
		super(code, msg);
		this.tracks = tracks;
	}

	public List<TrackData> getTracks() {
		return tracks;
	}

	public void setTracks(List<TrackData> tracks) {
		this.tracks = tracks;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public GenreBean getGenre() {
		return genre;
	}

	public void setGenre(GenreBean genre) {
		this.genre = genre;
	}

}
