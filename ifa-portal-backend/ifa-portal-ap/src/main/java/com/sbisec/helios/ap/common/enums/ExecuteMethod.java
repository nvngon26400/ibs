package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 執行方法
 * 
 * @author  齋藤
 * 
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ExecuteMethod {
    
    LIMIT("1", "指値"), MARKET("2", "成行"), STOP("3", "逆指値");
    
    private final String id;
    
    private final String label;
    
    private ExecuteMethod(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ExecuteMethod valueOfId(String id) {
        
        ExecuteMethod[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
