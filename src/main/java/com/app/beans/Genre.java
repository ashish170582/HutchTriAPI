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
public class Genre {

    private int genreId;
    private String genreName;
    private String genreImageUrl;
    private Images images;

    public Genre(int genreId, String genreName, String genreImageUrl) {
        this.genreId = genreId;
        this.genreName = genreName;
        this.genreImageUrl = genreImageUrl;
        this.images=new Images(genreImageUrl);
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getGenreImageUrl() {
        return genreImageUrl;
    }

    public void setGenreImageUrl(String genreImageUrl) {
        this.genreImageUrl = genreImageUrl;
    }

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

//    @SuppressWarnings("finally")
//	protected static List<Genre> getTopGenre(int countryId, int imageTechRefId) {
//        List<Genre> lst = new ArrayList<Genre>();
//        try {
//            MySQL mysql = new MySQL();
//            ResultSet rs = mysql.prepareCall("{CALL `Mziiki_Content`.`GetGenreMetaData`(1," + countryId + ",1,0,'iPhone OS','OperatingSystemVersion','DeviceModel','DeviceId()','DevicePin',0,0,0," + imageTechRefId + ",0,10)}");
//            while (rs.next()) {
//                lst.add(new Genre(rs.getInt("genre_id"), rs.getString("genre_name"), rs.getString("image_url")));
//            }
//            mysql.close();
//        } catch (Exception e) {
//            System.out.println("Exception in Mziiki Genre.getTopGenre(int countryId, int imageTechRefId) - " + e.getMessage());
//        } finally {
//            return lst;
//        }
//    }
}
