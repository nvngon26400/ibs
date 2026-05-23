package com.sbisec.helios.ap.safe.common.exception.enums;

/**
 * サービスの種類
 */
public enum ServiceType {
    /** common */
    COMMON("0","common"),
    /** account */
    ACCOUNT("1","account"),
    /** domestic_product */
    DOMESTIC_PRODUCT("2","domestic_product"),
    /** fund-chart */
    FUND_CHART("3","fund-chart"),
    /** fund-product */
    FUND_PRODUCT("4","fund-product"),
    /** fund-trade */
    FUND_TRADE("5","fund-trade"),
    /** system */
    SYSTEM("6","system"),
    /** kago */
    KAGO("7","kago"),
    /** zeisei */
    ZEISEI("8","zeisei"),
    /** donation */
    DONATION("9","donation"),
    /** assets */
    ASSETS("10","assets"),
    /** wrap */
    WRAP("11","wrap"),

    ;
	
	private final String id;
    
    private final String label;

    private ServiceType(String id, String label) {
	    this.id = id;
	    this.label = label;
	}
    
    public String getId() {        
        return id;
    }
    
    public String getLabel() {        
        return label;
    }
    
    public static ServiceType valueOfId(String id) {
        
    	ServiceType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (enums[i].getId() == id)
                return enums[i];
        }
        
        return null;
    }
}
