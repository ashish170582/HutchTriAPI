package com.beans;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = { "tracks" })

@XmlRootElement(name = "root")
public class Tracks extends Root {

	private int resultCount;
	private List<TrackData> tracks;

	private List<ContainerBean> containerList;

	public Tracks() {
	}

	public Tracks(List<TrackData> tracks) {
		super(0, "Success");
		this.resultCount = tracks.size();
		this.tracks = tracks;
		this.containerList = new ArrayList<ContainerBean>();
	}

	public Tracks(List<TrackData> tracks, List<ContainerBean> containerList) {
		super(0, "Success");
		this.tracks = tracks;
		this.containerList = containerList;
	}

	public Tracks(int code, String msg, List<TrackData> tracks) {
		super(code, msg);
		this.tracks = tracks;
		this.containerList = new ArrayList<ContainerBean>();
	}

	public Tracks(int code, String msg, List<TrackData> tracks, List<ContainerBean> containerList) {
		super(code, msg);
		this.tracks = tracks;
		this.containerList = containerList;
	}

	public List<ContainerBean> getContainerList() {
		return containerList;
	}

	public void setContainerList(List<ContainerBean> containerList) {
		this.containerList = containerList;
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
