package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：信用取引区分（算出）変換値
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertCalcDomesticMarginTradetypeText {
    
    ANY("0", "-"), SIX_MONTH("6", "6ヶ月"), NO_LIMIT("9", "無期限"), LONG_NO_LIMIT("C", "無期限"), ONE_DAY("D",
            "日計り"), ONE_DAY_PREMIUM("E", "日計りH"), NONE("S", "-");
    
    private final String id;
    
    private final String label;
    
    private ConvertCalcDomesticMarginTradetypeText(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ConvertCalcDomesticMarginTradetypeText valueOfId(String id) {
        
        ConvertCalcDomesticMarginTradetypeText[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
