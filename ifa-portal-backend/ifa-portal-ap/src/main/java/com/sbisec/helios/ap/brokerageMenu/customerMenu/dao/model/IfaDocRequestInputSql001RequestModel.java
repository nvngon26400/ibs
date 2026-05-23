package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 書類請求入力sql1リクエスト
 *
 * @author xin.huang
 * 
 */
@Data
public class IfaDocRequestInputSql001RequestModel {

    /** 書類区分 */
    private String shoruiKbn;

    /** 削除フラグ */
    private String sakujoFlg;
}
