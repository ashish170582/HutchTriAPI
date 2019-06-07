/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

import com.beans.RadioBean;
import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class DiscoverTab extends Root {

    private List<Album> album;
    private List<Artist> artist;
    private List<FeaturedPlaylist> playlist;
    private List<Genre> genre;
    private List<RadioBean> radio;

    public DiscoverTab(List<Album> album, List<Artist> artist, List<FeaturedPlaylist> playlist, int code, String message) {
        super(code, message);
        this.album = album;
        this.artist = artist;
        this.playlist = playlist;
    }

    public DiscoverTab() {
        super(0, "Success");
    }

    public List<Album> getAlbum() {
        return album;
    }

    public void setAlbum(List<Album> album) {
        this.album = album;
    }

    public List<Artist> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist> artist) {
        this.artist = artist;
    }

    public List<FeaturedPlaylist> getPlaylist() {
        return playlist;
    }

    public void setPlaylist(List<FeaturedPlaylist> playlist) {
        this.playlist = playlist;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<RadioBean> getRadio() {
        return radio;
    }

    public void setRadio(List<RadioBean> radio) {
        this.radio = radio;
    }

    public static DiscoverTab getDiscoverTabData(int countryId,int imageTechRefId){
        DiscoverTab tab=new DiscoverTab();
        tab.setAlbum(Album.getTopAlbum(countryId, imageTechRefId));
        tab.setArtist(Artist.getTopArtist(countryId, imageTechRefId));
        tab.setPlaylist(FeaturedPlaylist.getTopPlaylist(countryId, imageTechRefId));
        tab.setGenre(Genre.getTopGenre(countryId, imageTechRefId));
        tab.setRadio(RadioBean.getTopRadio(countryId, imageTechRefId));        
        return tab;
    }
    

}
