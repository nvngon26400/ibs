package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求取消sql1リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestCancelConfirmSql001RequestModel {

    /** 書類請求NO */
    private String shoruiSeikyuuNo;

    /** 枝番 */
    private String edaban;

    /** ユーザーID */
    private String userId;

    /** 取消区分 */
    private String torikeshiKbn;
}
