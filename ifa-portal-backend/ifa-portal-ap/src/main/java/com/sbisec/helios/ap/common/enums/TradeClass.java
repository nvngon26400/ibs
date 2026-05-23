package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 取引種別
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum TradeClass {
    
    // TODO 区分定義値未設定
      BUY("0", "買付")
    , SELL("1", "売却")
    , SEPARATE("2","立会外分売")
    , RETAIL_OFFER("3", "立会外トレード")
    , FRACTION_BUY("4", "単元未満買")
    , FRACTION_SELL("5","単元未満売")
    , MARGIN_NEW_BUY("6", "信用新規買")
    , MARGIN_NEW_SELL("7", "信用新規売")
    , MARGIN_REPAY_BUY("8", "信用返済買")
    , MARGIN_REPAY_SELL("9", "信用返済売")
    , RECEIPT("10", "現引")
    , DELIVERY("11", "現渡")
    , BB("12", "BB申込")
    , SUBSCRIPT("13", "募集")
    , STORE_BUY("14", "店頭買")
    , STORE_SELL("15", "店頭売")
    , WITHDRAW("16", "出金")
    , DEPOSIT("17", "入金")
    , DEPOSIT_TRANSFER_AZUKARI("18", "保証金振替（預り金⇒保証金）")
    , DEPOSIT_TRANSFER_HOSHOU("19", "保証金振替（保証金⇒預り金）")
    , COLLATERAL_TRANSFER_PROTECTION("20", "代用振替（保護⇒代用）")
    , COLLATERAL_TRANSFER_SUBSTITUTION("21", "代用振替（代用⇒保護）")
    ;
    
    private final String id;
    
    private final String label;
    
    private TradeClass(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static TradeClass valueOfId(String id) {
        
        TradeClass[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
