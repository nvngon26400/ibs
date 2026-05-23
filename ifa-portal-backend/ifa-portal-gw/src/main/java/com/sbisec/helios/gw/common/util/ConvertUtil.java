package com.sbisec.helios.gw.common.util;

import java.text.Normalizer;

import com.ibm.icu.text.Transliterator;
import com.sbibits.earth.util.StringUtil;
import com.sbisec.helios.ap.common.enums.SearchType;

/**
 * ConvertUtil<br>
 * SQL string half-width full-width conversion utility
 *
 * @author Yoshitaka.Nishida
 *
 */
public class ConvertUtil {

	/**
	 * toSearchString
	 *
	 * @param src input String
	 * @param searchType enums SearchType id
	 * @return SQL search string obtained by adding an escape code to convert the half-width to full-width
	 */
	public static String toSearchString(String src, int searchType) {
		if(!StringUtil.isNullOrEmpty(src)){
			src = toFullWidth(src);
			if(searchType != SearchType.EQUAL.getId()){
				src = addEscape(src);
				if(searchType == SearchType.INCLUDING.getId()){
					src = String.format("%%%s%%", src);
				} else if(searchType == SearchType.BEGINNING.getId()){
					src = String.format("%s%%", src);
				}
			}
		}
		return src;
	}
	/**
	 * toFullWidth<br>
	 * convert the half-width to full-width
	 *
	 * @param String src
	 * @return String obtained by converting the half-width to full-width
	 */
	public static String toFullWidth(String src) {
		// Unicode normalization
		// (half Kanner full-width kana conversion, full-width alphanumeric symbol - alphanumeric symbol conversion)
		src = Normalizer.normalize(src, Normalizer.Form.NFKC);

		// Half-size special symbol full-width conversion
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < src.length(); i++) {
			sb.append(toFullWidthChar(src.charAt(i)));
		}
		String result = sb.toString();

		// ICU (International Components for Unicode) Halfwidth-Fullwidth
		Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");
		result = transliterator.transliterate(result);

		return result;
	}

	/**
	 * addEscape<br>
	 * adding an escape code
	 * @param src
	 * @return String obtained by adding an escape code
	 */
	public static String addEscape(String src) {
		src = src.replaceAll("％", "!％");
		src = src.replaceAll("＿", "!＿");
		return src;
	}

	/**
	 * toFullWidthChar<br>
	 * Half-size special symbol full-width conversion
	 *
	 * @param char value
	 * @return char Character was em convert the half-size special symbol
	 */
	private static char toFullWidthChar(char value){
		if (value == '\'') { 		// In the case of single-byte apostrophe
			return '’';
		} else if (value == '\"') { // In the case of single-byte quotation marks
			return '”';
		} else if (value == '`')  { // In the case of half-grave accent em angle quotation marks (start)
			return '‘';
		} else if (value == '\\')  { // In the case of single-byte ¥
			return '￥';
		} else {
			return value;
		}
	}

	public static String addEscapeDoubleQuotation(String src) {
		src = src.replaceAll("\"", "\"\"");
		return src;
	}

}
