package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = {
    "tracks"
})

@XmlRootElement(name = "root")
public class Karaoke extends Root {

    private int resultCount;
    private int followCount;
    private int followedCount;
    private List<karaokeData> tracks;
    private List<ContainerBean> containerList;
    public Karaoke() {
    }

    public Karaoke(List<karaokeData> tracks,List<ContainerBean> containerList,int followCount,int followedCount) {
        super(0, "Success");
        this.resultCount = tracks.size();
        this.tracks = tracks;
        this.containerList=containerList;
        this.followCount=followCount;
        this.followedCount=followedCount;
    }

/*    public Karaoke(int code, String msg, List<karaokeData> tracks,List<ContainerBean> containerList) {
        super(code, msg);
        this.tracks = tracks;
        this.containerList=containerList;
    }*/

/*    public Karaoke(int resultCount, int followCount, int followedCount,
			List<karaokeData> tracks, List<ContainerBean> containerList) {
		super();
		this.resultCount = resultCount;
		this.followCount = followCount;
		this.followedCount = followedCount;
		this.tracks = tracks;
		this.containerList = containerList;
	}*/

	public List<karaokeData> getTracks() {
        return tracks;
    }
	public List<ContainerBean> getContainerList() {
		return containerList;
	}
	public void setContainerList(List<ContainerBean> containerList) {
		this.containerList = containerList;
	}
    public void setTracks(List<karaokeData> tracks) {
        this.tracks = tracks;
    }

    public int getResultCount() {
        return resultCount;
    }
	 public void setFollowedCount(int followedCount) {
		this.followedCount = followedCount;
	}
	 public void setFollowCount(int followCount) {
		this.followCount = followCount;
	}
	 public int getFollowedCount() {
		return followedCount;
	}
	 public int getFollowCount() {
		return followCount;
	}
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

}
