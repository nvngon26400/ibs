package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 保証金不足 自動振替設定(米国株式)
 *
 *　@author 卞
 *
 */

@JsonFormat(shape = Shape.OBJECT)
public enum MarginShortfallSecuritiesCheck {
    
    TRUE("1", "true"), FALSE("0", "false");
    
    private final String id;
    
    private final String label;
    
    private MarginShortfallSecuritiesCheck(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static MarginShortfallSecuritiesCheck valueOfId(String id) {
        
        MarginShortfallSecuritiesCheck[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
