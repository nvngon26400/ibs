package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 現引現渡注文確認SQLリクエスト
 * 2024/04/01 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaReceiptDeliveryOrderConfirmSql002RequestModel {
    
    /** IFA注文番号（数字） */
    private String ifaOrderNo;
    
    /** 銘柄コード（半角英数字） */
    private String brandCode;
    
    /** 新規売買区分 */
    private String openTradeKbn;
    
    /** 新規市場 */
    private String openMarket;
    
    /** 弁済期限 */
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
    
    /** 預り区分 */
    private String depositType;
    
    /** 注文数量 */
    private String quantity;
    
    /** 作成者 */
    private String createUser;
    
    /** 更新者 */
    private String updateUser;
}
