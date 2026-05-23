package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 特定口座区分
 * 
 * @author 松田
 *
 */
public enum SpecificAccount {
    
    SPECIFIC_PAYMENT_PROXY("1", "特定口座(代行納付)")
    , SPECIFIC_FINAL_TAX("2", "特定口座(確定申告)")
    , UNSPECIFIC("3", "非特定口座")
    , UNREGIST(" ", "未登録")
    ;
    
    private final String id;
    
    private final String label;
    
    private SpecificAccount(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static SpecificAccount valueOfId(String id) {
        
        SpecificAccount[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
