package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 注文状況一覧：訂正ステータス・RBE訂正ステータスから変数訂正ステータスに変換
 * 
 * @author 齋藤
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum ConvertCalcVarModStatus {
    
    CSS("SS", "S"), CS1("S1", "1"), CS2("S2", "2"), CS3("S3", "3"), 
    C1S("1S", "1"), C11("11", "1"), C12("12", "2"), C13("13", "1"), 
    C2S("2S", "2"), C21("21", "2"), C22("22","2"), C23("23", "2"), 
    C3S("3S", "3"), C31("31", "1"), C32("32", "2"), C33("33", "3");
    
    private final String id;
    
    private final String label;
    
    private ConvertCalcVarModStatus(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static ConvertCalcVarModStatus valueOfId(String id) {
        
        ConvertCalcVarModStatus[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
