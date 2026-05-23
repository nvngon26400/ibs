package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

/**
 * 積立コース設定
 *
 * @author SBI大連 夏
 * @date   2025/03/28
 */
public enum AccumulateCourse {

    ACCUMULATE_COURSE_ONE ("1", "毎日", "EVERY_DAY"),
    ACCUMULATE_COURSE_TWO ("2", "毎週", "EVERY_WEEK"),
    ACCUMULATE_COURSE_THREE ("3", "毎月", "EVERY_MONTH"),
    ACCUMULATE_COURSE_FOUR ("4", "複数日", "MULTIPLE_DAYS"),
    ACCUMULATE_COURSE_FIVE ("5", "隔月", "BIMONTHLY");
    
    /** 設定日の日付が31の場合、MM/月末と表示する */
    private final static String MONTH_END_CODE = "31";
    private final static String MONTH_END_VALUE = "月末";
    
    private final String id;
    private final String name;
    private final String value;
    
    private AccumulateCourse(String id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
    public static AccumulateCourse nameOfId(String id) {
        AccumulateCourse[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
    public static AccumulateCourse valueOfId(String id) {
        AccumulateCourse[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }

    // 積立コースが毎日の場合：毎日
    public static String getEveryDayCourse() {
        return AccumulateCourse.ACCUMULATE_COURSE_ONE.getName();
    }

    // 積立コースが毎週の場合：毎週（X曜日）
    public static String getEveryWeekCourse(String dataValue) {
        return WeeklyKbn.nameOfId(dataValue) != null
                ? AccumulateCourse.ACCUMULATE_COURSE_TWO.getName() + "（" + WeeklyKbn.nameOfId(dataValue).getName() + "）"
                : AccumulateCourse.ACCUMULATE_COURSE_TWO.getName() + "（" + "）";
    }

    // 積立コースが毎月の場合：毎月（XX日）
    public static String getEveryMonthCourse(String dataValue) {

        if (StringUtils.isNotEmpty(dataValue)) {
            return StringUtils.equals(MONTH_END_CODE, dataValue)
                    ? AccumulateCourse.ACCUMULATE_COURSE_THREE.getName() + "（" + MONTH_END_VALUE + "）"
                    : AccumulateCourse.ACCUMULATE_COURSE_THREE.getName() + "（"
                            + dataValue.replaceAll("\\s+", "").replaceFirst("^0+(?=\\d)", "") + "日）";
        } else {
            return AccumulateCourse.ACCUMULATE_COURSE_THREE.getName() + "（" + "）";
        }
    }

    // 積立コースが複数日の場合：複数日（XX日,XX日,XX日）
    public static String getMultipleDaysCourse(String dataValue) {

        if (StringUtils.isNotEmpty(dataValue)) {
            dataValue = Arrays.stream(dataValue.split(";")).filter(s -> !s.isEmpty())
                    .map(s -> s.replaceFirst("^0+(?!$)", "")).collect(Collectors.joining(","));
            return ACCUMULATE_COURSE_FOUR.getName() + "（" + dataValue + "日）";
        } else {
            return ACCUMULATE_COURSE_FOUR.getName() + "（" + "）";
        }
    }
    
    // 積立コースが隔月の場合：奇数月（XX日） or 偶数月（XX日）
    public static String getBimonthlyCourse(String dataValue1, String dataValue2) {

        if (StringUtils.equals(BimonthlyKbn.ODD_MONTH.getId(), dataValue1)) {
            // 奇数月（XX日）
            if (dataValue2 != null) {
                return StringUtils.equals(MONTH_END_CODE, dataValue2) ?  BimonthlyKbn.ODD_MONTH.getName() + "（" + MONTH_END_VALUE + "）" : BimonthlyKbn.ODD_MONTH.getName() + "（" + dataValue2.replaceAll("\\s+", "").replaceFirst("^0+(?=\\d)", "") + "日）";
            } else {
                return BimonthlyKbn.ODD_MONTH.getName() + "（" + "）";
            }
        } else if (StringUtils.equals(BimonthlyKbn.EVEN_MONTH.getId(), dataValue1)) {
            // 偶数月（XX日）
            if (dataValue2 != null) {
                return StringUtils.equals(MONTH_END_CODE, dataValue2) ?  BimonthlyKbn.EVEN_MONTH.getName() + "（" + MONTH_END_VALUE + "）" : BimonthlyKbn.EVEN_MONTH.getName() + "（" + dataValue2.replaceAll("\\s+", "").replaceFirst("^0+(?=\\d)", "") + "日）";
            } else {
                return BimonthlyKbn.EVEN_MONTH.getName() + "（" + "）";
            }
        }
        return null;
    }
}
