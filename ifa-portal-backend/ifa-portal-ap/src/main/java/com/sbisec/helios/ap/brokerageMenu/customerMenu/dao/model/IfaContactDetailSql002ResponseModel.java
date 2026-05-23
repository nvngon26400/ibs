package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL002用レスポンスモデル
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactDetailSql002ResponseModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
    /** 回答NO */
    private String kaitouNo;
    /** 登録グループ */
    private String tourokuGroup;
    /** ユーザ名 */
    private String userNm;
    /** 回答内容 */
    private String kaitouNaiyou;
    /** 登録日時 */
    private String tourokuNichiji;
}
