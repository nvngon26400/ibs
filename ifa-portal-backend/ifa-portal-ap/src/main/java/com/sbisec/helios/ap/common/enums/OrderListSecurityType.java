package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 商品区分（注文一覧）
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum OrderListSecurityType {
    
    DOMESTIC("0", "国内株式（現物・信用・現引・現渡）"), MUTUAL_FUND("1", "国内投資信託"), SUBSCRIPT("2", "募集注文"), FOREIGN_ENTRUST("3",
            "外国株式（委託注文）"), FOREIGN_COUNTER("4", "外国株式（店頭注文）"), MMF("5", "外貨建MMF");
    
    private final String id;
    
    private final String label;
    
    private OrderListSecurityType(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static OrderListSecurityType valueOfId(String id) {
        
        OrderListSecurityType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
