/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

/**
 *
 * @author Rajat.kumar
 */
public class DownloadHistory{
    private String downloadDate;
    private String resourceCode;
    private int downloadTypeId;
    private String resourceTitle;
    private String artistName;
    private String albumTitle;
    private String expiryDate;
    private int allowDownload;
    private String resourceImageUrl;

    public DownloadHistory() {
    }

    public DownloadHistory(String downloadDate, String resourceCode, int downloadTypeId, String resourceTitle, String artistName, String albumTitle, String expiryDate, int allowDownload, String resourceImageUrl) {
        this.downloadDate = downloadDate;
        this.resourceCode = resourceCode;
        this.downloadTypeId = downloadTypeId;
        this.resourceTitle = resourceTitle;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.expiryDate = expiryDate;
        this.allowDownload = allowDownload;
        this.resourceImageUrl = resourceImageUrl;
    }

    public String getDownloadDate() {
        return downloadDate;
    }

    public void setDownloadDate(String downloadDate) {
        this.downloadDate = downloadDate;
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
    }

    public int getDownloadTypeId() {
        return downloadTypeId;
    }

    public void setDownloadTypeId(int downloadTypeId) {
        this.downloadTypeId = downloadTypeId;
    }

    public String getResourceTitle() {
        return resourceTitle;
    }

    public void setResourceTitle(String resourceTitle) {
        this.resourceTitle = resourceTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getAllowDownload() {
        return allowDownload;
    }

    public void setAllowDownload(int allowDownload) {
        this.allowDownload = allowDownload;
    }

    public String getResourceImageUrl() {
        return resourceImageUrl;
    }

    public void setResourceImageUrl(String resourceImageUrl) {
        this.resourceImageUrl = resourceImageUrl;
    }

    
}
