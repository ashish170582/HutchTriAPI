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
public class FeaturedPlaylist {

    private int playlistId;
    private String playlistName;
    private String playlistDescription;
    private String playlistImageUrl;
    private int trackCount;
    private Images images;
    

    public FeaturedPlaylist() {
    }

    public FeaturedPlaylist(int playlistId, String playlistName, String playlistDescription, String playlistImageUrl,int trackCount) {
        this.playlistId = playlistId;
        this.playlistName = playlistName;
        this.playlistDescription = playlistDescription;
        this.playlistImageUrl = playlistImageUrl;
        this.trackCount=trackCount;
        this.images=new Images(playlistImageUrl);
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistDescription() {
        return playlistDescription;
    }

    public void setPlaylistDescription(String playlistDescription) {
        this.playlistDescription = playlistDescription;
    }

    public String getPlaylistImageUrl() {
        return playlistImageUrl;
    }

    public void setPlaylistImageUrl(String playlistImageUrl) {
        this.playlistImageUrl = playlistImageUrl;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    @SuppressWarnings("finally")
	public static List<FeaturedPlaylist> getTopPlaylist(int countryId, int imageTechRefId) {
        List<FeaturedPlaylist> lst = new ArrayList<FeaturedPlaylist>();
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `Mziiki_Content`.`GetFeaturedPlaylistMetaData`(1," + countryId + ",1,0,'iPhone OS','OSV','DeviceModel','DeviceId()','getDevicePin',0,0," + imageTechRefId + ",0,10)}");
            while (rs.next()) {
                lst.add(new FeaturedPlaylist(rs.getInt("playlist_id"), rs.getString("playlist_name"), rs.getString("playlist_desc"), rs.getString("image_url"),rs.getInt("track_count")));
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in Mziiki FeaturedPlaylist.getTopPlaylist(int countryId,int imageTechRefId) - " + e.getMessage());
        } finally {
            return lst;
        }
    }

}
