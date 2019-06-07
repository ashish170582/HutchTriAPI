package com.services;

import org.springframework.stereotype.Service;

import com.app.beans.RootResponse;
import com.beans.RequestParameter;


@Service
public interface ContentServices {

	RootResponse search(RequestParameter reqParam);

	RootResponse getHomeData(RequestParameter reqParam);

	RootResponse getGenreArtist(RequestParameter reqParam);

	RootResponse getGenreAlbum(RequestParameter reqParam);

	RootResponse getAllGenre(RequestParameter reqParam);

	RootResponse getArtistTrack(RequestParameter reqParam);

	RootResponse getArtistAlbum(RequestParameter reqParam);

	RootResponse getAllArtist(RequestParameter reqParam);

	RootResponse getAlbumTrack(RequestParameter reqParam);

	RootResponse getAllAlbum(RequestParameter reqParam);

	RootResponse getTrackInfo(RequestParameter reqParam);

	RootResponse getTrackList(RequestParameter reqParam);

	RootResponse getGenreArtistAlbumTrackList(RequestParameter reqParam);

	RootResponse getRecommended(RequestParameter reqParam);

	RootResponse getPlayList(RequestParameter reqParam);

	RootResponse getPlayListTracks(RequestParameter reqParam);

	RootResponse getRadio(RequestParameter reqParam);

	RootResponse getRadioTracks(RequestParameter reqParam);

	RootResponse newReleasedPopularFeaturedAlbum(RequestParameter reqParam);

	RootResponse newReleasedArtist(RequestParameter reqParam);

	RootResponse newReleasedTracks(RequestParameter reqParam);

	RootResponse filterByLetter(RequestParameter reqParam);

	RootResponse recommendedSimilarArtist(RequestParameter reqParam);
	
}
