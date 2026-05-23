package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaMatchedTradeDetailCommonRequestDto {
    
    /** 約定日. */
    private String tradeDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 取引区分. */
    private String tradeClassification;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 信用取引区分（全角半角）. */
    private String marginTradeTypeText;
    
    /** 非特定預り売買区分（全角半角）. */
    private String notSpecificDepositTradeType;
    
}
