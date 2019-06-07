package com.beans;

import java.util.List;

public class LeftMenuTitles {
	private List<LeftMenuTitle> leftMenuButtons;

	public LeftMenuTitles() {
		// TODO Auto-generated constructor stub
	}

	public LeftMenuTitles(List<LeftMenuTitle> leftMenuButtons) {
		super();
		this.leftMenuButtons = leftMenuButtons;
	}

	public void setLeftMenuButtons(List<LeftMenuTitle> leftMenuButtons) {
		this.leftMenuButtons = leftMenuButtons;
	}

	public List<LeftMenuTitle> getLeftMenuButtons() {
		return leftMenuButtons;
	}

	@Override
	public String toString() {
		return "LeftMenuTitles [leftMenuButtons=" + leftMenuButtons + "]";
	}

}
