package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL006用リクエストモデル
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactConfirmSql006RequestModel {
    /** 問合せNO */
    private String toiawaseNo;
    /** 回答NO */
    private String kaitouNo;
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
}
