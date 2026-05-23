package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL005用レスポンスモデル
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactCorrectSql005ResponseModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** 回答NO */
    private String kaitouNo;
    /** ユーザID */
    private String userId;
    /** 更新ユーザID */
    private String kosinUserId;
    /** 削除ユーザID */
    private String sakujoUserId;
    /** 回答内容 */
    private String kaitouNaiyou;
    /** 回答日時 */
    private String kaitouNichiji;
    /** 登録日時 */
    private String tourokuNichiji;
    /** 変更日時 */
    private String henkouNichiji;
    /** 削除日時 */
    private String sakujoNichiji;
    /** 削除フラグ */
    private String sakujoFlg;
    /** 接触経路 */
    private String sessyokuKeiro;
    /** CCSユーザID */
    private String ccsUserId;
    /** ユーザ名 */
    private String userName;
    /** 登録グループ */
    private String tourokuGroup;
}
