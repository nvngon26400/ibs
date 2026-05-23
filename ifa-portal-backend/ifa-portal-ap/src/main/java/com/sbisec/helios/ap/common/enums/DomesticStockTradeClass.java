package com.sbisec.helios.ap.common.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 取引種別（国内株式）
 * 
 * @author 周
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DomesticStockTradeClass {
    
    SPOT_BUY("1", "現物買付"), SPOT_SELL("2", "現物売却"), SHINYOSHINKI_BUY("3", "信用新規買"), SHINYOSHINKI_SELL("4",
            "信用新規売"), MARGIN_REPAY_BUY("5", "信用返済買"), MARGIN_REPAY_SELL("6", "信用返済売");
    
    private final String id;
    
    private final String label;
    
    private DomesticStockTradeClass(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static DomesticStockTradeClass valueOfId(String id) {
        
        DomesticStockTradeClass[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
