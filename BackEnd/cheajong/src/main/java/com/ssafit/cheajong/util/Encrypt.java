package com.ssafit.cheajong.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

// 암호화를 위한 클래스
@Component
public class Encrypt {
	public String getEncrypt(String password) {

		String result = "";

		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			md.update(password.getBytes());
			byte[] pwd = md.digest();

			StringBuffer sb = new StringBuffer();
			for (byte b : pwd) {
				sb.append(String.format("%02x", b));
			}

			result = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;

	}
}
