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
import java.io.StringWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.beans.AdvertisementData;
import com.beans.AudioAdvertisementData;
import com.database.MySQL;

/**
 *
 * @author Rajat.kumar
 */
public class AudioAdvertisement extends HttpServlet {

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

		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			Object obj = null;
			String country = request.getParameter("country");
			String source = request.getParameter("source");
			String os = request.getParameter("os");
			String userTypeId = request.getParameter("usertype");
			String responseFormat = request.getParameter("format");
			String fileName = "/AMS-Content-Live/Content/Mziiki/AudioAds/MzikiAudioAd.mp3";

			if (os.trim().equalsIgnoreCase("blackberry")) {
				out.println("http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AudioAds/MzikiAudioAd.mp3");
				fileName = "/AMS-Content-Live/Content/Mziiki/AudioAds/MzikiAudioAd.mp3";
			} else {
				response.sendRedirect(
						"http://s3-eu-west-1.amazonaws.com/svacontent/Content/Mziiki/AudioAds/MzikiAudioAd.mp3");
			}
			// String fileName = "C:/Users/Rajat.kumar/Desktop/MzikiAudioAd.m4a";
			if (fileName == null || fileName.equals("")) {
				throw new ServletException("Invalid or non-existent file parameter in SendMp3 servlet.");
			}

			ServletOutputStream stream = null;
			BufferedInputStream buf = null;
			try {

				stream = response.getOutputStream();
				File audioFile = new File(fileName);
				// set response headers
				response.setContentType("audio/mpeg");
				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
				response.setContentLength((int) audioFile.length());
				FileInputStream input = new FileInputStream(audioFile);
				buf = new BufferedInputStream(input);
				int readBytes = 0;
				// read from the file; write to the ServletOutputStream
				while ((readBytes = buf.read()) != -1) {
					stream.write(readBytes);
				}
			} catch (IOException ioe) {
				throw new ServletException(ioe.getMessage());
			} finally {
				if (stream != null) {
					stream.close();
				}
				if (buf != null) {
					buf.close();
				}
			}
		} catch (Exception e) {
			System.out.println(
					"Exception in Mziiki AudioAdvertisement.processRequest(HttpServletRequest request, HttpServletResponse response) - "
							+ e.getMessage());
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
