package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 配受無区分
 * 
 * @author 松田
 *
 */
public enum DividendReceiptType {
    
    NOT_APPLICABLE("0", "該当しない"), APPLICABLE("1", "該当する");
    
    private final String id;
    
    private final String label;
    
    private DividendReceiptType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static DividendReceiptType valueOfId(String id) {
        
        DividendReceiptType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
