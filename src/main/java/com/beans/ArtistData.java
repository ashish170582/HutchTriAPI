package com.beans;

import java.util.Map;
import java.util.NavigableMap;
import java.util.TreeMap;
import javax.xml.bind.annotation.XmlType;

import com.app.beans.Images;

@XmlType(name = "", propOrder = { "artistid", "ivrMMNumber", "artist", "albumcount", "trackcount", "image" })
public class ArtistData {

	private int artistid;
	private String ivrMMNumber;
	private String artist;
	private int albumcount;
	private int trackcount;
	private String image;
	private Images images;
	private String pageTitle = "NA";
	private String metaDescription = "NA";
	private String metaKeywords = "NA";
	private String aboutArtist = "NA";
	private String trackShareCount = "<10";
	private String trackFavouriteCount = "<10";
	private String trackPlayCount = "<10";

	private static final NavigableMap<Long, String> suffixes = new TreeMap<>();

	static {
		suffixes.put(1_000L, "K");
		suffixes.put(1_000_000L, "M");
		suffixes.put(1_000_000_000L, "G");
		suffixes.put(1_000_000_000_000L, "T");
		suffixes.put(1_000_000_000_000_000L, "P");
		suffixes.put(1_000_000_000_000_000_000L, "E");
	}

	public ArtistData() {
	}

	public ArtistData(int artistid, String ivrMMNumber, String artist, int albumcount, int trackcount, String image) {
		super();
		this.artistid = artistid;
		this.ivrMMNumber = ivrMMNumber;
		this.artist = artist;
		this.albumcount = albumcount;
		this.trackcount = trackcount;
		this.image = image;
		this.images = new Images(image);
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
		Map.Entry<Long, String> e = suffixes.floorEntry(value);
		Long divideBy = e.getKey();
		String suffix = e.getValue();

		long truncated = value / (divideBy / 10); // the number part of the output times 10
		boolean hasDecimal = truncated < 100 && (truncated / 10d) != (truncated / 10);
		return hasDecimal ? (truncated / 10d) + suffix : (truncated / 10) + suffix;
	}

	public int getArtistid() {
		return artistid;
	}

	public String getIvrMMNumber() {
		return ivrMMNumber;
	}

	public void setIvrMMNumber(String ivrMMNumber) {
		this.ivrMMNumber = ivrMMNumber;
	}

	public void setArtistid(int artistid) {
		this.artistid = artistid;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getAlbumcount() {
		return albumcount;
	}

	public void setAlbumcount(int albumcount) {
		this.albumcount = albumcount;
	}

	public int getTrackcount() {
		return trackcount;
	}

	public void setTrackcount(int trackcount) {
		this.trackcount = trackcount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	public String getPageTitle() {
		return pageTitle;
	}

	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaKeywords() {
		return metaKeywords;
	}

	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	public String getAboutArtist() {
		return aboutArtist;
	}

	public void setAboutArtist(String aboutArtist) {
		this.aboutArtist = aboutArtist;
	}

	public String getTrackShareCount() {
		return trackShareCount;
	}

	public void setTrackShareCount(long trackShareCount) {
		this.trackShareCount = format(trackShareCount);
	}

	public String getTrackFavouriteCount() {
		return trackFavouriteCount;
	}

	public void setTrackFavouriteCount(long trackFavouriteCount) {
		this.trackFavouriteCount = format(trackFavouriteCount);
	}

	public String getTrackPlayCount() {
		return trackPlayCount;
	}

	public void setTrackPlayCount(long trackPlayCount) {
		this.trackPlayCount = format(trackPlayCount);
	}

}
