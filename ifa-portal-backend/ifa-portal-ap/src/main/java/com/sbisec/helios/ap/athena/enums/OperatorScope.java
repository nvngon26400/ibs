package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:オペレータ権限
 * @author 松田
 * @date: 2023/9/16
 */
public enum OperatorScope {
    
    EXCHANGE_TRADE("EXCHANGE_TRADE", "為替取引");
    
    OperatorScope(String id, String name) {
        
        this.id = id;
        this.name = name;
    }
    
    private String id;
    
    private String name;
    
    public String getId() {
        
        return id;
    }
    
    public String getName() {
        
        return name;
    }
    
    public static OperatorScope getById(String id) {
        
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        
        OperatorScope[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id)) {
                return enums[i];
            }
        }
        
        return null;
    }
}
