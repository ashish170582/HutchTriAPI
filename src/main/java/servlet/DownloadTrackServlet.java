/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.beans.Root;
import com.database.MySQL;

import beans.config.AESEncriptionDecription;

/**
 *
 * @author Rajat.kumar
 */
public class DownloadTrackServlet extends HttpServlet {

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
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			byte[] valueDecodedBase64 = Base64.getDecoder().decode(request.getParameter("p"));
			// byte[] valueDecoded = Base64.getDecoder().decode(request.getParameter("p"));
			byte[] valueDecoded = new AESEncriptionDecription().decrypt(new String(valueDecodedBase64));
			String query = new String(valueDecoded);
			String[] params = query.split("&");
			Map<String, String> map = new HashMap<>();
			for (String param : params) {
				String[] p = param.split("=");
				String name = p[0];
				if (p.length > 1) {
					String value = p[1];
					map.put(name, value);
					/*
					 * System.out.println("Name-"+name); System.out.println("Value-"+value);
					 */
				}
			}
			String TrackTitle = "", TrackFile = "";
			int code = 0;
			Locale locale = request.getLocale();
			ResourceBundle bundle = ResourceBundle.getBundle("message_" + (String) map.get("lang"), locale);
			;
			try {
				MySQL mysql = new MySQL();
				int techRefID;
				String quality = map.get("dwldquality");
				if (quality == null) {
					quality = "1";
				}
				switch (quality) {
				case "0":
					techRefID = 57; // 96 kbps
					break;
				case "1":
					techRefID = 5; // 128 kpus
					break;
				case "2":
					techRefID = 56; // 64kbps
					break;
				default:
					techRefID = 5;

				}
				ResultSet rs = mysql.prepareCall("{CALL `GetTrackInfo`(" + (String) map.get("ocid") + ",'"
						+ (String) map.get("trackid") + "', " + techRefID + "  ,126)}");
				if (rs != null) {
					while (rs.next()) {
						TrackTitle = rs.getString("resource_title");
						TrackFile = rs.getString("filePath");
					}
				}
				rs = mysql.prepareCall("{CALL `UserOfflineValidate`(1," + (String) map.get("ocid") + ",'"
						+ (String) map.get("os") + "','" + (String) map.get("devid") + "','"
						+ (String) map.get("userid") + "','" + (String) map.get("trackid") + "', " + techRefID
						+ "  ,126,'" + (String) map.get("token") + "')}");

				if (rs != null) {
					while (rs.next()) {
						code = rs.getInt("code");
					}
				}

				System.out.println("REsponse code--" + code);
				mysql.close();
			} catch (Exception e) {
				System.out.println(
						"Exception in Mziiki DownloadTrackServlet.processRequest(HttpServletRequest request, HttpServletResponse response) Internal - "
								+ e.getMessage());
			}
			// Check URL Time Validation
			int downloadaresponse = 0;
			if (code == 1)
				downloadaresponse = fileDownloading(request, response, TrackTitle, TrackFile);
			if (downloadaresponse == 1) {
				MySQL mysql = new MySQL();
				mysql.prepareCall("{CALL `UserOfflineValidate`(2," + (String) map.get("ocid") + ",'"
						+ (String) map.get("os") + "','" + (String) map.get("devid") + "','"
						+ (String) map.get("userid") + "','" + (String) map.get("trackid") + "', " + 64 + "  ,126,'"
						+ (String) map.get("token") + "')}");
				mysql.close();
			}

			else {
				Root obj = new Root(code, bundle.getString(code + ""));
				PrintWriter out = response.getWriter();
				response.setContentType("application/json");
				out.print("{\"root\":" + toJson(obj) + "}");
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			System.out.println(
					"Exception in Mziiki DownloadTrackServlet.processRequest(HttpServletRequest request, HttpServletResponse response) External - "
							+ e.getMessage());
		}
	}

	public int fileDownloading(HttpServletRequest request, HttpServletResponse response, String fileName,
			String downloadFile) throws IOException {
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
		int rescode = 0;
		try {
			stream = response.getOutputStream();
			File mp3 = new File(downloadFile);

			// set response headers
			response.setContentType("audio/mpeg");

			response.addHeader("Content-Disposition", "attachment; filename=" + fileName.trim() + ".mp3");

			response.setContentLength((int) mp3.length());

			FileInputStream input_1 = new FileInputStream(mp3);
			buf = new BufferedInputStream(input_1);
			int readBytes = 0;
			// read from the file; write to the ServletOutputStream
			while ((readBytes = buf.read()) != -1) {
				stream.write(readBytes);
			}
			rescode = 1;
		} catch (Exception e) {
			rescode = 0;
			// throw new ServletException(ioe.getMessage());
			e.printStackTrace();
			System.out.println(
					"Exception in Mziiki DownloadTrackServlet.fileDownloading(HttpServletRequest request, HttpServletResponse response, String fileName, String downloadFile) - "
							+ e.getMessage());
		} finally {
			if (stream != null) {
				stream.close();
			}
			if (buf != null) {
				buf.close();
			}
			return rescode;
		}

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
		try {
			processRequest(request, response);
		} catch (Exception e) {

		}
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
		try {
			processRequest(request, response);
		} catch (Exception e) {

		}
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

	public String toJson(Object object) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(object);
			return result;
		} catch (Exception e) {
			System.out.println("Exception in Glo Nigeria MainServlet.toJson(Object object) - " + e.getMessage());
		}
		return null;
	}
}
