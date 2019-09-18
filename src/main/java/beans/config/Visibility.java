/*    */ package beans.config;

/*    */
/*    */ public class Visibility
/*    */ {
	/*    */ private VisibilityValue casual;
	/*    */ private VisibilityValue lite;
	/*    */ private VisibilityValue premium;

	/*    */
	/*    */ public Visibility(VisibilityValue casual, VisibilityValue lite, VisibilityValue premium)
	/*    */ {
		/* 19 */ this.casual = casual;
		/* 20 */ this.lite = lite;
		/* 21 */ this.premium = premium;
		/*    */ }

	/*    */
	/*    */ public VisibilityValue getCasual() {
		/* 25 */ return this.casual;
		/*    */ }

	/*    */
	/*    */ public void setCasual(VisibilityValue casual) {
		/* 29 */ this.casual = casual;
		/*    */ }

	/*    */
	/*    */ public VisibilityValue getLite() {
		/* 33 */ return this.lite;
		/*    */ }

	/*    */
	/*    */ public void setLite(VisibilityValue lite) {
		/* 37 */ this.lite = lite;
		/*    */ }

	/*    */
	/*    */ public VisibilityValue getPremium() {
		/* 41 */ return this.premium;
		/*    */ }

	/*    */
	/*    */ public void setPremium(VisibilityValue premium) {
		/* 45 */ this.premium = premium;
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * beans.config.Visibility JD-Core Version: 0.6.0
 */