package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql2リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql002RequestModel {

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;
}
