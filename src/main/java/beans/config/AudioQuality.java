/*    */ package beans.config;

/*    */
/*    */ public class AudioQuality
/*    */ {
	/*    */ private String abr;
	/*    */ private int techReferenceId;
	/*    */ private String codec;
	/*    */ private String codecType;
	/*    */ private int bitRate;
	/*    */ private int noOfChannels;
	/*    */ private int samplingRate;
	/*    */ private String fileExtention;

	/*    */
	/*    */ public AudioQuality(String abr, int techReferenceId, String codec, String codecType, int bitRate,
			int noOfChannels, int samplingRate, String fileExtention)
	/*    */ {
		/* 21 */ this.abr = abr;
		/* 22 */ this.techReferenceId = techReferenceId;
		/* 23 */ this.codec = codec;
		/* 24 */ this.codecType = codecType;
		/* 25 */ this.bitRate = bitRate;
		/* 26 */ this.noOfChannels = noOfChannels;
		/* 27 */ this.samplingRate = samplingRate;
		/* 28 */ this.fileExtention = fileExtention;
		/*    */ }

	/*    */
	/*    */ public String getAbr() {
		/* 32 */ return this.abr;
		/*    */ }

	/*    */
	/*    */ public void setAbr(String abr) {
		/* 36 */ this.abr = abr;
		/*    */ }

	/*    */
	/*    */ public int getTechReferenceId() {
		/* 40 */ return this.techReferenceId;
		/*    */ }

	/*    */
	/*    */ public void setTechReferenceId(int techReferenceId) {
		/* 44 */ this.techReferenceId = techReferenceId;
		/*    */ }

	/*    */
	/*    */ public String getCodec() {
		/* 48 */ return this.codec;
		/*    */ }

	/*    */
	/*    */ public void setCodec(String codec) {
		/* 52 */ this.codec = codec;
		/*    */ }

	/*    */
	/*    */ public String getCodecType() {
		/* 56 */ return this.codecType;
		/*    */ }

	/*    */
	/*    */ public void setCodecType(String codecType) {
		/* 60 */ this.codecType = codecType;
		/*    */ }

	/*    */
	/*    */ public int getBitRate() {
		/* 64 */ return this.bitRate;
		/*    */ }

	/*    */
	/*    */ public void setBitRate(int bitRate) {
		/* 68 */ this.bitRate = bitRate;
		/*    */ }

	/*    */
	/*    */ public int getNoOfChannels() {
		/* 72 */ return this.noOfChannels;
		/*    */ }

	/*    */
	/*    */ public void setNoOfChannels(int noOfChannels) {
		/* 76 */ this.noOfChannels = noOfChannels;
		/*    */ }

	/*    */
	/*    */ public int getSamplingRate() {
		/* 80 */ return this.samplingRate;
		/*    */ }

	/*    */
	/*    */ public void setSamplingRate(int samplingRate) {
		/* 84 */ this.samplingRate = samplingRate;
		/*    */ }

	/*    */
	/*    */ public String getFileExtention() {
		/* 88 */ return this.fileExtention;
		/*    */ }

	/*    */
	/*    */ public void setFileExtention(String fileExtention) {
		/* 92 */ this.fileExtention = fileExtention;
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * beans.config.AudioQuality JD-Core Version: 0.6.0
 */