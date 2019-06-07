package com.services.impl;

import java.util.List;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.app.beans.RootResponse;
import com.beans.Album;
import com.beans.AlbumData;
import com.beans.Albums;
import com.beans.ArtistAlbum;
import com.beans.ArtistData;
import com.beans.ArtistTrack;
import com.beans.Artists;
import com.beans.FeaturedPlaylist;
import com.beans.FeaturedPlaylistBean;
import com.beans.FeaturedPlaylistTracks;
import com.beans.Genre;
import com.beans.GenreAlbums;
import com.beans.GenreArtists;
import com.beans.GenreBean;
import com.beans.GenreTracks;
import com.beans.HomeItems;
import com.beans.Radio;
import com.beans.RadioBean;
import com.beans.RadioTracks;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.TrackData;
import com.beans.Tracks;
import com.database.DataBaseProcedures;
import com.services.ContentServices;

@Service
public class ContentServiceImpl implements ContentServices {
	
	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	private MessageSource messageSource;
	private Root obj;
    @SuppressWarnings("rawtypes")
	private  List list = null;

 //SEARCH(ALBUM/ARTIST/SONGS)
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse search(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("tracks") || reqParam.getEventType().equalsIgnoreCase("track")) {
                list = dbProcedures.trackSearch(reqParam);
                if (list.isEmpty()) {
                    obj = new Root(113, messageSource.getMessage("113", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("albums") || reqParam.getEventType().equalsIgnoreCase("album")) {
                list = dbProcedures.albumSearch(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(111, messageSource.getMessage("111", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("artists") || reqParam.getEventType().equalsIgnoreCase("artist")) {
                list = dbProcedures.artistSearch(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(112, messageSource.getMessage("112", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("playlist") || reqParam.getEventType().equalsIgnoreCase("playlists")) {
                list = dbProcedures.playlistSearch(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(112, messageSource.getMessage("112", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new FeaturedPlaylist(list);
                }
            } else {
            	obj = new Root(114, messageSource.getMessage("114", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 9: - " + e.getMessage());
        	obj = new Root(114, messageSource.getMessage("114", null,new Locale(reqParam.getLanguageCode())));

        }
		return new RootResponse(obj);
	}
	//GET_HOME_ITEMS
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getHomeData(RequestParameter reqParam) {
        try {
            list = dbProcedures.getHomeItem(reqParam);
            if (list.isEmpty()) {
                obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new HomeItems(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 10: - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_TRACK_INFO
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getTrackInfo(RequestParameter reqParam) {
        try {
            list = dbProcedures.trackInfo(reqParam);
            if (list.isEmpty()) {
                obj = new Root(197, messageSource.getMessage("197", null,new Locale(reqParam.getLanguageCode())));
            } else {
                try {
                    TrackData ddata = (TrackData) list.get(0);                                    
                    if (ddata.getRcode().startsWith("-")) {                                        
                        obj = new Root(196, messageSource.getMessage("196", null,new Locale(reqParam.getLanguageCode())));
                    } else {
                        obj = new Tracks(list);
                    }
                } catch (Exception e) {
                    obj = new Tracks(list);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 11: - " + e.getMessage());
            obj = new Root(197, messageSource.getMessage("197", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_ALL_ALBUM
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getAllAlbum(RequestParameter reqParam) {
        try {
            list = dbProcedures.allAlbums(reqParam);
            if (list.isEmpty()) {
                obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Albums(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 12: - " + e.getMessage());
            obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	 //GET_ALBUM_TRACKS
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getAlbumTrack(RequestParameter reqParam) {
        try {
            AlbumData alb = dbProcedures.album(reqParam);
            list = dbProcedures.albumTracks(reqParam );
            if (list.isEmpty()) {
                obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Album(alb, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 14: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_ALL_ARTIST
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getAllArtist(RequestParameter reqParam) {
        try {
            list = dbProcedures.allArtists(reqParam);
            if (list.isEmpty()) {
                obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Artists(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 15: - " + e.getMessage());
            obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_ARTIST_ALBUMS
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getArtistAlbum(RequestParameter reqParam) {
        try {
            ArtistData artistData = dbProcedures.artist(reqParam);
            list = dbProcedures.artistAlbums(reqParam);
            if (list.isEmpty() || artistData == null) {
                obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new ArtistAlbum(artistData, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 16: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_ARTIST_TRACKS
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getArtistTrack(RequestParameter reqParam) {
        try {
            ArtistData artistData = dbProcedures.artist(reqParam);
            list =dbProcedures.artistTracks(reqParam);
            if (list.isEmpty() || artistData == null) {
                obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new ArtistTrack(artistData, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 17: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//Get All Genre
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getAllGenre(RequestParameter reqParam) {
        try {
            list = dbProcedures.allGenre(reqParam);
            if (list.isEmpty()) {
                obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Genre(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 18: - " + e.getMessage());
            obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_GENRE_ALBUMS
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getGenreAlbum(RequestParameter reqParam) {
		GenreBean genre;
        try {
            genre = dbProcedures.genre(reqParam);
            list = dbProcedures.genreAlbums(reqParam);
            if (list.isEmpty() || genre == null) {
                obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new GenreAlbums(genre, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 19: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_Genre_ARTIST
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getGenreArtist(RequestParameter reqParam) {
		GenreBean genre;
        try {
            genre =dbProcedures.genre(reqParam);
            list = dbProcedures.genreArtists(reqParam);
            if (list.isEmpty() || genre == null) {
                obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new GenreArtists(genre, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 20: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	//-----------After 20 ------------------------------------------------------------------
	
	
	//GENRE_TRACKS_LIST 
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getTrackList(RequestParameter reqParam) {
		GenreBean genre;
        try {
            genre = dbProcedures.genre(reqParam);
            list = dbProcedures.genreTrackList(reqParam);
            if (list.isEmpty() || genre == null) {
            	obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new GenreTracks(genre, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 21: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GENRE_ARTIST_ALBUM_LIST/ GENRE_ARTIST_TRACK_LIST 
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getGenreArtistAlbumTrackList(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("album") || reqParam.getEventType().equalsIgnoreCase("albums")) {
                list = dbProcedures.genreArtistAlbums(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            } else {
                list = dbProcedures.genreArtistTrackList(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 22: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//RECOMMENDED (ALBUM/ARTIST/TRACK) 
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getRecommended(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("albums") || reqParam.getEventType().equalsIgnoreCase("album")) {
                list = dbProcedures.albumRecommendation(reqParam);
                if (list.isEmpty()) {
                    obj = new Root(191, messageSource.getMessage("191", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("artists") || reqParam.getEventType().equalsIgnoreCase("artist")) {
                list = dbProcedures.artistRecommendation(reqParam);
                if (list.isEmpty()) {
                    obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("tracks") || reqParam.getEventType().equalsIgnoreCase("track")) {
                list = dbProcedures.trackRecommendation(reqParam);
                if (list.isEmpty()) {
                    obj = new Root(193, messageSource.getMessage("193", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            } else {
                obj = new Root(100, messageSource.getMessage("100", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 23: - " + e.getMessage());
            obj = new Root(100, messageSource.getMessage("100", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_FEATURED_PLAYLIST
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getPlayList(RequestParameter reqParam) {
        try {
            list = dbProcedures.getFeaturedPlaylist(reqParam);
            if (list.isEmpty()) {
                obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new FeaturedPlaylist(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 24: - " + e.getMessage());
            obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_FEATURED_PLAYLIST_TRACKS
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getPlayListTracks(RequestParameter reqParam) {
        try {
            FeaturedPlaylistBean playlist = dbProcedures.getFeaturedPlaylistInfo(reqParam);
            list = dbProcedures.featuredPlaylistTrackList(reqParam);
            if (list.isEmpty()) {
                obj = new Root(132, messageSource.getMessage("132", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new FeaturedPlaylistTracks(playlist, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 25: - " + e.getMessage());
            obj = new Root(132, messageSource.getMessage("132", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_RADIO 
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getRadio(RequestParameter reqParam) {
        try {
            list = dbProcedures.getRadio(reqParam);
            if (list.isEmpty()) {
                obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Radio(list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 26: - " + e.getMessage());
            obj = new Root(121, messageSource.getMessage("121", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	//GET_RADIO_TRACKS 
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getRadioTracks(RequestParameter reqParam) {
        try {
            RadioBean radio = dbProcedures.getRadioInfo(reqParam);
            list = dbProcedures.radioTrackList(reqParam);
            if (list.isEmpty()) {
            	obj = new Root(132, messageSource.getMessage("132", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new RadioTracks(radio, list);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 27: - " + e.getMessage());
            obj = new Root(132, messageSource.getMessage("132", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	
	//----------------------------------------------------------------------------------
	
	//New/Popular/Featured Albums
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse newReleasedPopularFeaturedAlbum(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("1")) {
                list = dbProcedures.newAlbums(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("2")) {
                list = dbProcedures.popularAlbums(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("3")) {
                list = dbProcedures.featuredAlbums(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 83): - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse newReleasedArtist(RequestParameter reqParam) {
        //New/Popular/Featured Artists
        try {
            if (reqParam.getEventType().equalsIgnoreCase("1")) {
                list = dbProcedures.newArtists(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("2")) {
                list = dbProcedures.popularAlbums(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("3")) {
                list = dbProcedures.featuredArtists(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 84): - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse newReleasedTracks(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("1")) {
                list = dbProcedures.newTracks(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("2")) {
                list = dbProcedures.popularTracks(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("3")) {
                list = dbProcedures.featuredTracks(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(116, messageSource.getMessage("116", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 85): - " + e.getMessage());
            obj = new Root(110, messageSource.getMessage("110", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse filterByLetter(RequestParameter reqParam) {
        try {
            if (reqParam.getEventType().equalsIgnoreCase("1")) {
                list = dbProcedures.albumFilter(reqParam);
                if (list.isEmpty()) {
                    obj = new Root(251, messageSource.getMessage("251", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Albums(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("2")) {
                list = dbProcedures.artistFilter(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(252, messageSource.getMessage("252", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("3")) {
                list = dbProcedures.playlistFilter(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(253, messageSource.getMessage("253", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new FeaturedPlaylist(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("4")) {
                list = dbProcedures.genreFilter(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(254, messageSource.getMessage("254", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("5")) {
                list = dbProcedures.trackFilter(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(255, messageSource.getMessage("255", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Tracks(list);
                }
            } else {
            	obj = new Root(256, messageSource.getMessage("256", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 86): - " + e.getMessage());
            obj = new Root(256, messageSource.getMessage("256", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse recommendedSimilarArtist(RequestParameter reqParam) {
        //Recommended Artists
        try {
            if (reqParam.getEventType().equalsIgnoreCase("artist")) {
                list = dbProcedures.artistRecommendationByArtist(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("album")) {
                list = dbProcedures.artistRecommendationByAlbum(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("genre")) {
                list = dbProcedures.artistRecommendationByGenre(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("playlist")) {
                list = dbProcedures.artistRecommendationByPlaylist(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else if (reqParam.getEventType().equalsIgnoreCase("track")) {
                list = dbProcedures.artistRecommendationByTrack(reqParam);
                if (list.isEmpty()) {
                	obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
                } else {
                    obj = new Artists(list);
                }
            } else {
            	obj = new Root(100, messageSource.getMessage("100", null,new Locale(reqParam.getLanguageCode())));
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.(case 92): - " + e.getMessage());
            obj = new Root(192, messageSource.getMessage("192", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	
	
}
