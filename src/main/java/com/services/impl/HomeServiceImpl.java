/*     */ package com.services.impl;

/*     */
/*     */ import com.app.beans.RootResponse;
/*     */ import com.beans.AllItem;
/*     */ import com.beans.Artist;
/*     */ import com.beans.ArtistData;
/*     */ import com.beans.Container;
/*     */ import com.beans.ContainerItem;
/*     */ import com.beans.RequestParameter;
/*     */ import com.beans.Root;
/*     */ import com.beans.Tracks;
/*     */ import com.database.DataBaseProcedures;
/*     */ import com.services.HomeServices;
/*     */ import java.io.PrintStream;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.MessageSource;
/*     */ import org.springframework.stereotype.Service;

/*     */
/*     */ @Service
/*     */ public class HomeServiceImpl/*     */ implements HomeServices
/*     */ {
	/*     */
	/*     */ @Autowired
	/*     */ DataBaseProcedures dbProcedures;
	/*     */
	/*     */ @Autowired
	/*     */ private MessageSource messageSource;
	/*     */ private Root obj;
	/* 31 */ private List list = null;
	private List list1 = null;
	private List list2 = null;
	private List list3 = null;
	private List list4 = null;
	private List list10 = null;

	/*     */
	/*     */ public RootResponse getContainer(RequestParameter reqParam)
	/*     */ {
		/* 37 */ this.obj = new Container(this.dbProcedures.getContainerList(reqParam));
		/* 38 */ return new RootResponse(this.obj);
		/*     */ }

	/*     */
	/*     */ public RootResponse ContainerItem(RequestParameter reqParam) {
		/* 42 */ this.obj = new ContainerItem(this.dbProcedures.getContainerItemList(reqParam.getCountryId(),
				reqParam.getUserId(), reqParam.getNumericId(), reqParam.getImageTechRefId(), reqParam.getStartLimit(),
				reqParam.getEndLimit()));
		/* 43 */ return new RootResponse(this.obj);
		/*     */ }

	/*     */
	/*     */ public RootResponse getArtistInfo(RequestParameter reqParam)
	/*     */ {
		/*     */ try
		/*     */ {
			/* 50 */ boolean following = this.dbProcedures.followingArtist(reqParam);
			/* 51 */ ArtistData ar = this.dbProcedures.artist(reqParam);
			/* 52 */ reqParam.setEventType("artist");
			/* 53 */ reqParam.setId(String.valueOf(reqParam.getArtistId()));
			/* 54 */ this.list = this.dbProcedures.artistRecommendationByArtist(reqParam);
			/* 55 */ this.list1 = this.dbProcedures.getArtistTopTracks(reqParam);
			/* 56 */ this.list2 = this.dbProcedures.getArtistSingleTracks(reqParam);
			/* 57 */ this.list3 = this.dbProcedures.getArtistFeaturedTracks(reqParam);
			/* 58 */ this.list4 = this.dbProcedures.getArtistVideoTracks(reqParam);
			/* 59 */ if ((ar == null) || (ar.getArtist().equalsIgnoreCase("0")))
				/* 60 */ this.obj = new Root(195,
						this.messageSource.getMessage("195", null, new Locale(reqParam.getLanguageCode())));
			/*     */ else
				/* 62 */ this.obj = new Artist(following, ar, this.list, this.list1, this.list2, this.list3,
						this.list4);
			/*     */ }
		/*     */ catch (Exception e) {
			/* 65 */ System.out.println("Exception in HutchTriBeatzMainServlet.CASE 17: - " + e.getMessage());
			/* 66 */ this.obj = new Root(195,
					this.messageSource.getMessage("195", null, new Locale(reqParam.getLanguageCode())));
			/*     */ }
		/*     */
		/* 69 */ return new RootResponse(this.obj);
		/*     */ }

	/*     */
	/*     */ public RootResponse followArtist(RequestParameter reqParam)
	/*     */ {
		/* 74 */ if (reqParam.getEventType().equalsIgnoreCase("follow")) {
			/* 75 */ this.dbProcedures.followArtist(reqParam);
			/* 76 */ this.obj = new Root(336,
					this.messageSource.getMessage("336", null, new Locale(reqParam.getLanguageCode())));
			/*     */ } else {
			/* 78 */ this.dbProcedures.unfollowArtist(reqParam);
			/* 79 */ this.obj = new Root(337,
					this.messageSource.getMessage("337", null, new Locale(reqParam.getLanguageCode())));
			/*     */ }
		/* 81 */ return new RootResponse(this.obj);
		/*     */ }

	/*     */
	/*     */ public RootResponse discoverAllItems(RequestParameter reqParam)
	/*     */ {
		/* 86 */ if ((reqParam.getEventType().equalsIgnoreCase("track"))
				|| (reqParam.getEventType().equalsIgnoreCase("tracks"))) {
			/* 87 */ this.list = this.dbProcedures.getAllItemList( reqParam.getCountryId(),
					reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(),
					reqParam.getEndLimit());
			/* 88 */ if (this.list.isEmpty())
				/* 89 */ this.obj = new Root(114,
						this.messageSource.getMessage("114", null, new Locale(reqParam.getLanguageCode())));
			/*     */ else {
				/* 91 */ this.obj = new Tracks(this.list);
				/*     */ }
			/*     */
			/*     */ }
		/* 95 */ else if (reqParam.getEventType().equalsIgnoreCase("karaoke")) {
			/* 96 */ this.list = this.dbProcedures.getAllItemList( reqParam.getCountryId(),
					reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(),
					reqParam.getEndLimit());
			/* 97 */ List containerlist = this.dbProcedures.getKaraokeContainerList(reqParam);
			/* 98 */ if (this.list.isEmpty())
				/* 99 */ this.obj = new Root(114,
						this.messageSource.getMessage("114", null, new Locale(reqParam.getLanguageCode())));
			/*     */ else {
				/* 101 */ this.obj = new Tracks(this.list, containerlist);
				/*     */ }
			/*     */
			/*     */ }
		/* 105 */ else if (reqParam.getEventType().equalsIgnoreCase("crbt")) {
			/* 106 */ this.list = this.dbProcedures.getAllItemList( reqParam.getCountryId(),
					reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(),
					reqParam.getEndLimit());
			/* 107 */ if (this.list.isEmpty())
				/* 108 */ this.obj = new Root(114,
						this.messageSource.getMessage("114", null, new Locale(reqParam.getLanguageCode())));
			/*     */ else
				/* 110 */ this.obj = new Tracks(this.list);
			/*     */ }
		/*     */ else {
			/* 113 */ List AllItemList = this.dbProcedures.getAllItemList(reqParam,reqParam.getCountryId(),
					reqParam.getEventType(), reqParam.getImageTechRefId(), reqParam.getStartLimit(),
					reqParam.getEndLimit());
			/* 114 */ if ((AllItemList == null) || (AllItemList.size() == 0)) {
				/* 115 */ this.obj = new AllItem(1, "No content available.", AllItemList);
				/*     */ }
			/*     */ else
				/* 118 */ this.obj = new AllItem(AllItemList);
			/*     */ }
		/* 120 */ return new RootResponse(this.obj);
		/*     */ }

	/*     */
	/*     */ public RootResponse getArtistSeeAll(RequestParameter reqParam)
	/*     */ {
		/* 126 */ if ((reqParam.getEventType().equalsIgnoreCase("top"))
				|| (reqParam.getEventType().equalsIgnoreCase("tops")))
			/* 127 */ this.list = this.dbProcedures.getArtistTopTracks(reqParam);
		/* 128 */ else if ((reqParam.getEventType().equalsIgnoreCase("single"))
				|| (reqParam.getEventType().equalsIgnoreCase("singles")))
			/* 129 */ this.list = this.dbProcedures.getArtistSingleTracks(reqParam);
		/* 130 */ else if ((reqParam.getEventType().equalsIgnoreCase("featured"))
				|| (reqParam.getEventType().equalsIgnoreCase("feature")))
			/* 131 */ this.list = this.dbProcedures.getArtistFeaturedTracks(reqParam);
		/* 132 */ else if ((reqParam.getEventType().equalsIgnoreCase("video"))
				|| (reqParam.getEventType().equalsIgnoreCase("videos")))
			/* 133 */ this.list = this.dbProcedures.getArtistVideoTracks(reqParam);
		/*     */ else {
			/* 135 */ this.list = this.dbProcedures.getArtistTopTracks(reqParam);
			/*     */ }
		/* 137 */ if (this.list.isEmpty())
			/* 138 */ this.obj = new Root(114,
					this.messageSource.getMessage("114", null, new Locale(reqParam.getLanguageCode())));
		/*     */ else {
			/* 140 */ this.obj = new Tracks(this.list);
			/*     */ }
		/* 142 */ return new RootResponse(this.obj);
		/*     */ }
	/*     */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * com.services.impl.HomeServiceImpl JD-Core Version: 0.6.0
 */