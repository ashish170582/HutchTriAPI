package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumData", propOrder = { "album", "tracks" })
@XmlRootElement(name = "root")
public class ArtistTrack extends Root {
	private int resultCount;
	private ArtistData artist;
	private List<TrackData> tracks;

	public ArtistTrack() {
	}

	public ArtistTrack(ArtistData artist, List<TrackData> tracks) {
		super(0, "Success");
		this.resultCount = tracks.size();
		this.artist = artist;
		this.tracks = tracks;
	}

	public ArtistData getArtist() {
		return artist;
	}

	public void setArtist(ArtistData artist) {
		this.artist = artist;
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

}
