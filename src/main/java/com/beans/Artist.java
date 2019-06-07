package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumData", propOrder = { "album", "tracks" })
@XmlRootElement(name = "root")
public class Artist extends Root {
	private boolean followingArtist = false;
	private ArtistData artistInfo;
	private List<ArtistData> similarArtistList;
	private List<TrackData> topTrackList;
	private List<TrackData> singleTrackList;
	private List<TrackData> featuredTrackList;
	private List<TrackData> videoTrackList;

	public Artist() {
	}

	public Artist(boolean followingArtist, ArtistData artistInfo, List<ArtistData> similarArtistList,
			List<TrackData> topTrackList, List<TrackData> singleTrackList, List<TrackData> featuredTrackList,
			List<TrackData> videoTrackList) {
		super(0, "Success");
		this.followingArtist = followingArtist;
		this.artistInfo = artistInfo;
		this.similarArtistList = similarArtistList;
		this.topTrackList = topTrackList;
		this.singleTrackList = singleTrackList;
		this.featuredTrackList = featuredTrackList;
		this.videoTrackList = videoTrackList;
	}

	public boolean isFollowingArtist() {
		return followingArtist;
	}

	public void setFollowingArtist(boolean followingArtist) {
		this.followingArtist = followingArtist;
	}

	public ArtistData getArtistInfo() {
		return artistInfo;
	}

	public void setArtistInfo(ArtistData artistInfo) {
		this.artistInfo = artistInfo;
	}

	public List<ArtistData> getSimilarArtistList() {
		return similarArtistList;
	}

	public void setSimilarArtistList(List<ArtistData> similarArtistList) {
		this.similarArtistList = similarArtistList;
	}

	public List<TrackData> getTopTrackList() {
		return topTrackList;
	}

	public void setTopTrackList(List<TrackData> topTrackList) {
		this.topTrackList = topTrackList;
	}

	public List<TrackData> getSingleTrackList() {
		return singleTrackList;
	}

	public void setSingleTrackList(List<TrackData> singleTrackList) {
		this.singleTrackList = singleTrackList;
	}

	public List<TrackData> getFeaturedTrackList() {
		return featuredTrackList;
	}

	public void setFeaturedTrackList(List<TrackData> featuredTrackList) {
		this.featuredTrackList = featuredTrackList;
	}

	public List<TrackData> getVideoTrackList() {
		return videoTrackList;
	}

	public void setVideoTrackList(List<TrackData> videoTrackList) {
		this.videoTrackList = videoTrackList;
	}

}
