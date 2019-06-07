/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package beans.config;

public class AudioQuality {
	private String abr;
	private int techReferenceId;
	private String codec;
	private String codecType;
	private int bitRate;
	private int noOfChannels;
	private int samplingRate;
	private String fileExtention;

	public AudioQuality(String abr, int techReferenceId, String codec, String codecType, int bitRate, int noOfChannels,
			int samplingRate, String fileExtention) {
		this.abr = abr;
		this.techReferenceId = techReferenceId;
		this.codec = codec;
		this.codecType = codecType;
		this.bitRate = bitRate;
		this.noOfChannels = noOfChannels;
		this.samplingRate = samplingRate;
		this.fileExtention = fileExtention;
	}

	public String getAbr() {
		return abr;
	}

	public void setAbr(String abr) {
		this.abr = abr;
	}

	public int getTechReferenceId() {
		return techReferenceId;
	}

	public void setTechReferenceId(int techReferenceId) {
		this.techReferenceId = techReferenceId;
	}

	public String getCodec() {
		return codec;
	}

	public void setCodec(String codec) {
		this.codec = codec;
	}

	public String getCodecType() {
		return codecType;
	}

	public void setCodecType(String codecType) {
		this.codecType = codecType;
	}

	public int getBitRate() {
		return bitRate;
	}

	public void setBitRate(int bitRate) {
		this.bitRate = bitRate;
	}

	public int getNoOfChannels() {
		return noOfChannels;
	}

	public void setNoOfChannels(int noOfChannels) {
		this.noOfChannels = noOfChannels;
	}

	public int getSamplingRate() {
		return samplingRate;
	}

	public void setSamplingRate(int samplingRate) {
		this.samplingRate = samplingRate;
	}

	public String getFileExtention() {
		return fileExtention;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

}
