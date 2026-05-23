package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

/**
 * 追証項目セット区分
 * （現行から移植）
 * @author 松田
 *
 */
public enum DeficitSetKbn {
    
    ALL("all", "Ｔ?Ｔ?４まで全てセット", "A"), 
    UNNECESSARY("unnecessary", "セット不要（初期値をセット）", "N");
    
    private final String id;
    
    private final String title;
    
    private final String code;
    
    private DeficitSetKbn(String id, String title, String code) {
        
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
    
    public static DeficitSetKbn valueOfId(String id) {
        
        if (id == null)
            return null;
        
        DeficitSetKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }
        
        return null;
    }
}
