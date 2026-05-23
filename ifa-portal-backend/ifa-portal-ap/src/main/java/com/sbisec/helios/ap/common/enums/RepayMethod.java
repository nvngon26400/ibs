package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 返済方法
 * 
 * @author  松田
 * 
 */
@JsonFormat(shape = Shape.OBJECT)
public enum RepayMethod {
    
    BATCH("0", "一括指定"), INDIVIDUAL("1", "個別指定"), INDIVIDUAL_ONLY("2", "個別指定（単独）");
    
    private final String id;
    
    private final String label;
    
    private RepayMethod(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static RepayMethod valueOfId(String id) {
        
        RepayMethod[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
