package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *　期間条件
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum PeriodConditions {
    
    TODAY("0", "当日中"), PERIOD_DESIGNATION("1", "期間指定");
    
    private final String id;
    
    private final String label;
    
    private PeriodConditions(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static PeriodConditions valueOfId(String id) {
        
        PeriodConditions[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
