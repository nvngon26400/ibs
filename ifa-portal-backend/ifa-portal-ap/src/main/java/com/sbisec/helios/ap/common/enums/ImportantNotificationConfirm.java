package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 重要なお知らせの確認
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ImportantNotificationConfirm {
    
    OFF("0", "未確認"), ON("1", "確認済み");
    
    private final String id;
    
    private final String label;
    
    private ImportantNotificationConfirm(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ImportantNotificationConfirm valueOfId(String id) {
        
        ImportantNotificationConfirm[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
