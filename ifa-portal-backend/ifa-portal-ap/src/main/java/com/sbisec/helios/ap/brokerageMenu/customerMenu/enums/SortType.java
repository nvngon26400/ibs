package com.sbisec.helios.ap.brokerageMenu.customerMenu.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import org.apache.commons.codec.binary.StringUtils;

/**
 * 分類表示区分
 * 
 * @author SCSK
 *
 */
@JsonFormat(shape = Shape.OBJECT)
public enum SortType {
    
    ONE("1", "■入金額"),
    TWO("2", "■支払額"),
    THREE("3", "■未約定買注文金額"),
    FOUR("4", "■出金・振替指示額"),
    FIVE("5", "■受取額"),
    SIX("6", "■未約定信用決済損");
    
    /** リクエストタイプ */
    private final String value;
    
    /** 分類表示名　*/
    private final String sortName;
    
    /** 定数値の設定 */
    private SortType(final String value, final String sortName) {
        
        this.value = value;
        this.sortName = sortName;
    }
    
    /**
     * リクエストタイプの取得
     *
     * @return 文字列値
     */
    public String getValue() {
        
        return this.value;
    }
    
    /**
     * 分類表示名の取得
     *
     * @return 日本語値
     */
    public String getName() {
        
        return this.sortName;
    }
    
    public static SortType nameOfValue(String value) {
        
        SortType[] enums = values();
        
        for (int i = 0; i < enums.length; i++) {
            if (StringUtils.equals(enums[i].getValue(), value))
                return enums[i];
        }
        
        return null;
    }
}
