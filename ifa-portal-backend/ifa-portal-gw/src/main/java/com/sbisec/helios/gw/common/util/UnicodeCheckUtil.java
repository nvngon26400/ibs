package com.sbisec.helios.gw.common.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sbibits.earth.util.StringUtil;

/**
 * 
 * check unicode tool
 *
 */
public class UnicodeCheckUtil {

	private static final Logger logger = LoggerFactory.getLogger(UnicodeCheckUtil.class);

	private static final String SJIS = "sjis";
	private static final String D83C = "d83c"; //Enclosed Alphanumeric Supplement:🄬
	private static final String D83D = "d83d"; //emojji:💥
	private static final String D83E = "d83e"; //emojji:🤕

	/**
	 * get error UTF-8 character
	 * @param strInput
	 * @return
	 */
	public static String getErrorUtf8Str(String strInput) {

		List<String> list = new ArrayList<>();
		try {
			String strSjis = new String(strInput.getBytes(SJIS), SJIS);
			if (strInput.compareTo(strSjis) != 0) {
				if (strInput.length() == strSjis.length()) {
					getNormalUtf8Str(strInput, strSjis, list);
				} else {
					getSpecialUtf8Str(strInput, strSjis, list);
				}
			}
		} catch (Exception e) {
			logger.warn("Get illegal characters error:{}" , e.getMessage());
			return StringUtil.EMPTY_STRING;
		}
		return String.join(",", list);
	}

	/**
	 * getSpecialUtf8Str [https://www.fuhaozi.com/unicode/Enclosed_Alphanum_Sup.html#google_vignette]
	 * @param strInput
	 * @param strSjis
	 * @param list
	 */
	private static void getSpecialUtf8Str(String strInput, String strSjis, List<String> list) {

		char cInput;
		char cSjis;
		int t = 0;
		for (int i = 0; i < strInput.length(); i++) {
			cInput = strInput.charAt(i);
			cSjis = strSjis.charAt(t++);
			if (cInput != cSjis) {
				if (Integer.toHexString(cInput).equals(D83C) || Integer.toHexString(cInput).equals(D83D) || Integer.toHexString(cInput).equals(D83E)) {
					String str = charToUnicode(strInput.charAt(i)) + charToUnicode(strInput.charAt(++i));
					list.add(unicodeToString(str));
				} else {
					switch (cInput) {
					case 0xff3c: // FULLWIDTH REVERSE SOLIDUS ->
						break;
					case 0xff5e: // FULLWIDTH TILDE ->
						break;
					case 0x2225: // PARALLEL TO ->
						break;
					case 0xff0d: // FULLWIDTH HYPHEN-MINUS ->
						break;
					case 0xffe0: // FULLWIDTH CENT SIGN ->
						break;
					case 0xffe1: // FULLWIDTH POUND SIGN ->
						break;
					case 0xffe2: // FULLWIDTH NOT SIGN ->
						break;
					default:
						list.add(String.valueOf(cInput));
					}
				}
			} 
		}
	}

	/**
	 * getNormalUtf8Str [➀、㏒など]
	 * @param strInput
	 * @param strSjis
	 * @param list
	 */
	private static void getNormalUtf8Str(String strInput, String strSjis, List<String> list) {
		char cInput;
		char cSjis;
		for (int i = 0; i < strInput.length(); i++) {
			cInput = strInput.charAt(i);
			cSjis = strSjis.charAt(i);
			if (cInput != cSjis) {
				switch (cInput) {
				case 0xff3c: // FULLWIDTH REVERSE SOLIDUS ->
					break;
				case 0xff5e: // FULLWIDTH TILDE ->
					break;
				case 0x2225: // PARALLEL TO ->
					break;
				case 0xff0d: // FULLWIDTH HYPHEN-MINUS ->
					break;
				case 0xffe0: // FULLWIDTH CENT SIGN ->
					break;
				case 0xffe1: // FULLWIDTH POUND SIGN ->
					break;
				case 0xffe2: // FULLWIDTH NOT SIGN ->
					break;
				default:
					list.add(String.valueOf(cInput));
				}
			}
		}
	}

	/**
	 * char to unicode
	 * @param str
	 * @return
	 */
	public static String charToUnicode(char c) {
		return "\\u" + Integer.toHexString(c);
	}

	/**
	 * unicode to String
	 * @param unicode
	 * @return
	 */
	public static String unicodeToString(String unicode) {
		StringBuffer sb = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int index = Integer.parseInt(hex[i], 16);
			sb.append((char) index);
		}
		return sb.toString();
	}
}
