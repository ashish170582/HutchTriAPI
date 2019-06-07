package com.services;

import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.RequestParameter;


@Service
public interface HomeServices {

	RootResponse getContainer(RequestParameter reqParam);

	RootResponse ContainerItem(RequestParameter reqParam);

	RootResponse getArtistInfo(RequestParameter reqParam);

	RootResponse followArtist(RequestParameter reqParam);

	RootResponse discoverAllItems(RequestParameter reqParam);

	RootResponse getArtistSeeAll(RequestParameter reqParam);


	
}
