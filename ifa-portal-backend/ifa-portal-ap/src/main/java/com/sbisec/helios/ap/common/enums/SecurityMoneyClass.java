package com.sbisec.helios.ap.common.enums;

import org.apache.commons.codec.binary.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

/**
 * 証券金銭種別
 * 
 * @author 松田
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum SecurityMoneyClass {
    
    DOMESTIC_STOCKS("01", "国内株式")
    , DOMESTIC_INVESTMENT_TRUSTS("06", "国内投信")
    , YEN_DENOMINATED_BOND("12","円貨建債券")
    , FOREIGN_STOCK("15", "外国株式")
    , FOREIGN_MMF("16", "外貨建MMF")
    , FOREIGN_INVESTMENT_TRUSTS("08","外国投信")
    , FOREIGN_BOND("14", "外貨建債券")
    , YEN("99", "円貨")
    , FOREIGN("98", "外貨");
    
    private final String id;
    
    private final String label;
    
    private SecurityMoneyClass(String id, String label) {
        
        this.id = id;
        this.label = label;
    }
    
    public String getId() {
        
        return id;
    }
    
    public String getLabel() {
        
        return label;
    }
    
    public static SecurityMoneyClass valueOfId(String id) {
        
        SecurityMoneyClass[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getId(), id))
                return enums[i];
        }
        
        return null;
    }
}
