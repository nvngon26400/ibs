package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 保証金不足 自動振替設定(米ドル)
 *
 *　@author 卞
 *
 */

@JsonFormat(shape = Shape.OBJECT)
public enum MarginShortfallCashCheck {
    
    TRUE("1", "true"), FALSE("0", "false");
    
    private final String id;
    
    private final String label;
    
    private MarginShortfallCashCheck(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static MarginShortfallCashCheck valueOfId(String id) {
        
        MarginShortfallCashCheck[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
