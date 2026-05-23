package com.sbisec.helios.ap.common.util;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import com.sbibits.earth.util.DateUtil;
import com.sbibits.earth.util.StringUtil;

/**
 *
 * @author Yongfeng.wu
 */
public class DateFormatUtil {
    
    public static final String SEPARATED_YYYYMMDD = "yyyy-MM-dd";
    
    public static final String SEPARATED_YYMMDD = "yy/MM/dd";
    
    public static final String SEPARATED_YYYYMMDDHHSSMMSSS = "yyyy-MM-dd HH:mm:ss.SSS";
    
    public static final String SEPARATED_YYYYMMDDHHMM = "yyyy/MM/dd HH:mm";
    
    public static final String YYYYMMDD = "yyyyMMdd";
    
    public static final String SEPARATED_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
    
    /** (米株DB登録用) APIの戻り値が空の場合は初期値'19000101' */
    public static final String AS_DEFUAL_ORDER_INPUT_DATE = "19000101";
    
    /** (米株DB登録用) APIの戻り値が空の場合は初期値'1900/01/01 00:00:00' */
    public static final String AS_DEFUAL_ORDER_INPUT_DATETIME = "1900/01/01 00:00:00";
    
    /**
     * "YYYYMMDD"を"YYYY/MM/DD"に変更。整合性のチェックは特になし。 値が不正な場合、"-"を戻す
     *
     * @author Yongfeng.wu
     * @param ymd
     * @return
     */
    public static String convertDateString(String _ymd) {
        
        String ymd = _ymd;
        if (ymd == null)
            ymd = "";
        ymd = ymd.trim();
        
        if (ymd.length() < 8)
            return "-";
        else
            return ymd.substring(0, 4) + "/" + ymd.substring(4, 6) + "/" + ymd.substring(6, 8);
    }

    /**
     * "YYYYMM"を"YYYY/MM"に変更。整合性のチェックは特になし。 値が不正な場合、"-"を戻す
     *
     * @author SCSK
     * @param ym
     * @return
     */
    public static String convertDateYmString(String _ym) {
        
        String ym = _ym;
        if (ym == null)
            ym = "";
        ym = ym.trim();
        
        if (ym.length() < 6)
            return "-";
        else
            return ym.substring(0, 4) + "/" + ym.substring(4, 6);
    }
    
    /**
     * "YYYYMMDDHH24MISS"を"YYYY/MM/DD HH24:MI"に変更。整合性のチェックは特になし。 値が不正な場合、"-"を戻す
     *
     * @author Yongfeng.wu
     * @param ymd
     * @return
     */
    public static String convertTimeString(String _ymdhm) {
        
        String ymdhm = _ymdhm;
        if (ymdhm == null)
            ymdhm = "";
        ymdhm = ymdhm.trim();
        if (ymdhm.length() < 14)
            return "-";
        else
            return ymdhm.substring(0, 4) + "/" + ymdhm.substring(4, 6) + "/" + ymdhm.substring(6, 8) + "&nbsp;"
                    + ymdhm.substring(8, 10) + ":" + ymdhm.substring(10, 12);
    }
    
    /**
     * "YYYY/MM/DD"を"YYYYMMDD"に変更。整合性のチェックは特になし。
     *
     * @param ymd
     * @return
     */
    public static String dateStringYMD(String _ymd) {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        _ymd = _ymd.trim();
        return _ymd.substring(0, 4) + _ymd.substring(5, 7) + _ymd.substring(8, 10);
    }
    
    /**
     * "YYYY-MM-DD"から"YYYY/MM/DD"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToSlash(String _ymd) {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        try {
            Date _date = DateUtil.parse(_ymd, SEPARATED_YYYYMMDD);
            return DateUtil.format(_date, DateUtil.SEPARATED_YYYYMMDD);
        } catch (Exception e) {
            return _ymd;
        }
        
    }
    
    /**
     * "YYYY/MM/DD"から"YYYY-MM-DD"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToHyphen(String _ymd) throws Exception {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        try {
            Date _date = DateUtil.parse(_ymd, DateUtil.SEPARATED_YYYYMMDD);
            return DateUtil.format(_date, SEPARATED_YYYYMMDD);
        } catch (Exception e) {
            return _ymd;
        }
    }
    
    /**
     * "YYYY/MM/DD"又は"YYYY-MM-DD"から"YYYYMMDD"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToYmdNoSign(String ymd) {
        
        if (StringUtil.isNullOrEmpty(ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        String format = null;
        String strHypen = "^\\d{4}(\\-)\\d{2}\\1\\d{2}$";
        String strSlash = "^\\d{4}(\\/)\\d{2}\\1\\d{2}$";
        
        Pattern ptnHypen = Pattern.compile(strHypen);
        Pattern ptnSlash = Pattern.compile(strSlash);
        if (ptnHypen.matcher(ymd).matches()) {
            format = SEPARATED_YYYYMMDD;
        } else if (ptnSlash.matcher(ymd).matches()) {
            format = DateUtil.SEPARATED_YYYYMMDD;
        } else {
            return ymd;
        }
        try {
            Date date = DateUtil.parse(ymd, format);
            return DateUtil.format(date, DateUtil.YYYYMMDD);
        } catch (Exception e) {
            return ymd;
        }
    }
    
    /**
     * "YYYY-MM-DD"から"YY/MM/DD"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToYy(String _ymd) {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        try {
            Date _date = DateUtil.parse(_ymd, SEPARATED_YYYYMMDD);
            return DateUtil.format(_date, SEPARATED_YYMMDD);
        } catch (Exception e) {
            return _ymd;
        }
    }
    
    /**
     * "yyyy-MM-dd'T'HH:mm:ss"からDateに変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static Date dateFormatToDate(String _ymd) throws Exception {
        
        Date date = null;
        try {
            OffsetDateTime time = OffsetDateTime.parse(_ymd, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String ymdhms = time.format(DateTimeFormatter.ofPattern(SEPARATED_YYYYMMDDHHSSMMSSS));
            date = DateUtil.parse(ymdhms, SEPARATED_YYYYMMDDHHSSMMSSS);
        } catch (Exception e) {
            return null;
        }
        return date;
        
    }
    
    /**
     * "yyyy-MM-dd'T'HH:mm:ss"から"YYYYMMDD"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToYmd(String _ymd) {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        try {
            OffsetDateTime time = OffsetDateTime.parse(_ymd, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return time.format(DateTimeFormatter.ofPattern(DateUtil.YYYYMMDD));
        } catch (Exception e) {
            return StringUtil.EMPTY_STRING;
        }
    }
    
    /**
     * "yyyy-MM-dd'T'HH:mm:ss"から"YYYY/MM/DD HH:MM"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToSeparatedYmdhm(String _ymd) {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        try {
            OffsetDateTime time = OffsetDateTime.parse(_ymd, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return time.format(DateTimeFormatter.ofPattern(SEPARATED_YYYYMMDDHHMM));
        } catch (Exception e) {
            return StringUtil.EMPTY_STRING;
        }
    }
    
    /**
     * "yyyy-MM-dd'T'HH:mm:ss"から"YYYY/MM/DD"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToSeparatedYmd(String _ymd) {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        try {
            OffsetDateTime time = OffsetDateTime.parse(_ymd, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            return time.format(DateTimeFormatter.ofPattern(DateUtil.SEPARATED_YYYYMMDD));
        } catch (Exception e) {
            return StringUtil.EMPTY_STRING;
        }
    }
    
    /**
     * "OffsetDateTime"からDateに変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付(YYYYMMDD)
     * @throws Exception
     */
    public static String offSetFormatToYmd(String _ymd) throws Exception {
        
        if (StringUtil.isNullOrEmpty(_ymd)) {
            return StringUtil.EMPTY_STRING;
        }
        
        Date date = null;
        try {
            OffsetDateTime time = OffsetDateTime.parse(_ymd, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            String ymdhms = time.format(DateTimeFormatter.ofPattern(SEPARATED_YYYYMMDDHHSSMMSSS));
            date = DateUtil.parse(ymdhms, SEPARATED_YYYYMMDDHHSSMMSSS);
            return DateUtil.format(date, DateUtil.YYYYMMDD);
        } catch (Exception e) {
            return StringUtil.EMPTY_STRING;
        }
        
    }
    
    /**
     * "yyyy-MM-dd'T'HH:mm:ss"から"yyyy-MM-dd HH:mm:ss"に変更。
     * 
     * @param date フォーマットしたい日付
     * @return フォーマットした日付
     * @throws Exception
     */
    public static String dateFormatToSeparatedYmdhms(String _ymd) throws Exception {
        
        String date = null;
        try {
            OffsetDateTime time = OffsetDateTime.parse(_ymd, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            date = time.format(DateTimeFormatter.ofPattern(SEPARATED_YYYYMMDDHHMMSS));
        } catch (Exception e) {
            return null;
        }
        return date;
        
    }
}
