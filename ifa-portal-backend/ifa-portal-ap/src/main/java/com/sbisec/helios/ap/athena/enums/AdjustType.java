package com.sbisec.helios.ap.athena.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * @Description:上乗せ区分
 * @author 松田
 * @date: 16/09/2023
 */
public enum AdjustType {
    
    ADJUST_AMOUNT("ADJUST_AMOUNT", "金額指定（上乗せ金額）"), 
    ADJUST_PERCENT("ADJUST_PERCENT", "パーセント指定（上乗せ金額）"),
	UNSPECIFIED("UNSPECIFIED","未定義");
    
    AdjustType(String id, String name) {
        
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
    
    public static AdjustType getById(String id) {
        
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        
        AdjustType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id)) {
                return enums[i];
            }
        }
        
        return null;
    }
}
