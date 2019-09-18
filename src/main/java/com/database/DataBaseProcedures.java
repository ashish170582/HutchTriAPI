package com.database;

import java.io.StringWriter;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import com.app.beans.DownloadHistory;
import com.app.beans.DownloadPackage;
import com.app.beans.Purchase;
import com.app.beans.SubscribedPackage;
import com.app.beans.SubscriptionBenefits;
import com.app.beans.SubscriptionPackage;
import com.beans.AlbumData;
import com.beans.AppVersionInfoData;
import com.beans.ArtistData;
import com.beans.CciPortalResponse;
import com.beans.ContainerBean;
import com.beans.ContainerItemBean;
import com.beans.CountInfo;
import com.beans.CountInfoData;
import com.beans.CountryInfoData;
import com.beans.DashBoardData;
import com.beans.DedicateeData;
import com.beans.DedicationData;
import com.beans.DeviceInformation;
import com.beans.DownloadURL;
import com.beans.FeaturedPlaylistBean;
import com.beans.FeedbackSubjectData;
import com.beans.FollowKaraokeArtistData;
import com.beans.GenreBean;
import com.beans.HikeKeyboardItemBean;
import com.beans.HomeItemData;
import com.beans.KaraokeCommentData;
import com.beans.LeftMenuTitle;
import com.beans.MobileOperatorData;
import com.beans.NotificationData;
import com.beans.OperatorData;
import com.beans.OptScreenConfig;
import com.beans.PaymentMethod;
import com.beans.PromotionShareConfig;
import com.beans.RadioBean;
import com.beans.RequestParameter;
import com.beans.Root;
import com.beans.SignUpViaConfigurationBean;
import com.beans.Splash;
import com.beans.SplashScreenData;
import com.beans.TabbedPaneBean;
import com.beans.TabbedPaneItemBean;
import com.beans.TrackData;
import com.beans.Transaction;
import com.beans.UserInfoData;
import com.beans.UserOfflineInformationBean;
import com.beans.karaokeTrackData;

import beans.config.AESEncriptionDecription;
import beans.config.AudioQuality;
import beans.config.AudioQualityConfiguration;
import beans.config.Configuration;
import beans.config.ConfigurationValue;
import beans.config.Visibility;
import beans.config.VisibilityValue;

@Component
public class DataBaseProcedures {

	@Autowired
	private DataSource dataSource;
	@Value("${api.domain.name}")
	private String apiDomainName = "";
	
	@Autowired
	private JdbcTemplate jdbcTemplateObject;

	@SuppressWarnings("finally")
	public AppVersionInfoData getAppVersionInfo(String countryCode, String operatingSystem, String applicationVersion) {
		AppVersionInfoData appVersionInfoData = null;
		try {

			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("ApplicationVersionInformation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
				    .addValue("inCountryCode", countryCode)
					.addValue("inOperatingSystem", operatingSystem)
					.addValue("inApplicationVersion", applicationVersion);					
			
				Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);
				
				appVersionInfoData = new AppVersionInfoData(
						resultMap.get("android").toString().toString(),
						resultMap.get("android_update_msg").toString(),
						resultMap.get("android_tab").toString(),
						resultMap.get("android_tab_update_msg").toString(),
						resultMap.get("ios").toString(),
						resultMap.get("ios_update_msg").toString(),
						resultMap.get("blackberry").toString(),
						resultMap.get("blackberry_update_msg").toString(),
						resultMap.get("blackberry10").toString(),
						resultMap.get("blackberry10_update_msg").toString(),
						resultMap.get("windows").toString(),
						resultMap.get("windows_update_msg").toString(),
						Integer.parseInt(resultMap.get("update_popup_frequency").toString()),
						resultMap.get("cache").toString(), 
						Integer.parseInt(resultMap.get("update_required").toString()),
						resultMap.get("update_message").toString());
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `ApplicationVersionInformation`('" + countryCode + "','"
//					+ operatingSystem + "','" + applicationVersion + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					appVersionInfoData = new AppVersionInfoData(rs.getString("android"),
//							rs.getString("android_update_msg"), rs.getString("android_tab"),
//							rs.getString("android_tab_update_msg"), rs.getString("ios"), rs.getString("ios_update_msg"),
//							rs.getString("blackberry"), rs.getString("blackberry_update_msg"),
//							rs.getString("blackberry10"), rs.getString("blackberry10_update_msg"),
//							rs.getString("windows"), rs.getString("windows_update_msg"),
//							rs.getInt("update_popup_frequency"), rs.getString("cache"), rs.getInt("update_required"),
//							rs.getString("update_message"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.setAppVersionInfo() - " + e.getMessage());
		} finally {
			return appVersionInfoData;
		}
	}

	// Get Total Follow Folled Artist
	@SuppressWarnings("finally")
	public String getFollowArtist(RequestParameter reqParam) {
		String response = "0#0#0";
		try {
			
//			MySQL1 mysql = new MySQL1();
//			ResultSet rs1 = mysql.prepareCall(
//					"{CALL `FOLLOWKARAOKEARTIST`(5," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FOLLOWKARAOKEARTIST");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("IN_FLAG",5)
					.addValue("in_USER_ID", reqParam.getUserId())
					.addValue("in_FOLLOW_USER_ID", reqParam.getArtistId());
			  
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
			 //   System.out.println("FOLLOWKARAOKEARTIST in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);
				response = resultMap.get("follow_count").toString() + "#" + resultMap.get("followed_count").toString() + "#"
						+ resultMap.get("vFollowing").toString();
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FOLLOWKARAOKEARTIST`(5," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					response = rs.getString("follow_count") + "#" + rs.getString("followed_count") + "#"
//							+ rs.getString("vFollowing");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFollowArtist(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return response;
		}
	}

	public List<ContainerBean> getKaraokeContainerList(RequestParameter reqParam, DeviceInformation devInfo) {
		List<ContainerBean> lst = new ArrayList<ContainerBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetKaraokeContainerList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("in_lang", reqParam.getLanguageCode());
					
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
				    
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  int containder_id = 0;
		    	  containder_id = Integer.parseInt(resultMap.get("container_id").toString());
					if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34
							|| containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74
							|| containder_id == 84 || containder_id == 94 || containder_id == 104
							|| containder_id == 114) {
						lst.add(new ContainerBean(Integer.parseInt(resultMap.get("container_id").toString()), Integer.parseInt(resultMap.get("seq_id").toString()),
								Integer.parseInt(resultMap.get("container_type_id").toString()), rs.get("container_title").toString(), Integer.parseInt(resultMap.get("see_all").toString()),
										Integer.parseInt(resultMap.get("item_list_type_id").toString()),
								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
										Integer.parseInt(resultMap.get("container_id").toString()), reqParam.getImageTechRefId(), 0, 6)));
					} else {
						lst.add(new ContainerBean(Integer.parseInt(resultMap.get("container_id").toString()), Integer.parseInt(resultMap.get("seq_id").toString()),
								Integer.parseInt(resultMap.get("container_type_id").toString()), rs.get("container_title").toString(), Integer.parseInt(resultMap.get("see_all").toString()),
										Integer.parseInt(resultMap.get("item_list_type_id").toString()),
								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
										Integer.parseInt(resultMap.get("container_id").toString()), reqParam.getImageTechRefId(), 0, 20)));
					}		    				    			  
		    	  });
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetKaraokeContainerList`(" + reqParam.getCountryId() + ",'"
//					+ devInfo.getDeviceId() + "','" + reqParam.getLanguageCode() + "')}");
//			int containder_id = 0;
//			if (rs != null) {
//				while (rs.next()) {
//					containder_id = rs.getInt("container_id");
//					if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34
//							|| containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74
//							|| containder_id == 84 || containder_id == 94 || containder_id == 104
//							|| containder_id == 114) {
//						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
//								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
//								rs.getInt("item_list_type_id"),
//								getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(),
//										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 6)));
//					} else {
//						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
//								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
//								rs.getInt("item_list_type_id"),
//								getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(),
//										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 20)));
//					}
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getKaraokeContainerList(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<SubscriptionPackage> getSubscriptionPackageList(RequestParameter reqParam) {
		List<SubscriptionPackage> lst = new ArrayList<SubscriptionPackage>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("SubscriptionPackage");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()					
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("in_lang", reqParam.getLanguageCode())
					.addValue("inOS", reqParam.getOperatingSystem());
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new SubscriptionPackage(Integer.parseInt(resultMap.get("package_id").toString()), 
		    			  resultMap.get("package_name").toString(),
		    			  resultMap.get("package_description").toString(),
		    			  resultMap.get("price_info").toString(),
		    			  resultMap.get("subscription_amount").toString(),
		    			  resultMap.get("renewal_amount").toString(),
		    			  resultMap.get("validity_period").toString(),
		    			  Integer.parseInt(resultMap.get("validity_in_days").toString()),
		    			  resultMap.get("currency_sign").toString(),
		    			  resultMap.get("redirectUrl").toString()
		    			  ));	
		    				    			  
		    	  });
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `SubscriptionPackage`(" + reqParam.getCountryId() + "," + reqParam.getOperatorId() + ",'"
//							+ reqParam.getLanguageCode() + "','" + reqParam.getOperatingSystem() + "')}");
//			while (rs.next()) {
//				lst.add(new SubscriptionPackage(rs.getInt("package_id"), rs.getString("package_name"),
//						rs.getString("package_description"), rs.getString("price_info"),
//						rs.getString("subscription_amount"), rs.getString("renewal_amount"),
//						rs.getString("validity_period"), rs.getInt("validity_in_days"), rs.getString("currency_sign"),
//						rs.getString("redirectUrl")));
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(" Exception in MainServlet.getSubscriptionPackageList(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<DownloadPackage> getDownloadPackageList(RequestParameter reqParam) {
		List<DownloadPackage> lst = new ArrayList<DownloadPackage>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("DownloadPackage");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId());
			
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new DownloadPackage(
		    			  Integer.parseInt(resultMap.get("package_id").toString()), 
		    			  resultMap.get("package_name").toString(),
		    			  resultMap.get("package_description").toString(),
		    			  resultMap.get("price_info").toString(),
		    			  resultMap.get("subscription_amount").toString(),
		    			  resultMap.get("renewal_amount").toString(),
		    			  resultMap.get("validity_period").toString(),
		    			  Integer.parseInt(resultMap.get("validity_in_days").toString()),
		    			  resultMap.get("currency_sign").toString()
		    			  ));	
		    				    			  
		    	  });
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `DownloadPackage`(" + reqParam.getCountryId() + "," + reqParam.getOperatorId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new DownloadPackage(rs.getInt("package_id"), rs.getString("package_name"),
//							rs.getString("package_description"), rs.getString("price_info"),
//							rs.getString("subscription_amount"), rs.getString("renewal_amount"),
//							rs.getString("validity_period"), rs.getInt("validity_in_days"),
//							rs.getString("currency_sign")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getDownloadPackageList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	
	@SuppressWarnings("finally")
	public int requestSubscription(RequestParameter reqParam) {
		int responseCode = 110;
		try {
		//	MySQL mysql = new MySQL();
			if (reqParam.getOperatorId() != -1) {

				SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Subscribe");			  			  
				  SqlParameterSource inParams = new MapSqlParameterSource()
						
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inBillingViaId", reqParam.getViaId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inPackageId", reqParam.getEventType())
						.addValue("inMobileNumber", reqParam.getMsisdn());
				  

				  Map<String, Object> rs = jdbcCall.execute(inParams);
	 				///////      System.out.println("sign in response :: "+ rs);					      
	 				ArrayList<Object> ar = new ArrayList<Object>();
	 				ar = (ArrayList) rs.get("#result-set-1");
	 				Map resultMap = (Map) ar.get(0);				    
	 				responseCode =Integer.parseInt(resultMap.get("code").toString());
				 				
				 				
//				ResultSet rs = mysql.prepareCall("{CALL `Subscribe`(" + reqParam.getCountryId() + ","
//						+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + "," + reqParam.getViaId() + ","
//						+ reqParam.getUserId() + "," + reqParam.getEventType() + ",'" + reqParam.getMsisdn() + "')}");
//				if (rs != null) {
//					while (rs.next()) {
//						responseCode = rs.getInt("code");
//					}
//					System.out.println("responseCode ::  " + responseCode);
//				}
			} else {
				responseCode = 110;
			}
			//mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.requestSubscription(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	@SuppressWarnings("finally")
	public int requestActivate(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Activate");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inBillingViaId", reqParam.getViaId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inPackageId", reqParam.getEventType())
			  		.addValue("inMobileNumber", reqParam.getMsisdn());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());

				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `Activate`(" + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + "," + reqParam.getViaId() + ","
//					+ reqParam.getUserId() + "," + reqParam.getEventType() + ",'" + reqParam.getMsisdn() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.requestActivate(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	@SuppressWarnings("finally")
	public int requestUnSubscription(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Unsubscribe");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inUserId", reqParam.getUserId());			  

			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `Unsubscribe`(" + reqParam.getUserId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
			//mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out
					.println("Exception in HutchTriBeatzMainServlet.requestUnSubscription(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	@SuppressWarnings("finally")
	public int appInstallViaShareClickTraking(RequestParameter reqParam) {
		int resultCode = 317;
		try {

			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("appInstallViaShareClickTraking");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inEventType",reqParam.getEventType())
					  	.addValue("inCountryCode",reqParam.getCountryCode())
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inApplicationVersion", reqParam.getApplicationVersion())
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())						  
						.addValue("inUserId", reqParam.getUserId() )
						.addValue("utm_campaign", reqParam.getCampaign())
						.addValue("utm_source", reqParam.getSource())
						.addValue("utm_medium", reqParam.getMedium())
						.addValue("utm_term", reqParam.getTerm())
						.addValue("utm_content", reqParam.getContent());
			 
			    Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				resultCode =Integer.parseInt(resultMap.get("code").toString());
				
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `appInstallViaShareClickTraking`('" + reqParam.getEventType()
//					+ "','" + reqParam.getCountryCode() + "'," + reqParam.getCountryId() + "," + reqParam.getSourceId()
//					+ ",'" + reqParam.getApplicationVersion() + "','" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getCampaign() + "',   '" + reqParam.getSource() + "' ,  '" + reqParam.getMedium()
//					+ "'  ,  '" + reqParam.getTerm() + "'  ,    '" + reqParam.getContent() + "'           )}");
//			if (rs != null) {
//				while (rs.next()) {
//					resultCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.appInstallViaShareClickTraking(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return resultCode;
		}
	}

	@SuppressWarnings("finally")
	public AudioQualityConfiguration getAudioQualityConfiguration(RequestParameter reqParam) {
		AudioQualityConfiguration audioQualityConfiguration = null;
		AudioQuality auto = null, high = null, medium = null, low = null;
		String ssDomainName = "";
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("AudioQualityConfiguration");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inCountryCode", reqParam.getCountryCode())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem())
					.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
					.addValue("inDeviceModel", reqParam.getDeviceModel())
					.addValue("inDeviceId", reqParam.getDeviceId())
			  		.addValue("inDevicePin", reqParam.getDevicePin());
			  
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);	  
				
				
				auto = new AudioQuality(resultMap.get("auto_abr").toString(),
						Integer.parseInt(resultMap.get("auto_tech_ref_id").toString()),
						resultMap.get("auto_codec").toString(),
						resultMap.get("auto_codecType").toString(),
						Integer.parseInt(resultMap.get("auto_bitRate").toString()),
						Integer.parseInt(resultMap.get("auto_noOfChannels").toString()),
						Integer.parseInt(resultMap.get("auto_samplingRate").toString()),
						resultMap.get("auto_fileExtention").toString());
				high = new AudioQuality(
						resultMap.get("high_abr").toString(),
						Integer.parseInt(resultMap.get("high_tech_ref_id").toString()),
						resultMap.get("high_codec").toString(),
						resultMap.get("high_codecType").toString(),
						Integer.parseInt(resultMap.get("high_bitRate").toString()),
						Integer.parseInt(resultMap.get("high_noOfChannels").toString()),
						Integer.parseInt(resultMap.get("high_samplingRate").toString()),
						resultMap.get("high_fileExtention").toString());
				medium = new AudioQuality(
						resultMap.get("medium_abr").toString(),
						Integer.parseInt(resultMap.get("medium_tech_ref_id").toString()),
						resultMap.get("medium_codec").toString(),
						resultMap.get("medium_codecType").toString(),
						Integer.parseInt(resultMap.get("medium_bitRate").toString()),
						Integer.parseInt(resultMap.get("medium_noOfChannels").toString()),
						Integer.parseInt(resultMap.get("medium_samplingRate").toString()),
						resultMap.get("medium_fileExtention").toString());
				low = new AudioQuality(resultMap.get("low_abr").toString(),
						Integer.parseInt(resultMap.get("low_tech_ref_id").toString()),
						resultMap.get("low_codec").toString(),
						resultMap.get("low_codecType").toString(),
						Integer.parseInt(resultMap.get("low_bitRate").toString()),
						Integer.parseInt(resultMap.get("low_noOfChannels").toString()),
						Integer.parseInt(resultMap.get("low_samplingRate").toString()),
						resultMap.get("low_fileExtention").toString());
				ssDomainName = resultMap.get("ssDomainName").toString();
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `AudioQualityConfiguration`('" + reqParam.getCountryCode() + "',"
//					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					auto = new AudioQuality(rs.getString("auto_abr"), rs.getInt("auto_tech_ref_id"),
//							rs.getString("auto_codec"), rs.getString("auto_codecType"), rs.getInt("auto_bitRate"),
//							rs.getInt("auto_noOfChannels"), rs.getInt("auto_samplingRate"),
//							rs.getString("auto_fileExtention"));
//					high = new AudioQuality(rs.getString("high_abr"), rs.getInt("high_tech_ref_id"),
//							rs.getString("high_codec"), rs.getString("high_codecType"), rs.getInt("high_bitRate"),
//							rs.getInt("high_noOfChannels"), rs.getInt("high_samplingRate"),
//							rs.getString("high_fileExtention"));
//					medium = new AudioQuality(rs.getString("medium_abr"), rs.getInt("medium_tech_ref_id"),
//							rs.getString("medium_codec"), rs.getString("medium_codecType"), rs.getInt("medium_bitRate"),
//							rs.getInt("medium_noOfChannels"), rs.getInt("medium_samplingRate"),
//							rs.getString("medium_fileExtention"));
//					low = new AudioQuality(rs.getString("low_abr"), rs.getInt("low_tech_ref_id"),
//							rs.getString("low_codec"), rs.getString("low_codecType"), rs.getInt("low_bitRate"),
//							rs.getInt("low_noOfChannels"), rs.getInt("low_samplingRate"),
//							rs.getString("low_fileExtention"));
//					ssDomainName = rs.getString("ssDomainName");
//				}
//
//			}
//			mysql.close();
			audioQualityConfiguration = new AudioQualityConfiguration(auto, high, medium, low);
			audioQualityConfiguration.setSsDomainName(ssDomainName);
		} catch (Exception e) {
			audioQualityConfiguration = null;
			System.out.println(
					"Exception in Voda_ghana MainServlet.getAudioQualityConfiguration(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return audioQualityConfiguration;
		}
	}


	@SuppressWarnings("unchecked")
	public UserInfoData signIn(RequestParameter reqParam) {
		UserInfoData userinfodata = null;
		try {
			SimpleJdbcCall jdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("SignIn");
			SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId())
					.addValue("inViaId", reqParam.getViaId())
					.addValue("inDevPin", reqParam.getDevicePin())
					.addValue("inUserDeviceId", reqParam.getUserDeviceId())
					.addValue("inUserName", reqParam.getUserName().replaceAll("'", "''"))
					.addValue("inMobileNumber", reqParam.getMsisdn())
					.addValue("inEmailAddress", reqParam.getEmailAddress())
					.addValue("inPassword", reqParam.getPassword())
					.addValue("inGenderId", reqParam.getGenderId())
					.addValue("inProfilePictureUrl",
							getFinalRedirectedUrl("http://graph.facebook.com/" + reqParam.getFbProfileId() + "/picture?type=large").replaceAll("'", "''"));

			
			
			
			Map<String, Object> rs = jdbcCall.execute(inParams);
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList<Object>) rs.get("#result-set-1");		
			
			
			for(int i =0; i< ar.size();i++)
			{
				Map<String, Object> resultMap = (Map<String, Object>) ar.get(i);			
				 userinfodata = new UserInfoData(Integer.parseInt(resultMap.get("user_id").toString()),
						  resultMap.get("user_promo_code").toString(), resultMap.get("user_name").toString(),
						  resultMap.get("mobile_number").toString(), resultMap.get("email_address").toString(),
						  Integer.parseInt(resultMap.get("gender_id").toString()),
						  resultMap.get("password").toString(), resultMap.get("image_url").toString(),
						  Integer.parseInt(resultMap.get("user_type_id").toString()),
						  Integer.parseInt(resultMap.get("user_status").toString()),
						  Integer.parseInt(resultMap.get("action_id").toString()),
						  Integer.parseInt(resultMap.get("registered").toString()),
						  Integer.parseInt(resultMap.get("mobile_number_verification").toString()),
						  resultMap.get("billing_mobile_number").toString(),
						  resultMap.get("dialing_code").toString(),
						  Integer.parseInt(resultMap.get("mobile_number_length").toString()), new
						  SubscribedPackage( Integer.parseInt(resultMap.get("package_id").toString()),
						  resultMap.get("package_name").toString(), resultMap.get("package_price").toString(),
						  resultMap.get("package_validity_period").toString(),
						  resultMap.get("package_start_date").toString(),
						  resultMap.get("package_end_date").toString(), resultMap.get("android_token").toString(),
						  resultMap.get("ios_token").toString(),
						  getBooleanValue(resultMap.get("optScreenVisibility").toString()) )				  
						  );		   
			}
			
					
			 

		} catch (Exception e) {
			e.printStackTrace();
			userinfodata = null;
			System.out.println("  {CALL `SignIn`(" + reqParam.getCountryId() + "," + reqParam.getSourceId()
			+ ",'" + reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
			+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getViaId()
			+ "'," + reqParam.getDevicePin() + "," + reqParam.getUserDeviceId() + ",'"
			+ reqParam.getUserName().replaceAll("'", "''") + "','"+ reqParam.getMsisdn() + "','"
			+ reqParam.getEmailAddress().replaceAll("'", "''") + "','"
			+ reqParam.getPassword().replaceAll("'", "''") + "'," + reqParam.getGenderId() + ",'"
			+ getFinalRedirectedUrl(
					"http://graph.facebook.com/" + reqParam.getFbProfileId() + "/picture?type=large")
							.replaceAll("'", "''")
			+ "')}");
		} finally {
			return userinfodata;
		}

	}

	@SuppressWarnings("finally")
	public void signOut(RequestParameter reqParam) {
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("SignOut");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inSource", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inDev_id", reqParam.getDeviceId())
					.addValue("inAppVer", reqParam.getApplicationVersion())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem());
					
			    Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);
			  
			//	System.out.println(Integer.parseInt(resultMap.get("code").toString()));
				
//			MySQL mysql = new MySQL();
//			mysql.prepareCall("{CALL `SignOut`(" + reqParam.getSourceId() + "," + reqParam.getUserId() + ",'"
//					+ reqParam.getDeviceId() + "','" + reqParam.getApplicationVersion() + "','"
//					+ reqParam.getOperatingSystem() + "')}");
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.signOut(RequestParameter reqParam) - " + e.getMessage());
		} finally {
		}
	}

	@SuppressWarnings("finally")
	public int sendSMS(RequestParameter reqParam) {
		int responseCode = 320;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("SendSMS");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inMobileNumber", reqParam.getMsisdn())
					.addValue("inMessage", reqParam.getMessage())
					.addValue("inSender", reqParam.getSender());
			
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `SendSMS`('" + reqParam.getMsisdn() + "','" + reqParam.getMessage()
//					+ "','" + reqParam.getSender() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.changePassword(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}

	}

	 

 

	@SuppressWarnings("finally")
	public int getSubscriptionTrialStatus(int userId) {
		int responseCode = 0;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetSubscriptionTrialStatus");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inUserId", userId);					
			
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("status_code").toString());
								
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetSubscriptionTrialStatus`(" + userId + ")}");
//			while (rs.next()) {
//				responseCode = rs.getInt("status_code");
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out
					.println(" Exception in MainServlet.forgetPassword(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return responseCode;
		}
	}

	@SuppressWarnings("finally")
	public String GetSubscriptionTrialStatus(int userId) {
		String outResponce = "";
		int responseCode = 0;
		int userStatus = 99;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetSubscriptionTrialStatus1");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inUserId", userId);					
			
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("status_code").toString());
				userStatus =Integer.parseInt(resultMap.get("user_status").toString());
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetSubscriptionTrialStatus1`(" + userId + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("status_code");
//					userStatus = rs.getInt("user_status");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.GetSubscriptionTrialStatus(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			outResponce = String.valueOf(responseCode) + "#" + String.valueOf(userStatus);
			return outResponce;
		}
	}

	@SuppressWarnings("finally")
	public UserInfoData getUserInfo(RequestParameter reqParam) {
		UserInfoData userinfodata = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserInfo");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inUserName", reqParam.getUserName())
					.addValue("inEmail", reqParam.getEmailAddress())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inGender", reqParam.getGenderId())
					.addValue("inUpdateType", reqParam.getEventType())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inAppVer", reqParam.getApplicationVersion());
				
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
				  for(int i =0; i< ar.size();i++)
					{
						Map resultMap = (Map) ar.get(i);
						userinfodata = new UserInfoData(Integer.parseInt(resultMap.get("user_id").toString()),
								resultMap.get("user_promo_code").toString(),
								resultMap.get("user_name").toString(),
								resultMap.get("mobile_number").toString(),
								resultMap.get("email_address").toString(),
								Integer.parseInt(resultMap.get("gender_id").toString()),
								resultMap.get("password").toString(),
								resultMap.get("image_url").toString(),
								Integer.parseInt(resultMap.get("user_type_id").toString()),
								Integer.parseInt(resultMap.get("user_status").toString()),
								Integer.parseInt(resultMap.get("action_id").toString()),
								Integer.parseInt(resultMap.get("registered").toString()),
								Integer.parseInt(resultMap.get("mobile_number_verification").toString()),
								resultMap.get("billing_mobile_number").toString(), 
								resultMap.get("dialing_code").toString(),
								Integer.parseInt(resultMap.get("mobile_number_length").toString()),
								new SubscribedPackage(Integer.parseInt(resultMap.get("package_id").toString()),
										resultMap.get("package_name").toString(),
										resultMap.get("package_price").toString(),
										resultMap.get("package_validity_period").toString(),
										resultMap.get("package_start_date").toString(),
										resultMap.get("package_end_date").toString(),
										resultMap.get("android_token").toString(),
										resultMap.get("ios_token").toString(),
										getBooleanValue(resultMap.get("optScreenVisibility").toString()))
								);	
					}	
			  
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserInfo`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getUserId() + ",'" + reqParam.getUserName() + "','" + reqParam.getEmailAddress() + "','"
//					+ reqParam.getMsisdn() + "'," + reqParam.getGenderId() + "," + reqParam.getEventType() + ",'"
//					+ reqParam.getOperatingSystem() + "','" + reqParam.getApplicationVersion() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					userinfodata = new UserInfoData(rs.getInt("user_id"), rs.getString("user_promo_code"),
//							rs.getString("user_name"), rs.getString("mobile_number"), rs.getString("email_address"),
//							rs.getInt("gender_id"), rs.getString("password"), rs.getString("image_url"),
//							rs.getInt("user_type_id"), rs.getInt("user_status"), rs.getInt("action_id"),
//							rs.getInt("registered"), rs.getInt("mobile_number_verification"),
//							rs.getString("billing_mobile_number"), rs.getString("dialing_code"),
//							rs.getInt("mobile_number_length"),
//							new SubscribedPackage(rs.getInt("package_id"), rs.getString("package_name"),
//									rs.getString("package_price"), rs.getString("package_validity_period"),
//									rs.getString("package_start_date"), rs.getString("package_end_date"),
//									rs.getString("android_token"), rs.getString("ios_token"),
//									rs.getBoolean("optScreenVisibility")));
//				}
//			}
//			mysql.close();
			if (userinfodata.getUserid() == 0) {
				userinfodata = null;
			}
			return userinfodata;
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getUserInfo(RequestParameter reqParam) - " + e.getMessage());

			return null;
		}
	}

	@SuppressWarnings("finally")
	public UserInfoData setUserInfo(RequestParameter reqParam) {
		UserInfoData userinfodata = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserInfo");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inUserName", reqParam.getUserName())
					.addValue("inEmail", reqParam.getEmailAddress())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inGender", reqParam.getGenderId())
					.addValue("inUpdateType", reqParam.getEventType())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inAppVer", reqParam.getApplicationVersion());
//			  
			  
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
				for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					userinfodata = new UserInfoData(Integer.parseInt(resultMap.get("user_id").toString()),
							resultMap.get("user_promo_code").toString(),
							resultMap.get("user_name").toString(),
							resultMap.get("mobile_number").toString(),
							resultMap.get("email_address").toString(),
							Integer.parseInt(resultMap.get("gender_id").toString()),
							resultMap.get("password").toString(),
							resultMap.get("image_url").toString(),
							Integer.parseInt(resultMap.get("user_type_id").toString()),
							Integer.parseInt(resultMap.get("user_status").toString()),
							Integer.parseInt(resultMap.get("action_id").toString()),
							Integer.parseInt(resultMap.get("registered").toString()),
							Integer.parseInt(resultMap.get("mobile_number_verification").toString()),
							resultMap.get("billing_mobile_number").toString(), 
							resultMap.get("dialing_code").toString(),
							Integer.parseInt(resultMap.get("mobile_number_length").toString()),
							new SubscribedPackage(Integer.parseInt(resultMap.get("package_id").toString()),
									resultMap.get("package_name").toString(),
									resultMap.get("package_price").toString(),
									resultMap.get("package_validity_period").toString(),
									resultMap.get("package_start_date").toString(),
									resultMap.get("package_end_date").toString(),
									resultMap.get("android_token").toString(),
									resultMap.get("ios_token").toString(),
									getBooleanValue(resultMap.get("optScreenVisibility").toString()))
							);	
				}	
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserInfo`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getUserId() + ",'" + reqParam.getUserName() + "','" + reqParam.getEmailAddress() + "','"
//					+ reqParam.getMsisdn() + "'," + reqParam.getGenderId() + "," + reqParam.getEventType() + ",'"
//					+ reqParam.getOperatingSystem() + "','" + reqParam.getApplicationVersion() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					userinfodata = new UserInfoData(rs.getInt("user_id"), rs.getString("user_promo_code"),
//							rs.getString("user_name"), rs.getString("mobile_number"), rs.getString("email_address"),
//							rs.getInt("gender_id"), rs.getString("password"), rs.getString("image_url"),
//							rs.getInt("user_type_id"), rs.getInt("user_status"), rs.getInt("action_id"),
//							rs.getInt("registered"), rs.getInt("mobile_number_verification"),
//							rs.getString("billing_mobile_number"), rs.getString("dialing_code"),
//							rs.getInt("mobile_number_length"),
//							new SubscribedPackage(rs.getInt("package_id"), rs.getString("package_name"),
//									rs.getString("package_price"), rs.getString("package_validity_period"),
//									rs.getString("package_start_date"), rs.getString("package_end_date"),
//									rs.getString("android_token"), rs.getString("ios_token"),
//									rs.getBoolean("optScreenVisibility")));
//					// userinfodata.setUserSignUpVia(getUserSignUpVia(rs.getInt("user_id")));
//					// userinfodata.setUserSubscriptionStatus(getUserSubscriptionStatus(rs.getInt("user_id"),
//					// reqParam.getEventId()));
//
//				}
//			}
//			mysql.close();
			if (userinfodata.getUserid() == 0) {
				userinfodata = null;
			}
		} catch (Exception e) {
			userinfodata = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.setUserInfo(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return userinfodata;
		}

	}

	@SuppressWarnings("finally")
	public List<TrackData> trackSearch(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Search");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  Integer.parseInt(resultMap.get("album_id").toString()),
		    			  resultMap.get("album_title").toString(),
		    			  Integer.parseInt(resultMap.get("artist_id").toString()),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("genre_id").toString()),
		    			  resultMap.get("genre_name").toString(),
		    			  Long.parseLong(resultMap.get("play_count").toString()),
		    			  Long.parseLong(resultMap.get("favourite_count").toString()),
		    			  Long.parseLong(resultMap.get("share_count").toString()),
		    			  Long.parseLong(resultMap.get("size").toString()),
		    			  Long.parseLong(resultMap.get("duration").toString()),
		    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
		    			  resultMap.get("image_url").toString(),
		    			  resultMap.get("video_id").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"))
		    			  
		    			  );	
		    				    			  
		    	  });	
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql
//					.prepareCall("{CALL `Search`(1," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
//							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
			// System.out.println("Search--"+lst.toString());
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackSearch(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> albumSearch(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Search");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_rating").toString()),
		    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql
//					.prepareCall("{CALL `Search`(2," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
//							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.albumSearch(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistSearch(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Search");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql
//					.prepareCall("{CALL `Search`(3," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
//							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistSearch(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<FeaturedPlaylistBean> playlistSearch(RequestParameter reqParam) {
		List<FeaturedPlaylistBean> lst = new ArrayList<FeaturedPlaylistBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Search");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",4)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new FeaturedPlaylistBean(Integer.parseInt(resultMap.get("playlist_id").toString()), 
		    			  resultMap.get("playlist_name").toString(),
		    			  resultMap.get("playlist_desc").toString(),  
		    			  resultMap.get("image_url").toString(),
		    			  Integer.parseInt(resultMap.get("track_count").toString())
		    			  ));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			// ResultSet rs = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(1," +
//			// reqParam.getCountryId() + "," + reqParam.getSourceId() + "," +
//			// reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','" +
//			// reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() +
//			// "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," +
//			// reqParam.getPlaylistId() + "," + reqParam.getAudioTechRefId() + "," +
//			// reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + "," +
//			// reqParam.getEndLimit() + ")}");
//			ResultSet rs = mysql
//					.prepareCall("{CALL `Search`(4," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
//							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
//							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.playlistSearch(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> albumFilter(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FilterByLetter");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_rating").toString()),
		    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });			  
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FilterByLetter`(1," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
//							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.albumFilter(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistFilter(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FilterByLetter");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),		    			
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });			  
			  
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FilterByLetter`(2," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
//							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistFilter(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<FeaturedPlaylistBean> playlistFilter(RequestParameter reqParam) {
		List<FeaturedPlaylistBean> lst = new ArrayList<FeaturedPlaylistBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FilterByLetter");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new FeaturedPlaylistBean(Integer.parseInt(resultMap.get("playlist_id").toString()), 
		    			  resultMap.get("playlist_name").toString(),
		    			  resultMap.get("playlist_desc").toString(),	
		    			  resultMap.get("image_url").toString(),
		    			  Integer.parseInt(resultMap.get("track_count").toString())		    			 
		    			  ));	
		    				    			  
		    	  });		
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FilterByLetter`(3," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
//							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
//							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.playlistFilter(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<GenreBean> genreFilter(RequestParameter reqParam) {
		List<GenreBean> lst = new ArrayList<GenreBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FilterByLetter");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",4)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new GenreBean(Integer.parseInt(resultMap.get("genre_id").toString()), 
		    			  resultMap.get("genre_name").toString(),		    			
		    			  resultMap.get("image_url").toString()		    			  	    			 
		    			  ));	
		    				    			  
		    	  });		
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FilterByLetter`(4," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
//							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new GenreBean(rs.getInt("genre_id"), rs.getString("genre_name"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreFilter(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<TrackData> trackFilter(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {

			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FilterByLetter");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",5)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSearch", reqParam.getSearchKeyword())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  Integer.parseInt(resultMap.get("album_id").toString()),
		    			  resultMap.get("album_title").toString(),
		    			  Integer.parseInt(resultMap.get("artist_id").toString()),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("genre_id").toString()),
		    			  resultMap.get("genre_name").toString(),
		    			  Long.parseLong(resultMap.get("play_count").toString()),
		    			  Long.parseLong(resultMap.get("favourite_count").toString()),
		    			  Long.parseLong(resultMap.get("share_count").toString()),
		    			  Long.parseLong(resultMap.get("size").toString()),
		    			  Long.parseLong(resultMap.get("duration").toString()),
		    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
		    			  resultMap.get("image_url").toString(),
		    			  resultMap.get("video_id").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"))
		    			  
		    			  );		    				    			  
		    	  });				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FilterByLetter`(5," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
//							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackFilter(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<HomeItemData> getHomeItem(RequestParameter reqParam) {
		List<HomeItemData> lst = new ArrayList<HomeItemData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetHomeItem");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new HomeItemData(Integer.parseInt(resultMap.get("id").toString()),
			    			  Integer.parseInt(resultMap.get("rid").toString()),
			    			  resultMap.get("name").toString(),
			    			  resultMap.get("type").toString(),
			    			  resultMap.get("desc").toString(),			    			
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetHomeItem`(" + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + ","
//					+ reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new HomeItemData(rs.getInt("id"), rs.getInt("rid"), rs.getString("name"),
//							rs.getString("type"), rs.getString("desc"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getHomeItem(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<TrackData> trackInfo(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetTrackInfo");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId());
					
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(
			    			  new TrackData(resultMap.get("resource_code").toString(), 
			    					  resultMap.get("ivrMMNumber").toString(),
			    					  resultMap.get("resource_title").toString(),
			    					  Integer.parseInt(resultMap.get("album_id").toString()),
			    					  resultMap.get("album_title").toString(),
			    					  Integer.parseInt(resultMap.get("artist_id").toString()),
			    					  resultMap.get("artist_name").toString(),
			    					  Integer.parseInt(resultMap.get("genre_id").toString()),
			    					  resultMap.get("genre_name").toString(),
			    					  Long.parseLong(resultMap.get("play_count").toString()),
			    					  Long.parseLong(resultMap.get("favourite_count").toString()),
			    					  Long.parseLong(resultMap.get("share_count").toString()),
			    					  Long.parseLong(resultMap.get("size").toString()),
			    					  Long.parseLong(resultMap.get("duration").toString()),
			    					  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    					  resultMap.get("image_url").toString(),
			    					  resultMap.get("video_id").toString(),
			    					  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    					  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    					  resultMap.get("lang_karaoke_available").toString().split("#")));			    				    			  
			    	  });			
					
			
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql
//					.prepareCall("{CALL `GetTrackInfo`(" + reqParam.getCountryId() + ",'" + reqParam.getTrackCode()
//							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackInfo(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<karaokeTrackData> karaokeInfo(RequestParameter reqParam) {
		List<karaokeTrackData> lst = new ArrayList<karaokeTrackData>();
		try {
			
//			MySQL1 mysql = new MySQL1();
//			ResultSet rs1 = mysql.prepareCall("{CALL `GetKaraokeInfo`(1,'" + reqParam.getUserId() + "','"
//					+ reqParam.getTrackCode() + "','" + reqParam.getEventType() + "','" + reqParam.getTabId() + "',"
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetKaraokeInfo");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("in_Flag",1)
					.addValue("in_user_id", reqParam.getUserId())
					.addValue("in_resource_code", reqParam.getTrackCode())
					.addValue("inItemType", reqParam.getEventType())
					.addValue("inTabId", reqParam.getTabId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				  System.out.println("GetKaraokeInfo in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				for(int i =0;i<ar.size();i++) 
				{
				  Map resultMap = (Map) ar.get(i);
				  lst.add(new karaokeTrackData(Integer.parseInt(resultMap.get("karaoke_id").toString()), 
		    			  resultMap.get("resource_code").toString(),
		    			  resultMap.get("title").toString(),
		    			  resultMap.get("artist_id").toString(),
		    			  resultMap.get("artist_title").toString(),
		    			  resultMap.get("fistUserId").toString(),
		    			  resultMap.get("secondUserId").toString(),
		    			  resultMap.get("singerType").toString(),
		    			  resultMap.get("recording_mode").toString(),
		    			  resultMap.get("record_type").toString(),
		    			  getBooleanValue(resultMap.get("isJoin").toString()),
		    			  resultMap.get("streamingUrl").toString(),		    			  
		    			  resultMap.get("imageUrl").toString(),
		    			  resultMap.get("score").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("userName").toString(),
		    			  Integer.parseInt(resultMap.get("firstKaraokeId").toString()),
		    			  getBooleanValue(resultMap.get("isFollowed").toString()),
		    			  getBooleanValue(resultMap.get("isReadyToPlay").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#")));
		    			  			    				    			  
		    	  }
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetKaraokeInfo`(1,'" + reqParam.getUserId() + "','"
//					+ reqParam.getTrackCode() + "','" + reqParam.getEventType() + "','" + reqParam.getTabId() + "',"
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new karaokeTrackData(rs.getInt("karaoke_id"), rs.getString("resource_code"),
//							rs.getString("title"), rs.getString("artist_id"), rs.getString("artist_title"),
//							rs.getString("fistUserId"), rs.getString("secondUserId"), rs.getString("singerType"),
//							rs.getString("recording_mode"), rs.getString("record_type"), rs.getBoolean("isJoin"),
//							rs.getString("streamingUrl"), rs.getString("imageUrl"), rs.getString("score"),
//							rs.getBoolean("isCrbtAvailable"), rs.getBoolean("isKaraokeAvailable"),
//							rs.getString("userName"), rs.getInt("firstKaraokeId"), rs.getBoolean("isFollowed"),
//							rs.getBoolean("isReadyToPlay"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.karaokeInfo(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public karaokeTrackData getkaraokeSongInfo(RequestParameter reqParam) {
		karaokeTrackData obj = null;
		try {
////			
//			MySQL1 mysql = new MySQL1();
//			ResultSet rs1 = mysql.prepareCall("{CALL `GetKaraokeInfo`(1,'" + reqParam.getUserId() + "','"
//					+ reqParam.getTrackCode() + "','" + reqParam.getEventType() + "','" + reqParam.getTabId() + "',"
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//
//			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetKaraokeInfo");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("in_Flag",1)
					.addValue("in_user_id", reqParam.getUserId())
					.addValue("in_resource_code", reqParam.getTrackCode())
					.addValue("inItemType", reqParam.getEventType())
					.addValue("inTabId", reqParam.getTabId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				for(int i =0;i<ar.size();i++) 
				{
				  Map resultMap = (Map) ar.get(i);
		    	  obj = new karaokeTrackData(Integer.parseInt(resultMap.get("karaoke_id").toString()), 
		    			  resultMap.get("resource_code").toString(),
		    			  resultMap.get("title").toString(),
		    			  resultMap.get("artist_id").toString(),
		    			  resultMap.get("artist_title").toString(),
		    			  resultMap.get("fistUserId").toString(),
		    			  resultMap.get("secondUserId").toString(),
		    			  resultMap.get("singerType").toString(),
		    			  resultMap.get("recording_mode").toString(),
		    			  resultMap.get("record_type").toString(),
		    			  getBooleanValue(resultMap.get("isJoin").toString()),
		    			  resultMap.get("streamingUrl").toString(),		    			  
		    			  resultMap.get("imageUrl").toString(),
		    			  resultMap.get("score").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("userName").toString(),
		    			  Integer.parseInt(resultMap.get("firstKaraokeId").toString()),
		    			  getBooleanValue(resultMap.get("isFollowed").toString()),
		    			  getBooleanValue(resultMap.get("isReadyToPlay").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"));
		    			  			    				    			  
		    	  }

//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetKaraokeInfo`(1,'" + reqParam.getUserId() + "','"
//					+ reqParam.getTrackCode() + "','" + reqParam.getEventType() + "','" + reqParam.getTabId() + "',"
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					obj = new karaokeTrackData(rs.getInt("karaoke_id"), rs.getString("resource_code"),
//							rs.getString("title"), rs.getString("artist_id"), rs.getString("artist_title"),
//							rs.getString("fistUserId"), rs.getString("secondUserId"), rs.getString("singerType"),
//							rs.getString("recording_mode"), rs.getString("record_type"), rs.getBoolean("isJoin"),
//							rs.getString("streamingUrl"), rs.getString("imageUrl"), rs.getString("score"),
//							rs.getBoolean("isCrbtAvailable"), rs.getBoolean("isKaraokeAvailable"),
//							rs.getString("userName"), rs.getInt("firstKaraokeId"), rs.getBoolean("isFollowed"),
//							rs.getBoolean("isReadyToPlay"), rs.getString("lang_karaoke_available").split("#"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.karaokeInfo(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return obj;
		}
	}

	public Map<Integer, List<KaraokeCommentData>> getKaraokeCommentList(RequestParameter reqParam) {
		Map<Integer, List<KaraokeCommentData>> map = new HashMap<Integer, List<KaraokeCommentData>>();
		List<KaraokeCommentData> list = new ArrayList<KaraokeCommentData>();
		int count = 0;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("KaraokeComment");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("IN_ID", reqParam.getId())
					.addValue("IN_USER_ID", reqParam.getUserId())
					.addValue("IN_KARAOKE_ID", reqParam.getKaraokeId())
					.addValue("IN_COMMENT", reqParam.getMessage())
					.addValue("IN_COMMENT_DATETIME", reqParam.getDatetime())					
					.addValue("IN_RECORDING_TYPE", reqParam.getRecordingMode())
					.addValue("IN_EVENT", 4)					
					.addValue("IN_STARTLIMIT", reqParam.getStartLimit())
					.addValue("IN_ENDLIMIT", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					for(int i =0; i< ar.size();i++)
					{
					  Map resultMap = (Map) ar.get(i);
			    	  count = Integer.parseInt(resultMap.get("totalCount").toString());
			    	  list.add(new KaraokeCommentData(Integer.parseInt(resultMap.get("id").toString()), 
	    			  Integer.parseInt(resultMap.get("karouke_id").toString()),
	    			  Integer.parseInt(resultMap.get("user_id").toString()),
	    			  resultMap.get("userComment").toString(),
	    			  resultMap.get("commentDateTime").toString(),
	    			  resultMap.get("commentUpdateDateTime").toString(),
	    			  resultMap.get("isUpdated").toString(),
	    			  resultMap.get("user_name").toString(),			    			  
	    			  resultMap.get("user_image_url").toString()
	    			  ));
			    				    			  
					}
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `KaraokeComment`(" + reqParam.getId() + "," + reqParam.getUserId()
//					+ "," + reqParam.getKaraokeId() + ",'" + reqParam.getMessage() + "','" + reqParam.getDatetime()
//					+ "'," + reqParam.getRecordingMode() + ",4," + reqParam.getStartLimit() + ","
//					+ reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					count = rs.getInt("totalCount");
//					list.add(new KaraokeCommentData(rs.getInt("id"), rs.getInt("karouke_id"), rs.getInt("user_id"),
//							rs.getString("userComment"), rs.getString("commentDateTime"),
//							rs.getString("commentUpdateDateTime"), rs.getString("isUpdated"), rs.getString("user_name"),
//							rs.getString("user_image_url")));
//				}
//			}
			map.put(count, list);
			//mysql.close();
			return map;
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.KaraokeComment(RequestParameter reqParam) - "
					+ e.getMessage());

			return map;
		}
	}

	public int karaokeComment(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("KaraokeComment");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()					
				.addValue("IN_ID", reqParam.getId())
				.addValue("IN_USER_ID", reqParam.getUserId())
				.addValue("IN_KARAOKE_ID", reqParam.getKaraokeId())
				.addValue("IN_COMMENT", reqParam.getMessage())
				.addValue("IN_COMMENT_DATETIME", reqParam.getDatetime())
				.addValue("IN_RECORDING_TYPE", reqParam.getRecordingMode())
				.addValue("IN_EVENT", reqParam.getEventType())
				.addValue("IN_STARTLIMIT", reqParam.getStartLimit())
				.addValue("IN_ENDLIMIT", reqParam.getEndLimit());
					
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
		
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `KaraokeComment`(" + reqParam.getId() + "," + reqParam.getUserId()
//					+ "," + reqParam.getKaraokeId() + ",'" + reqParam.getMessage() + "','" + reqParam.getDatetime()
//					+ "'," + reqParam.getRecordingMode() + "," + reqParam.getEventType() + ","
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			while (rs.next()) {
//				responseCode = rs.getInt("code");
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.keraokeComment(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public boolean FOLLOWKARAOKEARTIST(RequestParameter reqParam) {
		boolean following = false;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FOLLOWKARAOKEARTIST");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("IN_FLAG", reqParam.getEventType())
					.addValue("in_USER_ID", reqParam.getUserId())
					.addValue("in_FOLLOW_USER_ID", reqParam.getArtistId());

			  Map<String, Object> rs = jdbcCall.execute(inParams);
 				///////      System.out.println("sign in response :: "+ rs);					      
 				ArrayList<Object> ar = new ArrayList<Object>();
 				ar = (ArrayList) rs.get("#result-set-1");
 				Map resultMap = (Map) ar.get(0);				    
 				following =getBooleanValue(resultMap.get("code").toString());
			 				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`(" + reqParam.getEventType() + ","
//					+ reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					following = rs.getBoolean("follow");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return following;
		}
	}

	public List<FollowKaraokeArtistData> FOLLOWKARAOKEARTISTLISTING(RequestParameter reqParam) {
		List<FollowKaraokeArtistData> list = new ArrayList<FollowKaraokeArtistData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FOLLOWKARAOKEARTIST");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("IN_FLAG",reqParam.getEventType())
					.addValue("in_USER_ID", reqParam.getUserId())
					.addValue("in_FOLLOW_USER_ID", reqParam.getArtistId());
					
			Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			//Map resultMap1 = (Map) ar.get(0);
			      
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
	    	  list.add(new FollowKaraokeArtistData(
	    			  Integer.parseInt(resultMap.get("user_id").toString()), 
	    			  resultMap.get("userName").toString(),
	    			  resultMap.get("user_image_url").toString(),
	    			  getBooleanValue(resultMap.get("isArtistFollowed").toString())	    			  
	    			  ));	    				    			  
	    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`(" + reqParam.getEventType() + ","
//					+ reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
////                	   int followingCount=0;
////                	   int followersCount=0;
////                	   Boolean isArtistFollowed =false;
////                  	   ResultSet rs1 = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`(5," + rs.getInt("user_id") + ", "+ reqParam.getArtistId()+")}");
////		                	if (rs1 != null) {
////		                		while (rs1.next()) {
////		                			followingCount = rs1.getInt("follow_count");
////		                			followersCount = rs1.getInt("followed_count");   
////		                			isArtistFollowed= rs1.getBoolean("vFollowing");
////		                		}
////		                		rs1.close();	                		
////		                	}
//
//					// list.add(new
//					// FollowKaraokeArtistData(rs.getInt("user_id"),rs.getString("userName"),rs.getString("user_image_url")
//					// , followingCount , followersCount, isArtistFollowed ));
//					list.add(new FollowKaraokeArtistData(rs.getInt("user_id"), rs.getString("userName"),
//							rs.getString("user_image_url"), rs.getBoolean("isArtistFollowed")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.FOLLOWKARAOKEARTISTLISTING(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return list;
		}
	}

	@SuppressWarnings("finally")
	public CountryInfoData getCountryInfo(RequestParameter reqParam) {
		CountryInfoData data = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("CountryInformation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inUserId", reqParam.getUserId() )
					  	.addValue("inLang", reqParam.getLanguageCode())
					  	.addValue("inCountryCode",reqParam.getCountryCode())						
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inApplicationVersion", reqParam.getApplicationVersion())
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())
						.addValue("inSplashScreenType",reqParam.getEventType());						
			 
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
			//     System.out.println("country info response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				System.out.println("ArrayList ::"+ ar);
				Map resultMap = (Map) ar.get(0);
								
			//	System.out.println("resultMap ::"+ resultMap);
				data = new CountryInfoData();
				data.setCountryid(Integer.parseInt(resultMap.get("country_id").toString()));
				data.setCountryname(resultMap.get("country_name").toString());
				data.setDialingcode(resultMap.get("dialing_code").toString());
				data.setMobnolength(resultMap.get("mob_no_len").toString());
				data.setCode2digit(resultMap.get("code_2digit").toString());
				data.setCode3digit(resultMap.get("code_3digit").toString());
				data.setLanguageCode(resultMap.get("language_code").toString());
				data.setUserdevid(Integer.parseInt(resultMap.get("user_device_id").toString()));
				data.setAudioadversion(Integer.parseInt(resultMap.get("audio_ad_version").toString()));
				data.setAudioadurl(resultMap.get("audio ad_url").toString());
				data.setSplashversion(Integer.parseInt(resultMap.get("splash_version").toString()));
				data.setSplashurl(resultMap.get("splash_url").toString());
				data.setLogourl(resultMap.get("logo_url").toString());
				data.setMlogourl(resultMap.get("mlogo_url").toString());
				data.setNewLogoUrl(resultMap.get("new_logo_url").toString());
				data.setIosThemeColour(resultMap.get("ios_theme_colour").toString());
				data.setIsDataCacheEnable(getBooleanValue(resultMap.get("data_cache_enable").toString()));
				data.setSubscriptionAvailable(Integer.parseInt(resultMap.get("subscription_available").toString()));
				data.setDownloadAvailable(Integer.parseInt(resultMap.get("download_available").toString()));
				data.setIsDataCacheEnable(getBooleanValue(resultMap.get("data_cache_enable").toString()));
				data.setCrbtAvailable(Integer.parseInt(resultMap.get("crbt_available").toString()));
				data.setSubscriptionUrl(resultMap.get("subscription_url").toString());
				data.setSubLinkOpenAction(Integer.parseInt(resultMap.get("sub_link_open_action").toString()));
				data.setShareUrl(resultMap.get("share_url").toString());
				data.setMsisdnHeaderUrl(resultMap.get("msisdn_header_url").toString());

				data.setSongOptionsSequence(resultMap.get("song_options_sequence").toString());
				data.setSongPreviewDuration(Integer.parseInt(resultMap.get("song_preview_duration").toString()));

				data.setCli(resultMap.get("cli").toString());
				data.setOtpLength(Integer.parseInt(resultMap.get("otp_length").toString()));
				data.setOtpPosition(Integer.parseInt(resultMap.get("otpPosition").toString()));
				data.setShowStickyIcon(getBooleanValue(resultMap.get("show_sticky_icon").toString()));
				data.setShowLanguageChangePopup(getBooleanValue(resultMap.get("show_language_change_popup").toString()));

				data.setIsSocialScreenEnable(getBooleanValue(resultMap.get("social_screen_enable").toString()));

				data.setLoginRequiredAll(getBooleanValue(resultMap.get("login_required_all").toString()));
				data.setLoginRequiredShare(getBooleanValue(resultMap.get("login_required_share").toString()));
				data.setLoginRequiredCrbt(getBooleanValue(resultMap.get("login_required_crbt").toString()));
				data.setLoginRequiredPlayAudio(getBooleanValue(resultMap.get("login_required_play_audio").toString()));
				data.setLoginRequiredPlayVideo(getBooleanValue(resultMap.get("login_required_play_video").toString()));
				data.setLoginRequiredDownload(getBooleanValue(resultMap.get("login_required_download").toString()));
				data.setLoginRequiredOffline(getBooleanValue(resultMap.get("login_required_offline").toString()));
				data.setLoginRequiredFavourite(getBooleanValue(resultMap.get("login_required_favourite").toString()));

				data.setSubscriptionRequiredShare(getBooleanValue(resultMap.get("subscription_required_share").toString()));
				data.setSubscriptionRequiredCrbt(getBooleanValue(resultMap.get("subscription_required_crbt").toString()));
				data.setSubscriptionRequiredPlayAudio(getBooleanValue(resultMap.get("subscription_required_play_audio").toString()));
				data.setSubscriptionRequiredPlayVideo(getBooleanValue(resultMap.get("subscription_required_play_video").toString()));
				data.setSubscriptionRequiredDownload(getBooleanValue(resultMap.get("subscription_required_download").toString()));
				data.setSubscriptionRequiredOffline(getBooleanValue(resultMap.get("subscription_required_offline").toString()));
				data.setSubscriptionRequiredFavourite(getBooleanValue(resultMap.get("subscription_required_favourite").toString()));

				data.setLikeSongPopup(Integer.parseInt(resultMap.get("like_song_popup").toString()));
				data.setOfflineDownloadPopup(Integer.parseInt(resultMap.get("offline_download_popup").toString()));
				data.setLeftMenuButtonTitle(resultMap.get("leftMenuButtonTitle").toString());
				data.setLeftMenuButtonTitle_SW(resultMap.get("leftMenuButtonTitle_SW").toString());
				data.setUploadMusicVisibility(getBooleanValue(resultMap.get("uploadMusicVisibility").toString()));
				data.setArtistURL(resultMap.get("artistURL").toString());
				data.setMsisdnHeaderFinalUrl(resultMap.get("msisdnHeaderFinalUrl").toString());
				data.setOptScreenVisibility(getBooleanValue(resultMap.get("voptScreenVisibility").toString()));
				data.setIsPromoCodeScreen(getBooleanValue(resultMap.get("isPromoCodeScreen").toString()));
				data.setIsKaraokeAvailable(getBooleanValue(resultMap.get("isKaraokeAvailable").toString()));
				//System.out.println("Country Info Data ::"+ data.toString());
				
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `CountryInformation`('" + reqParam.getUserId() + "','"
//					+ reqParam.getLanguageCode() + "','" + reqParam.getCountryCode() + "'," + reqParam.getSourceId()
//					+ ",'" + reqParam.getApplicationVersion() + "','" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					data = new CountryInfoData();
//					data.setCountryid(rs.getInt("country_id"));
//					data.setCountryname(rs.getString("country_name"));
//					data.setDialingcode(rs.getString("dialing_code"));
//					data.setMobnolength(rs.getString("mob_no_len"));
//					data.setCode2digit(rs.getString("code_2digit"));
//					data.setCode3digit(rs.getString("code_3digit"));
//					data.setLanguageCode(rs.getString("language_code"));
//					data.setUserdevid(rs.getInt("user_device_id"));
//					data.setAudioadversion(rs.getInt("audio_ad_version"));
//					data.setAudioadurl(rs.getString("audio ad_url"));
//					data.setSplashversion(rs.getInt("splash_version"));
//					data.setSplashurl(rs.getString("splash_url"));
//					data.setLogourl(rs.getString("logo_url"));
//					data.setMlogourl(rs.getString("mlogo_url"));
//					data.setNewLogoUrl(rs.getString("new_logo_url"));
//					data.setIosThemeColour(rs.getString("ios_theme_colour"));
//					data.setIsDataCacheEnable(rs.getBoolean("data_cache_enable"));
//					data.setSubscriptionAvailable(rs.getInt("subscription_available"));
//					data.setDownloadAvailable(rs.getInt("download_available"));
//					data.setIsDataCacheEnable(rs.getBoolean("data_cache_enable"));
//					data.setCrbtAvailable(rs.getInt("crbt_available"));
//					data.setSubscriptionUrl(rs.getString("subscription_url"));
//					data.setSubLinkOpenAction(rs.getInt("sub_link_open_action"));
//					data.setShareUrl(rs.getString("share_url"));
//					data.setMsisdnHeaderUrl(rs.getString("msisdn_header_url"));
//
//					data.setSongOptionsSequence(rs.getString("song_options_sequence"));
//					data.setSongPreviewDuration(rs.getInt("song_preview_duration"));
//
//					data.setCli(rs.getString("cli"));
//					data.setOtpLength(rs.getInt("otp_length"));
//					data.setOtpPosition(rs.getInt("otpPosition"));
//					data.setShowStickyIcon(rs.getBoolean("show_sticky_icon"));
//					data.setShowLanguageChangePopup(rs.getBoolean("show_language_change_popup"));
//
//					data.setIsSocialScreenEnable(rs.getBoolean("social_screen_enable"));
//
//					data.setLoginRequiredAll(rs.getBoolean("login_required_all"));
//					data.setLoginRequiredShare(rs.getBoolean("login_required_share"));
//					data.setLoginRequiredCrbt(rs.getBoolean("login_required_crbt"));
//					data.setLoginRequiredPlayAudio(rs.getBoolean("login_required_play_audio"));
//					data.setLoginRequiredPlayVideo(rs.getBoolean("login_required_play_video"));
//					data.setLoginRequiredDownload(rs.getBoolean("login_required_download"));
//					data.setLoginRequiredOffline(rs.getBoolean("login_required_offline"));
//					data.setLoginRequiredFavourite(rs.getBoolean("login_required_favourite"));
//
//					data.setSubscriptionRequiredShare(rs.getBoolean("subscription_required_share"));
//					data.setSubscriptionRequiredCrbt(rs.getBoolean("subscription_required_crbt"));
//					data.setSubscriptionRequiredPlayAudio(rs.getBoolean("subscription_required_play_audio"));
//					data.setSubscriptionRequiredPlayVideo(rs.getBoolean("subscription_required_play_video"));
//					data.setSubscriptionRequiredDownload(rs.getBoolean("subscription_required_download"));
//					data.setSubscriptionRequiredOffline(rs.getBoolean("subscription_required_offline"));
//					data.setSubscriptionRequiredFavourite(rs.getBoolean("subscription_required_favourite"));
//
//					data.setLikeSongPopup(rs.getInt("like_song_popup"));
//					data.setOfflineDownloadPopup(rs.getInt("offline_download_popup"));
//					data.setLeftMenuButtonTitle(rs.getString("leftMenuButtonTitle"));
//					data.setLeftMenuButtonTitle_SW(rs.getString("leftMenuButtonTitle_SW"));
//					data.setUploadMusicVisibility(rs.getBoolean("uploadMusicVisibility"));
//					data.setArtistURL(rs.getString("artistURL"));
//					data.setMsisdnHeaderFinalUrl(rs.getString("msisdnHeaderFinalUrl"));
//					data.setOptScreenVisibility(rs.getBoolean("voptScreenVisibility"));
//					data.setIsPromoCodeScreen(rs.getBoolean("isPromoCodeScreen"));
//					data.setIsKaraokeAvailable(rs.getBoolean("isKaraokeAvailable"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			data = null;
			System.out.println("Exception in HutchTriBeatzMainServlet.getCountryInfo(RequestParameter reqParam) - "
					+ e.getMessage());
		}
		return data;

	}

	@SuppressWarnings("finally")
	public Visibility getAppFeaturesVisibility(RequestParameter reqParam) {
		int userTypeId;
		Visibility visibility = null;
		VisibilityValue visibilityValue[] = new VisibilityValue[3];
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FeatureVisibility");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryCode",reqParam.getCountryCode())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem());
			
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
			//  System.out.println("getAppFeaturesVisibility in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				
				
			  userTypeId = Integer.parseInt(resultMap.get("user_type_id").toString());
			  
				if (userTypeId == 1) {
					visibilityValue[0] = new VisibilityValue(Integer.parseInt(resultMap.get("banner_ads").toString()),
							Integer.parseInt(resultMap.get("audio_ads").toString()),
							Integer.parseInt(resultMap.get("favourite").toString()),
							Integer.parseInt(resultMap.get("dedication").toString()),
							Integer.parseInt(resultMap.get("share").toString()),
							Integer.parseInt(resultMap.get("crbt").toString()),
							Integer.parseInt(resultMap.get("offline").toString()),
							Integer.parseInt(resultMap.get("download").toString()));
				}
				if (userTypeId == 2) {
					visibilityValue[1] = new VisibilityValue(
							Integer.parseInt(resultMap.get("banner_ads").toString()),
							Integer.parseInt(resultMap.get("audio_ads").toString()),
							Integer.parseInt(resultMap.get("favourite").toString()),
							Integer.parseInt(resultMap.get("dedication").toString()),
							Integer.parseInt(resultMap.get("share").toString()),
							Integer.parseInt(resultMap.get("crbt").toString()),
							Integer.parseInt(resultMap.get("offline").toString()),
							Integer.parseInt(resultMap.get("download").toString()));
				}
				if (userTypeId == 3) {
					visibilityValue[2] = new VisibilityValue(
							Integer.parseInt(resultMap.get("banner_ads").toString()),
							Integer.parseInt(resultMap.get("audio_ads").toString()),
							Integer.parseInt(resultMap.get("favourite").toString()),
							Integer.parseInt(resultMap.get("dedication").toString()),
							Integer.parseInt(resultMap.get("share").toString()),
							Integer.parseInt(resultMap.get("crbt").toString()),
							Integer.parseInt(resultMap.get("offline").toString()),
							Integer.parseInt(resultMap.get("download").toString()));
				}
				visibility = new Visibility(visibilityValue[0], visibilityValue[1], visibilityValue[2]);
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `FeatureVisibility`('" + reqParam.getCountryCode() + "',"
//					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "')}");
//			// ResultSet rs = mysql.prepareCall("{call `FeatureVisibilityNew`('" +
//			// reqParam.getCountryCode() + "'," + reqParam.getSourceId() + ",'" +
//			// reqParam.getOperatingSystem() + "','" + reqParam.getDevice() + "','" +
//			// reqParam.getApplicationVersion() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					userTypeId = rs.getInt("user_type_id");
//					if (userTypeId == 1) {
//						visibilityValue[0] = new VisibilityValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
//								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
//								rs.getInt("offline"), rs.getInt("download"));
//					}
//					if (userTypeId == 2) {
//						visibilityValue[1] = new VisibilityValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
//								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
//								rs.getInt("offline"), rs.getInt("download"));
//					}
//					if (userTypeId == 3) {
//						visibilityValue[2] = new VisibilityValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
//								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
//								rs.getInt("offline"), rs.getInt("download"));
//					}
//				}
//				visibility = new Visibility(visibilityValue[0], visibilityValue[1], visibilityValue[2]);
//			}
//			mysql.close();

		} catch (Exception e) {
			visibility = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getAppFeaturesVisibility(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return visibility;
		}

	}

	@SuppressWarnings("finally")
	public Configuration getApplicationConfiguration(RequestParameter reqParam) {
		int userTypeId;
		Configuration configuration = null;
		ConfigurationValue configVal[] = new ConfigurationValue[3];
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FeatureConfiguration");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryCode",reqParam.getCountryCode())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem());
			
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);		
				
				userTypeId = Integer.parseInt(resultMap.get("user_type_id").toString());
				
				if (userTypeId == 1) {
					configVal[0] = new ConfigurationValue(
							Integer.parseInt(resultMap.get("banner_ads").toString()),
							Integer.parseInt(resultMap.get("audio_ads").toString()),
							Integer.parseInt(resultMap.get("favourite").toString()),
							Integer.parseInt(resultMap.get("dedication").toString()),
							Integer.parseInt(resultMap.get("share").toString()),
							Integer.parseInt(resultMap.get("crbt").toString()),
							Integer.parseInt(resultMap.get("offline_limit").toString()),
							Integer.parseInt(resultMap.get("offline_daily").toString()),
							Integer.parseInt(resultMap.get("offline_total").toString()),
							Integer.parseInt(resultMap.get("download").toString()),
							Integer.parseInt(resultMap.get("stream_interrupt").toString()),
							Integer.parseInt(resultMap.get("stream_limit").toString()),
							Integer.parseInt(resultMap.get("stream_preview").toString()),
							Integer.parseInt(resultMap.get("stream_daily").toString()),
							Integer.parseInt(resultMap.get("stream_total").toString()),
							Integer.parseInt(resultMap.get("stream_track").toString()));
				}
				if (userTypeId == 2) {
					configVal[1] = new ConfigurationValue(
							Integer.parseInt(resultMap.get("banner_ads").toString()),
							Integer.parseInt(resultMap.get("audio_ads").toString()),
							Integer.parseInt(resultMap.get("favourite").toString()),
							Integer.parseInt(resultMap.get("dedication").toString()),
							Integer.parseInt(resultMap.get("share").toString()),
							Integer.parseInt(resultMap.get("crbt").toString()),
							Integer.parseInt(resultMap.get("offline_limit").toString()),
							Integer.parseInt(resultMap.get("offline_daily").toString()),
							Integer.parseInt(resultMap.get("offline_total").toString()),
							Integer.parseInt(resultMap.get("download").toString()),
							Integer.parseInt(resultMap.get("stream_interrupt").toString()),
							Integer.parseInt(resultMap.get("stream_limit").toString()),
							Integer.parseInt(resultMap.get("stream_preview").toString()),
							Integer.parseInt(resultMap.get("stream_daily").toString()),
							Integer.parseInt(resultMap.get("stream_total").toString()),
							Integer.parseInt(resultMap.get("stream_track").toString()));
				}
				if (userTypeId == 3) {
					configVal[2] = new ConfigurationValue(
							Integer.parseInt(resultMap.get("banner_ads").toString()),
							Integer.parseInt(resultMap.get("audio_ads").toString()),
							Integer.parseInt(resultMap.get("favourite").toString()),
							Integer.parseInt(resultMap.get("dedication").toString()),
							Integer.parseInt(resultMap.get("share").toString()),
							Integer.parseInt(resultMap.get("crbt").toString()),
							Integer.parseInt(resultMap.get("offline_limit").toString()),
							Integer.parseInt(resultMap.get("offline_daily").toString()),
							Integer.parseInt(resultMap.get("offline_total").toString()),
							Integer.parseInt(resultMap.get("download").toString()),
							Integer.parseInt(resultMap.get("stream_interrupt").toString()),
							Integer.parseInt(resultMap.get("stream_limit").toString()),
							Integer.parseInt(resultMap.get("stream_preview").toString()),
							Integer.parseInt(resultMap.get("stream_daily").toString()),
							Integer.parseInt(resultMap.get("stream_total").toString()),
							Integer.parseInt(resultMap.get("stream_track").toString()));
				}
				configuration = new Configuration(configVal[0], configVal[1], configVal[2]);
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `FeatureConfiguration`('" + reqParam.getCountryCode() + "',"
//					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					userTypeId = ").toString()),"user_type_id");
//					if (userTypeId == 1) {
//						configVal[0] = new ConfigurationValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
//								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
//								rs.getInt("offline_limit"), rs.getInt("offline_daily"), rs.getInt("offline_total"),
//								rs.getInt("download"), rs.getInt("stream_interrupt"), rs.getInt("stream_limit"),
//								rs.getInt("stream_preview"), rs.getInt("stream_daily"), rs.getInt("stream_total"),
//								rs.getInt("stream_track"));
//					}
//					if (userTypeId == 2) {
//						configVal[1] = new ConfigurationValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
//								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
//								rs.getInt("offline_limit"), rs.getInt("offline_daily"), rs.getInt("offline_total"),
//								rs.getInt("download"), rs.getInt("stream_interrupt"), rs.getInt("stream_limit"),
//								rs.getInt("stream_preview"), rs.getInt("stream_daily"), rs.getInt("stream_total"),
//								rs.getInt("stream_track"));
//					}
//					if (userTypeId == 3) {
//						configVal[2] = new ConfigurationValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
//								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
//								rs.getInt("offline_limit"), rs.getInt("offline_daily"), rs.getInt("offline_total"),
//								rs.getInt("download"), rs.getInt("stream_interrupt"), rs.getInt("stream_limit"),
//								rs.getInt("stream_preview"), rs.getInt("stream_daily"), rs.getInt("stream_total"),
//								rs.getInt("stream_track"));
//					}
//				}
//				configuration = new Configuration(configVal[0], configVal[1], configVal[2]);
//			}
//			mysql.close();

		} catch (Exception e) {
			configuration = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getApplicationConfiguration(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return configuration;
		}

	}

	@SuppressWarnings("finally")
	public SignUpViaConfigurationBean getSignUpViaConfiguration(RequestParameter reqParam) {
		int signUpViaId = 0;
		SignUpViaConfigurationBean signUpViaConfiguration = new SignUpViaConfigurationBean();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetSignUpViaConfiguration");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryCode", reqParam.getCountryCode())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem());
			
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			Map resultMap = (Map) ar.get(0);	
			
			signUpViaId =Integer.parseInt(resultMap.get("signup_via_id").toString());
			
			switch (signUpViaId) {
			case 1:
				signUpViaConfiguration.setEmail(1);
				break;
			case 2:
				signUpViaConfiguration.setFacebook(1);
				break;
			case 3:
				signUpViaConfiguration.setGoogle(1);
				break;
			case 4:
				signUpViaConfiguration.setMobileNumber(1);
				break;
			default:
				break;
			}
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetSignUpViaConfiguration`('" + reqParam.getCountryCode() + "',"
//					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					signUpViaId = rs.getInt("signup_via_id");
//					switch (signUpViaId) {
//					case 1:
//						signUpViaConfiguration.setEmail(1);
//						break;
//					case 2:
//						signUpViaConfiguration.setFacebook(1);
//						break;
//					case 3:
//						signUpViaConfiguration.setGoogle(1);
//						break;
//					case 4:
//						signUpViaConfiguration.setMobileNumber(1);
//						break;
//					default:
//						break;
//					}
//				}
//			}
//			mysql.close();

		} catch (Exception e) {
			signUpViaConfiguration = new SignUpViaConfigurationBean();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getApplicationConfiguration(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return signUpViaConfiguration;
		}

	}

	 

	@SuppressWarnings("finally")
	public List<ContainerBean> getContainerList(RequestParameter reqParam) {
		List<ContainerBean> lst = new ArrayList<ContainerBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetContainerList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("in_lang", reqParam.getLanguageCode());
					
			  Map<String, Object> rs = jdbcCall.execute(inParams);			    			      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				System.out.println("Container List ::"+ ar);
				
				    
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;	    	
		    					lst.add(new ContainerBean(Integer.parseInt(resultMap.get("container_id").toString()), Integer.parseInt(resultMap.get("seq_id").toString()),
								Integer.parseInt(resultMap.get("container_type_id").toString()), resultMap.get("container_title").toString(), Integer.parseInt(resultMap.get("see_all").toString()),
										Integer.parseInt(resultMap.get("item_list_type_id").toString()),
								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),	Integer.parseInt(resultMap.get("container_id").toString()), reqParam.getImageTechRefId(), 0, 20)));
					    				    			  
		    	  });
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetContainerList`(" + reqParam.getCountryId() + ",'"
//					+ reqParam.getDeviceId() + "','" + reqParam.getLanguageCode() + "')}");
//			int containder_id = 0;
//			if (rs != null) {
//				while (rs.next()) {
//					containder_id = rs.getInt("container_id");
//					if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34
//							|| containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74
//							|| containder_id == 84 || containder_id == 94 || containder_id == 104
//							|| containder_id == 114) {
//						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
//								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
//								rs.getInt("item_list_type_id"),
//								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
//										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 6)));
//					} else {
//						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
//								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
//								rs.getInt("item_list_type_id"),
//								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
//										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 20)));
//					}
//
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception in HutchTriBeatzMainServlet.getContainers(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<ContainerItemBean> getContainerItemList(int countryId, int userID, int containerId, int imageTechRefId,
			int startLimit, int endLimit) {
		List<ContainerItemBean> lst = new ArrayList<ContainerItemBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetContainerItemList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", countryId)
					.addValue("inContainerId", containerId)
					.addValue("inImageTechRefId", imageTechRefId)
					.addValue("inUserId", userID)
					.addValue("inStart", startLimit)
					.addValue("inLimit", endLimit); 
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);		      					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//
//				MySQL1 mysql = new MySQL1();
//				ResultSet rs1 = mysql.prepareCall("{call `GetContainerItemList`(" + countryId + "," + containerId + ","
//				+ imageTechRefId + "," + userID + "," + startLimit + "," + endLimit + ")}");
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ContainerItemBean(resultMap.get("item_id").toString(), 
		    			  resultMap.get("item_seq_id").toString(),
		    			  resultMap.get("item_type_id").toString(),
		    			  resultMap.get("item_resource_id").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("resource_description").toString(),
		    			  resultMap.get("resource_image_url").toString(),
		    			  resultMap.get("click_url").toString(),		    			 
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#")));		    				    			  
		    	 
				});
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetContainerItemList`(" + countryId + "," + containerId + ","
//					+ imageTechRefId + "," + userID + "," + startLimit + "," + endLimit + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
//							rs.getString("item_type_id"), rs.getString("item_resource_id"),
//							rs.getString("resource_title"), rs.getString("resource_description"),
//							rs.getString("resource_image_url"), rs.getString("click_url"),
//							rs.getBoolean("isCrbtAvailable"), rs.getBoolean("isKaraokeAvailable"),
//							rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getContainerItemList(int containerId,int startLimit,int endLimit) - "
							+ e.getMessage());
		} finally {
			System.out.println("Data for  "+ containerId +" :: "+ lst);
			return lst;
		}

	}

	/// Karaoka List
	@SuppressWarnings("finally")
	public List<ContainerBean> getKaraokeContainerList(RequestParameter reqParam) {
		List<ContainerBean> lst = new ArrayList<ContainerBean>();
		try {
//			MySQL1 mysql = new MySQL1();
//			ResultSet rs1 = mysql.prepareCall("{call `GetKaraokeContainerList`(" + reqParam.getCountryId() + ",'"
//					+ reqParam.getDeviceId() + "','" + reqParam.getLanguageCode() + "')}");
//			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetKaraokeContainerList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("in_lang", reqParam.getLanguageCode());
					
			  Map<String, Object> rs = jdbcCall.execute(inParams);
		  //   System.out.println("GetKaraokeContainerList  in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
				    
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;	
		    	  String container_title="";
		    	  if(rs.get("container_title")!=null) {
		    		  container_title=rs.get("container_title").toString();
		    	  }
		   
						lst.add(new ContainerBean(Integer.parseInt(resultMap.get("container_id").toString()), Integer.parseInt(resultMap.get("seq_id").toString()),
								Integer.parseInt(resultMap.get("container_type_id").toString()), container_title, Integer.parseInt(resultMap.get("see_all").toString()),
										Integer.parseInt(resultMap.get("item_list_type_id").toString()),
								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
										Integer.parseInt(resultMap.get("container_id").toString()), reqParam.getImageTechRefId(), 0, 20)));
				 	    				    			  
		    	  });
				
				
				

//			int containder_id = 0;
//			if (rs != null) {
//				while (rs.next()) {
//					containder_id = rs.getInt("container_id");
//					if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34
//							|| containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74
//							|| containder_id == 84 || containder_id == 94 || containder_id == 104
//							|| containder_id == 114) {
//						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
//								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
//								rs.getInt("item_list_type_id"),
//								getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(),
//										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 6)));
//					} else {
//						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
//								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
//								rs.getInt("item_list_type_id"),
//								getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(),
//										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 20)));
//					}
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getKaraokeContainerList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<ContainerItemBean> getKaraokeItemList(int countryId, int userID, int containerId, int imageTechRefId,
			int startLimit, int endLimit) {
		List<ContainerItemBean> lst = new ArrayList<ContainerItemBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetKaraokaContainerItemList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", countryId)
					.addValue("inContainerId", containerId)
					.addValue("inImageTechRefId", imageTechRefId)
					.addValue("inUserId", userID)
					.addValue("inStart", startLimit)
					.addValue("inLimit", endLimit); 
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ContainerItemBean(resultMap.get("item_id").toString(), 
		    			  resultMap.get("item_seq_id").toString(),
		    			  resultMap.get("item_type_id").toString(),
		    			  resultMap.get("item_resource_id").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("resource_description").toString(),
		    			  resultMap.get("resource_image_url").toString(),
		    			  resultMap.get("click_url").toString(),		    			 
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#")));		    				    			  
		    	 
				});
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetKaraokaContainerItemList`(" + countryId + "," + containerId
//					+ "," + imageTechRefId + "," + userID + "," + startLimit + "," + endLimit + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
//							rs.getString("item_type_id"), rs.getString("item_resource_id"),
//							rs.getString("resource_title"), rs.getString("resource_description"),
//							rs.getString("resource_image_url"), rs.getString("click_url"),
//							rs.getBoolean("isCrbtAvailable"), rs.getBoolean("isKaraokeAvailable"),
//							rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getKaraokeItemList(int containerId,int startLimit,int endLimit) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	
	
	public List<TrackData> getAllItemList(int countryId, String itemType, int imageTechRefId, int startLimit,
			int endLimit) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAllItemList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", countryId)
					.addValue("inItemType", itemType)
					.addValue("inImageTechRefId", imageTechRefId)					
					.addValue("inStart", startLimit)
					.addValue("inLimit", endLimit); 
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
//				MySQL1 mysql = new MySQL1();
//				ResultSet rs1 = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
//						+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
//				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		   //	  System.out.println("resultMap :: "+ resultMap);
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), resultMap.get("ivrMMNumber").toString(),
							resultMap.get("resource_title").toString(), Integer.parseInt(resultMap.get("album_id").toString()), resultMap.get("album_title").toString(),
							Integer.parseInt(resultMap.get("artist_id").toString()), resultMap.get("artist_name").toString(), Integer.parseInt(resultMap.get("genre_id").toString()),
							resultMap.get("genre_name").toString(), Long.parseLong(resultMap.get("play_count").toString()), Long.parseLong(resultMap.get("favourite_count").toString()),
							Long.parseLong(resultMap.get("share_count").toString()), Long.parseLong(resultMap.get("size").toString()), Long.parseLong(resultMap.get("duration").toString()),
							resultMap.get("filePath").toString(), resultMap.get("image_url").toString(),
							resultMap.get("video_id").toString(), getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
							getBooleanValue(resultMap.get("isKaraokeAvailable").toString()), resultMap.get("lang_karaoke_available").toString().split("#"))
		    		  );		 
		    		 				    			  
		    	 
				});
			  
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
//					+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
//							rs.getString("item_type_id"), rs.getString("item_resource_id"),
//							rs.getString("resource_title"), rs.getString("resource_description"),
//							rs.getString("resource_image_url"), rs.getString("click_url"),
//							rs.getBoolean("isCrbtAvailable"), rs.getBoolean("isKaraokeAvailable"),
//							rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getContainerItemList(int containerId,int startLimit,int endLimit) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	// Karaoka List
	@SuppressWarnings("finally")
	public List<ContainerItemBean> getAllItemList(RequestParameter reqParam,int countryId, String itemType, int imageTechRefId, int startLimit,
			int endLimit) {
		List<ContainerItemBean> lst = new ArrayList<ContainerItemBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAllItemList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", countryId)
					.addValue("inItemType", itemType)
					.addValue("inImageTechRefId", imageTechRefId)					
					.addValue("inStart", startLimit)
					.addValue("inLimit", endLimit); 
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
//				MySQL1 mysql = new MySQL1();
//				ResultSet rs1 = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
//						+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
//				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    //	  System.out.println("resultMap :: "+ resultMap);
		    	  lst.add(new ContainerItemBean(resultMap.get("item_id").toString(), 
		    			  resultMap.get("item_seq_id").toString(),
		    			  resultMap.get("item_type_id").toString(),
		    			  resultMap.get("item_resource_id").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("resource_description").toString(),
		    			  resultMap.get("resource_image_url").toString(),
		    			  resultMap.get("click_url").toString(),		    			 
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#")));		    				    			  
		    	 
				});
			  
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
//					+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
//							rs.getString("item_type_id"), rs.getString("item_resource_id"),
//							rs.getString("resource_title"), rs.getString("resource_description"),
//							rs.getString("resource_image_url"), rs.getString("click_url"),
//							rs.getBoolean("isCrbtAvailable"), rs.getBoolean("isKaraokeAvailable"),
//							rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getContainerItemList(int containerId,int startLimit,int endLimit) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<TabbedPaneBean> getTabbedPane(RequestParameter reqParam) {
		List<TabbedPaneBean> lst = new ArrayList<TabbedPaneBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetTabbedPane");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()					
					.addValue("inCountryCode", reqParam.getCountryCode())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inApiVersion", reqParam.getApiVersion())
					.addValue("inDeviceId", reqParam.getDeviceId());
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TabbedPaneBean(
		    			  Integer.parseInt(resultMap.get("tab_id").toString()), 
		    			  Integer.parseInt(resultMap.get("seq_id").toString()), 
		    			  Integer.parseInt(resultMap.get("tab_type_id").toString()), 
		    			  resultMap.get("tab_title").toString()		    			 
		    			  ));			    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql
//					.prepareCall("{call `GetTabbedPane`('" + reqParam.getCountryCode() + "'," + reqParam.getSourceId()
//							+ ",'" + reqParam.getApiVersion() + "','" + reqParam.getDeviceId() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TabbedPaneBean(rs.getInt("tab_id"), rs.getInt("seq_id"), rs.getInt("tab_type_id"),
//							rs.getString("tab_title")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getTabbedPane(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<LeftMenuTitle> getLeftMenuTitle(RequestParameter reqParam) {
		List<LeftMenuTitle> lst = new ArrayList<LeftMenuTitle>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetLeftMenuTitle");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()					
					.addValue("lang", reqParam.getLanguageCode());
				

			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new LeftMenuTitle(
		    			  resultMap.get("title").toString(),
		    			  resultMap.get("url").toString(),
		    			  getBooleanValue(resultMap.get("isExternal").toString()),
		    			  resultMap.get("popupText").toString(),
		    			  resultMap.get("popupTitle").toString()));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetLeftMenuTitle`('" + reqParam.getLanguageCode() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new LeftMenuTitle(rs.getString("title"), rs.getString("url"), rs.getBoolean("isExternal"),
//							rs.getString("popupText"), rs.getString("popupTitle")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getLeftMenuTitle(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<PaymentMethod> getPaymentMethods(RequestParameter reqParam) {
		List<PaymentMethod> lst = new ArrayList<PaymentMethod>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetPaymentMethod");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()					
					.addValue("inCountryCode", reqParam.getCountryCode());
				

			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				ar.forEach(item->{
					  Map resultMap = (Map) item;
					  lst.add(new PaymentMethod(Integer.parseInt(resultMap.get("id").toString()), 
								resultMap.get("payment_method").toString()));
					
				  });		
				
			 	    
				
			 				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetPaymentMethod`('" + reqParam.getCountryCode() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new PaymentMethod(rs.getInt("id"), rs.getString("payment_method")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getPaymentMethods(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	 
	@SuppressWarnings("finally")
	public List<TabbedPaneBean> getDiscoverTabbedPane(RequestParameter reqParam) {
		List<TabbedPaneBean> lst = new ArrayList<TabbedPaneBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetTabbedPaneDiscover");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryCode", reqParam.getCountryCode())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inApiVersion", reqParam.getApiVersion());
					
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			//Map resultMap1 = (Map) ar.get(0);
			      
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
	    	  lst.add(new TabbedPaneBean(Integer.parseInt(resultMap.get("tab_id").toString()), 
	    			  Integer.parseInt(resultMap.get("seq_id").toString()),
	    			  Integer.parseInt(resultMap.get("tab_type_id").toString()),
	    			  resultMap.get("tab_title").toString()
	    			  ));	
	    				    			  
	    	  });		
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetTabbedPaneDiscover`('" + reqParam.getCountryCode() + "',"
//					+ reqParam.getSourceId() + "," + reqParam.getApiVersion() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TabbedPaneBean(rs.getInt("tab_id"), rs.getInt("seq_id"), rs.getInt("tab_type_id"),
//							rs.getString("tab_title")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out
					.println("Exception in HutchTriBeatzMainServlet.getDiscoverTabbedPane(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<TabbedPaneItemBean> getTabbedPaneItem(RequestParameter reqParam) {
		List<TabbedPaneItemBean> lst = new ArrayList<TabbedPaneItemBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetTabbedPaneItem");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inTabId", reqParam.getTabId())
					.addValue("inBannerSizeId", reqParam.getBannerTypeId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
					
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			//Map resultMap1 = (Map) ar.get(0);
			      
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
	    	  lst.add(new TabbedPaneItemBean(Integer.parseInt(resultMap.get("tab_id").toString()), 
	    			  Integer.parseInt(resultMap.get("item_seq_id").toString()),
	    			  Integer.parseInt(resultMap.get("item_type_id").toString()),
	    			  resultMap.get("item_resource_id").toString(),
	    			  resultMap.get("resource_title").toString(),
	    			  resultMap.get("resource_description").toString(),
	    			  resultMap.get("resource_image_url").toString(),
	    			  resultMap.get("banner_url").toString(),
	    			  resultMap.get("click_url").toString()
	    			  ));	
	    				    			  
	    	  });		
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetTabbedPaneItem`(" + reqParam.getCountryId() + ","
//					+ reqParam.getTabId() + "," + reqParam.getBannerTypeId() + "," + reqParam.getImageTechRefId() + ","
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TabbedPaneItemBean(rs.getInt("tab_id"), rs.getInt("item_seq_id"),
//							rs.getInt("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
//							rs.getString("resource_description"), rs.getString("resource_image_url"),
//							rs.getString("banner_url"), rs.getString("click_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getTabbedPaneItem(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<TabbedPaneItemBean> getTabbedPaneItemFilter(RequestParameter reqParam) {
		List<TabbedPaneItemBean> lst = new ArrayList<TabbedPaneItemBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetTabbedPaneItemFilter");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inTabId", reqParam.getTabId())
					.addValue("inBannerSizeId", reqParam.getBannerTypeId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inFilter", reqParam.getFilter())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
					
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			//Map resultMap1 = (Map) ar.get(0);
			      
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
	    	  lst.add(new TabbedPaneItemBean(Integer.parseInt(resultMap.get("tab_id").toString()), 
	    			  Integer.parseInt(resultMap.get("item_seq_id").toString()),
	    			  Integer.parseInt(resultMap.get("item_type_id").toString()),
	    			  resultMap.get("item_resource_id").toString(),
	    			  resultMap.get("resource_title").toString(),
	    			  resultMap.get("resource_description").toString(),
	    			  resultMap.get("resource_image_url").toString(),
	    			  resultMap.get("banner_url").toString(),
	    			  resultMap.get("click_url").toString()
	    			  ));	
	    				    			  
	    	  });		
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetTabbedPaneItemFilter`(" + reqParam.getCountryId() + ","
//					+ reqParam.getTabId() + "," + reqParam.getBannerTypeId() + "," + reqParam.getImageTechRefId() + ",'"
//					+ reqParam.getFilter() + "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TabbedPaneItemBean(rs.getInt("tab_id"), rs.getInt("item_seq_id"),
//							rs.getInt("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
//							rs.getString("resource_description"), rs.getString("resource_image_url"),
//							rs.getString("banner_url"), rs.getString("click_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getTabbedPaneItem(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public List<HikeKeyboardItemBean> getHikeKeyboardItem(RequestParameter reqParam) {
		List<HikeKeyboardItemBean> lst = new ArrayList<HikeKeyboardItemBean>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetHikeKeyboardItem");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
					
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new HikeKeyboardItemBean(Integer.parseInt(resultMap.get("item_seq_id").toString()), 
		    			  Integer.parseInt(resultMap.get("item_type_id").toString()),
		    			  resultMap.get("item_resource_id").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("resource_description").toString(),
		    			  resultMap.get("resource_image_url").toString(),
		    			  resultMap.get("share_message").toString()
		    			  ));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{call `GetHikeKeyboardItem`(" + reqParam.getCountryId() + "," + reqParam.getImageTechRefId() + ","
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new HikeKeyboardItemBean(rs.getInt("item_seq_id"), rs.getInt("item_type_id"),
//							rs.getString("item_resource_id"), rs.getString("resource_title"),
//							rs.getString("resource_description"), rs.getString("resource_image_url"),
//							rs.getString("share_message")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getTabbedPaneItem(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}

	}

	@SuppressWarnings("finally")
	public Splash getSplashScreenData(RequestParameter reqParam) {
		Splash data = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("SplashScreen");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystemId", reqParam.getOperatingSystemId())
					.addValue("inSplashScreenType", reqParam.getEventType());
					
					
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			Map resultMap = (Map) ar.get(0);
			 
			data = new Splash(new SplashScreenData(resultMap.get("splash_url").toString(),
					resultMap.get("splash_version").toString(),
					resultMap.get("logo_url").toString(), resultMap.get("logo_version").toString()));			
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql
//					.prepareCall("{call `SplashScreen`(" + reqParam.getCountryId() + "," + reqParam.getSourceId() + ","
//							+ reqParam.getOperatingSystemId() + "," + reqParam.getEventType() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					data = new Splash(new SplashScreenData(rs.getString("splash_url"), rs.getString("splash_version"),
//							rs.getString("logo_url"), rs.getString("logo_version")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			data = null;
			System.out.println("Exception in HutchTriBeatzMainServlet.getSplashScreenData(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return data;
		}

	}

	@SuppressWarnings("finally")
	public AlbumData album(RequestParameter reqParam) {
		AlbumData album = null;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAlbumMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inAlbumId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getCountryId())
					.addValue("inImageTechRefId", reqParam.getCountryId())
					.addValue("inStart", reqParam.getCountryId())
					.addValue("inLimit", reqParam.getCountryId());
			 
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					album = new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_rating").toString()),
		    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  );	
		    				    			  
		    	  }	
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					album = (new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.album(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return album;
		}
	}

	@SuppressWarnings("finally")
	public List<TrackData> albumTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			 SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAlbumMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inAlbumId", reqParam.getAlbumId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), resultMap.get("ivrMMNumber").toString(),
								resultMap.get("resource_title").toString(), Integer.parseInt(resultMap.get("album_id").toString()), resultMap.get("album_title").toString(),
								Integer.parseInt(resultMap.get("artist_id").toString()), resultMap.get("artist_name").toString(), Integer.parseInt(resultMap.get("genre_id").toString()),
								resultMap.get("genre_name").toString(), Long.parseLong(resultMap.get("play_count").toString()), Long.parseLong(resultMap.get("favourite_count").toString()),
								Long.parseLong(resultMap.get("share_count").toString()), Long.parseLong(resultMap.get("size").toString()), Long.parseLong(resultMap.get("duration").toString()),
								getFullStreamUrl(resultMap.get("filePath").toString(), reqParam), resultMap.get("image_url").toString(),
								resultMap.get("video_id").toString(), getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
								getBooleanValue(resultMap.get("isKaraokeAvailable").toString()), resultMap.get("lang_karaoke_available").toString().split("#"))
			    		  );			    			  
			    	  });			
					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.albumTracks(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> allAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			
			 SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAlbumMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inAlbumId", reqParam.getAlbumId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), resultMap.get("ivrMMNumber").toString(),
			    					  resultMap.get("album_title").toString(), resultMap.get("artist_name").toString(), 
			    					  Integer.parseInt(resultMap.get("album_rating").toString()),
			    					  Integer.parseInt(resultMap.get("album_tracks_count").toString()), 
			    					  resultMap.get("image_url").toString()));			    			  
			    	  });					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.allAlbums(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> newAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAlbumMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inAlbumId", reqParam.getAlbumId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_rating").toString()),
		    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.newAlbums(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> popularAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAlbumMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inAlbumId", reqParam.getAlbumId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_rating").toString()),
		    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.popularAlbums(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> featuredAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAlbumMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inAlbumId", reqParam.getAlbumId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_rating").toString()),
		    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.featuredAlbums(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<AlbumData> albumRecommendation(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inResourceCode", reqParam.getTrackCode() )
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("album_title").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_rating").toString()),
			    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });
			
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetRecommendation`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.albumRecommendation(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public ArtistData artist(RequestParameter reqParam) {
		ArtistData artist = null;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",5)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					
					artist = new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), resultMap.get("ivrMMNumber").toString(),
							resultMap.get("artist_name").toString(),  Integer.parseInt(resultMap.get("album_count").toString()), Integer.parseInt(resultMap.get("track_count").toString()),
							resultMap.get("image_url").toString());
					artist.setTrackShareCount(Long.parseLong(resultMap.get("track_share_count").toString()));
					artist.setTrackPlayCount(Long.parseLong(resultMap.get("track_play_count").toString()));
					artist.setTrackFavouriteCount(Long.parseLong(resultMap.get("track_favourite_count").toString()));
					artist.setPageTitle(resultMap.get("artist_page_title").toString());
					artist.setMetaDescription(resultMap.get("meta_description").toString());
					artist.setMetaKeywords(resultMap.get("meta_keywords").toString());
					artist.setAboutArtist(resultMap.get("about_artist").toString());
				}
				
				
//			MySQL mysql = new MySQL();
//			// ResultSet rs = mysql.prepareCall("{call `GetArtistMetaData`(5," +
//			// reqParam.getCountryId() + "," + reqParam.getSourceId() + "," +
//			// reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','" +
//			// reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() +
//			// "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" +
//			// reqParam.getTrackCode() + "'," + reqParam.getAudioTechRefId() + "," +
//			// reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + "," +
//			// reqParam.getEndLimit() + ")}");
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(5," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					artist = new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url"));
//					artist.setTrackShareCount(rs.getLong("track_share_count"));
//					artist.setTrackPlayCount(rs.getLong("track_play_count"));
//					artist.setTrackFavouriteCount(rs.getLong("track_favourite_count"));
//					artist.setPageTitle(rs.getString("artist_page_title"));
//					artist.setMetaDescription(rs.getString("meta_description"));
//					artist.setMetaKeywords(rs.getString("meta_keywords"));
//					artist.setAboutArtist(rs.getString("about_artist"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return artist;
		}
	}

	@SuppressWarnings("finally")
	public OptScreenConfig getOptScreenConfig(RequestParameter reqParam) {
		OptScreenConfig optObj = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetOPTPageConfig");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inUserId",reqParam.getUserId())
					.addValue("inMsisdn", reqParam.getMsisdn());					
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				for(int i =0; i< ar.size();i++)
				{
				  Map resultMap = (Map) ar.get(i);
		    	  optObj= new OptScreenConfig(
		    			  	resultMap.get("title").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							resultMap.get("buttonText").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							resultMap.get("infoText").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							getBooleanValue(resultMap.get("skipVisiblity").toString()),
							getBooleanValue(resultMap.get("infoVisiblity").toString()),
							Integer.parseInt(resultMap.get("infoType").toString()),
							resultMap.get("infoUrl").toString(),
							Integer.parseInt(resultMap.get("packageId").toString()),
							resultMap.get("imageUrl").toString(),
							getBooleanValue(resultMap.get("optScreenVisibility").toString()));    			  	
		    				    			  
		    	  }
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{call `GetOPTPageConfig`('" + reqParam.getUserId() + "','" + reqParam.getMsisdn() + "')}");
//			int containder_id = 0;
//			if (rs != null) {
//				while (rs.next()) {
//					optObj = new OptScreenConfig(
//							rs.getString("title").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//							rs.getString("buttonText").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//							rs.getString("infoText").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//							rs.getBoolean("skipVisiblity"), rs.getBoolean("infoVisiblity"), rs.getInt("infoType"),
//							rs.getString("infoUrl"), rs.getInt("packageId"), rs.getString("imageUrl"),
//							rs.getBoolean("optScreenVisibility"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in Mziiki MainServlet.getBillingUserConfig(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return optObj;
		}
	}

	@SuppressWarnings("finally")
	public boolean followingArtist(RequestParameter reqParam) {
		boolean following = false;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FollowingArtist");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inArtistId", reqParam.getArtistId());
			  

			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			Map resultMap = (Map) ar.get(0);				    
			following =getBooleanValue(resultMap.get("following").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FollowingArtist`(1," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					following = rs.getBoolean("following");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return following;
		}
	}

	@SuppressWarnings("finally")
	public boolean followArtist(RequestParameter reqParam) {
		boolean following = false;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FollowingArtist");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inArtistId", reqParam.getArtistId());
			  

			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				following =getBooleanValue(resultMap.get("following").toString());
			 				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FollowingArtist`(2," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					following = rs.getBoolean("following");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return following;
		}
	}

	@SuppressWarnings("finally")
	public boolean unfollowArtist(RequestParameter reqParam) {
		boolean following = false;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("FollowingArtist");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inArtistId", reqParam.getArtistId());
			 
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			Map resultMap = (Map) ar.get(0);				    
			following =getBooleanValue(resultMap.get("following").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `FollowingArtist`(3," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					following = rs.getBoolean("following");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return following;
		}
	}

	@SuppressWarnings("finally")
	public int insertNotification(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("NotificationSend");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inMsg", reqParam.getMessage())
					.addValue("inValue", reqParam.getTrackCode())
					.addValue("inNotification_title", reqParam.getSender())
					.addValue("inXid", reqParam.getNotificationId());
			 
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `NotificationSend`(" + reqParam.getUserId() + ",'"
//					+ reqParam.getMessage() + "'," + reqParam.getTrackCode() + ",'" + reqParam.getSender() + "',"
//					+ reqParam.getNotificationId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.insertNotification(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendation(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inResourceCode", reqParam.getTrackCode() )
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_count").toString()),
			    			  Integer.parseInt(resultMap.get("track_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });
					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetRecommendation`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out
					.println("Exception in HutchTriBeatzMainServlet.artistRecommendation(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendationByAlbum(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",1)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inId", reqParam.getId())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),		    		
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
				
				
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistRecommendationByAlbum(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public SubscriptionBenefits SubscriptionBenifits(int countryID, String langCode) {
		SubscriptionBenefits obj = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetBenefits");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", countryID)
					.addValue("in_lang", langCode);
					
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
				
			  for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					obj = new SubscriptionBenefits(resultMap.get("casualGeneralLine").toString(),
							resultMap.get("liteGeneralLine").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							resultMap.get("premiumGeneralLine").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							resultMap.get("benefitsHeading").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							resultMap.get("benefits").toString().replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r")
									.replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
									resultMap.get("pageHeader").toString(),
									resultMap.get("packHeader").toString(), 
									resultMap.get("benefitHeader").toString());
				}
			  
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetBenefits`(" + countryID + ",'" + langCode + "')}");
//			while (rs.next()) {
//				obj = new SubscriptionBenefits(rs.getString("casualGeneralLine"),
//						rs.getString("liteGeneralLine").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//						rs.getString("premiumGeneralLine").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//						rs.getString("benefitsHeading").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//						rs.getString("benefits").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r")
//								.replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
//						rs.getString("pageHeader"), rs.getString("packHeader"), rs.getString("benefitHeader"));
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.SubscriptionBenifits(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return obj;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendationByArtist(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inId", reqParam.getId())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),		    			 
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistRecommendation`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistRecommendationByArtist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendationByPlaylist(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inId", reqParam.getId())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),		    		
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistRecommendationByPlaylist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendationByGenre(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",4)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inId", reqParam.getId())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),		    		
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistRecommendationByGenre(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendationByTrack(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",5)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inId", reqParam.getId())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),		    		
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(5," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistRecommendationByTrack(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<ArtistData> allArtists(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			 
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				
			  ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("album_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.allArtists(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<ArtistData> newArtists(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item-> {
			    	  Map resultMap = (Map) item;
			    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_count").toString()),
			    			  Integer.parseInt(resultMap.get("track_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });	
			
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.newArtists(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<ArtistData> popularArtists(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item-> {
			    	  Map resultMap = (Map) item;
			    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_count").toString()),
			    			  Integer.parseInt(resultMap.get("track_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });	
			
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.popularArtists(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<ArtistData> featuredArtists(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",11)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("album_count").toString()),
		    			  Integer.parseInt(resultMap.get("track_count").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(11," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.featuredArtists(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<AlbumData> artistAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("album_title").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_rating").toString()),
			    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistAlbums(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public GenreBean genre(RequestParameter reqParam) {
		GenreBean genre = null;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",8)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inGenreId", reqParam.getGenreId() )
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					 
					for(int i =0; i< ar.size();i++)
					{
						Map resultMap = (Map) ar.get(i);
						genre = new GenreBean(Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  resultMap.get("image_url").toString()
			    			  );	
					}					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(8," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					genre = new GenreBean(rs.getInt("genre_id"), rs.getString("genre_name"), rs.getString("image_url"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genre(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return genre;
		}
	}

	public List<AlbumData> genreAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inGenreId", reqParam.getGenreId() )
						.addValue("inArtistId", reqParam.getArtistId())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("album_title").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_rating").toString()),
			    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });			
					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreAlbums(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<AlbumData> genreArtistAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",6)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inGenreId", reqParam.getGenreId() )
						.addValue("inArtistId", reqParam.getArtistId())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new AlbumData(Integer.parseInt(resultMap.get("album_id").toString()), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("album_title").toString(),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("album_rating").toString()),
			    			  Integer.parseInt(resultMap.get("album_tracks_count").toString()),
			    			  resultMap.get("image_url").toString()
			    			  ));	
			    				    			  
			    	  });			
					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(6," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
//							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
//							rs.getInt("album_tracks_count"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.genreArtistAlbums(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<ArtistData> genreArtists(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inGenreId", reqParam.getGenreId() )
						.addValue("inArtistId", reqParam.getArtistId())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  
			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			//Map resultMap1 = (Map) ar.get(0);
			      
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
	    	  lst.add(new ArtistData(Integer.parseInt(resultMap.get("artist_id").toString()), 
	    			  resultMap.get("ivrMMNumber").toString(),
	    			  resultMap.get("artist_name").toString(),
	    			  Integer.parseInt(resultMap.get("album_count").toString()),
	    			  Integer.parseInt(resultMap.get("track_count").toString()),
	    			  resultMap.get("image_url").toString()
	    			  ));	
	    				    			  
	    	  });
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
//							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.genreArtists(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> artistTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), resultMap.get("ivrMMNumber").toString(),
								resultMap.get("resource_title").toString(), Integer.parseInt(resultMap.get("album_id").toString()), resultMap.get("album_title").toString(),
								Integer.parseInt(resultMap.get("artist_id").toString()), resultMap.get("artist_name").toString(), Integer.parseInt(resultMap.get("genre_id").toString()),
								resultMap.get("genre_name").toString(), Long.parseLong(resultMap.get("play_count").toString()), Long.parseLong(resultMap.get("favourite_count").toString()),
								Long.parseLong(resultMap.get("share_count").toString()), Long.parseLong(resultMap.get("size").toString()), Long.parseLong(resultMap.get("duration").toString()),
								getFullStreamUrl(resultMap.get("filePath").toString(), reqParam), resultMap.get("image_url").toString(),
								resultMap.get("video_id").toString(), getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
								getBooleanValue(resultMap.get("isKaraokeAvailable").toString()), resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  );	
			    				    			  
			    	  });			
					
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}
	
	
	

	public List<TrackData> geAllTracks(RequestParameter reqParam, int countryId, String itemType, int imageTechRefId,
			int startLimit, int endLimit) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAllItemList");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", countryId)
					.addValue("inItemType", itemType)
					.addValue("inImageTechRefId", imageTechRefId)
					.addValue("inStart", startLimit)
					.addValue("inLimit", endLimit);
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
//			MySQL mysql = new MySQL();
//			// ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(6," +
//			// reqParam.getCountryId() + "," + reqParam.getArtistId() + "," +
//			// reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," +
//			// reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			ResultSet rs = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
//					+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistTopTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",6)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(6," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistSingleTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",7)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(7," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistFeaturedTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",8)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(8," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistVideoTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",9)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(9," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> newTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",4)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.newTracks(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> popularTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",10)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  Integer.parseInt(resultMap.get("album_id").toString()),
		    			  resultMap.get("album_title").toString(),
		    			  Integer.parseInt(resultMap.get("artist_id").toString()),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("genre_id").toString()),
		    			  resultMap.get("genre_name").toString(),
		    			  Long.parseLong(resultMap.get("play_count").toString()),
		    			  Long.parseLong(resultMap.get("favourite_count").toString()),
		    			  Long.parseLong(resultMap.get("share_count").toString()),
		    			  Long.parseLong(resultMap.get("size").toString()),
		    			  Long.parseLong(resultMap.get("duration").toString()),
		    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
		    			  resultMap.get("image_url").toString(),
		    			  resultMap.get("video_id").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"))
		    			  
		    			  );	
		    				    			  
		    	  });		
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(10," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.popularTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> featuredTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetArtistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",4)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  Integer.parseInt(resultMap.get("album_id").toString()),
		    			  resultMap.get("album_title").toString(),
		    			  Integer.parseInt(resultMap.get("artist_id").toString()),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("genre_id").toString()),
		    			  resultMap.get("genre_name").toString(),
		    			  Long.parseLong(resultMap.get("play_count").toString()),
		    			  Long.parseLong(resultMap.get("favourite_count").toString()),
		    			  Long.parseLong(resultMap.get("share_count").toString()),
		    			  Long.parseLong(resultMap.get("size").toString()),
		    			  Long.parseLong(resultMap.get("duration").toString()),
		    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
		    			  resultMap.get("image_url").toString(),
		    			  resultMap.get("video_id").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"))
		    			  
		    			  );	
		    				    			  
		    	  });		
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.featuredTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> trackRecommendation(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetRecommendation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inResourceCode", reqParam.getTrackCode() )
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#")));
			    				    			  
			    	  });					
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetRecommendation`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.trackRecommendation(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getAutoPlayFeature(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetAutoPlayMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inResourceCode", reqParam.getTrackCode() )
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#")));
			    				    			  
			    	  });	
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `GetAutoPlayMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.GetAutoPlayMetaData(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getFavouriteTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",1)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  Integer.parseInt(resultMap.get("album_id").toString()),
		    			  resultMap.get("album_title").toString(),
		    			  Integer.parseInt(resultMap.get("artist_id").toString()),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("genre_id").toString()),
		    			  resultMap.get("genre_name").toString(),
		    			  Long.parseLong(resultMap.get("play_count").toString()),
		    			  Long.parseLong(resultMap.get("favourite_count").toString()),
		    			  Long.parseLong(resultMap.get("share_count").toString()),
		    			  Long.parseLong(resultMap.get("size").toString()),
		    			  Long.parseLong(resultMap.get("duration").toString()),
		    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
		    			  resultMap.get("image_url").toString(),
		    			  resultMap.get("video_id").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"))
		    			  
		    			  );	
		    				    			  
		    	  });	
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getFavouriteTracks(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public int addTrackToFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);
				responseCode = Integer.parseInt(resultMap.get("code").toString());				

			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.addTrackToFavourite(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int delTrackFromFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode() )
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			  ArrayList<Object> ar = new ArrayList<Object>();
			  ar = (ArrayList) rs.get("#result-set-1");
			  Map resultMap = (Map) ar.get(0);
			  responseCode = Integer.parseInt(resultMap.get("code").toString());		
			  
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out
					.println("Exception in HutchTriBeatzMainServlet.delTrackFromFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public List<TrackData> selectFavourite(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",1)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());

			  		Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });	
					
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.selectFavourite(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public int insertFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			  ArrayList<Object> ar = new ArrayList<Object>();
			  ar = (ArrayList) rs.get("#result-set-1");
			  Map resultMap = (Map) ar.get(0);
			  responseCode = Integer.parseInt(resultMap.get("code").toString());     				
			 
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.insertFavourite(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	 

	public int updateFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",5)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getOldOrderId())
						.addValue("inLimit", reqParam.getNewOrderId());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			  ArrayList<Object> ar = new ArrayList<Object>();
			  ar = (ArrayList) rs.get("#result-set-1");
			  Map resultMap = (Map) ar.get(0);
			  responseCode = Integer.parseInt(resultMap.get("code").toString());    
			  
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(5," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getOldOrderId()
//					+ "," + reqParam.getNewOrderId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.updateFavourite(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int deleteFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			  ArrayList<Object> ar = new ArrayList<Object>();
			  ar = (ArrayList) rs.get("#result-set-1");
			  Map resultMap = (Map) ar.get(0);
			  responseCode = Integer.parseInt(resultMap.get("code").toString());    
			  
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.deleteFavourite(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public DownloadURL getDownloadUrl(RequestParameter reqParam, String messageException) {

	//	System.out.println("API Name--" + apiDomainName);
		DownloadURL dwld = new DownloadURL();
		try {
			dwld.setCode(0);
			dwld.setMessage("Success");
			String timestamp = String.valueOf(System.currentTimeMillis());
			try {
				SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserOffline");			  			  
				  SqlParameterSource inParams = new MapSqlParameterSource()
						  	.addValue("inFlag",2)
							.addValue("inCountryId", reqParam.getCountryId())
							.addValue("inSourceId", reqParam.getSourceId())
							.addValue("inUserId", reqParam.getUserId())
							.addValue("inOS", reqParam.getOperatingSystem())
							.addValue("inOSV", reqParam.getOperatingSystemVersion())
							.addValue("inDevModel", reqParam.getDeviceModel())
							.addValue("inDevId", reqParam.getDeviceId()) 
							.addValue("inDevPin", reqParam.getDevicePin())						  
							.addValue("inResourceCode", reqParam.getTrackCode() )							
							.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
							.addValue("inImageTechRefId", reqParam.getImageTechRefId())
							.addValue("inValidTime", timestamp)
							.addValue("inStart", reqParam.getStartLimit())
							.addValue("inLimit", reqParam.getEndLimit());

				  	Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					Map resultMap = (Map) ar.get(0);				    
					dwld.setCode(Integer.parseInt(resultMap.get("code").toString()));
					dwld.setMessage(resultMap.get("message").toString());

				  
//				MySQL mysql = new MySQL();
//				ResultSet rs = mysql.prepareCall("{CALL `UserOffline`(2," + reqParam.getCountryId() + ","
//						+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem()
//						+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//						+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode()
//						+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ",'" + timestamp
//						+ "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//				if (rs != null) {
//					while (rs.next()) {
//						dwld.setCode(rs.getInt("code"));
//						dwld.setMessage(rs.getString("message"));
//					}
//				}
//				mysql.close();
			} catch (Exception e) {
				System.out.println(
						"Exception in Glo Nigeria MainServlet.getDownloadUrl_New(RequestParameter reqParam, DeviceInformation devInfo) - "
								+ e.getMessage());
			}

			String params = "ocid=" + reqParam.getCountryId() + "&src=" + reqParam.getSourceId() + "&os="
					+ reqParam.getOperatingSystem() + "&osv=" + reqParam.getOperatingSystemVersion() + "&model="
					+ reqParam.getDeviceModel() + "&devid=" + reqParam.getDeviceId() + "&devpin="
					+ reqParam.getDevicePin() + "&evt=39&userid=" + reqParam.getUserId() + "&trackid="
					+ reqParam.getTrackCode() + "&dtype=1&dwldquality=" + reqParam.getDownloadQuality() + "&token="
					+ timestamp + "&lang=" + reqParam.getLanguageCode();
			dwld.setWithAd(0);
			dwld.setUrl(apiDomainName + "downloadtrack?p=" + DatatypeConverter
					.printBase64Binary(new AESEncriptionDecription().encrypt(params).getBytes("UTF-8")));
		} catch (Exception e) {
			dwld.setCode(110);
			dwld.setMessage(messageException);
			System.out.println(
					"Exception in Glo Nigeria MainServlet.getDownloadUrl_New(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return dwld;
		}
	}

	public void requestDedication(RequestParameter reqParam) {
		try {
		//	MySQL mysql = new MySQL();
			int count;
			String arr[];
			if (reqParam.getEventType().equalsIgnoreCase("1")) {
				arr = reqParam.getEmailAddress().split(";");
				count = arr.length;
				arr = null;
			} else {
				arr = reqParam.getMsisdn().split(";");
				count = arr.length;
				arr = null;
			}
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserDedication");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",1)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inDedicationTypeId", reqParam.getEventType() )
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inRecipientCount", count)
						.addValue("inDedicateeName", reqParam.getUserName())
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inEmail", reqParam.getEmailAddress())
						.addValue("inDedicationMessage", reqParam.getDedicationMessage())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId());
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				
			  
//			  
//			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
//					+ reqParam.getTrackCode() + "'," + count + ",'" + reqParam.getUserName() + "','"
//					+ reqParam.getMsisdn() + "','" + reqParam.getEmailAddress() + "','"
//					+ reqParam.getDedicationMessage() + "'," + reqParam.getAudioTechRefId() + ","
//					+ reqParam.getImageTechRefId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.requestDedication(RequestParameter reqParam) - "
					+ e.getMessage());
		}
	}

	public List<DedicateeData> recentlyDedicatee(RequestParameter reqParam) {
		List<DedicateeData> lst = new ArrayList<DedicateeData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserDedication");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inDedicationTypeId", reqParam.getEventType() )
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inRecipientCount", 0)
						.addValue("inDedicateeName", reqParam.getUserName())
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inEmail", reqParam.getEmailAddress())
						.addValue("inDedicationMessage", reqParam.getDedicationMessage())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId());
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new DedicateeData(Integer.parseInt(resultMap.get("id").toString()), 
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("dedicatee_name").toString(),
		    			  resultMap.get("dedicatee").toString(),
		    			  resultMap.get("datetime").toString()
		    			  ));			    				    			  
		    	  });			    
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
//					+ reqParam.getTrackCode() + "',0,'" + reqParam.getUserName() + "','" + reqParam.getMsisdn() + "','"
//					+ reqParam.getEmailAddress() + "','" + reqParam.getDedicationMessage() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new DedicateeData(rs.getInt("id"), rs.getString("resource_title"),
//							rs.getString("dedicatee_name"), rs.getString("dedicatee"), rs.getString("datetime")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.recentlyDedicatee(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<DedicateeData> removeRecentlyDedicatee(RequestParameter reqParam) {
		List<DedicateeData> lst = new ArrayList<DedicateeData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserDedication");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inDedicationTypeId", reqParam.getEventType() )
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inRecipientCount", 0)
						.addValue("inDedicateeName", reqParam.getUserName())
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inEmail", reqParam.getEmailAddress())
						.addValue("inDedicationMessage", reqParam.getDedicationMessage())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId());
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new DedicateeData(Integer.parseInt(resultMap.get("id").toString()), 
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("dedicatee_name").toString(),
		    			  resultMap.get("dedicatee").toString(),
		    			  resultMap.get("datetime").toString()
		    			  ));			    				    			  
		    	  });			    
				
//				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
//					+ reqParam.getTrackCode() + "',0,'" + reqParam.getUserName() + "','" + reqParam.getMsisdn() + "','"
//					+ reqParam.getEmailAddress() + "','" + reqParam.getDedicationId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new DedicateeData(rs.getInt("id"), rs.getString("resource_title"),
//							rs.getString("dedicatee_name"), rs.getString("dedicatee"), rs.getString("date_time")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.removeRecentlyDedicatee(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<DedicationData> getDedicationDetail(RequestParameter reqParam) {
		List<DedicationData> lst = new ArrayList<DedicationData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserDedication");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",4)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inDedicationTypeId", reqParam.getEventType() )
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inRecipientCount", 0)
						.addValue("inDedicateeName", reqParam.getUserName())
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inEmail", reqParam.getEmailAddress())
						.addValue("inDedicationMessage", reqParam.getDedicationMessage())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId());
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new DedicationData(
		    			  	Integer.parseInt(resultMap.get("dedication_id").toString()), 
		    			  	resultMap.get("user_name").toString(),
							resultMap.get("request_date").toString(),
							resultMap.get("dedication_msg").toString(),
							resultMap.get("resource_code").toString(),
							resultMap.get("resource_title").toString(),
							Integer.parseInt(resultMap.get("album_id").toString()),
							resultMap.get("album_title").toString(),
							Integer.parseInt(resultMap.get("artist_id").toString()),
							resultMap.get("artist_name").toString(),
							Integer.parseInt(resultMap.get("genre_id").toString()),
							resultMap.get("genre_name").toString(),
							Long.parseLong(resultMap.get("size").toString()),
							Long.parseLong(resultMap.get("duration").toString()),
							getFullStreamUrl(resultMap.get("filePath").toString(), reqParam).toString(),
							resultMap.get("image_url").toString(),
							resultMap.get("user_image_url").toString())
		    			  );			    				    			  
		    	  });			    
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
//					+ reqParam.getTrackCode() + "',0,'" + reqParam.getUserName() + "','" + reqParam.getMsisdn() + "','"
//					+ reqParam.getEmailAddress() + "','" + reqParam.getDedicationId() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new DedicationData(rs.getInt("dedication_id"), ).toStri).toString(),(),"user_name"),
//							rs.getString("request_date"), rs.getString("dedication_msg"), rs.getString("resource_code"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("user_image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getDedicationDetail(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<MobileOperatorData> crbtOperators(RequestParameter reqParam) {
		List<MobileOperatorData> lst = new ArrayList<MobileOperatorData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("CallerRingBackTone");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem())
					.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
					.addValue("inDeviceModel", reqParam.getDeviceModel())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inDevicePin", reqParam.getDevicePin())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inTransactionId", reqParam.getTransactionId())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inPassword", reqParam.getOneTimePassword());
			  

			Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");				
				      
			ar.forEach(item->{
		    Map resultMap = (Map) item;
		    lst.add(new MobileOperatorData(Integer.parseInt(resultMap.get("id").toString()), 
		    		resultMap.get("operator").toString()
		    	));	
		    				    			  
		    });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.crbtOperators(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	/*
	 * public Transaction requestCRBT(RequestParameter reqParam, DeviceInformation
	 * reqParam) { Transaction x = new Transaction(reqParam.getCountryId(),
	 * reqParam.getSourceId(), reqParam.getMsisdn()); try { MySQL mysql = new
	 * MySQL(); ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(2," +
	 * reqParam.getCountryId() + "," + reqParam.getOperatorId() + "," +
	 * reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "','" +
	 * reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() +
	 * "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," +
	 * reqParam.getUserId() + ",'" + reqParam.getTrackCode() + "','" + x.getXid() +
	 * "','" + reqParam.getMsisdn() + "','" + reqParam.getOneTimePassword() +
	 * "')}"); if (rs != null) { while (rs.next()) { //lst.add(new
	 * MobileOperatorData(rs.getInt("id"), rs.getString("operator"))); } }
	 * mysql.close(); } catch (Exception e) { System.out.
	 * println("Exception in HutchTriBeatzMainServlet.requestCRBT(RequestParameter reqParam) - "
	 * + e.getMessage()); } finally { return x; } }
	 */

	public Integer requestCRBT(RequestParameter reqParam) {
		Transaction x = new Transaction(reqParam.getCountryId(), reqParam.getSourceId(), reqParam.getMsisdn());
		Integer response = 167;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("CallerRingBackTone");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem())
					.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
					.addValue("inDeviceModel", reqParam.getDeviceModel())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inDevicePin", reqParam.getDevicePin())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inTransactionId", x.getXid())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inPassword", reqParam.getOneTimePassword());
			  

			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				response =Integer.parseInt(resultMap.get("code").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() + "','"
//					+ reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					response = rs.getInt("code");
//					// lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestCRBT(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return response;
		}
	}

	public int resendOTP(RequestParameter reqParam) {
		int responseCode = 110;
		try {
				
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("CallerRingBackTone");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem())
					.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
					.addValue("inDeviceModel", reqParam.getDeviceModel())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inDevicePin", reqParam.getDevicePin())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inTransactionId", reqParam.getTransactionId())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inPassword", reqParam.getOneTimePassword());
			  

			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
					
//			  
//			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.resendOTP(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int otpVerification(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("CallerRingBackTone");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",4)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )
						
						.addValue("inTransactionId", reqParam.getTransactionId())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());

			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			Map resultMap = (Map) ar.get(0);				    
			responseCode =Integer.parseInt(resultMap.get("code").toString());
			 				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.otpVerification(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public List<MobileOperatorData> billingOperators(RequestParameter reqParam) {
		List<MobileOperatorData> lst = new ArrayList<MobileOperatorData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("AvailableOperatorBilling");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
				.addValue("inCountryId", reqParam.getCountryId());
								
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new MobileOperatorData(Integer.parseInt(resultMap.get("id").toString()), 
		    			  resultMap.get("operator").toString()
		    			  ));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `AvailableOperatorBilling`(" + reqParam.getCountryId() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
//				}
//			}
		//	mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.billingOperators(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public Transaction requestPaidDownload(RequestParameter reqParam) {
		Transaction x = new Transaction(reqParam.getCountryId(), reqParam.getSourceId(), reqParam.getMsisdn());
		try {

			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PaidDownload");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )
						
						.addValue("inTransactionId", reqParam.getTransactionId())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				//responseCode =Integer.parseInt(resultMap.get("code").toString());
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() + "','"
//					+ reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.requestPaidDownload(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return x;
		}
	}

	public int resendOTPPaidDownload(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PaidDownload");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )
						
						.addValue("inTransactionId", reqParam.getTransactionId())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out
					.println("Exception in HutchTriBeatzMainServlet.resendOTPPaidDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int otpVerificationPaidDownload(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PaidDownload");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",4)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )
						
						.addValue("inTransactionId", reqParam.getTransactionId())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
 
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.otpVerificationPaidDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int chargingRequestPaidDownload(RequestParameter reqParam) {
		int responseCode = 0;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Download");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inBillingViaId", reqParam.getViaId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inPackageId", 2)
						
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inMobileNumber", reqParam.getMsisdn());
						
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);
				responseCode =Integer.parseInt(resultMap.get("code").toString());
     
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `Download`(" + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + "," + reqParam.getViaId() + ","
//					+ reqParam.getUserId() + ",2,'" + reqParam.getTrackCode() + "','" + reqParam.getMsisdn() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.chargingRequestPaidDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public List<Purchase> getPurchaseHistory(RequestParameter reqParam) {
		List<Purchase> lst = new ArrayList<Purchase>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PaidDownload");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",5)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )
						
						.addValue("inTransactionId", reqParam.getTransactionId())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new Purchase(
		    			  resultMap.get("resource_code").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("purchase_date").toString(),
		    			  resultMap.get("expiry_date").toString(),
		    			  Integer.parseInt(resultMap.get("allow_download").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(5," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new Purchase(rs.getString("resource_code"), rs.getString("resource_title"),
//							rs.getString("artist_name"), rs.getString("album_title"), rs.getString("purchase_date"),
//							rs.getString("expiry_date"), rs.getInt("allow_download"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getPurchaseHistory(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<DownloadHistory> getDownloadHistory(RequestParameter reqParam) {
		List<DownloadHistory> lst = new ArrayList<DownloadHistory>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PaidDownload");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",7)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())
						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )
						
						.addValue("inTransactionId", reqParam.getTransactionId())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());
				
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new DownloadHistory(
		    			  resultMap.get("date").toString(),
		    			  resultMap.get("resource_code").toString(),
		    			  Integer.parseInt(resultMap.get("download_type_id").toString()), 
		    			  resultMap.get("resource_title").toString(),
		    			  resultMap.get("artist_name").toString(),
		    			  resultMap.get("album_title").toString(),
		    			  resultMap.get("expiry_date").toString(),
		    			  Integer.parseInt(resultMap.get("allow_download").toString()),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });

			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(7," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new DownloadHistory(rs.getString("date"), rs.getString("resource_code"),
//							rs.getInt("download_type_id"), rs.getString("resource_title"), rs.getString("artist_name"),
//							rs.getString("album_title"), rs.getString("expiry_date"), rs.getInt("allow_download"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getDownloadHistory(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public Transaction mobileNumberVerificationRequest(RequestParameter reqParam) {
		Transaction x = new Transaction(reqParam.getCountryId(), reqParam.getSourceId(), reqParam.getMsisdn());
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("MobileNumberVerification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",1)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inOperatorId", reqParam.getOperatorId())
						.addValue("inSourceId", reqParam.getSourceId())						
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())	
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inResourceCode", reqParam.getTrackCode() )						
						.addValue("inTransactionId", x.getXid())						
						.addValue("inMsisdn", reqParam.getMsisdn())
						.addValue("inPassword", reqParam.getOneTimePassword());
			  
			   
						
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
//				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `MobileNumberVerification`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() + "','"
//					+ reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.mobileNumberVerificationRequest(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return x;
		}
	}

	 

	public int mobileNumberVerificationResendOTP(RequestParameter reqParam) {
		int responseCode = 110;
		try {

			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("MobileNumberVerification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem())
					.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
					.addValue("inDeviceModel", reqParam.getDeviceModel())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inDevicePin", reqParam.getDevicePin())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inTransactionId", reqParam.getTransactionId())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inPassword", reqParam.getOneTimePassword());
					
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `MobileNumberVerification`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.mobileNumberVerificationResendOTP(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int mobileNumberVerificationOTPVerification(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("MobileNumberVerification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inOperatorId", reqParam.getOperatorId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inOperatingSystem", reqParam.getOperatingSystem())
					.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
					.addValue("inDeviceModel", reqParam.getDeviceModel())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inDevicePin", reqParam.getDevicePin())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inTransactionId", reqParam.getTransactionId())
					.addValue("inMsisdn", reqParam.getMsisdn())
					.addValue("inPassword", reqParam.getOneTimePassword());
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				responseCode =Integer.parseInt(resultMap.get("code").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `MobileNumberVerification`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
//					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
//					+ "','" + reqParam.getOneTimePassword() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					responseCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.mobileNumberVerificationOTPVerification(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public CountInfo getFavouriteCount(RequestParameter reqParam) {
		CountInfoData info = new CountInfoData(reqParam.getUserId(), 0, 0, 0, 0, 1, "0", 0);
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserFavourite");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",4)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
			  ArrayList<Object> ar = new ArrayList<Object>();
			  ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
			  for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					info = new CountInfoData(Integer.parseInt(resultMap.get("user_id").toString()),
						  Integer.parseInt(resultMap.get("total_count").toString()),
						  Integer.parseInt(resultMap.get("user_type_id").toString()),
						  Integer.parseInt(resultMap.get("user_status").toString()),
						  Integer.parseInt(resultMap.get("play_counter").toString()),
						  Integer.parseInt(resultMap.get("device_status").toString()),
		    			  resultMap.get("device_status_msg").toString(),
		    			  getTimeStampForStreaming()
		    			  );	
				}					
				
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			while (rs.next()) {
//				info = new CountInfoData(rs.getInt("user_id"), rs.getInt("total_count"), rs.getInt("user_type_id"),
//						rs.getInt("user_status"), rs.getInt("play_counter"), rs.getInt("device_status"),
//						rs.getString("device_status_msg"), getTimeStampForStreaming());
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getFavouriteCount(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return new CountInfo(info);
		}
	}

	public CountInfo getNotificationCount(RequestParameter reqParam) {
		CountInfoData info = new CountInfoData(reqParam.getUserId(), 0, 0, 0, 0, 1, "0", 0);
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserNotification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("insourceId", reqParam.getSourceId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inNotificationId", reqParam.getNotificationId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("lang", reqParam.getLanguageCode())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					info = new CountInfoData(Integer.parseInt(resultMap.get("user_id").toString()),
						  Integer.parseInt(resultMap.get("total_count").toString()),
						  Integer.parseInt(resultMap.get("user_type_id").toString()),
						  Integer.parseInt(resultMap.get("user_status").toString()),
						  Integer.parseInt(resultMap.get("play_counter").toString()),
						  Integer.parseInt(resultMap.get("device_status").toString()),
		    			  resultMap.get("device_status_msg").toString(),
		    			  getTimeStampForStreaming()
		    			  );	
				}					
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{call `UserNotification`(3," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
//							+ reqParam.getDeviceId() + "'," + reqParam.getUserId() + ",'" + reqParam.getNotificationId()
//							+ "'," + reqParam.getImageTechRefId() + ",'" + reqParam.getLanguageCode() + "',"
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			while (rs.next()) {
//				info = new CountInfoData(rs.getInt("user_id"), rs.getInt("total_count"), rs.getInt("user_type_id"),
//						rs.getInt("user_status"), rs.getInt("play_counter"), rs.getInt("device_status"),
//						rs.getString("device_status_msg"), getTimeStampForStreaming());
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getNotificationCount(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return new CountInfo(info);
		}
	}

	public UserOfflineInformationBean getUserOfflineInformation(RequestParameter reqParam, String messageOffline1,
			String messageOffline2) {
		UserOfflineInformationBean info = null;
		String timestamp = String.valueOf(System.currentTimeMillis());
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserOffline");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId())
					.addValue("inDevPin", reqParam.getDevicePin())
					.addValue("inResourceCode", reqParam.getTrackCode())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inValidTime", timestamp)
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
					
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
			
			  for(int i =0; i< ar.size();i++)
			  {
				Map resultMap = (Map) ar.get(i);
				info = new UserOfflineInformationBean(Integer.parseInt(resultMap.get("code").toString()),
	    			  resultMap.get("message").toString(),
	    			  Integer.parseInt(resultMap.get("total_credits").toString()),
	    			  Integer.parseInt(resultMap.get("available_credits").toString()),
	    			  Integer.parseInt(resultMap.get("used_credits").toString()),
	    			  messageOffline1, messageOffline2
	    			  );	
			 }	
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserOffline`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ",'" + timestamp + "',"
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					info = new UserOfflineInformationBean(rs.getInt("code"), rs.getString("message"),
//							rs.getInt("total_credits"), rs.getInt("available_credits"), rs.getInt("used_credits"),
//							messageOffline1, messageOffline2);
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getUserOfflineInformation(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return info;
		}
	}

	public String getFullStreamUrl(String rcode, RequestParameter reqParam) {
		return "-";
	}

	public String getFullStreamUrl_WithEncryption(String rcode, RequestParameter reqParam) {
		String domainName = "stream.mziiki.com";
		String file = rcode.replaceAll("/var/www/html/ams/Content/", "amazons3/");
		String strCurrentDateTime = String.valueOf(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
		String secretkey = getSignature(file, strCurrentDateTime, "mycompanysecretvod");
		String url = "";
		String furl = "";
		String params = "dt=" + strCurrentDateTime + "&sc=" + secretkey + "&uid=" + reqParam.getUserId() + "&cid="
				+ reqParam.getCountryId() + "&sid=" + reqParam.getSourceId() + "&os=" + reqParam.getOperatingSystem()
				+ "&osv=" + reqParam.getOperatingSystemVersion() + "&model=" + reqParam.getDeviceModel() + "&devid="
				+ reqParam.getDeviceId();
		String playlist = "";
		try {
			if (reqParam.getStreamingProtocol().equalsIgnoreCase("rtsp")) {
				furl = "rtsp://" + domainName + "/aod/_definst_/" + reqParam.getAudioFormat() + ":";
			} else if (reqParam.getStreamingProtocol().equalsIgnoreCase("http")) {
				url = rcode.replaceAll("/var/www/html/ams/Content/",
						"http://s3-eu-west-1.amazonaws.com/svacontent/Content/");
				return url;
			} else {
				furl = "http://" + domainName + "/aod/_definst_/" + reqParam.getAudioFormat() + ":";
				playlist = "/playlist.m3u8";
			}
			try {
				file = DatatypeConverter.printBase64Binary(file.getBytes("UTF-8"));
				params = DatatypeConverter.printBase64Binary(params.getBytes("UTF-8"));
			} catch (Exception e) {
				System.out.println(
						"Exception in HutchTriBeatzMainServlet.getFullStreamUrl_WithEncryption(String rcode, RequestParameter reqParam) - "
								+ e.getMessage());
			}
		} catch (Exception e) {
			url = "-";
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFullStreamUrl_WithEncryption(String rcode, RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			url = furl + file + playlist + "?" + params;
			return url;
		}

	}

	public String getFullStreamUrl_WithOutEncryption(String rcode, RequestParameter reqParam) {

		String url = "";
		String furl = "";
		String lurl = "";

		String params = "uid=" + reqParam.getUserId() + "&cid=" + reqParam.getCountryId() + "&sid="
				+ reqParam.getSourceId() + "&os=" + reqParam.getOperatingSystem() + "&osv="
				+ reqParam.getOperatingSystemVersion() + "&model=" + reqParam.getDeviceModel() + "&devid="
				+ reqParam.getDeviceId() + "&dt=" + new SimpleDateFormat("yyyyMMdd").format(new Date());
		String playlist = "";
		try {
			if (reqParam.getStreamingProtocol().equalsIgnoreCase("rtsp")) {
				furl = "rtsp://stream.mziiki.com/aod/_definst_/";
				lurl = reqParam.getAudioFormat() + ":amazons3/" + rcode.replaceAll("/var/www/html/ams/Content/", "");
			} else if (reqParam.getStreamingProtocol().equalsIgnoreCase("http")) {
				url = rcode.replaceAll("/var/www/html/ams/Content/",
						"http://s3-eu-west-1.amazonaws.com/svacontent/Content/");
				return url;
			} else {
				furl = "http://stream.mziiki.com/aod/_definst_/";
				lurl = reqParam.getAudioFormat() + ":amazons3/" + rcode.replaceAll("/var/www/html/ams/Content/", "");
				playlist = "/playlist.m3u8";
			}
			try {
				// lurl = DatatypeConverter.printBase64Binary(lurl.getBytes("UTF-8"));
				// params = DatatypeConverter.printBase64Binary(params.getBytes("UTF-8"));
			} catch (Exception e) {
				System.out.println(
						"Exception in HutchTriBeatzMainServlet.getFullStreamUrl_WithOutEncryption(String rcode, RequestParameter reqParam) - "
								+ e.getMessage());
			}
		} catch (Exception e) {
			url = "-";
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFullStreamUrl_WithOutEncryption(String rcode, RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			url = furl + lurl + playlist + "?" + params;
			return url;
		}

	}

	public String getSignature(String filename, String timestamp, String secret_code) {
		String hashcheck = filename + timestamp + secret_code;
		String hashtext = "";
		try {
			MessageDigest m;
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(hashcheck.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getSignature(String filename, String timestamp, String secret_code) - "
							+ e.getMessage());
		}
		return hashtext;
	}

	public List<GenreBean> allGenre(RequestParameter reqParam) {
		List<GenreBean> lst = new ArrayList<GenreBean>();
		try {

			  SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inGenreId", reqParam.getGenreId() )
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//		
	  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);
					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new GenreBean(Integer.parseInt(resultMap.get("genre_id").toString()), resultMap.get("genre_name").toString(),
			    			  resultMap.get("image_url").toString()));					    	   
					});
					      
//					      for (int i=0; i< ar.size();i++)
//					      {
//					    	  Map resultMap = (Map) ar.get(i);
//					    	  lst.add(new GenreBean(Integer.parseInt(resultMap.get("genre_id").toString()), resultMap.get("genre_name").toString(),
//					    			  resultMap.get("image_url").toString()));
//					      }
					    ///////  System.out.println(Integer.parseInt(resultMap1.get("genre_id").toString()));
					      
//					      rs.forEach((k,v) -> System.out.println("Key = "
//					                + k + ", Value = " + v)); 
					      
//					      ArrayList<Object> ar = new ArrayList<Object>();
//					      ar = (ArrayList) rs.get("#result-set-1");
//					      Map resultMap = (Map) ar.get(0);
//					      System.out.println(Integer.parseInt(resultMap.get("user_id").toString()));
//					     
//					   

//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new GenreBean(rs.getInt("genre_id"), rs.getString("genre_name"),
//							rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.allGenre(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> genreTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",4)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inGenreId", reqParam.getGenreId() )
						.addValue("inArtistId", reqParam.getArtistId())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
		    			  resultMap.get("ivrMMNumber").toString(),
		    			  resultMap.get("resource_title").toString(),
		    			  Integer.parseInt(resultMap.get("album_id").toString()),
		    			  resultMap.get("album_title").toString(),
		    			  Integer.parseInt(resultMap.get("artist_id").toString()),
		    			  resultMap.get("artist_name").toString(),
		    			  Integer.parseInt(resultMap.get("genre_id").toString()),
		    			  resultMap.get("genre_name").toString(),
		    			  Long.parseLong(resultMap.get("play_count").toString()),
		    			  Long.parseLong(resultMap.get("favourite_count").toString()),
		    			  Long.parseLong(resultMap.get("share_count").toString()),
		    			  Long.parseLong(resultMap.get("size").toString()),
		    			  Long.parseLong(resultMap.get("duration").toString()),
		    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
		    			  resultMap.get("image_url").toString(),
		    			  resultMap.get("video_id").toString(),
		    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
		    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
		    			  resultMap.get("lang_karaoke_available").toString().split("#"))
		    			  
		    			  );	
		    				    			  
		    	  });		
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(4," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.genreTrackList(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> genreArtistTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetGenreMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",7)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inGenreId", reqParam.getGenreId() )
						.addValue("inArtistId", reqParam.getArtistId())
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(7," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
//					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
//					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out
					.println("Exception in HutchTriBeatzMainServlet.genreArtistTrackList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<NotificationData> getUserNotification(RequestParameter reqParam) {
		List<NotificationData> lst = new ArrayList<NotificationData>();
		try { // ," + reqParam.getSourceId() +
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserNotification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",1)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inNotificationId", reqParam.getNotificationId())
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("lang", reqParam.getLanguageCode())						
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());
			  
					  	Map<String, Object> rs = jdbcCall.execute(inParams);
						///////      System.out.println("sign in response :: "+ rs);					      
						ArrayList<Object> ar = new ArrayList<Object>();
						ar = (ArrayList) rs.get("#result-set-1");
						//Map resultMap1 = (Map) ar.get(0);
						      
						ar.forEach(item->{
				    	  Map resultMap = (Map) item;
				    	  lst.add(new NotificationData(Integer.parseInt(resultMap.get("notification_id").toString()), 
				    			  resultMap.get("sender_name").toString(),
				    			  resultMap.get("notification_title").toString(),
				    			  resultMap.get("notification_subtitle").toString(),
				    			  resultMap.get("notification_msg").toString(),
				    			  resultMap.get("value").toString(),
				    			  resultMap.get("notification_type").toString(),
				    			  Integer.parseInt(resultMap.get("read_status").toString()),
				    			  resultMap.get("date_time").toString(),
				    			  resultMap.get("image_url").toString()
				    			  ));	
				    				    			  
				    	  });
				
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `UserNotification`(1," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
//							+ reqParam.getDeviceId() + "'," + reqParam.getUserId() + "," + reqParam.getNotificationId()
//							+ "," + reqParam.getImageTechRefId() + ",'" + reqParam.getLanguageCode() + "',"
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new NotificationData(rs.getInt("notification_id"), rs.getString("sender_name"),
//							rs.getString("notification_title"), rs.getString("notification_subtitle"),
//							rs.getString("notification_msg"), rs.getString("value"), rs.getString("notification_type"),
//							rs.getInt("read_status"), rs.getString("date_time"), rs.getString("image_url")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getUserNotification(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<NotificationData> getNotificationInfo(RequestParameter reqParam) {
		List<NotificationData> lst = new ArrayList<NotificationData>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserNotification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",4)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("insourceId", reqParam.getSourceId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inNotificationId", reqParam.getNotificationId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("lang", reqParam.getLanguageCode())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new NotificationData(Integer.parseInt(resultMap.get("notification_id").toString()), 
		    			  resultMap.get("sender_name").toString(),
		    			  resultMap.get("notification_title").toString(),
		    			  resultMap.get("notification_subtitle").toString(),
		    			  resultMap.get("notification_msg").toString(),
		    			  resultMap.get("value").toString(),
		    			  resultMap.get("notification_type").toString(),
		    			  Integer.parseInt(resultMap.get("read_status").toString()),
		    			  resultMap.get("date_time").toString(),
		    			  resultMap.get("image_url").toString()
		    			  ));	
		    				    			  
		    	  });
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall(
//					"{CALL `UserNotification`(4," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
//							+ reqParam.getDeviceId() + "'," + reqParam.getUserId() + "," + reqParam.getNotificationId()
//							+ "," + reqParam.getImageTechRefId() + ",'" + reqParam.getLanguageCode() + "',"
//							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			while (rs.next()) {
//				lst.add(new NotificationData(rs.getInt("notification_id"), rs.getString("sender_name"),
//						rs.getString("notification_title"), rs.getString("notification_subtitle"),
//						rs.getString("notification_msg"), rs.getString("value"), rs.getString("notification_type"),
//						rs.getInt("read_status"), rs.getString("date_time"), rs.getString("image_url")));
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getNotificationInfo(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

	public void readNotification(RequestParameter reqParam) {
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserNotification");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("insourceId", reqParam.getSourceId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inNotificationId", reqParam.getNotificationId())
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("lang", reqParam.getLanguageCode())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserNotification`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getUserId() + "," + reqParam.getNotificationId() + "," + reqParam.getImageTechRefId()
//					+ ",'" + reqParam.getLanguageCode() + "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit()
//					+ ")}");
//			while (rs.next()) {
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.readNotification(RequestParameter reqParam) - " + e.getMessage());
		}
	}

	public void userFeedback(RequestParameter reqParam) {
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Feedback");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inArtistId", reqParam.getSourceId())
					.addValue("inAudioTechRefId", reqParam.getUserId())
					.addValue("inImageTechRefId", reqParam.getDeviceId())
					.addValue("inStart", reqParam.getEmailAddress())
					.addValue("inLimit", reqParam.getMsisdn())
					.addValue("inLimit", reqParam.getEventType())
					.addValue("inLimit", reqParam.getFeedbackData());
		
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap = (Map) ar.get(0);				    
				//responseCode =Integer.parseInt(resultMap.get("code").toString());
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `Feedback`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getDeviceId() + "','"
//					+ reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "'," + reqParam.getEventType() + ",'"
//					+ reqParam.getFeedbackData() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.userFeedback(RequestParameter reqParam) - "
					+ e.getMessage());
		}
	}

	public void audioQualityFeedback(RequestParameter reqParam) {
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("AudioQualityFeedback");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inApplicationVersion", reqParam.getApplicationVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inUserId", reqParam.getUserId() )
						.addValue("inUserDeviceId", reqParam.getUserDeviceId())
						.addValue("inResourceCode", reqParam.getTrackCode())						
						.addValue("inRating", reqParam.getRating())
						.addValue("inAction", reqParam.getAction())
						.addValue("inQuality", reqParam.getAudioQuality())
						.addValue("inNetwork", reqParam.getAction());

			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `AudioQualityFeedback`(" + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getApplicationVersion() + "','"
//					+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin()
//					+ "'," + reqParam.getUserId() + "," + reqParam.getUserDeviceId() + ",'" + reqParam.getTrackCode()
//					+ "'," + reqParam.getRating() + ",'" + reqParam.getAction() + "','" + reqParam.getAudioQuality()
//					+ "','" + reqParam.getNetwork() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out
					.println("Exception in HutchTriBeatzMainServlet.audioQualityFeedback(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public void socialSharingTrack(RequestParameter reqParam) {
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("SocialSharing");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("insourceId", reqParam.getSourceId())
					.addValue("inDeviceId", reqParam.getUserId())
					.addValue("inUserId", reqParam.getUserDeviceId())
					.addValue("inNotificationId", reqParam.getOperatingSystem())
					.addValue("inImageTechRefId", reqParam.getOperatingSystemVersion())
					.addValue("lang", reqParam.getDeviceModel())
					.addValue("inStart", reqParam.getDeviceId())
					.addValue("inLimit", reqParam.getDevicePin())
					.addValue("inLimit", reqParam.getTrackCode())
					.addValue("inLimit", reqParam.getEventType());
			  

			Map<String, Object> rs = jdbcCall.execute(inParams);
			 				///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			//Map resultMap = (Map) ar.get(0);				    
			 				
			 				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `SocialSharing`(" + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + "," + reqParam.getUserDeviceId() + ",'"
//					+ reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
//					+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin()
//					+ "','" + reqParam.getTrackCode() + "','" + reqParam.getEventType() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.socialSharingTrack(RequestParameter reqParam) - "
					+ e.getMessage());
		}
	}

	public void successfulOfflineDownload(RequestParameter reqParam) {
		String timestamp = String.valueOf(System.currentTimeMillis());
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserOffline");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",3)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inResourceCode", reqParam.getTrackCode() )							
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inValidTime", timestamp)
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());

			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				

				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserOffline`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ",'" + timestamp + "',"
//					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					// dwld.setCode(rs.getInt("code"));
//					// dwld.setMessage(rs.getString("message"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.successfulOfflineDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public List<FeedbackSubjectData> feedbackSubjectList(RequestParameter reqParam) {
		List<FeedbackSubjectData> lst = new ArrayList<FeedbackSubjectData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("Feedback");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inEmailAddress", reqParam.getEmailAddress())
					.addValue("inMobileNumber", reqParam.getMsisdn())
					.addValue("inFeedbackSubjectId", reqParam.getEventType())
					.addValue("inFeedbackData", reqParam.getFeedbackData());					
			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
				      
				ar.forEach(item->{
		    	  Map resultMap = (Map) item;
		    	  lst.add(new FeedbackSubjectData(Integer.parseInt(resultMap.get("fdbk_subject_id").toString()), 
		    			  resultMap.get("fdbk_subject").toString()));	
		    	  });
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `Feedback`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getDeviceId() + "','"
//					+ reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "'," + reqParam.getEventType() + ",'"
//					+ reqParam.getFeedbackData() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new FeedbackSubjectData(rs.getInt("fdbk_subject_id"), rs.getString("fdbk_subject")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.feedbackSubjectList(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public String toXml(Object object) {
		final StringWriter stringWriter = new StringWriter();
		try {
			JAXBContext jc = JAXBContext.newInstance(object.getClass());
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, stringWriter);
			return stringWriter.toString();

		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.toXml(Object object) - " + e.getMessage());
		}
		return null;
	}

	public String toJson(Object object) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(object);
			return result;
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.toJson(Object object) - " + e.getMessage());
		}
		return null;
	}

	public String getFinalRedirectedUrl(String url) {
		HttpURLConnection connection;
		String finalUrl = url;
		try {
			do {
				connection = (HttpURLConnection) new URL(finalUrl).openConnection();
				connection.setInstanceFollowRedirects(false);
				connection.setUseCaches(false);
				connection.setRequestMethod("GET");
				connection.connect();
				int responseCode = connection.getResponseCode();
				if (responseCode >= 300 && responseCode < 400) {
					String redirectedUrl = connection.getHeaderField("Location");
					if (null == redirectedUrl) {
						break;
					}
					finalUrl = redirectedUrl;
				} else {
					break;
				}
			} while (connection.getResponseCode() != HttpURLConnection.HTTP_OK);
			connection.disconnect();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFinalRedirectedUrl(String url) - " + e.getMessage());
		}
		return finalUrl;
	}

	public FeaturedPlaylistBean getFeaturedPlaylistInfo(RequestParameter reqParam) {
		FeaturedPlaylistBean playlist = null;
		try {
			System.out.println("in getFeaturedPlaylistInfo methods ");
					
//			MySQL1 mysql = new MySQL1();
//			ResultSet rs1 = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetFeaturedPlaylistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())	
					
					.addValue("inPlaylistId", reqParam.getPlaylistId() )
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
			  
			  
			
			
			
					Map<String, Object> rs = jdbcCall.execute(inParams);
					      System.out.println("GetFeaturedPlaylistMetaData  in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					for(int i =0; i< ar.size();i++)
					{
						Map resultMap = (Map) ar.get(i);
						playlist = new FeaturedPlaylistBean(Integer.parseInt(resultMap.get("playlist_id").toString()),
			    			  resultMap.get("playlist_name").toString(),
			    			  resultMap.get("playlist_desc").toString(),
			    			  resultMap.get("image_url").toString(),
			    			  Integer.parseInt(resultMap.get("track_count").toString())
			    			  );	
					}					
					

//			if (rs != null) {
//				while (rs.next()) {
//					playlist = new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
//							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			playlist = null;
		 

		} finally {
			return playlist;
		}
	}

	public RadioBean getRadioInfo(RequestParameter reqParam) {
		RadioBean radio = new RadioBean();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetRadioMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",3)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inRadioId", reqParam.getRadioId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					     
					for(int i =0; i< ar.size();i++)
					{
						Map resultMap = (Map) ar.get(i);
						radio = new RadioBean(Integer.parseInt(resultMap.get("radio_id").toString()),
			    			  resultMap.get("radio_name").toString(),
			    			  resultMap.get("radio_desc").toString(),
			    			  resultMap.get("image_url").toString(),
			    			  Integer.parseInt(resultMap.get("track_count").toString())
			    			  );	
					}					
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetRadioMetaData`(3," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getRadioId() + ","
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					radio = new RadioBean(rs.getInt("radio_id"), rs.getString("radio_name"), rs.getString("radio_desc"),
//							rs.getString("image_url"), rs.getInt("track_count"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getRadioInfo(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return radio;
		}
	}

	public List<FeaturedPlaylistBean> getFeaturedPlaylist(RequestParameter reqParam) {
		List<FeaturedPlaylistBean> lst = new ArrayList<FeaturedPlaylistBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetFeaturedPlaylistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inGenreId", reqParam.getGenreId() )
					.addValue("inArtistId", reqParam.getArtistId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new FeaturedPlaylistBean(Integer.parseInt(resultMap.get("playlist_id").toString()),
			    			  resultMap.get("playlist_name").toString(),
			    			  resultMap.get("playlist_desc").toString(),
			    			  resultMap.get("image_url").toString(),
			    			  Integer.parseInt(resultMap.get("track_count").toString())
			    			  ));	
			    				    			  
			    	  });
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
//							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getFeaturedPlaylist(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public boolean validateMobileNumber(RequestParameter reqParam) {
		boolean isValid = false;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("ValidateMobileNumber");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inMobileNumber", reqParam.getMsisdn());				
			  
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				
				isValid = getBooleanValue(resultMap.get("is_valid").toString());
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `ValidateMobileNumber`('" + reqParam.getMsisdn() + "')}");
//			while (rs.next()) {
//
//				isValid = rs.getBoolean("is_valid");
//			}
//			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(" Exception in MainServlet.isValid(RequestParameter reqParam) - " + e.getMessage());
		} finally {
			return isValid;
		}
	}

	public List<RadioBean> getRadio(RequestParameter reqParam) {
		List<RadioBean> lst = new ArrayList<RadioBean>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetRadioMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",1)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inRadioId", reqParam.getPlaylistId() )
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					      
					ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new RadioBean(Integer.parseInt(resultMap.get("radio_id").toString()),
			    			  resultMap.get("radio_name").toString(),
			    			  resultMap.get("radio_desc").toString(),
			    			  resultMap.get("image_url").toString(),
			    			  Integer.parseInt(resultMap.get("track_count").toString())
			    			  ));	
			    				    			  
			    	  });
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetRadioMetaData`(1," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new RadioBean(rs.getInt("radio_id"), rs.getString("radio_name"), rs.getString("radio_desc"),
//							rs.getString("image_url"), rs.getInt("track_count")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getFeaturedPlaylist(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> featuredPlaylistTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			
//			MySQL1 mysql = new MySQL1();
//			ResultSet rs1 = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
			
	
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetFeaturedPlaylistMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inFlag",2)
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inUserId", reqParam.getUserId())
						.addValue("inOS", reqParam.getOperatingSystem())
						.addValue("inOSV", reqParam.getOperatingSystemVersion())
						.addValue("inDevModel", reqParam.getDeviceModel())
						.addValue("inDevId", reqParam.getDeviceId()) 
						.addValue("inDevPin", reqParam.getDevicePin())						  
						.addValue("inPlaylistId", reqParam.getPlaylistId() )						
						.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
						.addValue("inImageTechRefId", reqParam.getImageTechRefId())
						.addValue("inStart", reqParam.getStartLimit())
						.addValue("inLimit", reqParam.getEndLimit());

			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				
				ar.forEach(item->{
			    	  Map resultMap = (Map) item;
			    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
			    			  resultMap.get("ivrMMNumber").toString(),
			    			  resultMap.get("resource_title").toString(),
			    			  Integer.parseInt(resultMap.get("album_id").toString()),
			    			  resultMap.get("album_title").toString(),
			    			  Integer.parseInt(resultMap.get("artist_id").toString()),
			    			  resultMap.get("artist_name").toString(),
			    			  Integer.parseInt(resultMap.get("genre_id").toString()),
			    			  resultMap.get("genre_name").toString(),
			    			  Long.parseLong(resultMap.get("play_count").toString()),
			    			  Long.parseLong(resultMap.get("favourite_count").toString()),
			    			  Long.parseLong(resultMap.get("share_count").toString()),
			    			  Long.parseLong(resultMap.get("size").toString()),
			    			  Long.parseLong(resultMap.get("duration").toString()),
			    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
			    			  resultMap.get("image_url").toString(),
			    			  resultMap.get("video_id").toString(),
			    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
			    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
			    			  resultMap.get("lang_karaoke_available").toString().split("#"))
			    			  
			    			  );	
			    				    			  
			    	  });		
				
			  
		
//			if (rs != null) {
//				while (rs.next()) {
//					// lst.add(new TrackData(rs.getString("resource_code"),
//					// rs.getString("resource_title"), rs.getInt("album_id"),
//					// rs.getString("album_title"), rs.getInt("artist_id"),
//					// rs.getString("artist_name"), rs.getInt("genre_id"),
//					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
//					// getFullStreamUrl(rs.getString("filePath"), reqParam),
//					// rs.getString("image_url"), rs.getString("video_id")));
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.featuredPlaylistTrackList(RequestParameter reqParam) - "
							+ e.getMessage() + "-" + "{CALL `GetFeaturedPlaylistMetaData`(2," + reqParam.getCountryId()
							+ "," + reqParam.getSourceId() + "," + reqParam.getUserId() + ",'"
							+ reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
							+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','"
							+ reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
		} finally {
			return lst;
		}
	}

	public List<TrackData> radioTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("GetRadioMetaData");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inFlag",2)
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inSourceId", reqParam.getSourceId())
					.addValue("inUserId", reqParam.getUserId())
					.addValue("inOS", reqParam.getOperatingSystem())
					.addValue("inOSV", reqParam.getOperatingSystemVersion())
					.addValue("inDevModel", reqParam.getDeviceModel())
					.addValue("inDevId", reqParam.getDeviceId()) 
					.addValue("inDevPin", reqParam.getDevicePin())						  
					.addValue("inRadioId", reqParam.getRadioId())
					.addValue("inAudioTechRefId", reqParam.getAudioTechRefId())						
					.addValue("inImageTechRefId", reqParam.getImageTechRefId())
					.addValue("inStart", reqParam.getStartLimit())
					.addValue("inLimit", reqParam.getEndLimit());
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					
					ar.forEach(item->{
				    	  Map resultMap = (Map) item;
				    	  lst.add(new TrackData(resultMap.get("resource_code").toString(), 
				    			  resultMap.get("ivrMMNumber").toString(),
				    			  resultMap.get("resource_title").toString(),
				    			  Integer.parseInt(resultMap.get("album_id").toString()),
				    			  resultMap.get("album_title").toString(),
				    			  Integer.parseInt(resultMap.get("artist_id").toString()),
				    			  resultMap.get("artist_name").toString(),
				    			  Integer.parseInt(resultMap.get("genre_id").toString()),
				    			  resultMap.get("genre_name").toString(),
				    			  Long.parseLong(resultMap.get("play_count").toString()),
				    			  Long.parseLong(resultMap.get("favourite_count").toString()),
				    			  Long.parseLong(resultMap.get("share_count").toString()),
				    			  Long.parseLong(resultMap.get("size").toString()),
				    			  Long.parseLong(resultMap.get("duration").toString()),
				    			  getFullStreamUrl(resultMap.get("filePath").toString(), reqParam),
				    			  resultMap.get("image_url").toString(),
				    			  resultMap.get("video_id").toString(),
				    			  getBooleanValue(resultMap.get("isCrbtAvailable").toString()),
				    			  getBooleanValue(resultMap.get("isKaraokeAvailable").toString()),
				    			  resultMap.get("lang_karaoke_available").toString().split("#"))
				    			  
				    			  );	
				    				    			  
				    	  });		
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `GetRadioMetaData`(2," + reqParam.getCountryId() + ","
//					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getRadioId() + ","
//					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
//					+ "," + reqParam.getEndLimit() + ")}");
//			if (rs != null) {
//				while (rs.next()) {
//					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
//							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
//							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
//							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
//							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
//							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
//							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
//							rs.getBoolean("isKaraokeAvailable"), rs.getString("lang_karaoke_available").split("#")));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.radioTrackList(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public Root getUserDeviceLoginInformation(RequestParameter reqParam) {
		Root obj1 = new Root();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserDeviceLoginInformation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					
					.addValue("inCountryId", reqParam.getCountryId())
					.addValue("inMobileNumber", reqParam.getMsisdn())
					.addValue("inDeviceId", reqParam.getDeviceId())
					.addValue("inVia", reqParam.getViaId())
					.addValue("inEmail", reqParam.getEmailAddress())
					.addValue("inAppVer", reqParam.getApplicationVersion())
			  		.addValue("inOperatingSystem", reqParam.getOperatingSystem());
//			  
			  Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				//Map resultMap1 = (Map) ar.get(0);
			
				for(int i =0; i< ar.size();i++)
				{
					Map resultMap = (Map) ar.get(i);
					obj1 = new Root(Integer.parseInt(resultMap.get("code").toString()), resultMap.get("message").toString());
				}
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `UserDeviceLoginInformation`(" + reqParam.getCountryId() + ",'"
//					+ reqParam.getMsisdn() + "','" + reqParam.getDeviceId() + "'," + reqParam.getViaId() + ",'"
//					+ reqParam.getEmailAddress() + "','" + reqParam.getApplicationVersion() + "','"
//					+ reqParam.getOperatingSystem() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					obj1 = new Root(rs.getInt("code"), rs.getString("message"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getUserDeviceLoginInformation(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {

			return obj1;
		}
	}

	public boolean validatePhoneNumber(String phoneNo) {
		// validate phone numbers of format "1234567890"
		if (phoneNo.matches("\\d{10}")) {
			return true;
		} else {
			return false;
		}
	}

	public int applicationInstallViaShare(RequestParameter reqParam) {
		int resultCode = 317;
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("ApplicationInstallViaShare");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					  	.addValue("inEventType",reqParam.getEventType())
					  	.addValue("inCountryCode",reqParam.getCountryCode())
						.addValue("inCountryId", reqParam.getCountryId())
						.addValue("inSourceId", reqParam.getSourceId())
						.addValue("inApplicationVersion", reqParam.getApplicationVersion())
						.addValue("inOperatingSystem", reqParam.getOperatingSystem())
						.addValue("inOperatingSystemVersion", reqParam.getOperatingSystemVersion())
						.addValue("inDeviceModel", reqParam.getDeviceModel())
						.addValue("inDeviceId", reqParam.getDeviceId()) 
						.addValue("inDevicePin", reqParam.getDevicePin())						  
						.addValue("inUserId", reqParam.getUserId() )
						.addValue("inPromotionCode", reqParam.getPromotionCode())
						.addValue("inAudioTechRefId", reqParam.getAction());					
			 
			    Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);				    
				resultCode =Integer.parseInt(resultMap.get("code").toString());			
			  
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `ApplicationInstallViaShare`('" + reqParam.getEventType() + "','"
//					+ reqParam.getCountryCode() + "'," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
//					+ reqParam.getApplicationVersion() + "','" + reqParam.getOperatingSystem() + "','"
//					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
//					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
//					+ reqParam.getPromotionCode() + "','" + reqParam.getAction() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					resultCode = rs.getInt("code");
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.applicationInstallViaShare(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return resultCode;
		}
	}

	public DeviceInformation getUserDeviceInformation(String emailAddress) {
		DeviceInformation devInfo = new DeviceInformation();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("UserDeviceInformation");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("inEmailAddress", emailAddress);					
//			  
			  	Map<String, Object> rs = jdbcCall.execute(inParams);
				///////      System.out.println("sign in response :: "+ rs);					      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				Map resultMap = (Map) ar.get(0);
				
				devInfo.setDeviceId(resultMap.get("dev_id").toString());
				devInfo.setOperatingSystem(resultMap.get("os").toString());
				devInfo.setDeviceModel(resultMap.get("dev_model").toString());
				devInfo.setOperatingSystemVersion(resultMap.get("osv").toString());
				devInfo.setDevicePin(resultMap.get("dev_pin").toString());
				
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `UserDeviceInformation`('" + emailAddress + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					devInfo.setDeviceId(rs.getString("dev_id"));
//					devInfo.setOperatingSystem(rs.getString("os"));
//					devInfo.setDeviceModel(rs.getString("dev_model"));
//					devInfo.setOperatingSystemVersion(rs.getString("osv"));
//					devInfo.setDevicePin(rs.getString("dev_pin"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println("Exception in HutchTriBeatzMainServlet.getUserDeviceInformation() - " + e.getMessage());
		}
		return devInfo;
	}

	// Send Notification
	public void sendNotification(String notificationID, String devPin, String lang, String type, String userId) {

		try {
			//MySQL sql = new MySQL();
			String sql="";
			if (type.equalsIgnoreCase("IN")) {
				sql="INSERT INTO tblNotification "
						+ " (user_id,sender_name,notification_title,notification_title_idLang,notification_subtitle,notification_msg,notification_msg_idLang,notification_image,value,notification_type,image_code,country_id)"
						+ " SELECT " + userId
						+ ",sender_name,notification_title,notification_title_idLang,notification_subtitle,notification_msg,notification_msg_idLang,notification_image,value,notification_type,image_code,country_id FROM `tblNotification` WHERE notification_id='"
						+ notificationID + "'";
				jdbcTemplateObject.update(sql);
				
				
			} else {
				if (lang.equalsIgnoreCase("en")) {
					sql= "INSERT INTO tblPushNotification "
							+ " (notification_id,notification_message,notification_image,os,device_id,device_pin,STATUS,remarks,request_date_time)"
							+ " SELECT notification_id,notification_msg,'NA','android','','" + devPin
							+ "',0,'NA',NOW() FROM `tblNotification` WHERE notification_id='" + notificationID + "'";
				jdbcTemplateObject.update(sql);
				}
				else {
					sql = "INSERT INTO tblPushNotification "
							+ " (notification_id,notification_message,notification_image,os,device_id,device_pin,STATUS,remarks,request_date_time)"
							+ " SELECT notification_id,notification_msg_idLang,'NA','android','','" + devPin
							+ "',0,'NA',NOW() FROM `tblNotification` WHERE notification_id='" + notificationID + "'";
				jdbcTemplateObject.update(sql);
				}
			}
			//sql.close();
		} catch (Exception e) {
			System.out.println("Exception in send ing Notification---" + e);
		}

	}

	// CCI Portal
	public CciPortalResponse getUserSubDetails(String msisdn) {
		CciPortalResponse obj = new CciPortalResponse();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("HUTCHTRICCIPORTAL");			  			  
			SqlParameterSource inParams = new MapSqlParameterSource()
			.addValue("IN_FLAG",2)
			.addValue("IN_MOBILE", msisdn);
					

			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
			Map resultMap = (Map) ar.get(0);				    
				
			obj.setDatetime(resultMap.get("datetime").toString());
			obj.setMobile_number(resultMap.get("mobile_number").toString());
			obj.setStatus(resultMap.get("status").toString());
			obj.setPackName(resultMap.get("package_description").toString());
			obj.setPrice(resultMap.get("price_info").toString());

			obj.setPackageStartDate(resultMap.get("package_start_date").toString());
			obj.setPackageEndtDate(resultMap.get("package_end_date").toString());
			obj.setPackageRenewDate(resultMap.get("package_renewal_date").toString());
			obj.setLastSuccessBilling(resultMap.get("last_successfull_billing").toString());
							
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `HUTCHTRICCIPORTAL`(2,'" + msisdn + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					obj.setDatetime(rs.getString("datetime"));
//					obj.setMobile_number(rs.getString("mobile_number"));
//					obj.setStatus(rs.getString("status"));
//					obj.setPackName(rs.getString("package_description"));
//					obj.setPrice(rs.getString("price_info"));
//
//					obj.setPackageStartDate(rs.getString("package_start_date"));
//					obj.setPackageEndtDate(rs.getString("package_end_date"));
//					obj.setPackageRenewDate(rs.getString("package_renewal_date"));
//					obj.setLastSuccessBilling(rs.getString("last_successfull_billing"));
//				}
//				mysql.close();
//			}

		} catch (Exception e) {
			System.out.println("Exception in send ing Notification---" + e);
		}
		return obj;
	}

	public List<CciPortalResponse> getUserSuccessDetails(String msisdn) {

		List<CciPortalResponse> list = new ArrayList<CciPortalResponse>();
		try {
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("HUTCHTRICCIPORTAL");			  			  
			SqlParameterSource inParams = new MapSqlParameterSource()
			.addValue("IN_FLAG",1)
			.addValue("IN_MOBILE", msisdn);
					

			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
		//	Map resultMap = (Map) ar.get(0);				    
				
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
		    	 
	    	  CciPortalResponse obj = new CciPortalResponse();
				obj.setDatetime(resultMap.get("datetime").toString());
				obj.setMobile_number(resultMap.get("mobile_number").toString());
				obj.setRemarks(resultMap.get("remarks").toString());
				obj.setPackName(resultMap.get("package_description").toString());
				obj.setPrice(resultMap.get("price_info").toString());
				list.add(obj);	
	    				    			  
	    	  });			
			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `HUTCHTRICCIPORTAL`(1,'" + msisdn + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					CciPortalResponse obj = new CciPortalResponse();
//					obj.setDatetime(rs.getString("datetime"));
//					obj.setMobile_number(rs.getString("mobile_number"));
//					obj.setRemarks(rs.getString("remarks"));
//					obj.setPackName(rs.getString("package_description"));
//					obj.setPrice(rs.getString("price_info"));
//					list.add(obj);
//				}
//				mysql.close();
		//	}
		} catch (Exception e) {
			System.out.println("Exception in send ing Notification---" + e);
		}
		return list;
	}

	public List<CciPortalResponse> getUserUnsubDetails(String msisdn) {

		List<CciPortalResponse> list = new ArrayList<CciPortalResponse>();
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("HUTCHTRICCIPORTAL");			  			  
			SqlParameterSource inParams = new MapSqlParameterSource()
			.addValue("IN_FLAG",3)
			.addValue("IN_MOBILE", msisdn);
					

			Map<String, Object> rs = jdbcCall.execute(inParams);
			///////      System.out.println("sign in response :: "+ rs);					      
			ArrayList<Object> ar = new ArrayList<Object>();
			ar = (ArrayList) rs.get("#result-set-1");
		//	Map resultMap = (Map) ar.get(0);				    
				
			ar.forEach(item->{
	    	  Map resultMap = (Map) item;
		    	 
	    	  CciPortalResponse obj = new CciPortalResponse();
				obj.setDatetime(resultMap.get("datetime").toString());
				obj.setMobile_number(resultMap.get("mobile_number").toString());
				obj.setRemarks(resultMap.get("status").toString());
				obj.setPackName(resultMap.get("package_description").toString());
				obj.setPrice(resultMap.get("price_info").toString());
				
				obj.setPackageStartDate(resultMap.get("package_start_date").toString());
				obj.setPackageEndtDate(resultMap.get("package_end_date").toString());
				obj.setPackageRenewDate(resultMap.get("package_renewal_date").toString());
				obj.setLastSuccessBilling(resultMap.get("last_successfull_billing").toString());
				obj.setUnsubDate(resultMap.get("unsub_date").toString());
				
				list.add(obj);	
	    				    			  
	    	  });			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `HUTCHTRICCIPORTAL`(3,'" + msisdn + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					CciPortalResponse obj = new CciPortalResponse();
//
//					obj.setDatetime(rs.getString("datetime"));
//					obj.setMobile_number(rs.getString("mobile_number"));
//					obj.setStatus(rs.getString("status"));
//					obj.setPackName(rs.getString("package_description"));
//					obj.setPrice(rs.getString("price_info"));
//
//					obj.setPackageStartDate(rs.getString("package_start_date"));
//					obj.setPackageEndtDate(rs.getString("package_end_date"));
//					obj.setPackageRenewDate(rs.getString("package_renewal_date"));
//					obj.setLastSuccessBilling(rs.getString("last_successfull_billing"));
//					obj.setUnsubDate(rs.getString("unsub_date"));
//					list.add(obj);
//				}
//				mysql.close();
			//}
		} catch (Exception e) {
			System.out.println("Exception in send ing Notification---" + e);
		}
		return list;
	}

	public PromotionShareConfig getPromotionShareConfig(RequestParameter reqParam) {
		PromotionShareConfig obj1 = null;
		try {
			
			SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PromotionShareConfig");			  			  
			  SqlParameterSource inParams = new MapSqlParameterSource()
					.addValue("in_Userid", reqParam.getUserId());
					
//			  
					Map<String, Object> rs = jdbcCall.execute(inParams);
					///////      System.out.println("sign in response :: "+ rs);					      
					ArrayList<Object> ar = new ArrayList<Object>();
					ar = (ArrayList) rs.get("#result-set-1");
					//Map resultMap1 = (Map) ar.get(0);
					
					for(int i =0; i< ar.size();i++)
					{
						Map resultMap = (Map) ar.get(i);
						obj1 = new PromotionShareConfig(Integer.parseInt(resultMap.get("id").toString()),
								resultMap.get("banner_url").toString(),
								resultMap.get("title").toString(),
								resultMap.get("msg").toString().toString(),
								resultMap.get("tnc_title").toString(), 
								resultMap.get("tnc_msg").toString(),
								resultMap.get("share_msg").toString());
					}
					
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{CALL `PromotionShareConfig`('" + reqParam.getUserId() + "')}");
//			if (rs != null) {
//				while (rs.next()) {
//					obj1 = new PromotionShareConfig(rs.getInt("id"), rs.getString("banner_url"), rs.getString("title"),
//							rs.getString("msg"), rs.getString("tnc_title"), rs.getString("tnc_msg"),
//							rs.getString("share_msg"));
//				}
//			}
//			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in Voda_ghana MainServlet.getPromotionShareConfig(RequestParameter reqParam, DeviceInformation devInfo) - "
							+ e.getMessage());
		} finally {
			return obj1;
		}
	}

	public List<OperatorData> getOperatorData(String fromDate, String todate) {

		List<OperatorData> list = new ArrayList<OperatorData>();
//		try {
//			
//			MySQL mysql = new MySQL();
//			ResultSet rs = mysql.prepareCall("{call `PRO_OPERATOR_DATA`(0)}");
//			rs = mysql.executeQuery(
//					"SELECT DATE_FORMAT(date_time,'%Y-%m-%d %H:%i') as date_time , pack_name as pack_name ,  sub_type as sub_type ,price as price , days as days , total_count as total_count , revenue as revenue    FROM tbl_operator_data WHERE  DATE(date_time) BETWEEN  '"
//							+ fromDate + "' and '" + todate + "' ORDER  BY date_time DESC ");
//
//			if (rs != null) {
//				while (rs.next()) {
//					OperatorData obj = new OperatorData();
//					obj.setDateTime(rs.getString("date_time"));
//					obj.setPackName(rs.getString("pack_name"));
//					obj.setSubType(rs.getString("sub_type"));
//					obj.setPrice(rs.getString("price"));
//					obj.setDays(rs.getInt("days"));
//					obj.setTotalCount(rs.getInt("total_count"));
//					obj.setRevenue(rs.getString("revenue"));
//					list.add(obj);
//				}
//				mysql.close();
//			}
//		} catch (Exception e) {
//			System.out.println("Exception in send ing Notification---" + e);
//		}
		return list;
	}
	
	

public List<DashBoardData> getDashBoardData() {	
	List<DashBoardData> list = new ArrayList<DashBoardData>();
	// MySQL mysql = new MySQL();	
	// ResultSet rs =null;
	        try {		        	
	        	SimpleJdbcCall jdbcCall = new   SimpleJdbcCall(dataSource).withProcedureName("PRO_MIS_GET_DASHBOARD_DATA")   ;
	        	Map<String, Object> rs = jdbcCall.execute();						      
				ArrayList<Object> ar = new ArrayList<Object>();
				ar = (ArrayList) rs.get("#result-set-1");
				for(int i =0; i< ar.size();i++)
				{
					DashBoardData obj=new DashBoardData();
					Map resultMap = (Map) ar.get(i);
					
                	obj.setDateTime(resultMap.get("date_time").toString());	  
                	obj.setTotalCount(resultMap.get("total_count").toString());
                	obj.setGrossRevenue(resultMap.get("gross_revenue").toString());
                	obj.setNetRevenue(resultMap.get("net_revenue").toString());
                	obj.setPack1(resultMap.get("pack_1").toString());
                	obj.setPack1Revnue(resultMap.get("pack_1_revnue").toString());	                	
                	obj.setPack2(resultMap.get("pack_2").toString());
                	obj.setPack2Revnue(resultMap.get("pack_2_revenue").toString());	                	
                	obj.setPack3(resultMap.get("pack_3").toString());
                	obj.setPack3Revnue(resultMap.get("pack_3_revnue").toString());                	                	
                	obj.setPack4(resultMap.get("pack_4").toString());
                	obj.setPack4Revnue(resultMap.get("pack_4_revnue").toString());
                	
                	obj.setBEATS7SC(resultMap.get("BEATS7SC").toString());
                	obj.setBEATS7SC_revenue(resultMap.get("BEATS7SC_revenue").toString());
                	
                	obj.setBEATS30SC8(resultMap.get("BEATS30SC8").toString());
                	obj.setBEATS30SC8_revenue(resultMap.get("BEATS30SC8_revenue").toString());
                	
                	obj.setBEATS30SC18(resultMap.get("BEATS30SC18").toString());
                	obj.setBEATS30SC18_revenue(resultMap.get("BEATS30SC18_revenue").toString());
                	
                	
                	
                	list.add(obj);   
                	
                	
					
				 
				}
				
				/*
	            // rs = mysql.prepareCall("{call `PRO_MIS_GET_DASHBOARD_DATA`()}");	            
	            if (rs != null) {
	                while (rs.next()) {
	                	DashBoardData obj=new DashBoardData();
	                	obj.setDateTime(rs.getString("date_time"));	  
	                	obj.setTotalCount(rs.getString("total_count"));
	                	obj.setGrossRevenue(rs.getString("gross_revenue"));
	                	obj.setNetRevenue(rs.getString("net_revenue"));
	                	obj.setPack1(rs.getString("pack_1"));
	                	obj.setPack1Revnue(rs.getString("pack_1_revnue"));	                	
	                	obj.setPack2(rs.getString("pack_2"));
	                	obj.setPack2Revnue(rs.getString("pack_2_revenue"));	                	
	                	obj.setPack3(rs.getString("pack_3"));
	                	obj.setPack3Revnue(rs.getString("pack_3_revnue"));	                	
	                	obj.setPack4(rs.getString("pack_4"));
	                	obj.setPack4Revnue(rs.getString("pack_4_revnue"));	                	
	                	list.add(obj);
	                }
	                }
	           */     
	               
	            
	 }catch(Exception e){
		 System.out.println("Exception in send ing Notification---"+e);
	 } 
		

	return list;
}




	public long getTimeStampForStreaming() {

		long currenttime = System.currentTimeMillis();

		System.out.println(
				"----> UTC DATE     -> " + (currenttime + new java.util.Date(currenttime).getTimezoneOffset()));

		return (currenttime + new java.util.Date(currenttime).getTimezoneOffset());
	}

	/*
	 * public static void main(String arr[]) { new
	 * DataBaseProcedures().getTimeStampForStreaming(); }
	 */
	private Boolean getBooleanValue(String p_Value) {
		Boolean bValue = false;

		if (p_Value.equals("1") || p_Value.toLowerCase().equals("true")) {
			bValue = true;
		} 

		return bValue;
	}

	
}
