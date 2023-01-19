package lmp.util;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncoder {
	
	/**
	 * 비밀번호 암호화 인코더
	 */
	private String iv;
	private Key keySpec;

	/**
	 * 16자리의 키값을 설정
	 * 
	 * @param key 암/복호화를 위한 키값         
	 * @throws UnsupportedEncodingException 키값의 길이가 16이하일 경우 발생
	 */
	final static String key = "QIsB7m5xYr8aJB08";

	public PasswordEncoder() throws UnsupportedEncodingException {
		this.iv = key.substring(0, 16);
		byte[] keyBytes = new byte[16];
		byte[] arrByteKey = key.getBytes("UTF-8");
		int len = arrByteKey.length;
		if (len > keyBytes.length) {
			len = keyBytes.length;
		}
		System.arraycopy(arrByteKey, 0, keyBytes, 0, len);
		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

		this.keySpec = keySpec;
	}

	/**
	 * password 암호화
	 * 
	 * @param password 암호화할 문자열   
	 * @return encryptedPassWord 암호화된 문자열
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String password) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {
		Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
		c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] encrypted = c.doFinal(password.getBytes("UTF-8"));
		String encryptedPassWord = Base64.getEncoder().encodeToString(encrypted);
		return encryptedPassWord;
	}

	/**
	 * password 복호화
	 * 
	 * @param encryptedPassWord 복호화할 문자열    
	 * @return decryptPassword 복호화된 문자열
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String decrypt(String encryptedPassWord) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
		byte[] byteStr = Base64.getDecoder().decode(encryptedPassWord);
		String decryptPassword = new String(cipher.doFinal(byteStr), "UTF-8");
		return decryptPassword;
	}
	
	/**
	 * 문자열과 암호화된 문자열을 비교 하여 true / false 반환
	 * 
	 * @param password
	 * @param encryptedPassword
	 * @return boolean
	 * @throws NoSuchAlgorithmException
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public boolean matches(String password, String encryptedPassword) throws NoSuchAlgorithmException,
			GeneralSecurityException, UnsupportedEncodingException{
		return encrypt(password).equals(encryptedPassword);
	}

}
