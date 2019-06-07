package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "FeaturedPlaylist", propOrder = { "featuredPlaylist" })

@XmlRootElement(name = "root")
public class FeaturedPlaylist extends Root {
	private int resultCount;
	private List<FeaturedPlaylistBean> featuredPlaylist;

	public FeaturedPlaylist() {
	}

	public FeaturedPlaylist(List<FeaturedPlaylistBean> featuredPlaylist) {
		super(0, "Success");
		this.resultCount = featuredPlaylist.size();
		this.featuredPlaylist = featuredPlaylist;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<FeaturedPlaylistBean> getFeaturedPlaylist() {
		return featuredPlaylist;
	}

	public void setFeaturedPlaylist(List<FeaturedPlaylistBean> featuredPlaylist) {
		this.featuredPlaylist = featuredPlaylist;
	}

}
