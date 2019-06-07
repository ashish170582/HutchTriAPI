package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "AlbumListType", propOrder = { "album" })

@XmlRootElement(name = "root")
public class Albums extends Root {

	private int resultCount;
	private List<AlbumData> album;

	public Albums() {
	}

	public Albums(List<AlbumData> album) {
		super(0, "Success");
		this.resultCount = album.size();
		this.album = album;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<AlbumData> getAlbum() {
		return album;
	}

	public void setAlbum(List<AlbumData> album) {
		this.album = album;
	}

}
