package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 東証リスト
 *
 * @author SCSK
 */
@Data
public class TokyoSecurity {
    
    /** 市場（全角）. */
    private String market;
    
    /** 建玉金額合計（数値(整数)）. */
    private String totalPrice;
    
    /** 評価額合計（前日）. */
    private String valuationTotalPreviousDay;
    
    /** 評価額合計（リアル）. */
    private String valuationTotalReal;
    
    /** 諸費用合計. */
    private String expensesTotal;
    
    /** 評価損益合計（前日）. */
    private String domesticPositionValuationTotalPreviousDay;
    
    /** 評価損益合計（リアル）. */
    private String domesticPositionValuationTotalReal;
    
}
