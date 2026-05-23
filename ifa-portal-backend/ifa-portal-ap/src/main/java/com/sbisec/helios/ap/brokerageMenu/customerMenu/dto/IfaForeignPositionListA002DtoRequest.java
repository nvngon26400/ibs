package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 米株建玉一覧 A002 リクエストパラメタ
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA002DtoRequest {
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 取引種別（全角半角）. */
    private String tradeClass;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 預り区分（全角半角）. */
    private String depositClassification;
    
    /** 新規売買区分（全角半角）. */
    private String newTradeClassification;
    
}
