package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

/**
 * 余力項目セット区分
 * （現行から移植）
 * @author 松田
 *
 */
public enum CapabilitySetKbn {
    
    APART("apart", "Ｔ＋１、Ｔ＋３日分のみセット", "1"), 
    ALL("all", "Ｔ?Ｔ＋５まで全てセット", "A");
    
    private final String id;
    
    private final String title;
    
    private final String code;
    
    private CapabilitySetKbn(String id, String title, String code) {
        
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
    
    public static CapabilitySetKbn valueOfId(String id) {
        
        if (id == null)
            return null;
        
        CapabilitySetKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }
        
        return null;
    }
}
