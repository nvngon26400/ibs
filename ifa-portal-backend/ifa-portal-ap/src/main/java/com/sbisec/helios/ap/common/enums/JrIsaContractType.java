package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * ジュニアISA契約区分
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum JrIsaContractType {
    
    CONTRACT("1", "契約"), CLOSED("9", "閉鎖済"), NOTCONTRACT(" ", "未契約");
    
    private final String id;
    
    private final String label;
    
    private JrIsaContractType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static JrIsaContractType valueOfId(String id) {
        
        JrIsaContractType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
