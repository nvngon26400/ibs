package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 *　取引種別（外国株式）
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ForeignStockTradeClass {
    
    SPOT_BUY("0", "現物買付"), SPOT_SELL("1", "現物売却"),MARGIN_NEW_BUY("2","信用新規買"),
    MARGIN_NEW_SELL("3", "信用新規売"), MARGIN_REPAY_BUY("4", "信用返済買"),MARGIN_REPAY_SELL("5","信用返済売"),
    FIX("6", "定期"), RECEIVED_TO_DEPOSIT("7", "保証金振替(預り金⇒保証金)"),DEPOSIT_TO_RECEIVED("8","保証金振替(保証金⇒預り金)"),
    PROTECTION_TO_SUBSTITUTION("9", "代用振替(保護⇒代用)"), SUBSTITUTION_TO_PROTECTION("10", "代用振替(代用⇒保護"),STORE_BUY("11","店頭買"),
    STORE_SELL("12", "店頭売");
    
    private final String id;
    
    private final String label;
    
    private ForeignStockTradeClass(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ForeignStockTradeClass valueOfId(String id) {
        
        ForeignStockTradeClass[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
