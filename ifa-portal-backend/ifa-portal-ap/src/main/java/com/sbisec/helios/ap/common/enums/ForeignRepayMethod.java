package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 返済方法（外国）
 * 
 * @author  松田
 * 
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ForeignRepayMethod {
    
    INDIVIDUAL("0", "個別指定"), BATCH("1", "一括指定");
    
    private final String id;
    
    private final String label;
    
    private ForeignRepayMethod(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignRepayMethod valueOfId(String id) {
        
        ForeignRepayMethod[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
