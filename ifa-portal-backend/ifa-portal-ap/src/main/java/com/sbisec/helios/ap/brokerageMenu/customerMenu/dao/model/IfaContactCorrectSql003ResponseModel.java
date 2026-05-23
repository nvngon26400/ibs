package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL003用レスポンスモデル
 * 画面ID:SUB0202_0106-07
 * 画面名:接触履歴修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactCorrectSql003ResponseModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** 顧客ID */
    private String kokyakuId;
    /** ユーザID */
    private String userId;
    /** 更新ユーザID */
    private String kosinUserId;
    /** 削除ユーザID */
    private String sakujoUserId;
    /** 問合せカテゴリコード（中） */
    private String toiawaseCd;
    /** カテゴリー名称（中） */
    private String toiawaseMei;
    /** 問合せカテゴリコード（大） */
    private String toiawaseDCd;
    /** カテゴリー名称（大） */
    private String toiawaseDMei;
    /** 問合せ内容 */
    private String toiawaseNaiyou;
    /** 問合せ日時 */
    private String toiawaseNichiji;
    /** 重要度 */
    private String juuyoudo;
    /** クレーム */
    private String cream;
    /** リクエスト */
    private String request;
    /** 方向 */
    private String houkou;
    /** 対応ステータス */
    private String taiouSts;
    /** 登録日時 */
    private String tourokuNichiji;
    /** 変更日時 */
    private String henkouNichiji;
    /** 削除日時 */
    private String sakujoNichiji;
    /** 削除フラグ */
    private String sakujoFlg;
    /** 訪問日 */
    private String houmonbi;
    /** 次回訪問予定日 */
    private String nextHoumonbi;
    /** 問合せカテゴリコード（小） */
    private String toiawaseSCd;
    /** カテゴリー名称（小） */
    private String toiawaseSMei;
    /** 接触経路 */
    private String sessyokuKeiro;
    /** IFA入力フラグ */
    private String ifaNyuuryokuFlg;
    /** CCSユーザID */
    private String ccsUserId;
    /** ユーザ名 */
    private String userName;
}
