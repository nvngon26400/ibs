package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.enums;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 決算確定区分
 *
 * @author SBI大連 夏
 * @date   2025/06/27
 */

public enum GetClosingKbn {

    CLOSINGKBN_ZERO("0", ""),
    CLOSINGKBN_ONE("1", "済");
    
    private final String name;
    private final String value;
    
    private GetClosingKbn(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public String getValue() {
        return value;
    }
    
    public static GetClosingKbn nameOfValue(String name) {
        GetClosingKbn[] enums = values();
        
        for(int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getName(), name)) {
                return enums[i];
            }
        }
        return null;
    }
    
}
