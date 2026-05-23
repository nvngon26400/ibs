package com.sbisec.helios.ap.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * Date related tools.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class DateUtil {

    /** Date format with format year/month/day hours/minutes/seconds. */
    public static final String SEPARATED_YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";
    /** Date format with format of year/month/day. */
    public static final String SEPARATED_YYYYMM = "yyyy/MM";
    /** Date format with format year/month/day. */
    public static final String SEPARATED_YYYYMMDD = "yyyy/MM/dd";
    /** Date format with format yearMonth. */
    public static final String YYYYMM = "yyyyMM";
    /** Date format with format yearMonthDay. */
    public static final String YYYYMMDD = "yyyyMMdd";
    /** Date format with format yearMonthDay. */
    public static final String JAPANESE_STYLE = "yyyy年MM月dd日";
    /** Date format with format yearMonth. */
    public static final String JAPANESE_STYLE_YM = "yyyy年MM月";
    /** Date format with format yearMonthDay. */
    public static final String NOT_SEPARATED_YYYYMMDD_HHMMSS = "yyyyMMddHHmmss";
    public static final String SEPARATED_DDMMMYYYY = "dd-MMM-yyyy";
    public static final String SEPARATED_HYPHEN_YYYYMMDD = "yyyy-MM-dd";
    /** Date format with format month/day/year. */
    public static final String SEPARATED_MMDDYYYY = "MM/dd/yyyy";

    /**
     * Instance current time.
     * 
     * @return
     */
    public static Calendar current() {
        return Calendar.getInstance();
    }

    /**
     * format data.
     * 
     * @param format   format
     * @param timeZone time zone
     * @return
     */
    public static String format(String format, TimeZone timeZone) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(current().getTimeInMillis());
    }

    /**
     * Format current time.
     * 
     * @param format format
     * @return
     */
    public static String format(String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(current().getTimeInMillis());
    }

}
