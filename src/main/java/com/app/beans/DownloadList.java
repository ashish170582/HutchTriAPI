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
public class DownloadList extends Root {
    List<DownloadHistory> downloadHistory;

    public DownloadList() {
        super(0, "Success");
    }

    public DownloadList(List<DownloadHistory> downloadHistory) {
        super(0, "Success");
        this.downloadHistory = downloadHistory;
    }

    public List<DownloadHistory> getDownloadHistory() {
        return downloadHistory;
    }

    public void setDownloadHistory(List<DownloadHistory> downloadHistory) {
        this.downloadHistory = downloadHistory;
    }

  

}
