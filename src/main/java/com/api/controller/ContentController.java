package com.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.beans.RequestParameter;
import com.services.impl.ContentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="Content" ,description="EVT (9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,84,85,86,92)")
public class ContentController {
	
	@Autowired
	ContentServiceImpl contentServiceImpl;
	
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	})

@ApiOperation(value = "Search Evt(9)", response = Iterable.class)
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public @ResponseBody Object search(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.search(reqParam);
	}

	@ApiOperation(value = "Get Home Item Evt(10)", response = Iterable.class)
	@RequestMapping(value = "/getHomeData", method = RequestMethod.POST)
	public @ResponseBody Object getHomeData(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getHomeData(reqParam);
	}
	
	@ApiOperation(value = "Get Track Info Evt(11)", response = Iterable.class)
	@RequestMapping(value = "/getTrackInfo", method = RequestMethod.POST)
	public @ResponseBody Object getTrackInfo(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getTrackInfo(reqParam);
	}
	
	@ApiOperation(value = "Get All Album Evt(12)", response = Iterable.class)
	@RequestMapping(value = "/getAllAlbum", method = RequestMethod.POST)
	public @ResponseBody Object getAllAlbum(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getAllAlbum(reqParam);
	}
	@ApiOperation(value = "Get Album Tracks Evt(13)", response = Iterable.class)
	@RequestMapping(value = "/getAlbumTrack", method = RequestMethod.POST)
	public @ResponseBody Object getAlbumTrack(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getAlbumTrack(reqParam);
	}
	@ApiOperation(value = "Get All Artist Evt(15)", response = Iterable.class)
	@RequestMapping(value = "/getAllArtist", method = RequestMethod.POST)
	public @ResponseBody Object getAllArtist(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getAllArtist(reqParam);
	}
	@ApiOperation(value = "Get Artist Album Evt(16)", response = Iterable.class)
	@RequestMapping(value = "/getArtistAlbum", method = RequestMethod.POST)
	public @ResponseBody Object getArtistAlbum(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getArtistAlbum(reqParam);
	}
	@ApiOperation(value = "Get Artist Track Evt(17)", response = Iterable.class)
	@RequestMapping(value = "/getArtistTrack", method = RequestMethod.POST)
	public @ResponseBody Object getArtistTrack(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getArtistTrack(reqParam);
	}

	@ApiOperation(value = "Get All Genre Evt(18)", response = Iterable.class)
	@RequestMapping(value = "/getAllGenre", method = RequestMethod.POST)
	public @ResponseBody Object getAllGenre(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getAllGenre(reqParam);
	}
	@ApiOperation(value = "Get Genre Album Evt(19)", response = Iterable.class)
	@RequestMapping(value = "/getGenreAlbum", method = RequestMethod.POST)
	public @ResponseBody Object getGenreAlbum(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getGenreAlbum(reqParam);
	}
	@ApiOperation(value = "Get Genre Artist List Evt(20)", response = Iterable.class)
	@RequestMapping(value = "/getGenreArtist", method = RequestMethod.POST)
	public @ResponseBody Object getGenreArtist(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getGenreArtist(reqParam);
	}
	
	@ApiOperation(value = "GENRE_TRACKS_LIST Evt(21)", response = Iterable.class)
	@RequestMapping(value = "/getTrackList", method = RequestMethod.POST)
	public @ResponseBody Object getTrackList(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getTrackList(reqParam);
	}
	
	@ApiOperation(value = "GENRE_ARTIST_ALBUM_LIST/ GENRE_ARTIST_TRACK_LIST  Evt(22)",notes="etype=(album/ tracks) for genre artist album / genre artist tracks. ", response = Iterable.class)
	@RequestMapping(value = "/getGenreArtistAlbumTrackList", method = RequestMethod.POST)
	public @ResponseBody Object getGenreArtistAlbumTrackList(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getGenreArtistAlbumTrackList(reqParam);
	}
	
	@ApiOperation(value = "RECOMMENDED (ALBUM/ARTIST/TRACK)   Evt(23)",notes="etype=(album/artist/ track) and via=(album/artist/track/genre)", response = Iterable.class)
	@RequestMapping(value = "/getRecommended", method = RequestMethod.POST)
	public @ResponseBody Object getRecommended(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getRecommended(reqParam);
	}
	
	@ApiOperation(value = "GET_PLAYLIST   Evt(24)", response = Iterable.class)
	@RequestMapping(value = "/getPlayList", method = RequestMethod.POST)
	public @ResponseBody Object getPlayList(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getPlayList(reqParam);
	}
	
	@ApiOperation(value = "GET_PLAYLIST_TRACKS   Evt(25)", response = Iterable.class)
	@RequestMapping(value = "/getPlayListTracks", method = RequestMethod.POST)
	public @ResponseBody Object getPlayListTracks(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getPlayListTracks(reqParam);
	}
	
	@ApiOperation(value = "GET_RADIO  Evt(26)", response = Iterable.class)
	@RequestMapping(value = "/getRadio", method = RequestMethod.POST)
	public @ResponseBody Object getRadio(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getRadio(reqParam);
	}
	@ApiOperation(value = "GET_RADIO_TRACKS  Evt(27)", response = Iterable.class)
	@RequestMapping(value = "/getRadioTracks", method = RequestMethod.POST)
	public @ResponseBody Object getRadioTracks(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.getRadioTracks(reqParam);
	}
	
	
	@ApiOperation(value = "NEW RELEASED/POPULAR/FEATURED - [ALBUM]   Evt(83)", response = Iterable.class)
	@RequestMapping(value = "/newReleasedPopularFeaturedAlbum", method = RequestMethod.POST)
	public @ResponseBody Object newReleasedPopularFeaturedAlbum(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.newReleasedPopularFeaturedAlbum(reqParam);
	}
	
	@ApiOperation(value = "NEW RELEASED - [ARTIST]   Evt(84)", response = Iterable.class)
	@RequestMapping(value = "/newReleasedArtist", method = RequestMethod.POST)
	public @ResponseBody Object newReleasedArtist(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.newReleasedArtist(reqParam);
	}
	
	@ApiOperation(value = "NEW RELEASED - [TRACK]   Evt(85)", response = Iterable.class)
	@RequestMapping(value = "/newReleasedTracks", method = RequestMethod.POST)
	public @ResponseBody Object newReleasedTracks(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.newReleasedTracks(reqParam);
	}
	
	@ApiOperation(value = "FILTER_BY_LETTER   Evt(86)", response = Iterable.class)
	@RequestMapping(value = "/filterByLetter", method = RequestMethod.POST)
	public @ResponseBody Object filterByLetter(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.filterByLetter(reqParam);
	}
	
	@ApiOperation(value = "RECOMMENDED/SIMILAR ARTIST   Evt(92)", response = Iterable.class)
	@RequestMapping(value = "/recommendedSimilarArtist", method = RequestMethod.POST)
	public @ResponseBody Object recommendedSimilarArtist(
			@RequestBody RequestParameter reqParam) {
	     	return   contentServiceImpl.recommendedSimilarArtist(reqParam);
	}
	
}
