package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用返済注文確認SQL002リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 新規売買区分（全角半角）. */
    private String openTradeKbn;
    
    /** 新規市場. */
    private String openMarket;
    
    /** 弁済期限（全角半角）. */
    private String paymentLimit;
    
    /** 親株新規約定日 */
    private String orgNewTradeDate;
    
    /** 新規約定日 */
    private String openTradeDate;
    
    /** 取得単価 */
    private String openPrice;
    
    /** 残高数量 */
    private String contPosition;
    
    /** 返済注文済未出来数量 */
    private String unactualQuantity;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 注文数量（数値(整数)）. */
    private String quantity;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;
}
