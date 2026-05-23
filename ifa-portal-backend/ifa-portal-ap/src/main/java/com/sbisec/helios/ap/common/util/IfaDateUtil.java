package com.sbisec.helios.ap.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sbisec.helios.ap.common.dao.SystemDateDao;


@Component
public class IfaDateUtil {
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

    
    @Autowired
    private SystemDateDao systemDateDao;
    
    /**
     * format data.
     * 
     * @param format   format
     * @param timeZone time zone
     * @return
     */
    public String format(String format, TimeZone timeZone) throws Exception{
    	
        Date systemDate = systemDateDao.getSystemDate();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(systemDate);
    }

    /**
     * Format current time.
     * 
     * @param format format
     * @return
     */
    public String format(String format) throws Exception{
    	
    	Date systemDate = systemDateDao.getSystemDate();
    	
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getDefault());
        return dateFormat.format(systemDate);
    }

    /**
     * Get current Date
     * 
     * @return 現在日時
     */
    public Date getCurrentDate() throws Exception {

        Date systemDate = systemDateDao.getSystemDate();

        return systemDate;

    }

    /**
     * Get current Date
     * 
     * @param timeZone タイムゾーン。nullの場合UTC+09:00
     * @return 現在日時
     */
    public LocalDate getCurrentLocalDate(ZoneId timeZone) throws Exception {

        Date systemDate = systemDateDao.getSystemDate();
        ZoneId innerTimeZone = Optional.ofNullable(timeZone).orElse(ZoneId.of("UTC+09:00"));
        LocalDate systemLocalDate = systemDate.toInstant().atZone(innerTimeZone).toLocalDate();

        return systemLocalDate;

    }

    /**
     * Get current Date
     * 
     * @param timeZone タイムゾーン。nullの場合UTC+09:00
     * @return 現在日時
     */
    public LocalDateTime getCurrentLocalDateTime(ZoneId timeZone) throws Exception {

        Date systemDate = systemDateDao.getSystemDate();
        ZoneId innerTimeZone = Optional.ofNullable(timeZone).orElse(ZoneId.of("UTC+09:00"));
        LocalDateTime systemLocalDateTime = systemDate.toInstant().atZone(innerTimeZone).toLocalDateTime();

        return systemLocalDateTime;

    }

    /**
     * Get current Date
     * 
     * @param timeZone タイムゾーン。nullの場合UTC+09:00
     * @return 現在日時
     */
    public LocalTime getCurrentLocalTime(ZoneId timeZone) throws Exception {

        Date systemDate = systemDateDao.getSystemDate();
        ZoneId innerTimeZone = Optional.ofNullable(timeZone).orElse(ZoneId.of("UTC+09:00"));
        LocalTime systemLocalTime = systemDate.toInstant().atZone(innerTimeZone).toLocalTime();

        return systemLocalTime;

    }

}
