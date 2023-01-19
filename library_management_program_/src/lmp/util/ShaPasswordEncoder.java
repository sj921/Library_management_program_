package lmp.util;

import java.security.MessageDigest;

public class ShaPasswordEncoder  {

	/**
	 * 비밀번호 암호화 인코더
	 */
	
	/**
	 * SHA-256 암호화 
	 * 
	 * @param txt
	 * @return encryptPassword
	 * @throws Exception
	 */
	public String encrypt(String txt) throws Exception{

		StringBuffer encryptPassword = new StringBuffer();

		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		mDigest.update(txt.getBytes());

		byte[] msgStr = mDigest.digest() ;

		for (byte b : msgStr) {
			encryptPassword.append(String.format("%02x",b));
		}

		return encryptPassword.toString();
	}

	
	/**
	 * 암호화 비밀번호 매칭 
	 * 
	 * @param pw
	 * @param encryptPassword
	 * @return boolean
	 * @throws Exception
	 */
	public boolean matches(String pw, String encryptPassword ) throws Exception {
		
		
		if (encryptPassword.equals(encrypt(pw))) {
            return true;
        } else {
            return false;
        }

	}

	

}

