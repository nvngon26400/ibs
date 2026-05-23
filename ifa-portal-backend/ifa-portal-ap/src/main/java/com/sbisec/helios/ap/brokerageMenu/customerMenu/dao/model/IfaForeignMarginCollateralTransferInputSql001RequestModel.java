package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 米株信用代用振替入力 SQL001 リクエストモデル
 *
 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferInputSql001RequestModel {

    /** 部店. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 注文日. */
    private String orderDay;
    
    /** 売買区分（全角半角）. */
    private String tradeType;
    
    /** ステータス. */
    private String orderStatus;

}
