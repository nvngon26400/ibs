package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *　取引停止フラグ
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum TradeSuspendFlag {
    
    DEGAULT(" ", "初期値"), NOT_SUSPEND("0", "取引停止口座ではない"), SUSPEND("1", "取引停止口座");
    
    private final String id;
    
    private final String label;
    
    private TradeSuspendFlag(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static TradeSuspendFlag valueOfId(String id) {
        
        TradeSuspendFlag[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
