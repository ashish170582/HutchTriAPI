package com.beans;



import java.io.Serializable;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "", propOrder = {
    "karaokeId",
    "resourceId",
    "resourceCode",
    "url",
})
public class karaokeData  extends Root  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int karaokeId;
	private String resourceCode; 
	private String 	url;
public karaokeData() {
	super(0, "Success");
	// TODO Auto-generated constructor stub
}
public karaokeData(int karaokeId, String resourceCode,
		String url) {
	super(0, "Success");
	this.karaokeId = karaokeId;
	this.resourceCode = resourceCode;
	this.url = url;
}
public int getKaraokeId() {
	return karaokeId;
}
public void setKaraokeId(int karaokeId) {
	this.karaokeId = karaokeId;
}
public String getResourceCode() {
	return resourceCode;
}
public void setResourceCode(String resourceCode) {
	this.resourceCode = resourceCode;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public static long getSerialversionuid() {
	return serialVersionUID;
}
@Override
public String toString() {
	return "karaokeData [karaokeId=" + karaokeId 
			+ ", resourceCode=" + resourceCode + ", url=" + url + "]";
}

    

}
