package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 現引現渡注文取消確認　A002レスポンス
 * 2024/05/21 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaReceiptDeliveryOrderCancelConfirmA002ResponseDto {

    /** 受注日時. */
    private String orderDayTime;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 市場（全角）. */
    private String market;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 数量（数値(整数)）. */
    private String unTradeQuantity;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 信用取引区分（全角半角）. */
    private String marginTradeTypeText;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 新規単価（数値(小数)）. */
    private String newPrice;
    
    /** 新規建日. */
    private String newTradeDate;
    
    /** 建玉No（数字）. */
    private String newPositionNumber;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 手数料区分（採用） */
    private String comIdR;

}
