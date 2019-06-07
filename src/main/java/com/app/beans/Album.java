/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

import com.database.MySQL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class Album {
    private int albumId;
    private String albumTitle;
    private String artistName;
    private int albumRating;
    private int trackCount;
    private String albumImageUrl;
    private Images images;

    public Album() {
    }

    public Album(int albumId, String albumTitle, String artistName, int albumRating, int trackCount, String albumImageUrl) {
        this.albumId = albumId;
        this.albumTitle = albumTitle;
        this.artistName = artistName;
        this.albumRating = albumRating;
        this.trackCount = trackCount;
        this.albumImageUrl = albumImageUrl;
        this.images=new Images(albumImageUrl);
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getAlbumRating() {
        return albumRating;
    }

    public void setAlbumRating(int albumRating) {
        this.albumRating = albumRating;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public String getAlbumImageUrl() {
        return albumImageUrl;
    }

    public void setAlbumImageUrl(String albumImageUrl) {
        this.albumImageUrl = albumImageUrl;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
  

    @SuppressWarnings("finally")
	public static List<Album> getTopAlbum(int countryId, int imageTechRefId) {
        List<Album> lst = new ArrayList<Album>();
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{call `Mziiki_Content`.`GetAlbumMetaData`(3," + countryId + ",0,0," + imageTechRefId + ",0,10)}");
            while (rs.next()) {
                lst.add(new Album(rs.getInt("album_id"), rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"), rs.getInt("album_tracks_count"), rs.getString("image_url")));
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in Mziiki Album.getTopAlbum(int countryId, int imageTechRefId) - " + e.getMessage());
        } finally {
            return lst;
        }
    }

}
