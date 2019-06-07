package com.beans;

import com.app.beans.Images;
import com.database.MySQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = { "radioId", "radioName", "radioDescription", "radioImageUrl" })

public class RadioBean {

	private int radioId;
	private String radioName;
	private String radioDescription;
	private String radioImageUrl;
	private int trackCount;
	private Images images;

	public RadioBean() {
	}

	public RadioBean(int radioId, String radioName, String radioDescription, String radioImageUrl, int trackCount) {
		this.radioId = radioId;
		this.radioName = radioName;
		this.radioDescription = radioDescription;
		this.radioImageUrl = radioImageUrl;
		this.trackCount = trackCount;
		this.images = new Images(radioImageUrl);
	}

	public int getRadioId() {
		return radioId;
	}

	public void setRadioId(int radioId) {
		this.radioId = radioId;
	}

	public String getRadioName() {
		return radioName;
	}

	public void setRadioName(String radioName) {
		this.radioName = radioName;
	}

	public String getRadioDescription() {
		return radioDescription;
	}

	public void setRadioDescription(String radioDescription) {
		this.radioDescription = radioDescription;
	}

	public String getRadioImageUrl() {
		return radioImageUrl;
	}

	public void setRadioImageUrl(String radioImageUrl) {
		this.radioImageUrl = radioImageUrl;
	}

	public int getTrackCount() {
		return trackCount;
	}

	public void setTrackCount(int trackCount) {
		this.trackCount = trackCount;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

	@SuppressWarnings("finally")
	public static List<com.beans.RadioBean> getTopRadio(int countryId, int imageTechRefId) {
		List<com.beans.RadioBean> lst = new ArrayList<com.beans.RadioBean> ();
		try {
			MySQL mysql = new MySQL();
			ResultSet rs = mysql.prepareCall("{CALL `Mziiki_Content`.`GetRadioMetaData`(1," + countryId
					+ ",1,0,'NA','NA','NA','NA','NA',0,0," + imageTechRefId + ",0,20)}");
			while (rs.next()) {
				lst.add(new RadioBean(rs.getInt("radio_id"), rs.getString("radio_name"), rs.getString("radio_desc"),
						rs.getString("image_url"), rs.getInt("track_count")));
			}
			mysql.close();
		} catch (Exception e) {
			System.out.println(
					"Exception in Mziiki Genre.getTopRadio(int countryId, int imageTechRefId) - " + e.getMessage());
		} finally {
			return lst;
		}
	}

}
