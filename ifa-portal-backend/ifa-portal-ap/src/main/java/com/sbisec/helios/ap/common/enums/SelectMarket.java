package com.sbisec.helios.ap.common.enums;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *　選択市場
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum SelectMarket {
    
    SOR("A", "当社優先市場／SOR"), TKY("0", "東証"), NGY("2", "名証"), FKO("6", "福証"), PTS("7", "PTS"), SPR("8", "札証"), DLU("H",
            "単元未満");
    
    private final String id;
    
    private final String label;
    
    private SelectMarket(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static SelectMarket valueOfId(String id) {
        
        SelectMarket[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
}
