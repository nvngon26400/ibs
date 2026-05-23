package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * A001 初期化 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCollateralSecurityDeliverInOutDetailA001RequestDto {
    
    /** 受渡日. */
    private String settlementDate;
    
    /** 表示基準日（受渡日）（全角半角）. */
    private String displayBaseDate;
    
    /** 入出庫区分. */
    private String deliverInOutClassification;
    
}
