package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 預り金自動スイープ申込区分
 * （現行から移植）
 * @author 松田
 *
 */
public enum AutoSweepKbn {
    
    NOT_APPLY("not_apply", "未開設", " ", "null"), 
    APPLY("apply", "自動スイープ申込済", "1", "1"), 
    CANCELEING("canceleing","自動スイープ解約中", "2", "2");
    
    private final String id;
    
    private final String title;
    
    private final String code;
    
    private final String completedValue;
    
    private AutoSweepKbn(String id, String title, String code, String completedValue) {
        
        this.id = id;
        this.title = title;
        this.code = code;
        this.completedValue = completedValue;
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
    
    public String getCompletedValue() {
        
        return completedValue;
    }
    
    /** code格納用 */
    private static Map<String, AutoSweepKbn> codeMap = new HashMap<String, AutoSweepKbn>(3);
    
    /** completed格納用 */
    private static Map<String, AutoSweepKbn> completedMap = new HashMap<String, AutoSweepKbn>(3);
    /**
     * 初期化
     *
     */
    static {
        codeMap.put(" ", NOT_APPLY);
        codeMap.put("1", APPLY);
        codeMap.put("2", CANCELEING);
        
        completedMap.put("1", APPLY);
        completedMap.put("2", CANCELEING);
    }
    
    /**
     * IDからインスタンス取得。
     * @param id
     * @return
     */
    public static AutoSweepKbn getInstanceByCode(String id) {
        
        return (AutoSweepKbn) codeMap.get(id);
    }
    
    /**
     * IDからインスタンス取得。
     * @param id
     * @return
     */
    public static AutoSweepKbn getInstanceByCompleted(String id) {
        
        return (AutoSweepKbn) completedMap.get(id);
    }
    
    public static AutoSweepKbn valueOfId(String id) {
        
        if (id == null)
            return null;
        
        AutoSweepKbn[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId().equals(id))
                return enums[i];
        }
        
        return null;
    }
}
