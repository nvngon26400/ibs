package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：外貨部委託　取引種別の算出
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertCalcForeignBuySellTypeName {
    
    NO1("STOCK,BUY", "0"), NO2("STOCK,SELL", "1"), NO3("MARGIN_OPEN,BUY", "2"), NO4("MARGIN_OPEN,SELL",
            "3"), NO5("MARGIN_CLOSE,BUY", "4"), NO6("MARGIN_CLOSE,SELL", "5");
    
    private final String id;
    
    private final String label;
    
    private ConvertCalcForeignBuySellTypeName(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ConvertCalcForeignBuySellTypeName valueOfId(String id) {
        
        ConvertCalcForeignBuySellTypeName[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
