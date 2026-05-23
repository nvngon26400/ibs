package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-04_1
 * 画面名：信用返済注文入力
 * 返済建玉明細
 * 2024/04/08 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderInputApiRepayPositionDetail {
    
    /** 建市場 */
    private String builtMarket;
    
    /** 新規建日（新規約定日） */
    private String constructionDate;
    
    /** 親株新規約定日 */
    private String parentStockTradeDate;
    
    /** 新規単価 */
    private String newPrice;
    
    /** 建玉No */
    private String positionNo;
    
    /** 残高数量 */
    private String contPosition;
    
    /** 諸経費 */
    private String cost;
    
    /** 返済注文済未出来数量 */
    private String unactualQuantity;
    
    /** 注文株数 */
    private String orderQuantity;
    
    /** 評価損益（リアル） */
    private String profitAndLossReal;
    
    /** 特定建玉区分 */
    private String specificPositionType;
    
    /** 権利区分 */
    private String rightType;
    
}
