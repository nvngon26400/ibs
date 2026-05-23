package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL003用リクエストモデル
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactConfirmSql003RequestModel {
    /** 問合せカテゴリコード（中） */
    private String toiawaseCd;
    /** カテゴリー名称（中） */
    private String toiawaseMei;
    /** 問合せカテゴリコード（大） */
    private String toiawaseDCd;
    /** カテゴリー名称（大） */
    private String toiawaseDMei;
    /** 問合せカテゴリコード（小） */
    private String toiawaseSCd;
    /** カテゴリー名称（小） */
    private String toiawaseSMei;
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
    /** 変更日時 */
    private String henkouNichiji;
    /** 訪問日 */
    private String houmonbi;
    /** 次回訪問予定日 */
    private String nextHoumonbi;
    /** 接触経路 */
    private String sessyokuKeiro;
    /** 更新ユーザID */
    private String kosinUserId;
    /** CCS更新ユーザID */
    private String ccsKosinUserId;
    /** IFA問合せNO */
    private String ifaToiawaseNo;
}
