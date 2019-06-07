package com.beans;

import javax.xml.bind.annotation.XmlType;

import com.app.beans.Images;

@XmlType(name = "", propOrder = { "id", "rid", "name", "type", "desc", "image" })

public class HomeItemData {

	private int id;
	private int rid;
	private String name;
	private String type;
	private String desc;
	private String image;
	private Images images;

	public HomeItemData() {
	}

	public HomeItemData(int id, int rid, String name, String type, String desc, String image) {
		this.id = id;
		this.rid = rid;
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.image = image;
		this.images = new Images(image);

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Images getImages() {
		return images;
	}

	public void setImages(Images images) {
		this.images = images;
	}

}
