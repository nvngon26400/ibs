package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL005用レスポンスモデル
 * 画面ID:SUB0202_0106-05
 * 画面名:接触履歴入力
 * 2025/07/28 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputSql005ResponseModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** 回答NO */
    private String kaitouNo;
    /** 登録グループ */
    private String tourokuGroup;
    /** 回答内容 */
    private String kaitouNaiyou;
}
