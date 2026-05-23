package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * SUB0202_0212-08_1_現引現渡注文入力
 *
 * @author 池亀緑
 */
@Data
public class IfaReceiptDeliveryOrderInputA006DtoRequest {
    
    /** 銘柄コード */
    private String brandCode;

    /** 取引種別 */ 
    private String tradeCd;
    
    /** 数量 */ 
    private String quantity;
    
    /** 信用取引区分 */
    private String marginTradeTypeText;

    /** 特定・一般区分 */
    private String accountType;
    
    /** 建市場 */ 
    private String builtMarket;

    /** 新規建日 */ 
    private String constructionDate;

    /** 親株新規約定日 */ 
    private String parentStockTradeDate;

    /** 新規建玉指定番号 */
    private String newOpenInterestNumber;

    /** 新規単価 */ 
    private String newPrice;

    /** 勧誘区分 */
    private String kanyuKbn; 

    /** 受注方法 */
    private String receiveOrderType;
    
    /** 取得単価 */
    private String openPrice;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;
    
    /** 弁済期限（算出） */
    private String paymentDeadlineCalculation;

    /** 確認項目の契約締結前交付書面の確認 */
    private String checkCustomerAttribute;

}
