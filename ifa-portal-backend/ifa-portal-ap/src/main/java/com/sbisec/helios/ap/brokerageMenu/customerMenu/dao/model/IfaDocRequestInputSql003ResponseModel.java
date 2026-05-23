package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql3レスポンスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql003ResponseModel {

    /** 書類コード */
    private String shoruiCd;

    /** 書類名 */
    private String shoruimei;
}
