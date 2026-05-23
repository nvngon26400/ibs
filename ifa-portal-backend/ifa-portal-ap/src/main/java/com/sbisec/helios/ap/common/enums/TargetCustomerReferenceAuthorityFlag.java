package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 対象顧客参照権限有無
 * 
 * @author 周
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum TargetCustomerReferenceAuthorityFlag {
    
    KENGEN_NASHI("0", "権限なし"), KENGEN_ARI("1", "権限あり");
    
    private final String id;
    
    private final String label;
    
    private TargetCustomerReferenceAuthorityFlag(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static TargetCustomerReferenceAuthorityFlag valueOfId(String id) {
        
        TargetCustomerReferenceAuthorityFlag[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
