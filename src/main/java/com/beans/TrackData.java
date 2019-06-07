package com.beans;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.TreeMap;
import org.codehaus.jackson.annotate.JsonProperty;

import com.app.beans.Images;

public class TrackData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rcode;
	private String ivrMMNumber;
	private String title;
	private int albumid;
	private String albumtitle;
	private String artistid;
	private String artist;
	private int genreid;
	private String genre;
	private long filesize;
	private long duration;
	private String playCount;
	private String favouriteCount;
	private String shareCount;

	private String stream;
	private String image;
	private String videoId;
	private Images images;

	private Boolean isCrbtAvailable;
	private Boolean isKaraokeAvailable;
	
	private String karaokeLang[]; 

	private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

	static {
		suffixes.put(1_000L, "K");
		suffixes.put(1_000_000L, "M");
		suffixes.put(1_000_000_000L, "G");
		suffixes.put(1_000_000_000_000L, "T");
		suffixes.put(1_000_000_000_000_000L, "P");
		suffixes.put(1_000_000_000_000_000_000L, "E");
	}

	public static String format(long value) {
		// Long.MIN_VALUE == -Long.MIN_VALUE so we need an adjustment here
		if (value == Long.MIN_VALUE) {
			return format(Long.MIN_VALUE + 1);
		}
		if (value < 0) {
			return "-" + format(-value);
		}
		if (value < 10) {
			return "<10";
		}
		if (value < 1000) {
			return Long.toString(value); // deal with easy case
		}
		Entry<Long, String> e = suffixes.floorEntry(value);
		Long divideBy = e.getKey();
		String suffix = e.getValue();

		long truncated = value / (divideBy / 10); // the number part of the output times 10
		boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
		return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
	}

	public TrackData() {
	}

	public TrackData(String rcode, String ivrMMNumber, String title, int albumid, String albumtitle, int artistid,
			String artist, int genreid, String genre, long playCount, long favouriteCount, long shareCount,
			long filesize, long duration, String stream, String image, String videoId, Boolean isCrbtAvailable,
			Boolean isKaraokeAvailable,String karaokeLang[]) {
		this.rcode = rcode;
		this.ivrMMNumber = ivrMMNumber;
		this.title = title;
		this.albumid = albumid;
		this.albumtitle = albumtitle;
		this.artistid = String.valueOf(artistid);
		this.artist = artist;
		this.genreid = genreid;
		this.genre = genre;
		this.playCount = format(playCount);
		this.favouriteCount = format(favouriteCount);
		this.shareCount = format(shareCount);
		this.filesize = filesize;
		this.duration = duration;
		this.stream = stream;
		this.image = image;
		this.videoId = videoId;
		// this.image = "-";
		this.images = new Images(image);
		this.isCrbtAvailable = isCrbtAvailable;
		this.isKaraokeAvailable = isKaraokeAvailable;
		this.karaokeLang=karaokeLang;
	}


	public String[] getKaraokeLang() {
		return karaokeLang;
	}

	public void setKaraokeLang(String[] karaokeLang) {
		this.karaokeLang = karaokeLang;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static NavigableMap<Long, String> getSuffixes() {
		return suffixes;
	}

	public void setArtistid(String artistid) {
		this.artistid = artistid;
	}

	public void setPlayCount(String playCount) {
		this.playCount = playCount;
	}

	public void setFavouriteCount(String favouriteCount) {
		this.favouriteCount = favouriteCount;
	}

	public void setShareCount(String shareCount) {
		this.shareCount = shareCount;
	}

	public void setIsCrbtAvailable(Boolean isCrbtAvailable) {
		this.isCrbtAvailable = isCrbtAvailable;
	}

	public void setIsKaraokeAvailable(Boolean isKaraokeAvailable) {
		this.isKaraokeAvailable = isKaraokeAvailable;
	}

	public String getRcode() {
		return rcode;
	}

	public void setRcode(String rcode) {
		this.rcode = rcode;
	}

	public String getIvrMMNumber() {
		return ivrMMNumber;
	}

	public void setIvrMMNumber(String ivrMMNumber) {
		this.ivrMMNumber = ivrMMNumber;
	}

/*	@JsonProperty(value = "isCrbtAvailable")
	public Boolean isCrbtAvailable() {
		return isCrbtAvailable;
	}*/
public Boolean getIsCrbtAvailable() {
	return isCrbtAvailable;
}
	public void setCrbtAvailable(Boolean isCrbtAvailable) {
		this.isCrbtAvailable = isCrbtAvailable;
	}

/*	@JsonProperty(value = "isKaraokeAvailable")
	public Boolean isKaraokeAvailable() {
		return isKaraokeAvailable;
	}
*/
	
	public Boolean getIsKaraokeAvailable() {
		return isKaraokeAvailable;
	}
	
	public void setKaraokeAvailable(Boolean isKaraokeAvailable) {
		this.isKaraokeAvailable = isKaraokeAvailable;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public String getAlbumtitle() {
		return albumtitle;
	}

	public void setAlbumtitle(String albumtitle) {
		this.albumtitle = albumtitle;
	}

	public String getArtistid() {
		return artistid;
	}

	public void setArtistid(int artistid) {
		this.artistid = String.valueOf(artistid);
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getGenreid() {
		return genreid;
	}

	public void setGenreid(int genreid) {
		this.genreid = genreid;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public long getDuration() {
		return duration;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getPlayCount() {
		return playCount;
	}

	public void setPlayCount(long playCount) {
		this.playCount = format(playCount);
	}

	public String getFavouriteCount() {
		return favouriteCount;
	}

	public void setFavouriteCount(long favouriteCount) {
		this.favouriteCount = format(favouriteCount);
	}

	public String getShareCount() {
		return shareCount;
	}

	public void setShareCount(long shareCount) {
		this.shareCount = format(shareCount);
	}

	public String getStream() {
		return stream;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getVideoId() {
		return videoId;
	}

	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "TrackData [rcode=" + rcode + ", ivrMMNumber=" + ivrMMNumber + ", title=" + title + ", albumid="
				+ albumid + ", albumtitle=" + albumtitle + ", artistid=" + artistid + ", artist=" + artist
				+ ", genreid=" + genreid + ", genre=" + genre + ", filesize=" + filesize + ", duration=" + duration
				+ ", playCount=" + playCount + ", favouriteCount=" + favouriteCount + ", shareCount=" + shareCount
				+ ", stream=" + stream + ", image=" + image + ", videoId=" + videoId + ", images=" + images
				+ ", isCrbtAvailable=" + isCrbtAvailable + ", isKaraokeAvailable=" + isKaraokeAvailable
				+ ", karaokeLang=" + Arrays.toString(karaokeLang) + "]";
	}
}
