package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = { "tracks" })

@XmlRootElement(name = "root")
public class FeaturedPlaylistTracks extends Root {

	private int resultCount;
	FeaturedPlaylistBean playlist;
	private List<TrackData> tracks;

	public FeaturedPlaylistTracks() {
	}

	public FeaturedPlaylistTracks(FeaturedPlaylistBean playlist, List<TrackData> tracks) {
		super(0, "Success");
		this.resultCount = tracks.size();
		this.playlist = playlist;
		this.tracks = tracks;
	}

	public FeaturedPlaylistTracks(int code, String msg, FeaturedPlaylistBean playlist, List<TrackData> tracks) {
		super(code, msg);
		this.playlist = playlist;
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

	public FeaturedPlaylistBean getPlaylist() {
		return playlist;
	}

	public void setPlaylist(FeaturedPlaylistBean playlist) {
		this.playlist = playlist;
	}

}
