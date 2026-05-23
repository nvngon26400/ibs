package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 米株信用代用振替確認 SQL001 リクエストモデル
 *
 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmSql001RequestModel {

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** システム日付. */
    private String systemDate;

}
