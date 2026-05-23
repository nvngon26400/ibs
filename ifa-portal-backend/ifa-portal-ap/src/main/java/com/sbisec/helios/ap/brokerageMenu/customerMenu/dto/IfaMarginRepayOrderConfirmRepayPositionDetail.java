package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用返済注文確認返済建玉明細.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmRepayPositionDetail {
    
    /** 返済建玉明細.親株新規約定日 */
    private String parentStockTradeDate;
    
    /** 返済建玉明細.新規建日 */
    private String constructionDate;
    
    /** 返済建玉明細.新規単価 */
    private String newPrice;
    
    /** 返済建玉明細.注文株数 */
    private String orderQuantity;
    
    /** 返済建玉明細.建市場 */
    private String builtMarket;
    
    /** 返済建玉明細.建玉No */
    private String positionNo;
    
    /** 返済建玉明細.残高数量 */
    private String contPosition;
    
    /** 返済建玉明細.諸経費 */
    private String cost;
    
    /** 返済建玉明細.返済注文済未出来数量 */
    private String unactualQuantity;
    
    /** 返済建玉明細.特定建玉区分 */
    private String specificPositionType;
    
}
