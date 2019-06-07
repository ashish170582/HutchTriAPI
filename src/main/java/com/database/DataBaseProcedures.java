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

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.beans.CciPortalResponseData;
import com.beans.ContainerBean;
import com.beans.ContainerItemBean;
import com.beans.CountInfo;
import com.beans.CountInfoData;
import com.beans.CountryInfoData;
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
	
	@Value("${api.domain.name}")
	private String apiDomainName="";
	
	@SuppressWarnings("finally")
    public AppVersionInfoData getAppVersionInfo(String countryCode, String operatingSystem, String applicationVersion) {
        AppVersionInfoData appVersionInfoData = null;
        try {

            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{call `ApplicationVersionInformation`('" + countryCode + "','" + operatingSystem + "','" + applicationVersion + "')}");
            if (rs != null) {
                while (rs.next()) {
                    appVersionInfoData = new AppVersionInfoData(rs.getString("android"), rs.getString("android_update_msg"), rs.getString("android_tab"), rs.getString("android_tab_update_msg"), rs.getString("ios"), rs.getString("ios_update_msg"), rs.getString("blackberry"), rs.getString("blackberry_update_msg"), rs.getString("blackberry10"), rs.getString("blackberry10_update_msg"), rs.getString("windows"), rs.getString("windows_update_msg"), rs.getInt("update_popup_frequency"), rs.getString("cache"), rs.getInt("update_required"), rs.getString("update_message"));
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.setAppVersionInfo() - " + e.getMessage());
        } finally {
            return appVersionInfoData;
        }
    }

    //Get Total Follow Folled Artist
    @SuppressWarnings("finally")
	public String getFollowArtist(RequestParameter reqParam) {
    	String response="0#0#0";
    	try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`(5," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
            if (rs != null) {
                while (rs.next()) {
                	response=rs.getString("follow_count")+"#"+rs.getString("followed_count")+"#"+rs.getString("vFollowing") ;
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.getFollowArtist(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return response;
        }
    }
    public List<ContainerBean> getKaraokeContainerList(RequestParameter reqParam, DeviceInformation devInfo) {
        List<ContainerBean> lst = new ArrayList<ContainerBean>();
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{call `GetKaraokeContainerList`(" + reqParam.getCountryId() + ",'" + devInfo.getDeviceId() + "','" + reqParam.getLanguageCode() + "')}");
            int containder_id = 0;
            if (rs != null) {
                while (rs.next()) {
                    containder_id = rs.getInt("container_id");
                    if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34 || containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74 || containder_id == 84 || containder_id == 94 || containder_id == 104 || containder_id == 114) {
                        lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"), rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"), rs.getInt("item_list_type_id"), getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId() , rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 6)));
                    } else {
                        lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"), rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"), rs.getInt("item_list_type_id"), getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(), rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 20)));
                    }
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.getKaraokeContainerList(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return lst;
        }

    }

	@SuppressWarnings("finally")
	public List<SubscriptionPackage> getSubscriptionPackageList(RequestParameter reqParam) {
		List<SubscriptionPackage> lst = new ArrayList<SubscriptionPackage>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `SubscriptionPackage`(" + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + ",'" + reqParam.getLanguageCode() + "','" + reqParam.getOperatingSystem()+ "')}");
			while (rs.next()) {
				lst.add(new SubscriptionPackage(rs.getInt("package_id"), rs.getString("package_name"),
						rs.getString("package_description"), rs.getString("price_info"),
						rs.getString("subscription_amount"), rs.getString("renewal_amount"),
						rs.getString("validity_period"), rs.getInt("validity_in_days"), rs.getString("currency_sign"), rs.getString("redirectUrl")));
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getSubscriptionPackageList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
	@SuppressWarnings("finally")
	public List<DownloadPackage> getDownloadPackageList(RequestParameter reqParam) {
		List<DownloadPackage> lst = new ArrayList<DownloadPackage>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `DownloadPackage`(" + reqParam.getCountryId() + "," + reqParam.getOperatorId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new DownloadPackage(rs.getInt("package_id"), rs.getString("package_name"),
							rs.getString("package_description"), rs.getString("price_info"),
							rs.getString("subscription_amount"), rs.getString("renewal_amount"),
							rs.getString("validity_period"), rs.getInt("validity_in_days"),
							rs.getString("currency_sign")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getDownloadPackageList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public int requestDirectSubscription(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();

			System.out.println("reqParam.getUserId()..................." + reqParam.getUserId());
			ResultSet rs = mysql.prepareCall("{CALL `DIRECT_ZA_BILLING`(" + reqParam.getCountryId() + ","
					+ reqParam.getUserId() + ",'" + reqParam.getMsisdn() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}

			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestSubscription(RequestParameter reqParam) - "
							+ e.getMessage());
		}
		return responseCode;
	}
	@SuppressWarnings("finally")
	public int requestSubscription(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			if (reqParam.getOperatorId() != -1) {

				ResultSet rs = mysql.prepareCall("{CALL `Subscribe`(" + reqParam.getCountryId() + ","
						+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + "," + reqParam.getViaId() + ","
						+ reqParam.getUserId() + "," + reqParam.getEventType() + ",'" + reqParam.getMsisdn() + "')}");
				if (rs != null) {
					while (rs.next()) {
						responseCode = rs.getInt("code");
					}
					System.out.println("responseCode ::  " + responseCode);
				}
			} else {
				responseCode = 110;
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestSubscription(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}
	@SuppressWarnings("finally")
	public int requestActivate(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `Activate`(" + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + "," + reqParam.getViaId() + ","
					+ reqParam.getUserId() + "," + reqParam.getEventType() + ",'" + reqParam.getMsisdn() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestActivate(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}
	@SuppressWarnings("finally")
	public int requestUnSubscription(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `Unsubscribe`(" + reqParam.getUserId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestUnSubscription(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}
	@SuppressWarnings("finally")
	public int appInstallViaShareClickTraking(RequestParameter reqParam) {
		int resultCode = 317;
		try {

			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `appInstallViaShareClickTraking`('" + reqParam.getEventType()
					+ "','" + reqParam.getCountryCode() + "'," + reqParam.getCountryId() + "," + reqParam.getSourceId()
					+ ",'" + reqParam.getApplicationVersion() + "','" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getCampaign() + "',   '" + reqParam.getSource() + "' ,  '" + reqParam.getMedium()
					+ "'  ,  '" + reqParam.getTerm() + "'  ,    '" + reqParam.getContent() + "'           )}");
			if (rs != null) {
				while (rs.next()) {
					resultCode = rs.getInt("code");
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `AudioQualityConfiguration`('" + reqParam.getCountryCode() + "',"
					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "')}");
			if (rs != null) {
				while (rs.next()) {
					auto = new AudioQuality(rs.getString("auto_abr"), rs.getInt("auto_tech_ref_id"),
							rs.getString("auto_codec"), rs.getString("auto_codecType"), rs.getInt("auto_bitRate"),
							rs.getInt("auto_noOfChannels"), rs.getInt("auto_samplingRate"),
							rs.getString("auto_fileExtention"));
					high = new AudioQuality(rs.getString("high_abr"), rs.getInt("high_tech_ref_id"),
							rs.getString("high_codec"), rs.getString("high_codecType"), rs.getInt("high_bitRate"),
							rs.getInt("high_noOfChannels"), rs.getInt("high_samplingRate"),
							rs.getString("high_fileExtention"));
					medium = new AudioQuality(rs.getString("medium_abr"), rs.getInt("medium_tech_ref_id"),
							rs.getString("medium_codec"), rs.getString("medium_codecType"), rs.getInt("medium_bitRate"),
							rs.getInt("medium_noOfChannels"), rs.getInt("medium_samplingRate"),
							rs.getString("medium_fileExtention"));
					low = new AudioQuality(rs.getString("low_abr"), rs.getInt("low_tech_ref_id"),
							rs.getString("low_codec"), rs.getString("low_codecType"), rs.getInt("low_bitRate"),
							rs.getInt("low_noOfChannels"), rs.getInt("low_samplingRate"),
							rs.getString("low_fileExtention"));
					ssDomainName = rs.getString("ssDomainName");
				}

			}
			mysql.close();
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
	@SuppressWarnings("finally")
	public UserInfoData signIn(RequestParameter reqParam) {
		UserInfoData userinfodata = null;
		try {
			MySQL mysql = new MySQL();

			ResultSet rs = mysql.prepareCall("{CALL `SignIn`(" + reqParam.getCountryId() + "," + reqParam.getSourceId()
					+ ",'" + reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
					+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "',"
					+ reqParam.getViaId() + "," + reqParam.getUserDeviceId() + ",'"
					+ reqParam.getUserName().replaceAll("'", "''") + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getEmailAddress().replaceAll("'", "''") + "','"
					+ reqParam.getPassword().replaceAll("'", "''") + "'," + reqParam.getGenderId() + ",'"
					+ getFinalRedirectedUrl(
							"http://graph.facebook.com/" + reqParam.getFbProfileId() + "/picture?type=large")
									.replaceAll("'", "''")
					+ "')}");

			if (rs != null) {
				while (rs.next()) {
					userinfodata = new UserInfoData(rs.getInt("user_id"), rs.getString("user_promo_code"),
							rs.getString("user_name"), rs.getString("mobile_number"), rs.getString("email_address"),
							rs.getInt("gender_id"), rs.getString("password"), rs.getString("image_url"),
							rs.getInt("user_type_id"), rs.getInt("user_status"), rs.getInt("action_id"),
							rs.getInt("registered"), rs.getInt("mobile_number_verification"),
							rs.getString("billing_mobile_number"), rs.getString("dialing_code"),
							rs.getInt("mobile_number_length"),
							new SubscribedPackage(rs.getInt("package_id"), rs.getString("package_name"),
									rs.getString("package_price"), rs.getString("package_validity_period"),
									rs.getString("package_start_date"), rs.getString("package_end_date"),
									rs.getString("android_token"), rs.getString("ios_token"),
									rs.getBoolean("optScreenVisibility")));
					// userinfodata.setUserSignUpVia(getUserSignUpVia(rs.getInt("user_id")));
					// userinfodata.setUserSubscriptionStatus(getUserSubscriptionStatus(rs.getInt("user_id"),
					// reqParam.getEventId() ));
				}
			}
			mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
			userinfodata = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.signIn(RequestParameter reqParam) - "
							+ e.getMessage() + "\t {CALL `SignIn`(" + reqParam.getCountryId() + ","
							+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "','"
							+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
							+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getViaId() + ","
							+ reqParam.getUserDeviceId() + ",'" + reqParam.getUserName().replaceAll("'", "''") + "','"
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
			MySQL mysql = new MySQL();
			mysql.prepareCall("{CALL `SignOut`(" + reqParam.getSourceId() + "," + reqParam.getUserId() + ",'"
					+ reqParam.getDeviceId() + "','" + reqParam.getApplicationVersion() + "','"
					+ reqParam.getOperatingSystem() + "')}");
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.signOut(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
		}
	}
	@SuppressWarnings("finally")
	public int sendSMS(RequestParameter reqParam) {
		int responseCode = 320;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `SendSMS`('" + reqParam.getMsisdn() + "','" + reqParam.getMessage()
					+ "','" + reqParam.getSender() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.changePassword(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}

	}
	@SuppressWarnings("finally")
	public int changePassword(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `ChangePassword`(" + reqParam.getUserId() + ",'"
					+ reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "','" + reqParam.getOldPassword()
					+ "','" + reqParam.getPassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.changePassword(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}

	}
	@SuppressWarnings("finally")
	public int forgetPassword(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `ForgetPassword`('" + reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println("Exception in HutchTriBeatzMainServlet.forgetPassword(RequestParameter reqParam) - "
					+ e.getMessage());
		} finally {
			return responseCode;
		}

	}
	@SuppressWarnings("finally")
	public int getSubscriptionTrialStatus(int userId) {
		int responseCode = 0;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetSubscriptionTrialStatus`(" + userId + ")}");
			while (rs.next()) {
				responseCode = rs.getInt("status_code");
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetSubscriptionTrialStatus1`(" + userId + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("status_code");
					userStatus = rs.getInt("user_status");
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserInfo`(1," + reqParam.getCountryId() + ","
					+ reqParam.getUserId() + ",'" + reqParam.getUserName() + "','" + reqParam.getEmailAddress() + "','"
					+ reqParam.getMsisdn() + "'," + reqParam.getGenderId() + "," + reqParam.getEventType() + ",'"
					+ reqParam.getOperatingSystem() + "','" + reqParam.getApplicationVersion() + "')}");
			if (rs != null) {
				while (rs.next()) {
					userinfodata = new UserInfoData(rs.getInt("user_id"), rs.getString("user_promo_code"),
							rs.getString("user_name"), rs.getString("mobile_number"), rs.getString("email_address"),
							rs.getInt("gender_id"), rs.getString("password"), rs.getString("image_url"),
							rs.getInt("user_type_id"), rs.getInt("user_status"), rs.getInt("action_id"),
							rs.getInt("registered"), rs.getInt("mobile_number_verification"),
							rs.getString("billing_mobile_number"), rs.getString("dialing_code"),
							rs.getInt("mobile_number_length"),
							new SubscribedPackage(rs.getInt("package_id"), rs.getString("package_name"),
									rs.getString("package_price"), rs.getString("package_validity_period"),
									rs.getString("package_start_date"), rs.getString("package_end_date"),
									rs.getString("android_token"), rs.getString("ios_token"),
									rs.getBoolean("optScreenVisibility")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserInfo`(2," + reqParam.getCountryId() + ","
					+ reqParam.getUserId() + ",'" + reqParam.getUserName() + "','" + reqParam.getEmailAddress() + "','"
					+ reqParam.getMsisdn() + "'," + reqParam.getGenderId() + "," + reqParam.getEventType() + ",'"
					+ reqParam.getOperatingSystem() + "','" + reqParam.getApplicationVersion() + "')}");
			if (rs != null) {
				while (rs.next()) {
					userinfodata = new UserInfoData(rs.getInt("user_id"), rs.getString("user_promo_code"),
							rs.getString("user_name"), rs.getString("mobile_number"), rs.getString("email_address"),
							rs.getInt("gender_id"), rs.getString("password"), rs.getString("image_url"),
							rs.getInt("user_type_id"), rs.getInt("user_status"), rs.getInt("action_id"),
							rs.getInt("registered"), rs.getInt("mobile_number_verification"),
							rs.getString("billing_mobile_number"), rs.getString("dialing_code"),
							rs.getInt("mobile_number_length"),
							new SubscribedPackage(rs.getInt("package_id"), rs.getString("package_name"),
									rs.getString("package_price"), rs.getString("package_validity_period"),
									rs.getString("package_start_date"), rs.getString("package_end_date"),
									rs.getString("android_token"), rs.getString("ios_token"),
									rs.getBoolean("optScreenVisibility")));
					// userinfodata.setUserSignUpVia(getUserSignUpVia(rs.getInt("user_id")));
					// userinfodata.setUserSubscriptionStatus(getUserSubscriptionStatus(rs.getInt("user_id"),
					// reqParam.getEventId()));

				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql
					.prepareCall("{CALL `Search`(1," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
			// System.out.println("Search--"+lst.toString());
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackSearch(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
	@SuppressWarnings("finally")
	public List<AlbumData> albumSearch(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql
					.prepareCall("{CALL `Search`(2," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql
					.prepareCall("{CALL `Search`(3," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			// ResultSet rs = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(1," +
			// reqParam.getCountryId() + "," + reqParam.getSourceId() + "," +
			// reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','" +
			// reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() +
			// "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," +
			// reqParam.getPlaylistId() + "," + reqParam.getAudioTechRefId() + "," +
			// reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + "," +
			// reqParam.getEndLimit() + ")}");
			ResultSet rs = mysql
					.prepareCall("{CALL `Search`(4," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword()
							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FilterByLetter`(1," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FilterByLetter`(2," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FilterByLetter`(3," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FilterByLetter`(4," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new GenreBean(rs.getInt("genre_id"), rs.getString("genre_name"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FilterByLetter`(5," + reqParam.getCountryId() + ",'" + reqParam.getSearchKeyword() + "',"
							+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackFilter(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
	@SuppressWarnings("finally")
	public List<HomeItemData> getHomeItem(RequestParameter reqParam) {
		List<HomeItemData> lst = new ArrayList<HomeItemData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetHomeItem`(" + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + ","
					+ reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new HomeItemData(rs.getInt("id"), rs.getInt("rid"), rs.getString("name"),
							rs.getString("type"), rs.getString("desc"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql
					.prepareCall("{CALL `GetTrackInfo`(" + reqParam.getCountryId() + ",'" + reqParam.getTrackCode()
							+ "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackInfo(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
    public List<karaokeTrackData> karaokeInfo(RequestParameter reqParam) {
        List<karaokeTrackData> lst = new ArrayList<karaokeTrackData>();
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `GetKaraokeInfo`(1,'" + reqParam.getUserId()+ "','" + reqParam.getTrackCode() + "','" + reqParam.getEventType() + "','" + reqParam.getTabId() + "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
            if (rs != null) {
                while (rs.next()) {
                   lst.add(new karaokeTrackData(rs.getInt("karaoke_id"),rs.getString("resource_code"),rs.getString("title"),rs.getString("artist_id"),rs.getString("artist_title"),rs.getString("fistUserId"),rs.getString("secondUserId"),rs.getString("singerType"),rs.getString("recording_mode"),rs.getString("record_type"),rs.getBoolean("isJoin"),rs.getString("streamingUrl"),rs.getString("imageUrl"),rs.getString("score"),rs.getBoolean("isCrbtAvailable"),rs.getBoolean("isKaraokeAvailable"),rs.getString("userName"),rs.getInt("firstKaraokeId"),rs.getBoolean("isFollowed"),rs.getBoolean("isReadyToPlay"),rs.getString("lang_karaoke_available").split("#")));
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.karaokeInfo(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return lst;
        }
    }
    
    public karaokeTrackData getkaraokeSongInfo(RequestParameter reqParam) {
    	karaokeTrackData obj=null;
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `GetKaraokeInfo`(1,'" + reqParam.getUserId()+ "','" + reqParam.getTrackCode() + "','" + reqParam.getEventType() + "','" + reqParam.getTabId() + "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
            if (rs != null) {
                while (rs.next()) {
                	obj=new karaokeTrackData(rs.getInt("karaoke_id"),rs.getString("resource_code"),rs.getString("title"),rs.getString("artist_id"),rs.getString("artist_title"),rs.getString("fistUserId"),rs.getString("secondUserId"),rs.getString("singerType"),rs.getString("recording_mode"),rs.getString("record_type"),rs.getBoolean("isJoin"),rs.getString("streamingUrl"),rs.getString("imageUrl"),rs.getString("score"),rs.getBoolean("isCrbtAvailable"),rs.getBoolean("isKaraokeAvailable"),rs.getString("userName"),rs.getInt("firstKaraokeId"),rs.getBoolean("isFollowed"),rs.getBoolean("isReadyToPlay"),rs.getString("lang_karaoke_available").split("#"));
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.karaokeInfo(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return obj;
        }
    }
    
    
    public Map<Integer,List<KaraokeCommentData>> getKaraokeCommentList(RequestParameter reqParam) {
    	Map<Integer,List<KaraokeCommentData>>map=new HashMap<Integer, List<KaraokeCommentData>>();
    	List<KaraokeCommentData>list=new ArrayList<KaraokeCommentData>();
    	int count=0;
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `KaraokeComment`(" + reqParam.getId() + "," + reqParam.getUserId() + "," + reqParam.getKaraokeId() + ",'" + reqParam.getMessage() + "','" + reqParam.getDatetime() + "'," + reqParam.getRecordingMode() + ",4,"+reqParam.getStartLimit()+","+reqParam.getEndLimit()+")}");
            if (rs != null) {
                while (rs.next()) {
                	count=rs.getInt("totalCount");
                	list.add(new KaraokeCommentData(rs.getInt("id"),rs.getInt("karouke_id"),rs.getInt("user_id"),rs.getString("userComment"), rs.getString("commentDateTime"), rs.getString("commentUpdateDateTime"), rs.getString("isUpdated"), rs.getString("user_name"), rs.getString("user_image_url")));
                }
            }
            map.put(count, list);
            mysql.close();
            return map;
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.KaraokeComment(RequestParameter reqParam) - " + e.getMessage());
            
            return map;
        }
    }
    public int karaokeComment(RequestParameter reqParam) {
        int responseCode = 110;
        try {
            MySQL mysql = new MySQL();
                ResultSet rs = mysql.prepareCall("{CALL `KaraokeComment`(" + reqParam.getId() + "," + reqParam.getUserId() + "," + reqParam.getKaraokeId() + ",'" + reqParam.getMessage() + "','" + reqParam.getDatetime() + "'," + reqParam.getRecordingMode() + "," + reqParam.getEventType() + ","+reqParam.getStartLimit()+","+reqParam.getEndLimit()+")}");
                    while (rs.next()) {
                        responseCode = rs.getInt("code");
                    }
            mysql.close();
        } catch (Exception e) {
            responseCode = 110;
            System.out.println("Exception in HutchTriBeatzMainServlet.keraokeComment(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return responseCode;
        }
    }
    
    
    public boolean FOLLOWKARAOKEARTIST(RequestParameter reqParam) {
        boolean following = false;
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`("+reqParam.getEventType()+"," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
            if (rs != null) {
                while (rs.next()) {
                    following = rs.getBoolean("follow");
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return following;
        }
    }
    
     public List<FollowKaraokeArtistData> FOLLOWKARAOKEARTISTLISTING(RequestParameter reqParam) {
    	List<FollowKaraokeArtistData> list=new ArrayList<FollowKaraokeArtistData>();
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`("+reqParam.getEventType()+"," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
            if (rs != null) {
                while (rs.next()) {
//                	   int followingCount=0;
//                	   int followersCount=0;
//                	   Boolean isArtistFollowed =false;
//                  	   ResultSet rs1 = mysql.prepareCall("{CALL `FOLLOWKARAOKEARTIST`(5," + rs.getInt("user_id") + ", "+ reqParam.getArtistId()+")}");
//		                	if (rs1 != null) {
//		                		while (rs1.next()) {
//		                			followingCount = rs1.getInt("follow_count");
//		                			followersCount = rs1.getInt("followed_count");   
//		                			isArtistFollowed= rs1.getBoolean("vFollowing");
//		                		}
//		                		rs1.close();	                		
//		                	}
		                
                	
                	//list.add(new FollowKaraokeArtistData(rs.getInt("user_id"),rs.getString("userName"),rs.getString("user_image_url") , followingCount , followersCount, isArtistFollowed ));
                 	list.add(new FollowKaraokeArtistData(rs.getInt("user_id"),rs.getString("userName"),rs.getString("user_image_url"),rs.getBoolean("isArtistFollowed") ));
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.FOLLOWKARAOKEARTISTLISTING(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return list;
        }
    }


	@SuppressWarnings("finally")
	public CountryInfoData getCountryInfo(RequestParameter reqParam) {
		CountryInfoData data = null;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `CountryInformation`('" + reqParam.getUserId() + "','"
					+ reqParam.getLanguageCode() + "','" + reqParam.getCountryCode() + "'," + reqParam.getSourceId()
					+ ",'" + reqParam.getApplicationVersion() + "','" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ")}");
			if (rs != null) {
				while (rs.next()) {
					data = new CountryInfoData();
					data.setCountryid(rs.getInt("country_id"));
					data.setCountryname(rs.getString("country_name"));
					data.setDialingcode(rs.getString("dialing_code"));
					data.setMobnolength(rs.getString("mob_no_len"));
					data.setCode2digit(rs.getString("code_2digit"));
					data.setCode3digit(rs.getString("code_3digit"));
					data.setLanguageCode(rs.getString("language_code"));
					data.setUserdevid(rs.getInt("user_device_id"));
					data.setAudioadversion(rs.getInt("audio_ad_version"));
					data.setAudioadurl(rs.getString("audio ad_url"));
					data.setSplashversion(rs.getInt("splash_version"));
					data.setSplashurl(rs.getString("splash_url"));
					data.setLogourl(rs.getString("logo_url"));
					data.setMlogourl(rs.getString("mlogo_url"));
					data.setNewLogoUrl(rs.getString("new_logo_url"));
					data.setIosThemeColour(rs.getString("ios_theme_colour"));
					data.setIsDataCacheEnable(rs.getBoolean("data_cache_enable"));
					data.setSubscriptionAvailable(rs.getInt("subscription_available"));
					data.setDownloadAvailable(rs.getInt("download_available"));
					data.setIsDataCacheEnable(rs.getBoolean("data_cache_enable"));
					data.setCrbtAvailable(rs.getInt("crbt_available"));
					data.setSubscriptionUrl(rs.getString("subscription_url"));
					data.setSubLinkOpenAction(rs.getInt("sub_link_open_action"));
					data.setShareUrl(rs.getString("share_url"));
					data.setMsisdnHeaderUrl(rs.getString("msisdn_header_url"));

					data.setSongOptionsSequence(rs.getString("song_options_sequence"));
					data.setSongPreviewDuration(rs.getInt("song_preview_duration"));

					data.setCli(rs.getString("cli"));
					data.setOtpLength(rs.getInt("otp_length"));
					data.setOtpPosition(rs.getInt("otpPosition"));
					data.setShowStickyIcon(rs.getBoolean("show_sticky_icon"));
					data.setShowLanguageChangePopup(rs.getBoolean("show_language_change_popup"));

					data.setIsSocialScreenEnable(rs.getBoolean("social_screen_enable"));

					data.setLoginRequiredAll(rs.getBoolean("login_required_all"));
					data.setLoginRequiredShare(rs.getBoolean("login_required_share"));
					data.setLoginRequiredCrbt(rs.getBoolean("login_required_crbt"));
					data.setLoginRequiredPlayAudio(rs.getBoolean("login_required_play_audio"));
					data.setLoginRequiredPlayVideo(rs.getBoolean("login_required_play_video"));
					data.setLoginRequiredDownload(rs.getBoolean("login_required_download"));
					data.setLoginRequiredOffline(rs.getBoolean("login_required_offline"));
					data.setLoginRequiredFavourite(rs.getBoolean("login_required_favourite"));

					data.setSubscriptionRequiredShare(rs.getBoolean("subscription_required_share"));
					data.setSubscriptionRequiredCrbt(rs.getBoolean("subscription_required_crbt"));
					data.setSubscriptionRequiredPlayAudio(rs.getBoolean("subscription_required_play_audio"));
					data.setSubscriptionRequiredPlayVideo(rs.getBoolean("subscription_required_play_video"));
					data.setSubscriptionRequiredDownload(rs.getBoolean("subscription_required_download"));
					data.setSubscriptionRequiredOffline(rs.getBoolean("subscription_required_offline"));
					data.setSubscriptionRequiredFavourite(rs.getBoolean("subscription_required_favourite"));

					data.setLikeSongPopup(rs.getInt("like_song_popup"));
					data.setOfflineDownloadPopup(rs.getInt("offline_download_popup"));
					data.setLeftMenuButtonTitle(rs.getString("leftMenuButtonTitle"));
					data.setLeftMenuButtonTitle_SW(rs.getString("leftMenuButtonTitle_SW"));
					data.setUploadMusicVisibility(rs.getBoolean("uploadMusicVisibility"));
					data.setArtistURL(rs.getString("artistURL"));
					data.setMsisdnHeaderFinalUrl(rs.getString("msisdnHeaderFinalUrl"));
					data.setOptScreenVisibility(rs.getBoolean("voptScreenVisibility"));
					data.setIsPromoCodeScreen(rs.getBoolean("isPromoCodeScreen"));
					data.setIsKaraokeAvailable(rs.getBoolean("isKaraokeAvailable"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			data = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getCountryInfo(RequestParameter reqParam) - "
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `FeatureVisibility`('" + reqParam.getCountryCode() + "',"
					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "')}");
			// ResultSet rs = mysql.prepareCall("{call `FeatureVisibilityNew`('" +
			// reqParam.getCountryCode() + "'," + reqParam.getSourceId() + ",'" +
			// reqParam.getOperatingSystem() + "','" + reqParam.getDevice() + "','" +
			// reqParam.getApplicationVersion() + "')}");
			if (rs != null) {
				while (rs.next()) {
					userTypeId = rs.getInt("user_type_id");
					if (userTypeId == 1) {
						visibilityValue[0] = new VisibilityValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
								rs.getInt("offline"), rs.getInt("download"));
					}
					if (userTypeId == 2) {
						visibilityValue[1] = new VisibilityValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
								rs.getInt("offline"), rs.getInt("download"));
					}
					if (userTypeId == 3) {
						visibilityValue[2] = new VisibilityValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
								rs.getInt("offline"), rs.getInt("download"));
					}
				}
				visibility = new Visibility(visibilityValue[0], visibilityValue[1], visibilityValue[2]);
			}
			mysql.close();

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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `FeatureConfiguration`('" + reqParam.getCountryCode() + "',"
					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "')}");
			if (rs != null) {
				while (rs.next()) {
					userTypeId = rs.getInt("user_type_id");
					if (userTypeId == 1) {
						configVal[0] = new ConfigurationValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
								rs.getInt("offline_limit"), rs.getInt("offline_daily"), rs.getInt("offline_total"),
								rs.getInt("download"), rs.getInt("stream_interrupt"), rs.getInt("stream_limit"),
								rs.getInt("stream_preview"), rs.getInt("stream_daily"), rs.getInt("stream_total"),
								rs.getInt("stream_track"));
					}
					if (userTypeId == 2) {
						configVal[1] = new ConfigurationValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
								rs.getInt("offline_limit"), rs.getInt("offline_daily"), rs.getInt("offline_total"),
								rs.getInt("download"), rs.getInt("stream_interrupt"), rs.getInt("stream_limit"),
								rs.getInt("stream_preview"), rs.getInt("stream_daily"), rs.getInt("stream_total"),
								rs.getInt("stream_track"));
					}
					if (userTypeId == 3) {
						configVal[2] = new ConfigurationValue(rs.getInt("banner_ads"), rs.getInt("audio_ads"),
								rs.getInt("favourite"), rs.getInt("dedication"), rs.getInt("share"), rs.getInt("crbt"),
								rs.getInt("offline_limit"), rs.getInt("offline_daily"), rs.getInt("offline_total"),
								rs.getInt("download"), rs.getInt("stream_interrupt"), rs.getInt("stream_limit"),
								rs.getInt("stream_preview"), rs.getInt("stream_daily"), rs.getInt("stream_total"),
								rs.getInt("stream_track"));
					}
				}
				configuration = new Configuration(configVal[0], configVal[1], configVal[2]);
			}
			mysql.close();

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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetSignUpViaConfiguration`('" + reqParam.getCountryCode() + "',"
					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "')}");
			if (rs != null) {
				while (rs.next()) {
					signUpViaId = rs.getInt("signup_via_id");
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
				}
			}
			mysql.close();

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
	public SignUpViaConfigurationBean getUserSignUpVia(int userId) {
		int signUpViaId = 0;
		SignUpViaConfigurationBean signUpViaConfiguration = new SignUpViaConfigurationBean();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetUserSignUpVia`(" + userId + ")}");
			if (rs != null) {
				while (rs.next()) {
					signUpViaId = rs.getInt("signup_via_id");
					System.out.println("servlet.MainServlet.getUserSignUpVia()" + signUpViaId);
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
				}
			}
			mysql.close();

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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetContainerList`(" + reqParam.getCountryId() + ",'"
					+ reqParam.getDeviceId() + "','" + reqParam.getLanguageCode() + "')}");
			int containder_id = 0;
			if (rs != null) {
				while (rs.next()) {
					containder_id = rs.getInt("container_id");
					if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34
							|| containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74
							|| containder_id == 84 || containder_id == 94 || containder_id == 104
							|| containder_id == 114) {
						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
								rs.getInt("item_list_type_id"),
								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 6)));
					} else {
						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
								rs.getInt("item_list_type_id"),
								getContainerItemList(reqParam.getCountryId(), reqParam.getUserId(),
										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 20)));
					}

				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getContainers(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<ContainerItemBean> getContainerItemList(int countryId, int userID, int containerId,
			int imageTechRefId, int startLimit, int endLimit) {
		List<ContainerItemBean> lst = new ArrayList<ContainerItemBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetContainerItemList`(" + countryId + "," + containerId + ","
					+ imageTechRefId + "," + userID + "," + startLimit + "," + endLimit + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
							rs.getString("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
							rs.getString("resource_description"), rs.getString("resource_image_url"),
							rs.getString("click_url"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getContainerItemList(int containerId,int startLimit,int endLimit) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	/// Karaoka List
	@SuppressWarnings("finally")
	public List<ContainerBean> getKaraokeContainerList(RequestParameter reqParam) {
		List<ContainerBean> lst = new ArrayList<ContainerBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetKaraokeContainerList`(" + reqParam.getCountryId() + ",'"
					+ reqParam.getDeviceId() + "','" + reqParam.getLanguageCode() + "')}");
			int containder_id = 0;
			if (rs != null) {
				while (rs.next()) {
					containder_id = rs.getInt("container_id");
					if (containder_id == 4 || containder_id == 14 || containder_id == 24 || containder_id == 34
							|| containder_id == 44 || containder_id == 54 || containder_id == 64 || containder_id == 74
							|| containder_id == 84 || containder_id == 94 || containder_id == 104
							|| containder_id == 114) {
						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
								rs.getInt("item_list_type_id"),
								getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(),
										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 6)));
					} else {
						lst.add(new ContainerBean(rs.getInt("container_id"), rs.getInt("seq_id"),
								rs.getInt("container_type_id"), rs.getString("container_title"), rs.getInt("see_all"),
								rs.getInt("item_list_type_id"),
								getKaraokeItemList(reqParam.getCountryId(), reqParam.getUserId(),
										rs.getInt("container_id"), reqParam.getImageTechRefId(), 0, 20)));
					}
				}
			}
			mysql.close();
		} catch (Exception e) {
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetKaraokaContainerItemList`(" + countryId + "," + containerId
					+ "," + imageTechRefId + "," + userID + "," + startLimit + "," + endLimit + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
							rs.getString("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
							rs.getString("resource_description"), rs.getString("resource_image_url"),
							rs.getString("click_url"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getKaraokeItemList(int containerId,int startLimit,int endLimit) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}

	// Karaoka List
	@SuppressWarnings("finally")
	public List<ContainerItemBean> getAllItemList(int countryId, String itemType, int imageTechRefId, int startLimit,
			int endLimit) {
		List<ContainerItemBean> lst = new ArrayList<ContainerItemBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
					+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ContainerItemBean(rs.getString("item_id"), rs.getString("item_seq_id"),
							rs.getString("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
							rs.getString("resource_description"), rs.getString("resource_image_url"),
							rs.getString("click_url"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetTabbedPane`('" + reqParam.getCountryCode() + "',"
					+ reqParam.getSourceId() + ",'" + reqParam.getApiVersion() + "','" + reqParam.getDeviceId() + "')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TabbedPaneBean(rs.getInt("tab_id"), rs.getInt("seq_id"), rs.getInt("tab_type_id"),
							rs.getString("tab_title")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getTabbedPane(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<LeftMenuTitle> getLeftMenuTitle(RequestParameter reqParam) {
		List<LeftMenuTitle> lst = new ArrayList<LeftMenuTitle>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetLeftMenuTitle`('"+reqParam.getLanguageCode()+"')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new LeftMenuTitle(rs.getString("title"), rs.getString("url"), rs.getBoolean("isExternal"),
							rs.getString("popupText"), rs.getString("popupTitle")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getLeftMenuTitle(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<PaymentMethod> getPaymentMethods(RequestParameter reqParam) {
		List<PaymentMethod> lst = new ArrayList<PaymentMethod>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetPaymentMethod`('" + reqParam.getCountryCode() + "')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new PaymentMethod(rs.getInt("id"), rs.getString("payment_method")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getPaymentMethods(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<PaymentMethod> getPackagePaymentMethods(int countryId, int packageId) {
		List<PaymentMethod> lst = new ArrayList<PaymentMethod>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetPackagePaymentMethods`(" + countryId + "," + packageId + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new PaymentMethod(rs.getInt("id"), rs.getString("payment_method")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getPaymentMethods(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<TabbedPaneBean> getDiscoverTabbedPane(RequestParameter reqParam) {
		List<TabbedPaneBean> lst = new ArrayList<TabbedPaneBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetTabbedPaneDiscover`('" + reqParam.getCountryCode() + "',"
					+ reqParam.getSourceId() + "," + reqParam.getApiVersion() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TabbedPaneBean(rs.getInt("tab_id"), rs.getInt("seq_id"), rs.getInt("tab_type_id"),
							rs.getString("tab_title")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getDiscoverTabbedPane(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<TabbedPaneItemBean> getTabbedPaneItem(RequestParameter reqParam) {
		List<TabbedPaneItemBean> lst = new ArrayList<TabbedPaneItemBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetTabbedPaneItem`(" + reqParam.getCountryId() + ","
					+ reqParam.getTabId() + "," + reqParam.getBannerTypeId() + "," + reqParam.getImageTechRefId() + ","
					+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TabbedPaneItemBean(rs.getInt("tab_id"), rs.getInt("item_seq_id"),
							rs.getInt("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
							rs.getString("resource_description"), rs.getString("resource_image_url"),
							rs.getString("banner_url"), rs.getString("click_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getTabbedPaneItem(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<TabbedPaneItemBean> getTabbedPaneItemFilter(RequestParameter reqParam) {
		List<TabbedPaneItemBean> lst = new ArrayList<TabbedPaneItemBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetTabbedPaneItemFilter`(" + reqParam.getCountryId() + ","
					+ reqParam.getTabId() + "," + reqParam.getBannerTypeId() + "," + reqParam.getImageTechRefId() + ",'"
					+ reqParam.getFilter() + "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TabbedPaneItemBean(rs.getInt("tab_id"), rs.getInt("item_seq_id"),
							rs.getInt("item_type_id"), rs.getString("item_resource_id"), rs.getString("resource_title"),
							rs.getString("resource_description"), rs.getString("resource_image_url"),
							rs.getString("banner_url"), rs.getString("click_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getTabbedPaneItem(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public List<HikeKeyboardItemBean> getHikeKeyboardItem(RequestParameter reqParam) {
		List<HikeKeyboardItemBean> lst = new ArrayList<HikeKeyboardItemBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{call `GetHikeKeyboardItem`(" + reqParam.getCountryId() + "," + reqParam.getImageTechRefId() + ","
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new HikeKeyboardItemBean(rs.getInt("item_seq_id"), rs.getInt("item_type_id"),
							rs.getString("item_resource_id"), rs.getString("resource_title"),
							rs.getString("resource_description"), rs.getString("resource_image_url"),
							rs.getString("share_message")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getTabbedPaneItem(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}

	}
	@SuppressWarnings("finally")
	public Splash getSplashScreenData(RequestParameter reqParam) {
		Splash data = null;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql
					.prepareCall("{call `SplashScreen`(" + reqParam.getCountryId() + "," + reqParam.getSourceId() + ","
							+ reqParam.getOperatingSystemId() + "," + reqParam.getEventType() + ")}");
			if (rs != null) {
				while (rs.next()) {
					data = new Splash(new SplashScreenData(rs.getString("splash_url"), rs.getString("splash_version"),
							rs.getString("logo_url"), rs.getString("logo_version")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			data = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getSplashScreenData(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return data;
		}

	}
	@SuppressWarnings("finally")
	public AlbumData album(RequestParameter reqParam) {
		AlbumData album = null;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					album = (new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(2," + reqParam.getCountryId() + ","
					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.albumTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
	@SuppressWarnings("finally")
	public List<AlbumData> allAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAlbumMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getAlbumId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetRecommendation`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.albumRecommendation(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
	@SuppressWarnings("finally")
	public ArtistData artist(RequestParameter reqParam) {
		ArtistData artist = null;
		try {
			MySQL mysql = new MySQL();
			// ResultSet rs = mysql.prepareCall("{call `GetArtistMetaData`(5," +
			// reqParam.getCountryId() + "," + reqParam.getSourceId() + "," +
			// reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','" +
			// reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() +
			// "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" +
			// reqParam.getTrackCode() + "'," + reqParam.getAudioTechRefId() + "," +
			// reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + "," +
			// reqParam.getEndLimit() + ")}");
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(5," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					artist = new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url"));
					artist.setTrackShareCount(rs.getLong("track_share_count"));
					artist.setTrackPlayCount(rs.getLong("track_play_count"));
					artist.setTrackFavouriteCount(rs.getLong("track_favourite_count"));
					artist.setPageTitle(rs.getString("artist_page_title"));
					artist.setMetaDescription(rs.getString("meta_description"));
					artist.setMetaKeywords(rs.getString("meta_keywords"));
					artist.setAboutArtist(rs.getString("about_artist"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return artist;
		}
	}
	@SuppressWarnings("finally")
	public OptScreenConfig getOptScreenConfig(RequestParameter reqParam) {
		OptScreenConfig optObj = null;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{call `GetOPTPageConfig`('" + reqParam.getUserId() + "','" + reqParam.getMsisdn() + "')}");
			int containder_id = 0;
			if (rs != null) {
				while (rs.next()) {
					optObj = new OptScreenConfig(
							rs.getString("title").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							rs.getString("buttonText").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							rs.getString("infoText").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
							rs.getBoolean("skipVisiblity"), rs.getBoolean("infoVisiblity"), rs.getInt("infoType"),
							rs.getString("infoUrl"), rs.getInt("packageId"), rs.getString("imageUrl"),
							rs.getBoolean("optScreenVisibility"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in Mziiki MainServlet.getBillingUserConfig(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return optObj;
		}
	}
	@SuppressWarnings("finally")
	public boolean followingArtist(RequestParameter reqParam) {
		boolean following = false;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FollowingArtist`(1," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					following = rs.getBoolean("following");
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return following;
		}
	}
	@SuppressWarnings("finally")
	public boolean followArtist(RequestParameter reqParam) {
		boolean following = false;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FollowingArtist`(2," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					following = rs.getBoolean("following");
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return following;
		}
	}
	@SuppressWarnings("finally")
	public boolean unfollowArtist(RequestParameter reqParam) {
		boolean following = false;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `FollowingArtist`(3," + reqParam.getUserId() + "," + reqParam.getArtistId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					following = rs.getBoolean("following");
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return following;
		}
	}
	@SuppressWarnings("finally")
	public int insertNotification(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `NotificationSend`(" + reqParam.getUserId() + ",'"
					+ reqParam.getMessage() + "'," + reqParam.getTrackCode() + ",'" + reqParam.getSender() + "',"
					+ reqParam.getNotificationId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.insertNotification(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}
	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendation(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetRecommendation`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistRecommendation(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}
	@SuppressWarnings("finally")
	public List<ArtistData> artistRecommendationByAlbum(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetBenefits`(" + countryID + ",'" + langCode + "')}");
			while (rs.next()) {
				obj = new SubscriptionBenefits(rs.getString("casualGeneralLine"),
						rs.getString("liteGeneralLine").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
						rs.getString("premiumGeneralLine").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
						rs.getString("benefitsHeading").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
						rs.getString("benefits").replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r")
								.replaceAll("\\\\n", "\n").replaceAll("\\\\r", "\r"),
						rs.getString("pageHeader"), rs.getString("packHeader"), rs.getString("benefitHeader"));
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistRecommendation`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(4," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetArtistRecommendation`(5," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(11," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(2," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(8," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					genre = new GenreBean(rs.getInt("genre_id"), rs.getString("genre_name"), rs.getString("image_url"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genre(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return genre;
		}
	}

	public List<AlbumData> genreAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreAlbums(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<AlbumData> genreArtistAlbums(RequestParameter reqParam) {
		List<AlbumData> lst = new ArrayList<AlbumData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(6," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new AlbumData(rs.getInt("album_id"), rs.getString("ivrMMNumber"),
							rs.getString("album_title"), rs.getString("artist_name"), rs.getInt("album_rating"),
							rs.getInt("album_tracks_count"), rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreArtistAlbums(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<ArtistData> genreArtists(RequestParameter reqParam) {
		List<ArtistData> lst = new ArrayList<ArtistData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new ArtistData(rs.getInt("artist_id"), rs.getString("ivrMMNumber"),
							rs.getString("artist_name"), rs.getInt("album_count"), rs.getInt("track_count"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreArtists(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> artistTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getAllTracks(RequestParameter reqParam, int countryId,
			String itemType, int imageTechRefId, int startLimit, int endLimit) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			// ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(6," +
			// reqParam.getCountryId() + "," + reqParam.getArtistId() + "," +
			// reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," +
			// reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			ResultSet rs = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
					+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getCrbtTracks(RequestParameter reqParam, int countryId,
			String itemType, int imageTechRefId, int startLimit, int endLimit) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			// ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(6," +
			// reqParam.getCountryId() + "," + reqParam.getArtistId() + "," +
			// reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," +
			// reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			ResultSet rs = mysql.prepareCall("{call `GetAllItemList`(" + countryId + ",'" + itemType + "',"
					+ imageTechRefId + "," + startLimit + "," + endLimit + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistTopTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(6," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistSingleTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(7," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistFeaturedTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(8," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getArtistVideoTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(9," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.artistTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> newTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(4," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.newTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> popularTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(10," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.popularTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> featuredTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetArtistMetaData`(4," + reqParam.getCountryId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.featuredTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> trackRecommendation(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetRecommendation`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.trackRecommendation(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getAutoPlayFeature(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {

			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `GetAutoPlayMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.GetAutoPlayMetaData(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> getFavouriteTracks(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFavouriteTracks(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public int addTrackToFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.addTrackToFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int delTrackFromFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.delTrackFromFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public List<TrackData> selectFavourite(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.selectFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public int insertFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.insertFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int signUpViaUpdate(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `SignUpVia_Update`(" + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "'," + reqParam.getViaId() + ",'"
					+ reqParam.getImageUrl() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.signUpViaUpdate(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int updateFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(5," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getOldOrderId()
					+ "," + reqParam.getNewOrderId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.updateFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int deleteFavourite(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.deleteFavourite(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

    public DownloadURL getDownloadUrl(RequestParameter reqParam, String messageException) {
    	
    	System.out.println("API Name--"+apiDomainName);
        DownloadURL dwld = new DownloadURL();
        try {
            dwld.setCode(0);
            dwld.setMessage("Success");
            String timestamp=String.valueOf(System.currentTimeMillis());
            try {
                MySQL mysql = new MySQL();
                ResultSet rs = mysql.prepareCall("{CALL `UserOffline`(2," + reqParam.getCountryId() + "," + reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "'," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ",'"+timestamp+"'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
                if (rs != null) {
                    while (rs.next()) {
                        dwld.setCode(rs.getInt("code"));
                        dwld.setMessage(rs.getString("message"));
                    }
                }
                mysql.close();
            } catch (Exception e) {
                System.out.println("Exception in Glo Nigeria MainServlet.getDownloadUrl_New(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
            }
            
                String params = "ocid=" + reqParam.getCountryId() + "&src=" + reqParam.getSourceId() + "&os=" + reqParam.getOperatingSystem() + "&osv=" + reqParam.getOperatingSystemVersion() + "&model=" + reqParam.getDeviceModel() + "&devid=" + reqParam.getDeviceId() + "&devpin=" + reqParam.getDevicePin() + "&evt=39&userid=" + reqParam.getUserId() + "&trackid=" + reqParam.getTrackCode() + "&dtype=1&dwldquality="+reqParam.getDownloadQuality()+"&token="+timestamp+"&lang="+reqParam.getLanguageCode();
                dwld.setWithAd(0);
                dwld.setUrl(apiDomainName+"downloadtrack?p=" + DatatypeConverter.printBase64Binary(new AESEncriptionDecription().encrypt(params).getBytes("UTF-8")));
        } catch (Exception e) {
            dwld.setCode(110);
            dwld.setMessage(messageException);
            System.out.println("Exception in Glo Nigeria MainServlet.getDownloadUrl_New(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return dwld;
        }
    }

	public void requestDedication(RequestParameter reqParam) {
		try {
			MySQL mysql = new MySQL();
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
			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
					+ reqParam.getTrackCode() + "'," + count + ",'" + reqParam.getUserName() + "','"
					+ reqParam.getMsisdn() + "','" + reqParam.getEmailAddress() + "','"
					+ reqParam.getDedicationMessage() + "'," + reqParam.getAudioTechRefId() + ","
					+ reqParam.getImageTechRefId() + ")}");
			if (rs != null) {
				while (rs.next()) {
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestDedication(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public List<DedicateeData> recentlyDedicatee(RequestParameter reqParam) {
		List<DedicateeData> lst = new ArrayList<DedicateeData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
					+ reqParam.getTrackCode() + "',0,'" + reqParam.getUserName() + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getEmailAddress() + "','" + reqParam.getDedicationMessage() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new DedicateeData(rs.getInt("id"), rs.getString("resource_title"),
							rs.getString("dedicatee_name"), rs.getString("dedicatee"), rs.getString("datetime")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.recentlyDedicatee(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<DedicateeData> removeRecentlyDedicatee(RequestParameter reqParam) {
		List<DedicateeData> lst = new ArrayList<DedicateeData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
					+ reqParam.getTrackCode() + "',0,'" + reqParam.getUserName() + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getEmailAddress() + "','" + reqParam.getDedicationId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new DedicateeData(rs.getInt("id"), rs.getString("resource_title"),
							rs.getString("dedicatee_name"), rs.getString("dedicatee"), rs.getString("date_time")));
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserDedication`(4," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getEventType() + ",'"
					+ reqParam.getTrackCode() + "',0,'" + reqParam.getUserName() + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getEmailAddress() + "','" + reqParam.getDedicationId() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new DedicationData(rs.getInt("dedication_id"), rs.getString("user_name"),
							rs.getString("request_date"), rs.getString("dedication_msg"), rs.getString("resource_code"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("user_image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getDedicationDetail(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<MobileOperatorData> crbtOperators(RequestParameter reqParam) {
		List<MobileOperatorData> lst = new ArrayList<MobileOperatorData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(1," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.crbtOperators(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	/*
	 * public Transaction requestCRBT(RequestParameter reqParam,
	 * DeviceInformation reqParam) { Transaction x = new
	 * Transaction(reqParam.getCountryId(), reqParam.getSourceId(),
	 * reqParam.getMsisdn()); try { MySQL mysql = new MySQL(); ResultSet rs =
	 * mysql.prepareCall("{CALL `CallerRingBackTone`(2," + reqParam.getCountryId() +
	 * "," + reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" +
	 * reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() +
	 * "','" + reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" +
	 * reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'" +
	 * reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() +
	 * "','" + reqParam.getOneTimePassword() + "')}"); if (rs != null) { while
	 * (rs.next()) { //lst.add(new MobileOperatorData(rs.getInt("id"),
	 * rs.getString("operator"))); } } mysql.close(); } catch (Exception e) {
	 * System.out.
	 * println("Exception in HutchTriBeatzMainServlet.requestCRBT(RequestParameter reqParam) - "
	 * + e.getMessage()); } finally { return x; } }
	 */

	public Integer requestCRBT(RequestParameter reqParam) {
		Transaction x = new Transaction(reqParam.getCountryId(), reqParam.getSourceId(), reqParam.getMsisdn());
		Integer response = 167;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(2," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					response = rs.getInt("code");
					// lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestCRBT(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return response;
		}
	}

	public int resendOTP(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(3," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.resendOTP(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int otpVerification(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `CallerRingBackTone`(4," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.otpVerification(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public List<MobileOperatorData> billingOperators(RequestParameter reqParam) {
		List<MobileOperatorData> lst = new ArrayList<MobileOperatorData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `AvailableOperatorBilling`(" + reqParam.getCountryId() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.billingOperators(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public Transaction requestPaidDownload(RequestParameter reqParam) {
		Transaction x = new Transaction(reqParam.getCountryId(), reqParam.getSourceId(), reqParam.getMsisdn());
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(2," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.requestPaidDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return x;
		}
	}

	public int resendOTPPaidDownload(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(3," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.resendOTPPaidDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int otpVerificationPaidDownload(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(4," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `Download`(" + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + "," + reqParam.getViaId() + ","
					+ reqParam.getUserId() + ",2,'" + reqParam.getTrackCode() + "','" + reqParam.getMsisdn() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(5," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new Purchase(rs.getString("resource_code"), rs.getString("resource_title"),
							rs.getString("artist_name"), rs.getString("album_title"), rs.getString("purchase_date"),
							rs.getString("expiry_date"), rs.getInt("allow_download"), rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getPurchaseHistory(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<DownloadHistory> getDownloadHistory(RequestParameter reqParam) {
		List<DownloadHistory> lst = new ArrayList<DownloadHistory>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `PaidDownload`(7," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new DownloadHistory(rs.getString("date"), rs.getString("resource_code"),
							rs.getInt("download_type_id"), rs.getString("resource_title"), rs.getString("artist_name"),
							rs.getString("album_title"), rs.getString("expiry_date"), rs.getInt("allow_download"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getDownloadHistory(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public Transaction mobileNumberVerificationRequest(RequestParameter reqParam) {
		Transaction x = new Transaction(reqParam.getCountryId(), reqParam.getSourceId(), reqParam.getMsisdn());
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `MobileNumberVerification`(1," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + x.getXid() + "','" + reqParam.getMsisdn() + "','"
					+ reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new MobileOperatorData(rs.getInt("id"), rs.getString("operator")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.mobileNumberVerificationRequest(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return x;
		}
	}

	public int checkSignUpViaMobileNumber(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `CheckSignUpViaMobileNumber`('" + reqParam.getMsisdn() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
		} catch (Exception e) {
			responseCode = 110;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.mobileNumberVerificationResendOTP(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return responseCode;
		}
	}

	public int mobileNumberVerificationResendOTP(RequestParameter reqParam) {
		int responseCode = 110;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `MobileNumberVerification`(2," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `MobileNumberVerification`(3," + reqParam.getCountryId() + ","
					+ reqParam.getOperatorId() + "," + reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem()
					+ "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'"
					+ reqParam.getTrackCode() + "','" + reqParam.getTransactionId() + "','" + reqParam.getMsisdn()
					+ "','" + reqParam.getOneTimePassword() + "')}");
			if (rs != null) {
				while (rs.next()) {
					responseCode = rs.getInt("code");
				}
			}
			mysql.close();
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
		CountInfoData info = new CountInfoData(reqParam.getUserId(), 0, 0, 0, 0, 1, "0",0);
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{call `UserFavourite`(4," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			while (rs.next()) {
				info = new CountInfoData(rs.getInt("user_id"), rs.getInt("total_count"), rs.getInt("user_type_id"),
						rs.getInt("user_status"), rs.getInt("play_counter"), rs.getInt("device_status"),
						rs.getString("device_status_msg"),getTimeStampForStreaming());
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getFavouriteCount(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return new CountInfo(info);
		}
	}

	public CountInfo getNotificationCount(RequestParameter reqParam) {
		CountInfoData info = new CountInfoData(reqParam.getUserId(), 0, 0, 0, 0, 1, "0",0);
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{call `UserNotification`(3," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
							+ reqParam.getDeviceId() + "'," + reqParam.getUserId() + ",'" + reqParam.getNotificationId()
							+ "'," + reqParam.getImageTechRefId() + ",'" + reqParam.getLanguageCode() + "',"
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			while (rs.next()) {
				info = new CountInfoData(rs.getInt("user_id"), rs.getInt("total_count"), rs.getInt("user_type_id"),
						rs.getInt("user_status"), rs.getInt("play_counter"), rs.getInt("device_status"),
						rs.getString("device_status_msg"),getTimeStampForStreaming());
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getNotificationCount(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return new CountInfo(info);
		}
	}

	public UserOfflineInformationBean getUserOfflineInformation(RequestParameter reqParam,
			String messageOffline1, String messageOffline2) {
		UserOfflineInformationBean info = null;
		String timestamp=String.valueOf(System.currentTimeMillis());
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserOffline`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ",'"+timestamp+"'," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					info = new UserOfflineInformationBean(rs.getInt("code"), rs.getString("message"),
							rs.getInt("total_credits"), rs.getInt("available_credits"), rs.getInt("used_credits"),
							messageOffline1, messageOffline2);
				}
			}
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new GenreBean(rs.getInt("genre_id"), rs.getString("genre_name"),
							rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.allGenre(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> genreTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(4," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreTrackList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> genreArtistTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetGenreMetaData`(7," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getGenreId() + ","
					+ reqParam.getArtistId() + "," + reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId()
					+ "," + reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.genreArtistTrackList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<NotificationData> getUserNotification(RequestParameter reqParam) {
		List<NotificationData> lst = new ArrayList<NotificationData>();
		try { // ," + reqParam.getSourceId() +
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `UserNotification`(1," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
							+ reqParam.getDeviceId() + "'," + reqParam.getUserId() + "," + reqParam.getNotificationId()
							+ "," + reqParam.getImageTechRefId() + ",'" + reqParam.getLanguageCode() + "',"
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new NotificationData(rs.getInt("notification_id"), rs.getString("sender_name"),
							rs.getString("notification_title"), rs.getString("notification_subtitle"),
							rs.getString("notification_msg"), rs.getString("value"), rs.getString("notification_type"),
							rs.getInt("read_status"), rs.getString("date_time"), rs.getString("image_url")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getUserNotification(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<NotificationData> getNotificationInfo(RequestParameter reqParam) {
		List<NotificationData> lst = new ArrayList<NotificationData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall(
					"{CALL `UserNotification`(4," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'"
							+ reqParam.getDeviceId() + "'," + reqParam.getUserId() + "," + reqParam.getNotificationId()
							+ "," + reqParam.getImageTechRefId() + ",'" + reqParam.getLanguageCode() + "',"
							+ reqParam.getStartLimit() + "," + reqParam.getEndLimit() + ")}");
			while (rs.next()) {
				lst.add(new NotificationData(rs.getInt("notification_id"), rs.getString("sender_name"),
						rs.getString("notification_title"), rs.getString("notification_subtitle"),
						rs.getString("notification_msg"), rs.getString("value"), rs.getString("notification_type"),
						rs.getInt("read_status"), rs.getString("date_time"), rs.getString("image_url")));
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.getNotificationInfo(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public void readNotification(RequestParameter reqParam) {
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserNotification`(2," + reqParam.getCountryId() + ","
					+ reqParam.getUserId() + "," + reqParam.getNotificationId() + "," + reqParam.getImageTechRefId()
					+ ",'" + reqParam.getLanguageCode() + "'," + reqParam.getStartLimit() + "," + reqParam.getEndLimit()
					+ ")}");
			while (rs.next()) {
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					" Exception in MainServlet.readNotification(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public void userFeedback(RequestParameter reqParam) {
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `Feedback`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getDeviceId() + "','"
					+ reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "'," + reqParam.getEventType() + ",'"
					+ reqParam.getFeedbackData() + "')}");
			if (rs != null) {
				while (rs.next()) {
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.userFeedback(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public void audioQualityFeedback(RequestParameter reqParam) {
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `AudioQualityFeedback`(" + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getApplicationVersion() + "','"
					+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "',"
					+ reqParam.getUserId() + "," + reqParam.getUserDeviceId() + ",'" + reqParam.getTrackCode() + "',"
					+ reqParam.getRating() + ",'" + reqParam.getAction() + "','" + reqParam.getAudioQuality() + "','"
					+ reqParam.getNetwork() + "')}");
			if (rs != null) {
				while (rs.next()) {
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.audioQualityFeedback(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public void socialSharingTrack(RequestParameter reqParam) {
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `SocialSharing`(" + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + "," + reqParam.getUserDeviceId() + ",'"
					+ reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
					+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','"
					+ reqParam.getTrackCode() + "','" + reqParam.getEventType() + "')}");
			if (rs != null) {
				while (rs.next()) {
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.socialSharingTrack(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public void successfulOfflineDownload(RequestParameter reqParam) {
		String timestamp=String.valueOf(System.currentTimeMillis());
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserOffline`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "','" + reqParam.getTrackCode() + "',"
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + ",'"+timestamp+"'," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// dwld.setCode(rs.getInt("code"));
					// dwld.setMessage(rs.getString("message"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.successfulOfflineDownload(RequestParameter reqParam) - "
							+ e.getMessage());
		}
	}

	public List<FeedbackSubjectData> feedbackSubjectList(RequestParameter reqParam) {
		List<FeedbackSubjectData> lst = new ArrayList<FeedbackSubjectData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `Feedback`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getDeviceId() + "','"
					+ reqParam.getEmailAddress() + "','" + reqParam.getMsisdn() + "'," + reqParam.getEventType() + ",'"
					+ reqParam.getFeedbackData() + "')}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new FeedbackSubjectData(rs.getInt("fdbk_subject_id"), rs.getString("fdbk_subject")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.feedbackSubjectList(RequestParameter reqParam) - "
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					playlist = new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			playlist = null;
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFeaturedPlaylistInfo(RequestParameter reqParam) - "
							+ e.getMessage() + "/r/n" + "{CALL `GetFeaturedPlaylistMetaData`(3,"
							+ reqParam.getCountryId() + "," + reqParam.getSourceId() + "," + reqParam.getUserId() + ",'"
							+ reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
							+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin()
							+ "'," + reqParam.getPlaylistId() + "," + reqParam.getAudioTechRefId() + ","
							+ reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + ","
							+ reqParam.getEndLimit() + ")}");

		} finally {
			return playlist;
		}
	}

	public RadioBean getRadioInfo(RequestParameter reqParam) {
		RadioBean radio = new RadioBean();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetRadioMetaData`(3," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getRadioId() + ","
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					radio = new RadioBean(rs.getInt("radio_id"), rs.getString("radio_name"), rs.getString("radio_desc"),
							rs.getString("image_url"), rs.getInt("track_count"));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getRadioInfo(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return radio;
		}
	}

	public List<FeaturedPlaylistBean> getFeaturedPlaylist(RequestParameter reqParam) {
		List<FeaturedPlaylistBean> lst = new ArrayList<FeaturedPlaylistBean>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new FeaturedPlaylistBean(rs.getInt("playlist_id"), rs.getString("playlist_name"),
							rs.getString("playlist_desc"), rs.getString("image_url"), rs.getInt("track_count")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFeaturedPlaylist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public boolean validateMobileNumber(RequestParameter reqParam) {		
		boolean isValid = false;
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `ValidateMobileNumber`('" + reqParam.getMsisdn() + "')}");
			while (rs.next()) {
			
				isValid = rs.getBoolean("is_valid");
			} 
			mysql.close();
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
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetRadioMetaData`(1," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new RadioBean(rs.getInt("radio_id"), rs.getString("radio_name"), rs.getString("radio_desc"),
							rs.getString("image_url"), rs.getInt("track_count")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.getFeaturedPlaylist(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public List<TrackData> featuredPlaylistTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetFeaturedPlaylistMetaData`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getPlaylistId() + ","
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					// lst.add(new TrackData(rs.getString("resource_code"),
					// rs.getString("resource_title"), rs.getInt("album_id"),
					// rs.getString("album_title"), rs.getInt("artist_id"),
					// rs.getString("artist_name"), rs.getInt("genre_id"),
					// rs.getString("genre_name"), rs.getLong("size"), rs.getLong("duration"),
					// getFullStreamUrl(rs.getString("filePath"), reqParam),
					// rs.getString("image_url"), rs.getString("video_id")));
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.featuredPlaylistTrackList(RequestParameter reqParam) - "
							+ e.getMessage() + "-" + "{CALL `GetFeaturedPlaylistMetaData`(2," + reqParam.getCountryId()
							+ "," + reqParam.getSourceId() + "," + reqParam.getUserId() + ",'"
							+ reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','"
							+ reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin()
							+ "'," + reqParam.getPlaylistId() + "," + reqParam.getAudioTechRefId() + ","
							+ reqParam.getImageTechRefId() + "," + reqParam.getStartLimit() + ","
							+ reqParam.getEndLimit() + ")}");
		} finally {
			return lst;
		}
	}

	public List<TrackData> radioTrackList(RequestParameter reqParam) {
		List<TrackData> lst = new ArrayList<TrackData>();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `GetRadioMetaData`(2," + reqParam.getCountryId() + ","
					+ reqParam.getSourceId() + "," + reqParam.getUserId() + ",'" + reqParam.getOperatingSystem() + "','"
					+ reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','"
					+ reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getRadioId() + ","
					+ reqParam.getAudioTechRefId() + "," + reqParam.getImageTechRefId() + "," + reqParam.getStartLimit()
					+ "," + reqParam.getEndLimit() + ")}");
			if (rs != null) {
				while (rs.next()) {
					lst.add(new TrackData(rs.getString("resource_code"), rs.getString("ivrMMNumber"),
							rs.getString("resource_title"), rs.getInt("album_id"), rs.getString("album_title"),
							rs.getInt("artist_id"), rs.getString("artist_name"), rs.getInt("genre_id"),
							rs.getString("genre_name"), rs.getLong("play_count"), rs.getLong("favourite_count"),
							rs.getLong("share_count"), rs.getLong("size"), rs.getLong("duration"),
							getFullStreamUrl(rs.getString("filePath"), reqParam), rs.getString("image_url"),
							rs.getString("video_id"), rs.getBoolean("isCrbtAvailable"),
							rs.getBoolean("isKaraokeAvailable"),rs.getString("lang_karaoke_available").split("#")));
				}
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in HutchTriBeatzMainServlet.radioTrackList(RequestParameter reqParam) - "
							+ e.getMessage());
		} finally {
			return lst;
		}
	}

	public Root getUserDeviceLoginInformation(RequestParameter reqParam) {
		Root obj1 = new Root();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `UserDeviceLoginInformation`(" + reqParam.getCountryId() + ",'"
					+ reqParam.getMsisdn() + "','" + reqParam.getDeviceId() + "'," + reqParam.getViaId() + ",'"
					+ reqParam.getEmailAddress() + "','" + reqParam.getApplicationVersion() + "','"
					+ reqParam.getOperatingSystem() + "')}");
			if (rs != null) {
				while (rs.next()) {
					obj1 = new Root(rs.getInt("code"), rs.getString("message"));
				}
			}
			mysql.close();
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

            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{call `ApplicationInstallViaShare`('" + reqParam.getEventType() + "','" + reqParam.getCountryCode() + "'," + reqParam.getCountryId() + "," + reqParam.getSourceId() + ",'" + reqParam.getApplicationVersion() + "','" + reqParam.getOperatingSystem() + "','" + reqParam.getOperatingSystemVersion() + "','" + reqParam.getDeviceModel() + "','" + reqParam.getDeviceId() + "','" + reqParam.getDevicePin() + "'," + reqParam.getUserId() + ",'" + reqParam.getPromotionCode() + "','" + reqParam.getAction() + "')}");
            if (rs != null) {
                while (rs.next()) {
                    resultCode = rs.getInt("code");
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.applicationInstallViaShare(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return resultCode;
        }
    }
    public DeviceInformation getUserDeviceInformation(String emailAddress) {
        DeviceInformation devInfo = new DeviceInformation();
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{call `UserDeviceInformation`('" + emailAddress + "')}");
            if (rs != null) {
                while (rs.next()) {
                    devInfo.setDeviceId(rs.getString("dev_id"));
                    devInfo.setOperatingSystem(rs.getString("os"));
                    devInfo.setDeviceModel(rs.getString("dev_model"));
                    devInfo.setOperatingSystemVersion(rs.getString("osv"));
                    devInfo.setDevicePin(rs.getString("dev_pin"));
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in HutchTriBeatzMainServlet.getUserDeviceInformation() - " + e.getMessage());
        }
        return devInfo;
    }

    //Send Notification
	public void sendNotification(String notificationID,
			String devPin,
			String lang,
			String type,
			String userId
			) {
		
		 try{
	        	MySQL sql=new MySQL();
	        	if(type.equalsIgnoreCase("IN")){
		        		sql.executeUpdate("INSERT INTO tblNotification " +
		        				" (user_id,sender_name,notification_title,notification_title_idLang,notification_subtitle,notification_msg,notification_msg_idLang,notification_image,value,notification_type,image_code,country_id)" +
		        				" SELECT "+userId+",sender_name,notification_title,notification_title_idLang,notification_subtitle,notification_msg,notification_msg_idLang,notification_image,value,notification_type,image_code,country_id FROM `tblNotification` WHERE notification_id='"+notificationID+"'");
	        	}
	        	else{		
	        	if(lang.equalsIgnoreCase("en"))
	        		sql.executeUpdate("INSERT INTO tblPushNotification " +
	        				" (notification_id,notification_message,notification_image,os,device_id,device_pin,STATUS,remarks,request_date_time)" +
	        				" SELECT notification_id,notification_msg,'NA','android','','"+devPin+"',0,'NA',NOW() FROM `tblNotification` WHERE notification_id='"+notificationID+"'");
	        	else
	        		sql.executeUpdate("INSERT INTO tblPushNotification " +
	        				" (notification_id,notification_message,notification_image,os,device_id,device_pin,STATUS,remarks,request_date_time)" +
	        				" SELECT notification_id,notification_msg_idLang,'NA','android','','"+devPin+"',0,'NA',NOW() FROM `tblNotification` WHERE notification_id='"+notificationID+"'");
	        	}
	        	sql.close();
		 }catch(Exception e){
			 System.out.println("Exception in send ing Notification---"+e);
		 }
		
	}

	//CCI Portal
	public CciPortalResponse getUserSubDetails(String msisdn) {
		CciPortalResponse obj = new CciPortalResponse();
		        try {
		            MySQL mysql = new MySQL();
		            ResultSet rs = mysql.prepareCall("{call `HUTCHTRICCIPORTAL`(2,'" + msisdn + "')}");
		            if (rs != null) {
		                while (rs.next()) {
		                	obj.setDatetime(rs.getString("datetime"));
		                	obj.setMobile_number(rs.getString("mobile_number"));
		                	obj.setStatus(rs.getString("status"));
		                	obj.setPackName(rs.getString("package_description"));
		                	obj.setPrice(rs.getString("price_info"));
		                	
		                	obj.setPackageStartDate(rs.getString("package_start_date"));
		                	obj.setPackageEndtDate(rs.getString("package_end_date"));
		                	obj.setPackageRenewDate(rs.getString("package_renewal_date"));
		                	obj.setLastSuccessBilling(rs.getString("last_successfull_billing"));
		                }
		                mysql.close();
		            }
	        	
		 }catch(Exception e){
			 System.out.println("Exception in send ing Notification---"+e);
		 }
		return obj;
	}
	public List<CciPortalResponse> getUserSuccessDetails(String msisdn) {
		
		List<CciPortalResponse> list = new ArrayList<CciPortalResponse>();
		        try {
		            MySQL mysql = new MySQL();
		            ResultSet rs = mysql.prepareCall("{call `HUTCHTRICCIPORTAL`(1,'" + msisdn + "')}");
		            if (rs != null) {
		                while (rs.next()) {
		                	CciPortalResponse obj=new CciPortalResponse();
		                	obj.setDatetime(rs.getString("datetime"));
		                	obj.setMobile_number(rs.getString("mobile_number"));
		                	obj.setRemarks(rs.getString("remarks"));
		                	obj.setPackName(rs.getString("package_description"));
		                	obj.setPrice(rs.getString("price_info"));
		                	list.add(obj);
		                }
		                mysql.close();
		            }
		 }catch(Exception e){
			 System.out.println("Exception in send ing Notification---"+e);
		 }
		return list;
	}
	public List<CciPortalResponse> getUserUnsubDetails(String msisdn) {
		
		List<CciPortalResponse> list = new ArrayList<CciPortalResponse>();
		        try {
		            MySQL mysql = new MySQL();
		            ResultSet rs = mysql.prepareCall("{call `HUTCHTRICCIPORTAL`(3,'" + msisdn + "')}");
		            if (rs != null) {
		                while (rs.next()) {
		                	CciPortalResponse obj=new CciPortalResponse();
		                	
		                	obj.setDatetime(rs.getString("datetime"));
		                	obj.setMobile_number(rs.getString("mobile_number"));
		                	obj.setStatus(rs.getString("status"));
		                	obj.setPackName(rs.getString("package_description"));
		                	obj.setPrice(rs.getString("price_info"));
		                	
		                	obj.setPackageStartDate(rs.getString("package_start_date"));
		                	obj.setPackageEndtDate(rs.getString("package_end_date"));
		                	obj.setPackageRenewDate(rs.getString("package_renewal_date"));
		                	obj.setLastSuccessBilling(rs.getString("last_successfull_billing"));
		                	obj.setUnsubDate(rs.getString("unsub_date"));
		                	list.add(obj);
		                }
		                mysql.close();
		            }
		 }catch(Exception e){
			 System.out.println("Exception in send ing Notification---"+e);
		 }
		return list;
	}
    public PromotionShareConfig getPromotionShareConfig(RequestParameter reqParam) {
    	PromotionShareConfig obj1 = null;
        try {
            MySQL mysql = new MySQL();
            ResultSet rs = mysql.prepareCall("{CALL `PromotionShareConfig`('" + reqParam.getUserId() + "')}");
            if (rs != null) {
                while (rs.next()) {
                    obj1 = new PromotionShareConfig(rs.getInt("id"),rs.getString("banner_url") ,rs.getString("title") ,rs.getString("msg") ,rs.getString("tnc_title") ,rs.getString("tnc_msg") ,rs.getString("share_msg"));
                }
            }
            mysql.close();
        } catch (Exception e) {
            System.out.println("Exception in Voda_ghana MainServlet.getPromotionShareConfig(RequestParameter reqParam, DeviceInformation devInfo) - " + e.getMessage());
        } finally {
            return obj1;
        }
    }
    
    
    	
public List<OperatorData> getOperatorData(String fromDate , String todate) {
		
		List<OperatorData> list = new ArrayList<OperatorData>();
		        try {		        	
		            MySQL mysql = new MySQL();		         
		            ResultSet rs = mysql.prepareCall("{call `PRO_OPERATOR_DATA`(0)}");
		            rs		= mysql.executeQuery("SELECT DATE_FORMAT(date_time,'%Y-%m-%d %H:%i') as date_time , pack_name as pack_name ,  sub_type as sub_type ,price as price , days as days , total_count as total_count , revenue as revenue    FROM tbl_operator_data WHERE  DATE(date_time) BETWEEN  '"+fromDate+"' and '"+todate+"' ORDER  BY date_time DESC ");
		            
		            if (rs != null) {
		                while (rs.next()) {
		                	OperatorData obj=new OperatorData();
		                	obj.setDateTime(rs.getString("date_time"));
		                	obj.setPackName(rs.getString("pack_name"));
		                	obj.setSubType(rs.getString("sub_type"));
		                	obj.setPrice(rs.getString("price"));
		                	obj.setDays(rs.getInt("days"));
		                	obj.setTotalCount(rs.getInt("total_count"));
		                	obj.setRevenue(rs.getString("revenue"));		                
		                	list.add(obj);
		                }
		                mysql.close();
		            }
		 }catch(Exception e){
			 System.out.println("Exception in send ing Notification---"+e);
		 }
		return list;
	}

    
    
    
    public long getTimeStampForStreaming() {
    	
    	long currenttime=System.currentTimeMillis();
    	
    	System.out.println("----> UTC DATE     -> " + (currenttime + new java.util.Date(currenttime).getTimezoneOffset()));
    	
    	return (currenttime + new java.util.Date(currenttime).getTimezoneOffset());
    }

/*    public static void main(String arr[]) {
    	new  DataBaseProcedures().getTimeStampForStreaming();
    }*/
    
}
