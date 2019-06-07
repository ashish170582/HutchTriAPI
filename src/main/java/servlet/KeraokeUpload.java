package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.RandomStringUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.beans.Root;
import com.beans.karaokeData;
import com.database.MySQL;

/**
 * Servlet implementation class KeraokeUpload
 */
// @WebServlet("/KeraokeUpload")
public class KeraokeUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	private final String UPLOAD_Solo_DIRECTORY = "/var/www/html/ams/Content/HutchTri/karaoke/solo";
	private final String UPLOAD_collab_DIRECTORY = "/var/www/html/ams/Content/HutchTri/karaoke/collab";
	private final String UPLOAD_TEMP_DIRECTORY = "/var/www/html/ams/Content/HutchTri/karaoke/temp";

	public KeraokeUpload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String karaokeId = request.getParameter("karaokeId");
		String resourceCode = request.getParameter("resourceCode");
		Long userId = Long.valueOf(request.getParameter("userId"));
		String score = request.getParameter("score");
		String recordingMode = request.getParameter("recordingMode");
		String singerType = request.getParameter("singerType");
		String recordType = request.getParameter("recordType");
		Boolean isJoin = Boolean.valueOf(request.getParameter("isJoin"));
		String timstamp = request.getParameter("timestamp");

		Boolean isSuccessUploded = false;

		// process only if its multipart content
		PrintWriter out = response.getWriter();
		String UPLOAD_DIRECTORY = "";
		karaokeData karObj = null;

		if (recordingMode.equalsIgnoreCase("solo"))
			UPLOAD_DIRECTORY = UPLOAD_Solo_DIRECTORY + "/" + resourceCode + "/";
		else
			UPLOAD_DIRECTORY = UPLOAD_collab_DIRECTORY + "/" + resourceCode + "/";
		// This is For Upload Collabs file with Join Upload
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				createDirector(UPLOAD_DIRECTORY);
				createDirector(UPLOAD_TEMP_DIRECTORY);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						String ext = getExt(recordType);
						do {
							name = generateUniqueFileName(resourceCode, userId) + "." + ext;
						} while (new File(UPLOAD_DIRECTORY + File.separator + name).exists());
						String filePath = UPLOAD_DIRECTORY + File.separator + name;

						// System.out.println("File Path--"+filePath);
						// Save Karaoke Information into Database
						MySQL sql = new MySQL();
						ResultSet rs = callProcedure(1, karaokeId, resourceCode, userId, score, recordingMode,
								singerType, recordType, isJoin, filePath, timstamp, sql);
						while (rs.next()) {
							karObj = new karaokeData(rs.getInt("karaokeId"), rs.getString("resourceCode"),
									rs.getString("filePath"));
							isSuccessUploded = rs.getBoolean("isSuccessfullyUploaded");
						}
						if (isSuccessUploded == false) {
							File file;
							if (!recordingMode.equalsIgnoreCase("solo") && isJoin == true) {
								file = new File(UPLOAD_TEMP_DIRECTORY + File.separator + name);
							} else {
								file = new File(UPLOAD_DIRECTORY + File.separator + name);
							}
							item.write(file);
							// System.out.println(recordingMode+"------------"+isJoin);
							if (!recordingMode.equalsIgnoreCase("solo") && isJoin == true) {
								karaokeData karObj1 = null;
								rs = callProcedure(3, karaokeId, resourceCode, userId, score, recordingMode, singerType,
										recordType, isJoin, filePath, timstamp, sql);
								while (rs.next()) {
									karObj1 = new karaokeData(rs.getInt("karaokeId"), rs.getString("resourceCode"),
											rs.getString("filePath"));
								}
								final String OUT_DIRECTORY = filePath;
								final String IN_USER_DIRECTORY1 = karObj1.getUrl() + "";
								final String IN_USER_DIRECTORY2 = file + "";
								/*
								 * System.out.println("OUT_DIRECTORY---"+OUT_DIRECTORY);
								 * System.out.println("IN_USER_DIRECTORY1---"+IN_USER_DIRECTORY1);
								 * System.out.println("IN_USER_DIRECTORY2---"+IN_USER_DIRECTORY2);
								 */
								new Thread() {
									public void run() {
										String[] cmd = { "D:\\Softwares\\Java Software\\ffmpeg\\bin\\ffmpeg", "-i",
												IN_USER_DIRECTORY1, "-i", IN_USER_DIRECTORY2, "-filter_complex",
												"[0:v]pad=iw*2:ih[int];[int][1:v]overlay=W/2:0[vid] ", "-map", "[vid]",
												"-c:v", "libx264", "-crf", "23", "-preset", "veryfast",
												"-filter_complex",
												"amix=inputs=2:duration=longest:dropout_transition=2", OUT_DIRECTORY };
										try {
											Runtime.getRuntime().exec(cmd);
											try {
												System.out.println("Delete File Response---"
														+ new File(IN_USER_DIRECTORY2).delete());

											} catch (Exception e) {
												System.out.println("Exception is Here---" + e);
											}
											// Delete file from Temp Folder After Marge
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}.start();
							}
							rs = callProcedure(2, karaokeId, resourceCode, userId, score, recordingMode, singerType,
									recordType, isJoin, filePath, timstamp, sql);
							while (rs.next())
								isSuccessUploded = rs.getBoolean("isSuccessfullyUploaded");
						}
						sql.close();

					}
				}
			} catch (Exception ex) {
				System.out.println("Exception is here-------" + ex);
			}
		}
		Object obj = null;
		if (karObj == null || isSuccessUploded == false)
			obj = new Root(1, "failure");
		else
			obj = (Root) karObj;

		response.setContentType("application/json");
		System.out.println(obj.toString());
		out.print("\"root\":" + toJson(obj) + "");
		out.flush();
		out.close();
	}

	@SuppressWarnings("finally")
	public ResultSet callProcedure(int flag, String karaokeId, String resourceCode, Long userId, String score,
			String recordingMode, String singerType, String recordType, Boolean isJoin, String filePath,
			String timstamp, MySQL sql) {

		ResultSet rs = null;
		try {
			rs = sql.prepareCall("call UserKaraoke(" + flag + ",'" + karaokeId + "','" + resourceCode + "','" + userId
					+ "','" + score + "','" + recordingMode + "','" + singerType + "','" + recordType + "'," + isJoin
					+ ",'" + filePath + "','" + timstamp + "')");
		} catch (Exception e) {
			System.out.println("Exception in calling UserKaraoke---");
		} finally {
			return rs;
		}
	}

	public String getExt(String fileType) {
		if (fileType.equalsIgnoreCase("audio"))
			return "mp3";
		else
			return "mp4";

	}

	public void createDirector(String path) {
		File file = new File(path);
		if (!file.isDirectory())
			file.mkdirs();
	}

	String generateUniqueFileName(String resourceCode, Long UserID) {
		String filename = "";
		long millis = System.currentTimeMillis();
		String datetime = new Date().toGMTString();
		datetime = datetime.replace(" ", "");
		datetime = datetime.replace(":", "");
		String rndchars = RandomStringUtils.randomAlphanumeric(10);
		// filename = rndchars + "_" + datetime + "_" + millis+ "_" +resourceCode + "_"
		// +UserID ;
		filename = (rndchars).replaceAll("\\/", "").replaceAll("\\\\", "") + "_" + millis + "_" + resourceCode + "_"
				+ UserID;
		return filename;
	}

	public String toJson(Object object) {
		String result = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			result = mapper.writeValueAsString(object);
			System.out.println("Result---------" + result);
			return result;
		} catch (Exception e) {
			System.out.println("Exception in KeraokeUpload.toJson(Object object) - " + e.getMessage());
		}
		return null;
	}

}
