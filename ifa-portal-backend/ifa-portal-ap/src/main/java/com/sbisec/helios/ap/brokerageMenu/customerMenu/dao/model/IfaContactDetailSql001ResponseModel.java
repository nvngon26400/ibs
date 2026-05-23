package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL001用レスポンスモデル
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactDetailSql001ResponseModel {
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** 問合せNO */
    private String toiawaseNo;
    /** 問合せ日時 */
    private String toiawaseNichiji;
    /** ユーザ名 */
    private String userNm;
    /** カテゴリー名称（大） */
    private String toiawaseDMei;
    /** カテゴリー名称（中） */
    private String toiawaseMei;
    /** カテゴリー名称（小） */
    private String toiawaseSMei;
    /** 接触経路 */
    private String sessyokuKeiro;
    /** 訪問日 */
    private String houmonbi;
    /** クレーム */
    private String cream;
    /** リクエスト */
    private String request;
    /** 重要度 */
    private String juuyoudo;
    /** 対応ステータス */
    private String taiouSts;
    /** 方向 */
    private String houkou;
    /** 次回訪問予定日 */
    private String nextHoumonbi;
    /** 問合せ内容 */
    private String toiawaseNaiyou;
    /** 登録日時 */
    private String tourokuNichiji;
    /** IFA専用フラグ */
    private String ifaSenyouFlg;

}
