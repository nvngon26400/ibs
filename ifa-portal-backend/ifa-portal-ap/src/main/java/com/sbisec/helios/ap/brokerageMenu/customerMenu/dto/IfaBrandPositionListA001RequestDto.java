package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 銘柄別建玉一覧A001リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaBrandPositionListA001RequestDto {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 新規売買区分（全角半角）. */
    private String openTradeKbn;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 並替順序. */
    private String sortOrder;
    
}
