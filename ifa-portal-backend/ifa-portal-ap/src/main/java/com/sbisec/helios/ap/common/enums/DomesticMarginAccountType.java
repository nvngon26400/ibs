package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * コンプラチェック種類
 * 
 * @author 周
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DomesticMarginAccountType {
    
    CASH_ACCOUNT(" ", "現物口座"), MARGIN_ACCOUNT("1", "信用口座");
    
    private final String id;
    
    private final String label;
    
    private DomesticMarginAccountType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static DomesticMarginAccountType valueOfId(String id) {
        
        DomesticMarginAccountType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
