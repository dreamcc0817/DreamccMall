package com.dreamcc.mall.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Title: DreamccMall
 * @Package: com.dreamcc.mall.util
 * @Description:
 * @Author: dreamcc
 * @Date: 2018/8/11
 * @Version: V1.0
 */
public class MD5Utils {

	public static String encrypt(String source) {
		return encodeMd5(source.getBytes());
	}

	private static String encodeMd5(byte[] source) {
		try {
			return encodeHex(MessageDigest.getInstance("MD5").digest(source));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e.getMessage(), e);
		}
	}

	private static String encodeHex(byte[] bytes) {
		StringBuffer buffer = new StringBuffer(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10)
				buffer.append("0");
			buffer.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		return buffer.toString();
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	/**
	 * return upper case MD5
	 *
	 * @param origin
	 * @param charsetname
	 * @return
	 */
	private static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString.toUpperCase();
	}

	public static String MD5EncodeUtf8ByYaml(String origin) {
		origin = origin + YamlUtils.getProperty("password.salt", "");
		return MD5Encode(origin, "utf-8");
	}

	private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
