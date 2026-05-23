package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 預り残高詳細 SQL001 抽出 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaDepositBalanceDetailSql001RequestModel {

    /** 回数（数値(整数)）. */
//    private String times;
    
    /** 号2（全角半角）. */
//    private String issue2;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

}
