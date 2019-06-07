package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.database.MySQL;

/**
 * Servlet implementation class Notification
 */
@WebServlet("/Notification")
public class NotificationUrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NotificationUrl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String notificationID = "";
		String devPin = "";
		String lang = "";
		String type = "";
		String userId = "";
		request.getParameter("notificationID");
		PrintWriter out = response.getWriter();
		String writeResponse = "";
		if (request.getParameter("notificationID") != null)
			notificationID = request.getParameter("notificationID");
		if (request.getParameter("devPin") != null)
			devPin = request.getParameter("devPin");
		if (request.getParameter("lang") != null)
			lang = request.getParameter("lang");
		if (request.getParameter("type") != null)
			type = request.getParameter("type");
		if (request.getParameter("userId") != null)
			userId = request.getParameter("userId");
		if (!notificationID.equalsIgnoreCase("") && !devPin.equalsIgnoreCase("")) {
			System.out.println(userId);
			try {
				MySQL sql = new MySQL();
				if (type.equalsIgnoreCase("IN")) {
					sql.executeUpdate("INSERT INTO tblNotification "
							+ " (user_id,sender_name,notification_title,notification_title_myLang,notification_subtitle,notification_msg,notification_msg_myLang,notification_image,value,notification_type,image_code,country_id)"
							+ " SELECT " + userId
							+ ",sender_name,notification_title,notification_title_myLang,notification_subtitle,notification_msg,notification_msg_myLang,notification_image,value,notification_type,image_code,country_id FROM `tblNotification` WHERE notification_id='"
							+ notificationID + "'");
				} else {
					if (lang.equalsIgnoreCase("MY"))
						sql.executeUpdate("INSERT INTO tblPushNotification "
								+ " (notification_id,notification_message,notification_image,os,device_id,device_pin,STATUS,remarks,request_date_time)"
								+ " SELECT notification_id,notification_msg_myLang,'NA','android','','" + devPin
								+ "',0,'NA',NOW() FROM `tblNotification` WHERE notification_id='" + notificationID
								+ "'");
					else
						sql.executeUpdate("INSERT INTO tblPushNotification "
								+ " (notification_id,notification_message,notification_image,os,device_id,device_pin,STATUS,remarks,request_date_time)"
								+ " SELECT notification_id,notification_msg,'NA','android','','" + devPin
								+ "',0,'NA',NOW() FROM `tblNotification` WHERE notification_id='" + notificationID
								+ "'");
				}
				sql.close();
			} catch (Exception e) {
				System.out.println("Exception in send ing Notification---" + e);
			}
			writeResponse = "Notification send successfully...........";
		} else {
			writeResponse = "Parameters missing..........";
		}
		out.write(writeResponse);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
