package com.beans;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = {
    "tracks"
})

@XmlRootElement(name = "root")
public class KaraokeInfo extends Root implements Serializable {

	private karaokeTrackData karaokeData;
    public KaraokeInfo() {
    	super(0, "Success");
    }
	public KaraokeInfo(karaokeTrackData karaokeData) {
		super(0, "Success");
		this.karaokeData = karaokeData;
	}
	public karaokeTrackData getKaraokeData() {
		return karaokeData;
	}
	public void setKaraokeData(karaokeTrackData karaokeData) {
		this.karaokeData = karaokeData;
	}
	@Override
	public String toString() {
		return "KaraokeInfo [karaokeData=" + karaokeData + ", getKaraokeData()=" + getKaraokeData() + ", getCode()="
				+ getCode() + ", getMessage()=" + getMessage() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
}
