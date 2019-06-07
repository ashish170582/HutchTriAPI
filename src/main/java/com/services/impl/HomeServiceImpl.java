package com.services.impl;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import com.beans.ContainerItem;
import com.beans.ContainerItemBean;
import com.app.beans.RootResponse;
import com.beans.AllItem;
import com.beans.Artist;
import com.beans.ArtistData;
import com.beans.Container;
import com.beans.ContainerBean;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.Tracks;
import com.database.DataBaseProcedures;
import com.services.HomeServices;

@Service
public class HomeServiceImpl implements HomeServices {
	
	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	private MessageSource messageSource;
	private Root obj;
   @SuppressWarnings({ "rawtypes", "unused" })
   private  List list = null, list1 = null, list2 = null, list3 = null, list4 = null,list10=null;

	//GET_HOME_ITEMS
	@Override
	public RootResponse getContainer(RequestParameter reqParam) {
		obj = new Container(dbProcedures.getContainerList(reqParam));
		return new RootResponse(obj);
	}
	@Override
	public RootResponse ContainerItem(RequestParameter reqParam) {
		obj = new ContainerItem(dbProcedures.getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(), reqParam.getNumericId(), reqParam.getImageTechRefId(), reqParam.getStartLimit(), reqParam.getEndLimit()));
		return new RootResponse(obj);
	}
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getArtistInfo(RequestParameter reqParam) {

        try {
            boolean following = dbProcedures.followingArtist(reqParam);
            ArtistData ar = dbProcedures.artist(reqParam);
            reqParam.setEventType("artist");
            reqParam.setId(String.valueOf(reqParam.getArtistId()));
            list = dbProcedures.artistRecommendationByArtist(reqParam);
            list1 = dbProcedures.getArtistTopTracks(reqParam);
            list2 = dbProcedures.getArtistSingleTracks(reqParam);
            list3 = dbProcedures.getArtistFeaturedTracks(reqParam);
            list4 = dbProcedures.getArtistVideoTracks(reqParam);
            if (ar == null || ar.getArtist().equalsIgnoreCase("0")) {
            	obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Artist(following, ar, list, list1, list2, list3, list4);
            }
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.CASE 17: - " + e.getMessage());
            obj = new Root(195, messageSource.getMessage("195", null,new Locale(reqParam.getLanguageCode())));
        }
		
		return new RootResponse(obj);
	}
	
	@Override
	public RootResponse followArtist(RequestParameter reqParam) {
        if (reqParam.getEventType().equalsIgnoreCase("follow")) {
        	dbProcedures.followArtist(reqParam);
        	obj = new Root(321, messageSource.getMessage("321", null,new Locale(reqParam.getLanguageCode())));
        } else {
        	dbProcedures.unfollowArtist(reqParam);
        	obj = new Root(321, messageSource.getMessage("321", null,new Locale(reqParam.getLanguageCode())));
        }
		return new RootResponse(obj);
	}
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse discoverAllItems(RequestParameter reqParam) {
        if (reqParam.getEventType().equalsIgnoreCase("track") || reqParam.getEventType().equalsIgnoreCase("tracks")) {
            list = dbProcedures.getAllTracks(reqParam, reqParam.getCountryId(), reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(), reqParam.getEndLimit());
            if (list.isEmpty()) {
            	obj = new Root(114, messageSource.getMessage("114", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Tracks(list);
            }
        } 
        //For get Karaoke Song List
                else if (reqParam.getEventType().equalsIgnoreCase("karaoke")) {
            list =dbProcedures.getAllTracks(reqParam, reqParam.getCountryId(), reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(), reqParam.getEndLimit());
            List<ContainerBean> containerlist = dbProcedures.getKaraokeContainerList(reqParam);
            if (list.isEmpty()) {
            	obj = new Root(114, messageSource.getMessage("114", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Tracks(list,containerlist);
            } 
        }                        
        
        else if (reqParam.getEventType().equalsIgnoreCase("crbt")) {
            list = dbProcedures.getCrbtTracks(reqParam, reqParam.getCountryId(), reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(), reqParam.getEndLimit());
            if (list.isEmpty()) {
            	obj = new Root(114, messageSource.getMessage("114", null,new Locale(reqParam.getLanguageCode())));
            } else {
                obj = new Tracks(list);
            }
        } else {
        	List<ContainerItemBean> AllItemList=dbProcedures.getAllItemList(reqParam.getCountryId(), reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(), reqParam.getEndLimit());
        	if(AllItemList==null || AllItemList.size()==0){
        		obj=new AllItem(1,"No content available.",AllItemList);
        	}
        	else
        		obj=new AllItem(AllItemList);
        }
		return new RootResponse(obj);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getArtistSeeAll(RequestParameter reqParam) {
        if (reqParam.getEventType().equalsIgnoreCase("top") || reqParam.getEventType().equalsIgnoreCase("tops")) {
            list = dbProcedures.getArtistTopTracks(reqParam);
        } else if (reqParam.getEventType().equalsIgnoreCase("single") || reqParam.getEventType().equalsIgnoreCase("singles")) {
            list = dbProcedures.getArtistSingleTracks(reqParam);
        } else if (reqParam.getEventType().equalsIgnoreCase("featured") || reqParam.getEventType().equalsIgnoreCase("feature")) {
            list = dbProcedures.getArtistFeaturedTracks(reqParam);
        } else if (reqParam.getEventType().equalsIgnoreCase("video") || reqParam.getEventType().equalsIgnoreCase("videos")) {
            list = dbProcedures.getArtistVideoTracks(reqParam);
        } else {
            list = dbProcedures.getArtistTopTracks(reqParam);
        }
        if (list.isEmpty()) {
            obj = new Root(114, messageSource.getMessage("114", null,new Locale(reqParam.getLanguageCode())));
        } else {
            obj = new Tracks(list);
        }
		return new RootResponse(obj);
	}
}
