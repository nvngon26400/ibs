package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import org.apache.commons.lang3.StringUtils;

/**
 *
 *
 * @author SBI大連 夏
 * @date   2025/03/28
 */
public enum BimonthlyKbn {

    ODD_MONTH ("1", "奇数月", "ODD_MONTH"),
    EVEN_MONTH ("2", "偶数月", "EVEN_MONTH");
    
    private final String id;
    private final String name;
    private final String value;
    
    private BimonthlyKbn(String id, String name, String value) {
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
    
    public static BimonthlyKbn nameOfId(String id) {
        BimonthlyKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
    public static BimonthlyKbn valueOfId(String id) {
        BimonthlyKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
