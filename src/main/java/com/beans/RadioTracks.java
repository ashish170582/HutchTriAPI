package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = { "tracks" })

@XmlRootElement(name = "root")
public class RadioTracks extends Root {

	private int resultCount;
	RadioBean radio;
	private List<TrackData> tracks;

	public RadioTracks() {
	}

	public RadioTracks(RadioBean radio, List<TrackData> tracks) {
		super(0, "Success");
		this.resultCount = tracks.size();
		this.radio = radio;
		this.tracks = tracks;
	}

	public RadioTracks(int code, String msg, RadioBean radio, List<TrackData> tracks) {
		super(code, msg);
		this.radio = radio;
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

	public RadioBean getRadio() {
		return radio;
	}

	public void setRadio(RadioBean radio) {
		this.radio = radio;
	}

}
