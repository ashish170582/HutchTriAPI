/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.beans;

import java.util.List;

/**
 *
 * @author Rajat.kumar
 */
public class DownloadPackageList extends Root {
    List<DownloadPackage> downloadPackage;

    public DownloadPackageList() {
        super(0, "Success");
    }
    
    public DownloadPackageList(List<DownloadPackage> downloadPackage) {
        super(0, "Success");
        this.downloadPackage = downloadPackage;
    }

    public List<DownloadPackage> getDownloadPackage() {
        return downloadPackage;
    }

    public void setDownloadPackage(List<DownloadPackage> downloadPackage) {
        this.downloadPackage = downloadPackage;
    }

   
}
