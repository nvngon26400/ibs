package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * IFA問合せ・回答エラーテーブル用リクエストモデル
 *
 * @author lianhua.xia
 * @date 2025-01-22
 */
@Data
public class IfaContactSqlCommonRequestModel {
    /** 処理区分 */
    private String processId;
    /** 問合せNO */
    private String qToiawaseNo;
    /** 顧客ID */
    private String qKokyakuId;
    /** ユーザID */
    private String qUserId;
    /** 更新ユーザID */
    private String qKosinUserId;
    /** 問合せカテゴリコード（中） */
    private String qToiawaseCd;
    /** カテゴリー名称（中） */
    private String qToiawaseMei;
    /** 問合せカテゴリコード（大） */
    private String qToiawaseDCd;
    /** カテゴリー名称（大） */
    private String qToiawaseDMei;
    /** 問合せカテゴリコード（小） */
    private String qToiawaseSCd;
    /** カテゴリー名称（小） */
    private String qToiawaseSMei;
    /** 問合せ内容 */
    private String qToiawaseNaiyou;
    /** 問合せ日時 */
    private String qToiawaseNichiji;
    /** 重要度 */
    private String qJuuyoudo;
    /** クレーム */
    private String qCream;
    /** リクエスト */
    private String qRequest;
    /** 方向 */
    private String qHoukou;
    /** 対応ステータス */
    private String qTaiouSts;
    /** 訪問日 */
    private String qHoumonbi;
    /** 次回訪問予定日 */
    private String qNextHoumonbi;
    /** 問合せ登録日時 */
    private String qTourokuNichiji;
    /** 問合せ変更日時 */
    private String qHenkouNichiji;
    /** 問合せNO */
    private String aToiawaseNo;
    /** 回答NO */
    private String aKaitouNo;
    /** ユーザID */
    private String aUserId;
    /** 更新ユーザID */
    private String aKosinUserId;
    /** 回答内容 */
    private String aKaitouNaiyou;
    /** 回答日時 */
    private String aKaitouNichiji;
    /** 回答登録日時 */
    private String aTourokuNichiji;
    /** 回答変更日時 */
    private String aHenkouNichiji;
    /** 登録日時 */
    private String tourokuNichiji;
    /** 変更日時 */
    private String henkouNichiji;
    /** 削除日時 */
    private String sakujoNichiji;
    /** 処理フラグ */
    private String syoriFlg;
    /** APIフラグ */
    private String apiFlg;
    /** APIエラー回数 */
    private String apiErrCnt;
    /** Exception回数 */
    private String expErrCnt;
}
