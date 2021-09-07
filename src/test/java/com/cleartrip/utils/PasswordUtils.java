package com.cleartrip.utils;

import java.util.Base64;

public class PasswordUtils {

	public static String encodeString(String string) {

		byte[] bString = Base64.getEncoder().encode(string.getBytes());
		return new String(bString);

	}

	public static String decodeString(String string) {

		byte[] bString = Base64.getDecoder().decode(string.getBytes());
		return new String(bString);

	}

}
