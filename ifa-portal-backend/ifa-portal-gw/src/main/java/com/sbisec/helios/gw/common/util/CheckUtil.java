package com.sbisec.helios.gw.common.util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sbibits.earth.util.StringUtil;

/**
 * 妥当性チェック
 * @see #checkDate(String) 日付妥当性チェック
 * @see #isNumber(String) 文字列数値チェック
 */
public class CheckUtil {
	/**
	 *  日付妥当性チェック
	 * @param strDate   書式【YYYY/MM/DD】【YYYY-MM-DD】【YYYYMMD】<br>
	 * @return boolean
	 * @see #isNumber(String)
	 */
	public static boolean checkDate(String strDate) {
	    strDate = formatYYYYMMDD(strDate);
	    if(strDate.isEmpty()){
	    	return false;
	    }
	    strDate = strDate.replace('-', '/');
	    DateFormat format = DateFormat.getDateInstance();
	    // 日付/時刻解析を厳密に行うかどうかを設定する。
	    format.setLenient(false);
	    try {
	        format.parse(strDate);
	        return true;
	    } catch (Exception e) {
	        return false;
	    }
	}
	/**
	 *  文字列数値チェック
	 * @param val
	 * @return boolean
	 */
	public static boolean isNumber(String val) {
		String regex = "\\A[-]?[0-9]+\\z";
		Pattern p = Pattern.compile(regex);
		Matcher m1 = p.matcher(val);
		return m1.find();
	}

	/**
	 * 日付比較
	 * @param strDateFrom
	 * @param strDateTo
	 * @return From >= To : true / From < To : false
	 */
	public static boolean compareDate(String strDateFrom, String strDateTo){
		String strDateF = "";
		String strDateT = "";
		if(!checkDate(strDateFrom)){
			return false;
		}
		if(!checkDate(strDateTo)){
			return false;
		}
		strDateF = formatYYYYMMDD(strDateFrom);
		if(strDateF.isEmpty()){
			return false;
		}
		strDateT = formatYYYYMMDD(strDateTo);
		if(strDateT.isEmpty()){
			return false;
		}

		if(strDateF.compareTo(strDateT) > 0){
			return false;
		}
		return true;
	}

	/**
	 * サイズチェック
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static boolean checkStringBytes(String param, int maxSize) {
		try {
			if (maxSize < param.getBytes("EUC_JP").length)
				return false;
		} catch (UnsupportedEncodingException e) {
			return true;
		}

		return true;
	}

	/**
	 * サイズチェック
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static boolean checkStringBytesEqual(String param, int size) {
		if (StringUtil.isNullOrEmpty(param))
			return false;
		if (size != param.getBytes().length)
			return false;

		return true;
	}

	/**
	 * 日付編集
	 * @param strDate
	 * @return String YYYYMMDD / Error Date:""
	 */
	private static String formatYYYYMMDD(String strDate){
		if(strDate.length() == 8 && isNumber(strDate)){
			strDate = strDate.substring(0,4) + "/" + strDate.substring(4,6)+ "/" + strDate.substring(6,8);
		}
	    if (strDate == null || strDate.length() != 10) {
	        return "";
	    }
	    strDate = strDate.replace('-', '/');

		return strDate;
	}
}
