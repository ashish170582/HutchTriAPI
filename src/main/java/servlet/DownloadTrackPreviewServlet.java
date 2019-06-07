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
import java.sql.ResultSet;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.MySQL;

/**
 *
 * @author Rajat.kumar
 */
public class DownloadTrackPreviewServlet extends HttpServlet {

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
			byte[] valueDecoded = Base64.getDecoder().decode(request.getParameter("p"));
			String query = new String(valueDecoded);
			String[] params = query.split("&");
			Map<String, String> map = new HashMap<>();
			for (String param : params) {
				String[] p = param.split("=");
				String name = p[0];
				if (p.length > 1) {
					String value = p[1];
					map.put(name, value);
				}
			}
			String TrackTitle = "", TrackFile = "";
			try {
				MySQL mysql = new MySQL();

				ResultSet rs = mysql.prepareCall("{CALL `GetTrackInfo`(" + (String) map.get("ocid") + ",'"
						+ (String) map.get("trackid") + "',5,126)}");
				if (rs != null) {
					while (rs.next()) {
						TrackTitle = rs.getString("resource_title");
						TrackFile = rs.getString("filePath");
						TrackFile = TrackFile.replaceAll("005_0.mp3", "200_0.mp3");
						System.out.println(TrackFile);
						/// var/www/html/ams/Content/060/0000/192/07/060000019207005_0.mp3;
					}
				}
				mysql.close();
			} catch (Exception e) {
				System.out.println(
						"Exception in Mziiki DownloadTrackPreviewServlet.processRequest(HttpServletRequest request, HttpServletResponse response) Internal - "
								+ e.getMessage());
			}

			fileDownloading(request, response, TrackTitle, TrackFile, map);
		} catch (Exception e) {
			System.out.println(
					"Exception in Mziiki DownloadTrackPreviewServlet.processRequest(HttpServletRequest request, HttpServletResponse response) External - "
							+ e.getMessage());
		}
	}

	public int fileDownloading(HttpServletRequest request, HttpServletResponse response, String fileName,
			String downloadFile, Map<String, String> paramMap) throws IOException {
		ServletOutputStream stream = null;
		BufferedInputStream buf = null;
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
			// Successful Preview
			try {
				MySQL mysql = new MySQL();
				mysql.prepareCall("{CALL `PreviewDownloadLog`(1,'" + paramMap.get("ocid") + "','"
						+ paramMap.get("userid") + "','" + paramMap.get("trackid") + "','" + paramMap.get("src") + "','"
						+ paramMap.get("os") + "','" + paramMap.get("osv") + "','" + paramMap.get("model") + "','"
						+ paramMap.get("devid") + "','" + paramMap.get("devpin") + "')}");
				mysql.close();
			} catch (Exception e) {
				System.out.println("Exception in Mziiki DownloadTrackPreviewServlet.CALL `PreviewDownloadLog-1`");
			}
		} catch (Exception e) {
			// throw new ServletException(ioe.getMessage());
			// Unsuccessful Preview Download
			try {
				MySQL mysql = new MySQL();
				mysql.prepareCall("{CALL `PreviewDownloadLog`(2,'" + paramMap.get("ocid") + "','"
						+ paramMap.get("userid") + "','" + paramMap.get("trackid") + "','" + paramMap.get("src") + "','"
						+ paramMap.get("os") + "','" + paramMap.get("osv") + "','" + paramMap.get("model") + "','"
						+ paramMap.get("devid") + "','" + paramMap.get("devpin") + "')}");
				mysql.close();
			} catch (Exception ex) {
				System.out.println("Exception in Mziiki DownloadTrackPreviewServlet.CALL `PreviewDownloadLog-2`");
			}
			System.out.println(
					"Exception in Mziiki DownloadTrackPreviewServlet.fileDownloading(HttpServletRequest request, HttpServletResponse response, String fileName, String downloadFile) - "
							+ e.getMessage());
		} finally {
			if (stream != null) {
				stream.close();
			}
			if (buf != null) {
				buf.close();
			}
			return 0;
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

}
