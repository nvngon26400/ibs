package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 特定建玉区分
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum SpecificPositionType {
    
    GENERAL("0", "一般"), IDENTIFICATION("1", "特定"), HYPHEN("-", "-");
    
    private final String id;
    
    private final String label;
    
    private SpecificPositionType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static SpecificPositionType valueOfId(String id) {
        
        //if (id == null) return null;
        
        SpecificPositionType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }
        
        return null;
    }
}
