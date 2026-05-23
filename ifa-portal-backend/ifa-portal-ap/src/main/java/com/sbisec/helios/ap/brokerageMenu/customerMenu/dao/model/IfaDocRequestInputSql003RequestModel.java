package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql3リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql003RequestModel {

    /** 書類区分 */
    private String shoruiKbn;

    /** 分類コード */
    private String bunruiCd;

    /** 削除フラグ */
    private String sakujoFlg;
}
