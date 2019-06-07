package com.services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.beans.KaraokeUpload;
import com.app.beans.RootResponse;
import com.beans.RequestParameter;
import com.beans.Root;


@Service
public interface KaraokeService {

	RootResponse getKaraokeInfo(RequestParameter reqParam);

	RootResponse getKaraokeComment(RequestParameter reqParam);

	RootResponse getKaraokeArtistFollow(RequestParameter reqParam);


	Root uploadKaraokeSong(HttpServletRequest request, HttpServletResponse response, MultipartFile uploadFile);


}
