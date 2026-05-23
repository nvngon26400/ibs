package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *媒介可取引有無
 * 
 * @author 周
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum MediateAbleTradeFlag {
    
    NASHI("0", "あり"), ARI("1", "なし");
    
    private final String id;
    
    private final String label;
    
    private MediateAbleTradeFlag(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static MediateAbleTradeFlag valueOfId(String id) {
        
        MediateAbleTradeFlag[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
