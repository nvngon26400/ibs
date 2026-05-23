package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 建余力不足 自動振替設定(米国株式)
 *
 *　@author 卞
 *
 */

@JsonFormat(shape = Shape.OBJECT)
public enum MarginBuyingPowerShortfallSecuritiesCheck {
    
    TRUE("1", "true"), FALSE("0", "false");
    
    private final String id;
    
    private final String label;
    
    private MarginBuyingPowerShortfallSecuritiesCheck(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static MarginBuyingPowerShortfallSecuritiesCheck valueOfId(String id) {
        
        MarginBuyingPowerShortfallSecuritiesCheck[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
