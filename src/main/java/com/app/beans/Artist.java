/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;


import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class Artist {
    private int artistId;
    private String artistName;
    private int albumCount;
    private int trackCount;
    private String artistImageUrl;
    private Images images;

    public Artist(int artistId, String artistName, int albumCount, int trackCount, String artistImageUrl) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.albumCount = albumCount;
        this.trackCount = trackCount;
        this.artistImageUrl = artistImageUrl;
        this.images=new Images(artistImageUrl);
    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getAlbumCount() {
        return albumCount;
    }

    public void setAlbumCount(int albumCount) {
        this.albumCount = albumCount;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getArtistImageUrl() {
        return artistImageUrl;
    }

    public void setArtistImageUrl(String artistImageUrl) {
        this.artistImageUrl = artistImageUrl;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

//
//
//    
//    @SuppressWarnings("finally")
//	public static List<Artist> getTopArtist(int countryId,int imageTechRefId) {
//        List<Artist> lst = new ArrayList<Artist>();
//        try {
//            MySQL mysql = new MySQL();
//            ResultSet rs = mysql.prepareCall("{CALL `Mziiki_Content`.`GetArtistMetaData`(1," + countryId + ",0,0," + imageTechRefId + ",0,10)}");
//            while (rs.next()) {
//                lst.add(new Artist(rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"), rs.getString("image_url")));
//            }
//            mysql.close();
//        } catch (Exception e) {
//            System.out.println("Exception in Mziiki Artist.getTopArtist(int countryId,int imageTechRefId) - " + e.getMessage());
//        } finally {
//            return lst;
//        }
//    }
}
