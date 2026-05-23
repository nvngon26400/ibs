package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 * @author SBI大連 夏
 * @date   2025/03/28
 */
public enum WeeklyKbn {

    MONDAY ("1", "月曜日", "MONDAY"),
    TUESDAY ("2", "火曜日", "TUESDAY"),
    WEDNESDAY ("3", "水曜日", "WEDNESDAY"),
    THURSDAY ("4", "木曜日", "THURSDAY"),
    FRIDAY ("5", "金曜日", "FRIDAY");
    
    private final String id;
    private final String name;
    private final String value;
    
    private WeeklyKbn(String id, String name, String value) {
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
    
    public static WeeklyKbn nameOfId(String id) {
        WeeklyKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
    public static WeeklyKbn valueOfId(String id) {
        WeeklyKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
