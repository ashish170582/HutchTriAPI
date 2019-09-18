package com.services.impl;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.beans.Keraoke;
import com.app.beans.KaraokeUpload;
import com.app.beans.RootResponse;
import com.beans.ContainerBean;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.karaokeTrackData;
import com.database.DataBaseProcedures;
import com.services.KaraokeService;
import com.utility.KaraokeUtility;
import com.beans.*;



@Service
public class KaraokeServiceImpl implements KaraokeService {

	@Autowired
	DataBaseProcedures dbProcedures;
	@Autowired
	private MessageSource messageSource;
	private Root obj;

	@Autowired
	private KaraokeUtility karaokeUtility;
	
	@SuppressWarnings("rawtypes")
	private List list = null;

	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getKaraokeInfo(RequestParameter reqParam) {
		
		if(reqParam.getTabId()==4) {
			karaokeTrackData karaokeTrackData=dbProcedures.getkaraokeSongInfo(reqParam);
			if(karaokeTrackData==null) {
				obj = new Root(197, messageSource.getMessage("197", null, new Locale(reqParam.getLanguageCode())));	
			}else
				obj=new  KaraokeInfo(karaokeTrackData);
		}
		else {
		try {
			String count = dbProcedures.getFollowArtist(reqParam);
			System.out.println("count :::: "+count);
			List<ContainerBean> containerlist = dbProcedures.getKaraokeContainerList(reqParam);
			list = (List<karaokeTrackData>) dbProcedures.karaokeInfo(reqParam);
			if (list.isEmpty()) {
				obj = new Root(197, messageSource.getMessage("197", null, new Locale(reqParam.getLanguageCode())));
			} else {
				try {
					obj = new Keraoke(list, containerlist, Integer.parseInt(count.split("#")[0]),
							Integer.parseInt(count.split("#")[1]) , Integer.parseInt(count.split("#")[2]) != 0);
				} catch (Exception e) {
					obj = new Keraoke(list, containerlist, Integer.parseInt(count.split("#")[0]),
							Integer.parseInt(count.split("#")[1]),  Integer.parseInt(count.split("#")[2]) != 0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();			
			obj = new Root(197, messageSource.getMessage("197", null, new Locale(reqParam.getLanguageCode())));
		}
		}
		return new RootResponse(obj);
	}

	@Override
	public RootResponse getKaraokeComment(RequestParameter reqParam) {
		try {
			if (reqParam.getEventType().equalsIgnoreCase("4")) {
				Map<Integer, List<KaraokeCommentData>> map = dbProcedures.getKaraokeCommentList(reqParam);
				obj = new KaraokeComment((Integer) map.keySet().toArray()[0], map.get(map.keySet().toArray()[0]));
			} else {
				int responseCode = dbProcedures.karaokeComment(reqParam);
				obj = new Root(responseCode, messageSource.getMessage(String.valueOf(responseCode), null,
						new Locale(reqParam.getLanguageCode())));
			}
		} catch (Exception e) {
			obj = new Root(110, messageSource.getMessage("110", null, new Locale(reqParam.getLanguageCode())));
			System.out.println("Exception in HutchTriBeatzMainServlet. CASE 241: - " + e.getMessage());
		}
		return new RootResponse(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public RootResponse getKaraokeArtistFollow(RequestParameter reqParam) {
		
		
		try {
			if (reqParam.getEventType().equalsIgnoreCase("1")) {
				dbProcedures.FOLLOWKARAOKEARTIST(reqParam);
				obj = new Root(336, messageSource.getMessage("336", null, new Locale(reqParam.getLanguageCode())));
			} else if (reqParam.getEventType().equalsIgnoreCase("2")) {
				dbProcedures.FOLLOWKARAOKEARTIST(reqParam);
				obj = new Root(337, messageSource.getMessage("337", null, new Locale(reqParam.getLanguageCode())));
			} else if (reqParam.getEventType().equalsIgnoreCase("3") || reqParam.getEventType().equalsIgnoreCase("4")) {
				String count=dbProcedures.getFollowArtist(reqParam);
				list = dbProcedures.FOLLOWKARAOKEARTISTLISTING(reqParam);
				if (list.size() == 0) { 
					obj = new Root(338, messageSource.getMessage("338", null, new Locale(reqParam.getLanguageCode())));
				} else {
					obj = new FollowKaraokeArtist(Integer.parseInt(count.split("#")[0]),Integer.parseInt(count.split("#")[1]),list);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		return new RootResponse(obj);
	}

	
	//Upload Karaoke Video
	@Override
	public Root uploadKaraokeSong(HttpServletRequest request,HttpServletResponse response,
			MultipartFile uploadFile) {
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements()){
		 String paramName = params.nextElement();
		 System.out.println("Parameter Name - "+paramName+", Value - "+request.getParameter(paramName));
		}
		//return karaokeUtility.uploadKaraokeFile(reqParam,uploadFile);
		return karaokeUtility.uploadKaraokeFile(request,response,uploadFile);

	}

}
