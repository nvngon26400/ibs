package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 口座区分
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum AccountType {
    
    WHOLE(" ", "通常口座およびJrNISA口座の第一口座"), JRNISA("1", "JrNISA口座(第二口座のみ)"), ALL("2", "JrNISA口座(第一、第二口座両方)");
    
    private final String id;
    
    private final String label;
    
    private AccountType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static AccountType valueOfId(String id) {
        
        AccountType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
