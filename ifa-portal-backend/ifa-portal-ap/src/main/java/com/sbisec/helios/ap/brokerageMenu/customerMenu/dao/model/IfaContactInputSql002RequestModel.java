package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL002用リクエストモデル
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputSql002RequestModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** 削除フラグ */
    private String sakujoFlg;
}
