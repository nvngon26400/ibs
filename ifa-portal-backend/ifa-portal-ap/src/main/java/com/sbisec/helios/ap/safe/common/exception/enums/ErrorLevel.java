package com.sbisec.helios.ap.safe.common.exception.enums;


/**
 * エラーレベル、後続処理を中断するか継続するかの判定に使用する
 */
public enum ErrorLevel {
    /** 警告、後続処理は継続 */
	WARNING(2,"警告"),
    /** エラー、後続処理は中断 */
    ERROR(1,"エラー"),
    ;
	
	private final int id;
    
    private final String label;

    private ErrorLevel(int id, String label) {
	    this.id = id;
	    this.label = label;
	}
    
    public int getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ErrorLevel valueOfId(int id) {
        
    	ErrorLevel[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId() == id)
                return enums[i];
        }
        
        return null;
    }
}
