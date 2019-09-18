/*    */ package beans.config;

/*    */
/*    */ public class Configuration
/*    */ {
	/*    */ private ConfigurationValue casual;
	/*    */ private ConfigurationValue lite;
	/*    */ private ConfigurationValue premium;

	/*    */
	/*    */ public Configuration(ConfigurationValue casual, ConfigurationValue lite, ConfigurationValue premium)
	/*    */ {
		/* 19 */ this.casual = casual;
		/* 20 */ this.lite = lite;
		/* 21 */ this.premium = premium;
		/*    */ }

	/*    */
	/*    */ public ConfigurationValue getCasual() {
		/* 25 */ return this.casual;
		/*    */ }

	/*    */
	/*    */ public void setCasual(ConfigurationValue casual) {
		/* 29 */ this.casual = casual;
		/*    */ }

	/*    */
	/*    */ public ConfigurationValue getLite() {
		/* 33 */ return this.lite;
		/*    */ }

	/*    */
	/*    */ public void setLite(ConfigurationValue lite) {
		/* 37 */ this.lite = lite;
		/*    */ }

	/*    */
	/*    */ public ConfigurationValue getPremium() {
		/* 41 */ return this.premium;
		/*    */ }

	/*    */
	/*    */ public void setPremium(ConfigurationValue premium) {
		/* 45 */ this.premium = premium;
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * beans.config.Configuration JD-Core Version: 0.6.0
 */