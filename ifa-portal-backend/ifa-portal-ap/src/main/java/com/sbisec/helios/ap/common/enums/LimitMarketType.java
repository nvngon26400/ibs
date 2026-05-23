package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 指成区分
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum LimitMarketType {
    
    PRICE_LIMIT(" ", "指値"), YORISASI("Z", "寄指(Y)"), HIKESASI("I", "引指(H)"), FUNARI("F", "不成(F)"), IOC_SASI("P",
            "IOC指(I)"), MARKET_ORDER("N",
                    "成行(N)"), YORINARI("Y", "寄成(Y)"), HIKENARI("H", "引成(H)"), IOC_NARI("O", "IOC成(I)");
    
    private final String id;
    
    private final String label;
    
    private LimitMarketType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static LimitMarketType valueOfId(String id) {
        
        LimitMarketType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
