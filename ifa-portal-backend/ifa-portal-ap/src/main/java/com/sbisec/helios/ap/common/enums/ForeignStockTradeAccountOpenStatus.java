package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 外国株式取引口座開設状況
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ForeignStockTradeAccountOpenStatus {
    
    CLOSED("0", "未開設"), OPEN("1", "開設済み");
    
    private final String id;
    
    private final String label;
    
    private ForeignStockTradeAccountOpenStatus(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignStockTradeAccountOpenStatus valueOfId(String id) {
        
        ForeignStockTradeAccountOpenStatus[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
