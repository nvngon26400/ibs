package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 顧客選択 SQL002 レスポンス
 *
 * @author SCSK
 */
@Data
public class IfaCustomerSelectSql002ResponseModel {
    /** ユーザID. */
    private String userId;
    /** 顧客コード. */
    private String customerId;
    /** お気に入り. */
    private String favorite;
}
