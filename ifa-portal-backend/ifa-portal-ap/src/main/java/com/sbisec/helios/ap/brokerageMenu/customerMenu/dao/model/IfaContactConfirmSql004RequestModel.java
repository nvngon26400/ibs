package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL004用リクエストモデル
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactConfirmSql004RequestModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** IFA回答NO */
    private String ifaKaitouNo;
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
    /** CCS更新ユーザID */
    private String ccsKosinUserId;
    /** CCS削除ユーザID */
    private String ccsSakujoUserId;
    /** ユーザ名 */
    private String userName;
    /** 登録グループ */
    private String tourokuGroup;
}
