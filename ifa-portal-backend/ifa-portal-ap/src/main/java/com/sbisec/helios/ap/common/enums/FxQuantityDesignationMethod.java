package com.sbisec.helios.ap.common.enums;

import org.springframework.util.ObjectUtils;

/**
 * 
 * 数量の指定方法（為替）
 * @author 松田
 */
public enum FxQuantityDesignationMethod {
    FOREIGN("1","外貨で指定"),
    YEN("2","円で指定")
	;
    
    FxQuantityDesignationMethod(String id, String name) {
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
    
    public static FxQuantityDesignationMethod getById(String id) {
        
        if (ObjectUtils.isEmpty(id)) {
            return null;
        }
        
        FxQuantityDesignationMethod[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id)) {
                return enums[i];
            }
        }
        
        return null;
    }
    
}
