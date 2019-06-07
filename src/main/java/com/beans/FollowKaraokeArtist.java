/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

/**
 *
 * @author Rajat.kumar
 */
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
public class FollowKaraokeArtist extends Root {
    private int followingCount;
    private int followersCount;
    private List<FollowKaraokeArtistData> artistList;
    
	public FollowKaraokeArtist(int followingCount, int followersCount, List<FollowKaraokeArtistData> artistList) {
		 super(0, "Success");
		this.followingCount = followingCount;
		this.followersCount = followersCount;
		this.artistList = artistList;
	}

	public int getFollowingCount() {
		return followingCount;
	}

	public void setFollowingCount(int followingCount) {
		this.followingCount = followingCount;
	}

	public int getFollowersCount() {
		return followersCount;
	}

	public void setFollowersCount(int followersCount) {
		this.followersCount = followersCount;
	}

	public FollowKaraokeArtist(List<FollowKaraokeArtistData> artistList) {
		super(0, "Success");
		this.artistList = artistList;
	}

	public List<FollowKaraokeArtistData> getArtistList() {
		return artistList;
	}
	public void setArtistList(List<FollowKaraokeArtistData> artistList) {
		this.artistList = artistList;
	}
	@Override
	public String toString() {
		return "FollowKaraokeArtist [artistList=" + artistList + "]";
	}
}
