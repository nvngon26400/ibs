package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文種別
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum OrderClass {
    
    NORMAL("1", "通常/逆指値"), OCO("2", "OCO"), IFD("3", "IFD"), IFDOCO("4", "IFDOCO");
    
    private final String id;
    
    private final String label;
    
    private OrderClass(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static OrderClass valueOfId(String id) {
        
        OrderClass[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
}
