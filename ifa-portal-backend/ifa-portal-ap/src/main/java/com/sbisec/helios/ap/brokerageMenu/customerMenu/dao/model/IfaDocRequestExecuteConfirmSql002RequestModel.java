package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql10リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestExecuteConfirmSql002RequestModel {

    /** 銘柄コード */
    private String fundCode;

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 法人区分 */
    private String corporationType;
}
