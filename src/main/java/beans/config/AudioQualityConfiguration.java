/*    */ package beans.config;

/*    */
/*    */ public class AudioQualityConfiguration
/*    */ {
	/*    */ private String ssDomainName;
	/*    */ private AudioQuality auto;
	/*    */ private AudioQuality high;
	/*    */ private AudioQuality medium;
	/*    */ private AudioQuality low;

	/*    */
	/*    */ public AudioQualityConfiguration(AudioQuality auto, AudioQuality high, AudioQuality medium,
			AudioQuality low)
	/*    */ {
		/* 17 */ this.auto = auto;
		/* 18 */ this.high = high;
		/* 19 */ this.medium = medium;
		/* 20 */ this.low = low;
		/*    */ }

	/*    */
	/*    */ public String getSsDomainName() {
		/* 24 */ return this.ssDomainName;
		/*    */ }

	/*    */
	/*    */ public void setSsDomainName(String ssDomainName) {
		/* 28 */ this.ssDomainName = ssDomainName;
		/*    */ }

	/*    */
	/*    */ public AudioQuality getAuto() {
		/* 32 */ return this.auto;
		/*    */ }

	/*    */
	/*    */ public void setAuto(AudioQuality auto) {
		/* 36 */ this.auto = auto;
		/*    */ }

	/*    */
	/*    */ public AudioQuality getHigh() {
		/* 40 */ return this.high;
		/*    */ }

	/*    */
	/*    */ public void setHigh(AudioQuality high) {
		/* 44 */ this.high = high;
		/*    */ }

	/*    */
	/*    */ public AudioQuality getMedium() {
		/* 48 */ return this.medium;
		/*    */ }

	/*    */
	/*    */ public void setMedium(AudioQuality medium) {
		/* 52 */ this.medium = medium;
		/*    */ }

	/*    */
	/*    */ public AudioQuality getLow() {
		/* 56 */ return this.low;
		/*    */ }

	/*    */
	/*    */ public void setLow(AudioQuality low) {
		/* 60 */ this.low = low;
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * beans.config.AudioQualityConfiguration JD-Core Version: 0.6.0
 */