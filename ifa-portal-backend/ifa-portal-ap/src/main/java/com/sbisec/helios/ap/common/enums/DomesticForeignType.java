package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 国内外国区分
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DomesticForeignType {
    
    DOMESTIC("0", "国内"), FOREIGHN("1", "外国");
    
    private final String id;
    
    private final String label;
    
    private DomesticForeignType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static DomesticForeignType valueOfId(String id) {
        
        DomesticForeignType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
}
