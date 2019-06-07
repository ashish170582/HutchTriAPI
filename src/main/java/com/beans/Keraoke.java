package com.beans;



import java.util.List;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TrackList", propOrder = {
    "tracks"
})


public class Keraoke extends Root {

    private int resultCount;
    private int followingCount;
    private int followersCount;
    private Boolean isArtistFollowed;
    private List<karaokeData> tracks;
    private List<ContainerBean> containerList;
    public Keraoke() {
    }

    public Keraoke(List<karaokeData> tracks,List<ContainerBean> containerList,int followingCount,int followersCount, Boolean isArtistFollowed) {
    	super(0, "Success");
    	this.resultCount = tracks.size();
        this.tracks = tracks;
        this.containerList=containerList;
        this.followingCount=followingCount;
        this.followersCount=followersCount;
        
      //  System.out.println("isFollowed value--------------"+isArtistFollowed);
        this.isArtistFollowed=isArtistFollowed;
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
	public Boolean getIsArtistFollowed() {
		return isArtistFollowed;
	}

	public void setIsArtistFollowed(Boolean isArtistFollowed) {
		this.isArtistFollowed = isArtistFollowed;
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

	public void setResultCount(int resultCount) {
        this.resultCount = resultCount;
    }

	@Override
	public String toString() {
		return "Keraoke [resultCount=" + resultCount + ", followingCount=" + followingCount + ", followersCount="
				+ followersCount + ", isArtistFollowed=" + isArtistFollowed + ", tracks=" + tracks + ", containerList="
				+ containerList + "]";
	}
}
