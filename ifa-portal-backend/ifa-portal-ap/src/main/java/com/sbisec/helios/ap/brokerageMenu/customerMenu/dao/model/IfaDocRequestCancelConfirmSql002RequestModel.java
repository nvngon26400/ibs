package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * BM交付取消sql2リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestCancelConfirmSql002RequestModel {

    /** BM交付番号 */
    private String bmDeliveryNo;

    /** ユーザーID */
    private String userId;
}
