/*    */ package beans.config;

/*    */
/*    */ import java.io.PrintStream;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.util.Arrays;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ import org.apache.commons.codec.binary.Base64;

/*    */
/*    */ public class AESEncriptionDecription
/*    */ {
	/*    */ public static SecretKeySpec secretKey;
	/*    */ public static byte[] key;
	/*    */ public static final String strPssword = "9953795551ABCDEF9871415551ABCDEF";

	/*    */
	/*    */ public static void setKey(String myKey)
	/*    */ {
		/* 24 */ MessageDigest sha = null;
		/*    */ try {
			/* 26 */ key = myKey.getBytes("UTF-8");
			/*    */
			/* 28 */ sha = MessageDigest.getInstance("SHA-1");
			/* 29 */ key = sha.digest(key);
			/* 30 */ key = Arrays.copyOf(key, 16);
			/*    */
			/* 33 */ secretKey = new SecretKeySpec(key, "AES");
			/*    */ }
		/*    */ catch (NoSuchAlgorithmException e)
		/*    */ {
			/* 37 */ e.printStackTrace();
			/*    */ }
		/*    */ catch (UnsupportedEncodingException e) {
			/* 40 */ e.printStackTrace();
			/*    */ }
		/*    */ }

	/*    */
	/*    */ public String encrypt(String strToEncrypt)
	/*    */ {
		/* 46 */ String encrpt = "";
		/*    */ try {
			/* 48 */ Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			/* 49 */ cipher.init(1, secretKey);
			/* 50 */ encrpt = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
			/*    */ } catch (Exception e) {
			/* 52 */ System.out.println("Error while encrypting: " + e.toString());
			/*    */ }
		/* 54 */ return encrpt;
		/*    */ }

	/*    */
	/*    */ public byte[] decrypt(String strToDecrypt) {
		/* 58 */ byte[] decript = null;
		/*    */ try {
			/* 60 */ Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			/* 61 */ cipher.init(2, secretKey);
			/* 62 */ decript = cipher.doFinal(Base64.decodeBase64(strToDecrypt.trim()));
			/*    */ } catch (Exception e) {
			/* 64 */ System.out.println("Error while decrypting: " + e.toString());
			/*    */ }
		/* 66 */ return decript;
		/*    */ }

	/*    */
	/*    */ public static void main(String[] args)
	/*    */ {
		/* 72 */ String strToEncrypt = "Hello";
		/*    */
		/* 76 */ String encr = new AESEncriptionDecription().encrypt("Hello".trim());
		/* 77 */ System.out.println(encr);
		/*    */
		/* 82 */ System.out.println("String to Decrept: " + new String(new AESEncriptionDecription().decrypt(encr)));
		/*    */ }

	/*    */
	/*    */ static
	/*    */ {
		/* 19 */ setKey("9953795551ABCDEF9871415551ABCDEF");
		/*    */ }
	/*    */ }

/*
 * Location: C:\Users\DigiSpice\Desktop\WEB-INF\classes\ Qualified Name:
 * beans.config.AESEncriptionDecription JD-Core Version: 0.6.0
 */