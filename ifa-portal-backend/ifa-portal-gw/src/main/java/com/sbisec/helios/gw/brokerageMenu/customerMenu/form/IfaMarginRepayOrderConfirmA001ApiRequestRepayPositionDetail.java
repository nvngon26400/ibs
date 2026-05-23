package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 信用返済注文確認注文発注APIリクエスト返済建玉明細.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmA001ApiRequestRepayPositionDetail {
    
    /** 親株新規約定日. */
    @NotEmpty(message = "親株新規約定日")
    private String parentStockTradeDate;
    
    /** 新規建日. */
    @NotEmpty(message = "新規建日")
    private String constructionDate;
    
    /** 新規単価（数値(小数)）. */
    @NotEmpty(message = "新規単価")
    private String newPrice;
    
    /** 注文株数（数値(整数)）. */
    @NotEmpty(message = "注文株数")
    private String orderQuantity;
    
    /** 建市場（全角半角）. */
    @NotEmpty(message = "建市場")
    private String builtMarket;
    
    /** 建玉No（数字）. */
    @NotEmpty(message = "建玉No")
    private String positionNo;
    
    /** 残高数量（数値(整数)）. */
    @NotEmpty(message = "残高数量")
    private String contPosition;
    
    /** 諸経費（数値(整数)）. */
    @NotEmpty(message = "諸経費")
    private String cost;
    
    /** 返済注文済未出来数量（数値(整数)）. */
    @NotEmpty(message = "返済注文済未出来数量")
    private String unactualQuantity;
    
    /** 特定建玉区分（半角英数字）. */
    @NotEmpty(message = "特定建玉区分")
    private String specificPositionType;
    
}
