package com.beans;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GenreList", propOrder = { "genre" })

@XmlRootElement(name = "root")
public class Genre extends Root {
	private int resultCount;
	private List<GenreBean> genre;

	public Genre() {
	}

	public Genre(List<GenreBean> genre) {
		super(0, "Success");
		this.resultCount = genre.size();
		this.genre = genre;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public List<GenreBean> getGenre() {
		return genre;
	}

	public void setGenre(List<GenreBean> genre) {
		this.genre = genre;
	}

}
