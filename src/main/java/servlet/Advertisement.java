/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.codehaus.jackson.map.ObjectMapper;

import com.beans.AdvertisementData;
import com.database.MySQL;

/**
 *
 * @author Rajat.kumar
 */
public class Advertisement extends HttpServlet {

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
	 * methods.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	int countryId;
	int sourceId;
	int adVersion;
	int operatingSystemId;
	long userId;
	long userDeviceId;
	int userTypeId;
	String operatingSystem;
	String pageName;
	String responseFormat;

	public Advertisement() {
		this.countryId = 1;
		this.sourceId = 1;
		this.adVersion = 0;
		this.userId = 0;
		this.userDeviceId = 0;
		this.operatingSystem = "Other";
		this.pageName = "NA";
		this.responseFormat = "json";
		this.userTypeId = 1;
	}

	public int getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		try {
			if (countryId == null) {
				this.countryId = 1;
			} else {
				this.countryId = Integer.parseInt(countryId);
			}
		} catch (Exception e) {
			this.countryId = 1;
		}
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		try {
			if (sourceId == null) {
				this.sourceId = 1;
			} else {
				this.sourceId = Integer.parseInt(sourceId);
			}
		} catch (Exception e) {
			this.sourceId = 1;
		}
	}

	public int getAdVersion() {
		return adVersion;
	}

	public void setAdVersion(String adVersion) {
		try {
			if (adVersion == null) {
				this.adVersion = 0;
			} else {
				this.adVersion = Integer.parseInt(adVersion);
			}
		} catch (Exception e) {
			this.adVersion = 0;
		}
	}

	public int getOperatingSystemId() {
		return operatingSystemId;
	}

	public void setOperatingSystemId(String operatingSystem) {
		if (operatingSystem == null) {
			this.operatingSystemId = 2;
		} else if (operatingSystem.equalsIgnoreCase("android")) {
			this.operatingSystemId = 2;
		} else if (operatingSystem.equalsIgnoreCase("iPhone OS") || operatingSystem.equalsIgnoreCase("iOS")) {
			this.operatingSystemId = 1;
		} else if (operatingSystem.equalsIgnoreCase("blackberry") || operatingSystem.equalsIgnoreCase("RIM")) {
			this.operatingSystemId = 3;
		} else {
			this.operatingSystemId = 2;
		}

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		try {
			if (userId == null) {
				this.userId = 0;
			} else {
				this.userId = Long.parseLong(userId);
			}
		} catch (Exception e) {
			this.userId = 0;
		}
	}

	public long getUserDeviceId() {
		return userDeviceId;
	}

	public void setUserDeviceId(String userDeviceId) {
		try {
			if (userDeviceId == null) {
				this.userDeviceId = 0;
			} else {
				this.userDeviceId = Long.parseLong(userDeviceId);
			}
		} catch (Exception e) {
			this.userDeviceId = 0;
		}

	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		if (operatingSystem == null) {
			this.operatingSystem = "other";
		} else {
			this.operatingSystem = operatingSystem;
		}

	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		if (pageName == null) {
			this.pageName = "NA";
		} else {
			this.pageName = pageName;
		}

	}

	public String getResponseFormat() {
		return responseFormat;
	}

	public void setResponseFormat(String responseFormat) {
		if (responseFormat == null) {
			this.responseFormat = "json";
		} else {
			this.responseFormat = "json";
		}

	}

	public int getUserTypeId() {
		return userTypeId;
	}

	public void setUserTypeId(String userTypeId) {
		try {
			if (userTypeId == null) {
				this.userTypeId = 3;
			} else {
				this.userTypeId = Integer.parseInt(userTypeId);
			}
		} catch (Exception e) {
			this.userTypeId = 3;
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			Object obj = null;
			try {
				setCountryId(request.getParameter("country"));
				setSourceId(request.getParameter("source"));
				setAdVersion(request.getParameter("version"));
				setUserId(request.getParameter("userid"));
				setUserDeviceId(request.getParameter("udevid"));
				setOperatingSystem(request.getParameter("os"));
				setOperatingSystemId(request.getParameter("os"));
				setPageName(request.getParameter("page"));
				setResponseFormat(request.getParameter("format"));
				setUserTypeId(request.getParameter("usertype"));

				if (getSourceId() == 10 && getAdVersion() == 0 && getOperatingSystem().equalsIgnoreCase("android")) {
					obj = new AdvertisementData(1, "external",
							"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/2/banner19.jpg",
							"web", "https://play.google.com/store/apps/details?id=com.spice.mziiki", 0, "-", "-");
				} else if (getCountryId() == 224 && getAdVersion() == 2 && getUserTypeId() != 3
						&& getOperatingSystem().equalsIgnoreCase("android")) {
					obj = new AdvertisementData(3, "internal",
							"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
									+ getOperatingSystemId() + "/subscription.png",
							"subscription", "-", 0, "-", "-");
				} else if (getUserId() == 0 && getCountryId() != 224) {
					switch (randInt(1, 5)) {
					case 1:
						obj = new AdvertisementData(3, "internal",
								"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
										+ getOperatingSystemId() + "/banner17.jpg",
								"login", "-", 0, "-", "-");
						break;
					case 2:
						obj = new AdvertisementData(3, "internal",
								"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
										+ getOperatingSystemId() + "/banner17.jpg",
								"login", "-", 0, "-", "-");
						break;
					case 3:
						obj = new AdvertisementData(3, "internal",
								"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
										+ getOperatingSystemId() + "/banner17.jpg",
								"login", "-", 0, "-", "-");
						break;
					case 4:
						obj = new AdvertisementData(3, "internal",
								"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
										+ getOperatingSystemId() + "/banner17.jpg",
								"login", "-", 0, "-", "-");
						break;
					case 5:
						obj = new AdvertisementData(3, "internal",
								"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
										+ getOperatingSystemId() + "/banner17.jpg",
								"login", "-", 0, "-", "-");
						break;
					default:
						obj = new AdvertisementData(6, "internal",
								"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
										+ getOperatingSystemId() + "/banner17.jpg",
								"login", "-", 0, "-", "-");
						break;
					}
				} else {
					int ad_id = 0;
					String ad_type = "";
					String banner_url = "";
					String banner_type = "";
					String redirect_url = "";
					int resource_id = 0;
					String resource_title = "";
					String resource_img = "";
					try {
						MySQL mysql = new MySQL();
						ResultSet rs = null;
						rs = mysql.prepareCall("{call `GetAds`(" + getCountryId() + "," + getSourceId() + ","
								+ getUserId() + ",'" + getOperatingSystem() + "','" + getPageName() + "')}");
						if (rs != null) {
							while (rs.next()) {
								ad_id = rs.getInt("ad_id");
								ad_type = rs.getString("ad_type");
								banner_url = rs.getString("banner_url");
								banner_type = rs.getString("banner_type");
								redirect_url = rs.getString("redirect_url");
								resource_id = rs.getInt("resource_id");
								resource_title = rs.getString("resource_title");
								resource_img = rs.getString("resource_img");
							}
						}
						mysql.close();
					} catch (Exception e) {
						System.out.println("Exception in Mziiki Advertisement Try1 - " + e.getMessage());
					}
					obj = new AdvertisementData(ad_id, ad_type, banner_url, banner_type, redirect_url, resource_id,
							resource_title, resource_img);
				}
				// obj = null;
			} catch (Exception e) {
				obj = new AdvertisementData(6, "internal",
						"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AdsBanner/"
								+ getOperatingSystemId() + "/banner17.jpg",
						"login", "-", 0, "-", "-");
			}
			if (responseFormat.equalsIgnoreCase("json")) {
				response.setContentType("application/json");
				out.print("{\"root\":" + toJson(obj) + "}");
			} else {
				response.setContentType("xml;charset=UTF-8");
				out.print(toXml(obj));
			}

		} catch (Exception e) {
			System.out.println(
					"Exception in Mziiki(Banner) Advertisement.processRequest(HttpServletRequest request, HttpServletResponse response) - "
							+ e.getMessage());
		}
	}

	public int randInt(int min, int max) {

		// NOTE: Usually this should be a field rather than a method
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
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
			System.out.println("Exception in Mziiki Advertisement.toXml(Object object) - " + e.getMessage());
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
			System.out.println("Exception in Mziiki Advertisement.toJson(Object object) - " + e.getMessage());
		}
		return null;
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
	// + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request
	 *            servlet request
	 * @param response
	 *            servlet response
	 * @throws ServletException
	 *             if a servlet-specific error occurs
	 * @throws IOException
	 *             if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
