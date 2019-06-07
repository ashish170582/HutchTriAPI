package com.beans;

public class OptScreenConfig {

	private String title;
	private String buttonText;
	private String infoText;
	private Boolean skipVisibility;
	private Boolean infoVisibility;
	private int infoType;
	private String infoUrl;
	private int packageId;
	private String imageUrl;
	private Boolean optScreenVisibility;

	public OptScreenConfig() {
	}

	public Boolean getOptScreenVisibility() {
		return optScreenVisibility;
	}

	public void setOptScreenVisibility(Boolean optScreenVisibility) {
		this.optScreenVisibility = optScreenVisibility;
	}

	public OptScreenConfig(String title, String buttonText, String infoText, Boolean skipVisibility,
			Boolean infoVisibility, int infoType, String infoUrl, int packageId, String imageUrl,
			Boolean optScreenVisibility) {
		super();
		this.title = title;
		this.buttonText = buttonText;
		this.infoText = infoText;
		this.skipVisibility = skipVisibility;
		this.infoVisibility = infoVisibility;
		this.infoType = infoType;
		this.infoUrl = infoUrl;
		this.packageId = packageId;
		this.imageUrl = imageUrl;
		this.optScreenVisibility = optScreenVisibility;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getButtonText() {
		return buttonText;
	}

	public void setButtonText(String buttonText) {
		this.buttonText = buttonText;
	}

	public String getInfoText() {
		return infoText;
	}

	public void setInfoText(String infoText) {
		this.infoText = infoText;
	}

	public Boolean getSkipVisibility() {
		return skipVisibility;
	}

	public void setSkipVisibility(Boolean skipVisibility) {
		this.skipVisibility = skipVisibility;
	}

	public Boolean getInfoVisibility() {
		return infoVisibility;
	}

	public void setInfoVisibility(Boolean infoVisibility) {
		this.infoVisibility = infoVisibility;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}

	public String getInfoUrl() {
		return infoUrl;
	}

	public void setInfoUrl(String infoUrl) {
		this.infoUrl = infoUrl;
	}

	public int getPackageId() {
		return packageId;
	}

	public void setPackageId(int packageId) {
		this.packageId = packageId;
	}

	@Override
	public String toString() {
		return "OptScreenConfig [title=" + title + ", buttonText=" + buttonText + ", infoText=" + infoText
				+ ", skipVisibility=" + skipVisibility + ", infoVisibility=" + infoVisibility + ", infoType=" + infoType
				+ ", infoUrl=" + infoUrl + ", packageId=" + packageId + ", imageUrl=" + imageUrl
				+ ", optScreenVisibility=" + optScreenVisibility + "]";
	}

}
