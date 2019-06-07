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
public class Purchase{
    private String resourceCode;
    private String resourceTitle;
    private String artistName;
    private String albumTitle;
    private String purchaseDate;
    private String expiryDate;
    private int allowDownload;
    private String resourceImageUrl;
    private Images images;

    public Purchase() {
    }

    public Purchase(String resourceCode,String resourceTitle, String artistName, String albumTitle, String purchaseDate, String expiryDate, int allowDownload, String resourceImageUrl) {
        this.resourceCode=resourceCode;
        this.resourceTitle = resourceTitle;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
        this.purchaseDate = purchaseDate;
        this.expiryDate = expiryDate;
        this.allowDownload = allowDownload;
        this.resourceImageUrl = resourceImageUrl;
        this.images=new Images(resourceImageUrl);
    }

    public String getResourceCode() {
        return resourceCode;
    }

    public void setResourceCode(String resourceCode) {
        this.resourceCode = resourceCode;
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

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }
    
    
}
