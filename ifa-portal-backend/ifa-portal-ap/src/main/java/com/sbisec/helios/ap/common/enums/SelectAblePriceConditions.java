package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 選択可能価格条件
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum SelectAblePriceConditions {
    
    PRICE_LIMIT("1", "指値")
    , MARKET_ORDER("2", "成行")
    , STOP_ORDER_EXECUTE_PRICE("3","逆指値/指値")
    , STOP_MARKET_ORDER_EXECUTE_PRICE("4", "逆指値/成行")
    ;
    
    private final String id;
    
    private final String label;
    
    private SelectAblePriceConditions(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static SelectAblePriceConditions valueOfId(String id) {
        
        SelectAblePriceConditions[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
