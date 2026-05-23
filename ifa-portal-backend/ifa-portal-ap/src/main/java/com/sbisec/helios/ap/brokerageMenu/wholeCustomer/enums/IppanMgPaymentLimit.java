package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 一般信用売弁済期限年月日数
 * （現行から移植）
 * @author 松田
 *
 */
public enum IppanMgPaymentLimit {
    
    UNLIMITED("unlimited", "無期限", "  ");
    
    private final String id;
    
    private final String title;
    
    private final String code;
    
    private IppanMgPaymentLimit(String id, String title, String code) {
        
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
    private static Map<String, IppanMgPaymentLimit> codeMap = new HashMap<String, IppanMgPaymentLimit>(1);
    
    /**
     * 初期化
     */
    static {
        codeMap.put("  ", UNLIMITED);
    }
    
    public static IppanMgPaymentLimit getInstanceByCode(String id) {
        
        return (IppanMgPaymentLimit) codeMap.get(id);
    }
    
    public static IppanMgPaymentLimit valueOfCode(String code) {
        
        if (code == null)
            return null;
        
        IppanMgPaymentLimit[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(code))
                return enums[i];
        }
        
        return null;
    }
}
