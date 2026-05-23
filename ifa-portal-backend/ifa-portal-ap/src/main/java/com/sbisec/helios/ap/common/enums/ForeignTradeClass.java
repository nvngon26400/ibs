package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 取引種別（外貨）
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ForeignTradeClass {
    
      BUY("1", "買付")
    , SELL("2", "売却")
    ;
    
    private final String id;
    
    private final String label;
    
    private ForeignTradeClass(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignTradeClass valueOfId(String id) {
        
        ForeignTradeClass[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
