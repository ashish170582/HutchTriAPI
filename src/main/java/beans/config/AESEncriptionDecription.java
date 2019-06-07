package beans.config;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Base64;

public class AESEncriptionDecription {

	public static SecretKeySpec secretKey;
	public static byte[] key;
	public final static String strPssword = "9953795551ABCDEF9871415551ABCDEF";

	static {
		//System.out.println("Here ----------For Static");
		setKey(strPssword);
	}

	public static void setKey(String myKey) {
		//System.out.println("Going to setKey");
		MessageDigest sha = null;
		try {
			key = myKey.getBytes("UTF-8");
		//	System.out.println(key.length);
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16); // use only first 128 bit
			// System.out.println(key.length);
			// System.out.println(new String(key,"UTF-8"));
			secretKey = new SecretKeySpec(key, "AES");

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("After to setKey");
	}

	public String encrypt(final String strToEncrypt) {
		String encrpt = "";
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			encrpt = Base64.encodeBase64String(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return encrpt;
	}

	public byte[] decrypt(String strToDecrypt) {
		byte decript[] = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			decript = cipher.doFinal(Base64.decodeBase64(strToDecrypt.trim()));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return decript;
	}

	public static void main(String[] args) {

		//final String strToEncrypt = "ocid=166&src=1&os=android&osv=7.1.0.318&model=9900&devid=553648138&devpin=553648138&evt=39&userid=27&trackid=7070017457&dtype=1&dwldquality=0&token=1521795780879&lang=en";
		final String strToEncrypt = "Hello";
		// final String strPssword = "9953795551ABCDEF9871415551ABCDEF";
		// AESEncriptionDecription.setKey(strPssword);

		String encr = new AESEncriptionDecription().encrypt(strToEncrypt.trim());
		System.out.println(encr);
		;

		// System.out.println("String to Encrypt: " + new
		// AESEncriptionDecription().encrypt(strToEncrypt));
		System.out.println("String to Decrept: " + new String(new AESEncriptionDecription().decrypt(encr)));
		// System.out.println("Encrypted: " +
		// AESEncriptionDecription.getEncryptedString());

		// final String strToDecrypt =
		// AESEncriptionDecription.getEncryptedString();
		// AESEncriptionDecription.decrypt(strToDecrypt.trim());

		// System.out.println("String To Decrypt : " + strToDecrypt);
		// System.out.println("Decrypted : " +
		// AESEncriptionDecription.getDecryptedString());

	}

}
