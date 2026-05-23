package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *　買付余力(外貨)有無
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ForeignBuyPowerFlag {
    
    NOTHING("0", "なし"), EXISTS("1", "あり");
    
    private final String id;
    
    private final String label;
    
    private ForeignBuyPowerFlag(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignBuyPowerFlag valueOfId(String id) {
        
        ForeignBuyPowerFlag[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
