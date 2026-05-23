package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

/**
 *サービス時間チェック対象（外国）
 * 
 * @author 齋藤
 *
 */
public enum ForeignSurviceTimeCheck {
    
    PURCHASE(" ", "委託"), TRANSFER("1", "振替");
    
    private final String id;
    
    private final String label;
    
    private ForeignSurviceTimeCheck(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignSurviceTimeCheck valueOfId(String id) {
        
        ForeignSurviceTimeCheck[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
