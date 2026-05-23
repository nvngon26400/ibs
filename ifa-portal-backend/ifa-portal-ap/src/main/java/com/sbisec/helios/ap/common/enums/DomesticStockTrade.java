package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 取引種別（国内株式）
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum DomesticStockTrade {
    
    STOCK_BUY("1", "現物買付"), STOCK_SELL("2", "現物売却"), MARGIN_NEW_BUY("3", "信用新規買"), MARGIN_NEW_SELL("4",
            "信用新規売"), MARGIN_BACK_BUY("5", "信用返済買"), MARGIN_BACK_SELL("6", "信用返済売"), ACTUAL_DELIVERLY("7",
                    "現渡"), TRANSACTION("8", "現引"), BB("9", "BB申込"), RECRUITMENT("A", "募集"), OFF_AUCTION_SELL("B",
                            "立会外分売"), OFF_AUCTION_TRADE("C",
                                    "立会外トレード"), TANGENMIMAN_BUY("D", "単元未満買"), TANGENMIMAN_SELL("E", "単元未満売");
    
    private final String id;
    
    private final String label;
    
    private DomesticStockTrade(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static DomesticStockTrade valueOfId(String id) {
        
        DomesticStockTrade[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
    
}
