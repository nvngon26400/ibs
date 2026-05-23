package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *　信用口座区分(外国)
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ForeignMarginAccountType {
    
    SPOT(" ", "現物口座"), MARGIN("1", "信用口座");
    
    private final String id;
    
    private final String label;
    
    private ForeignMarginAccountType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignMarginAccountType valueOfId(String id) {
        
        ForeignMarginAccountType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
