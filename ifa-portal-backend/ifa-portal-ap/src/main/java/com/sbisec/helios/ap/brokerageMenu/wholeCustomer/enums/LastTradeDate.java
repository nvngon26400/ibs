package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 決済期日
 * （現行から移植）
 * @author 松田
 *
 */
public enum LastTradeDate {
    
    MUKIGEN("mukigen", "無期限", "99991231");
    
    private final String id;
    
    private final String title;
    
    private final String code;
    
    private LastTradeDate(String id, String title, String code) {
        
        this.id = id;
        this.title = title;
        this.code = code;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getTitle() {
        
        return title;
    }
    
    public String getCode() {
        
        return code;
    }
    
    /** code格納用 */
    private static Map<String, LastTradeDate> codeMap = new HashMap<String, LastTradeDate>(2);
    /**
     * 初期化
     *
     */
    static {
        codeMap.put("99991231", MUKIGEN);
    }
    
    /**
     * IDからインスタンス取得。
     * @param id
     * @return
     */
    public static LastTradeDate getInstanceByCode(String id) {
        
        return (LastTradeDate) codeMap.get(id);
    }
    
    public static LastTradeDate valueOfId(String id) {
        
        if (id == null)
            return null;
        
        LastTradeDate[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }
        
        return null;
    }
}
