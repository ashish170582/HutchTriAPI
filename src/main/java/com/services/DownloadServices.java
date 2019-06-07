package com.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.RequestParameter;


@Service
public interface DownloadServices {

	RootResponse saveTrackOfflineRequest(RequestParameter reqParam);

	RootResponse successfullySaveTrackOffline(RequestParameter reqParam);

	RootResponse getUserOfflineInformation(RequestParameter reqParam);

	RootResponse offLineStreamingLogs(RequestParameter reqParam);


	void downloadTrack(HttpServletRequest request, HttpServletResponse response);

	RootResponse playDone(RequestParameter reqParam);

}
