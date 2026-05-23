package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 顧客選択 SQL002 抽出 リクエスト
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectSql002RequestModel {

    /** 部店. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** お気に入り登録状況（数字）. */
    private String favoRegStatus;

    /** 顧客コード（数字）. */
    private String customerCode;

    /** ユーザーID（全角半角）. */
    private String userId;

}
