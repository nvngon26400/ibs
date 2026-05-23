package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * リクエスト
 * 画面ID：SUB0202_0106-03
 * 画面名：接触履歴入力
 * アクション：A002 確認
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
@JsonSerialize
public class IfaContactInputA002ApiRequest {

    /** 問合せカテゴリコード（大） */
    private String toiawaseDCd;
    
    /** カテゴリー名称（大） */
    private String toiawaseDMei;
    
    /** 問合せカテゴリコード（中） */
    private String toiawaseCd;
    
    /** カテゴリー名称（中） */
    private String toiawaseMei;
    
    /** 問合せカテゴリコード（小） */
    private String toiawaseSCd;

    /** カテゴリー名称（小） */
    private String toiawaseSMei;

    /** 接触経路 */
    private String sessyokuKeiro;

    /** 重要度 */
    private String juuyoudo;

    /** クレーム */
    private String cream;

    /** リクエスト */
    private String request;

    /** 対応ステータス */
    private String taiouSts;

    /** 入電方向 */
    private String houkou;

    /** 訪問日 */
    private String houmonbi;

    /** 次回訪問予定日 */
    private String nextHoumonbi;

    /** 内容 */
    private String toiawaseNaiyou;

    /** 回答 */
    private String kaitouNaiyou;

    /** 処理区分 */
    private String operationType;

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** IFA回答NO */
    private String ifaKaitouNo;

    /** 問合せNO */
    private String toiawaseNo;

    /** 回答NO */
    private String kaitouNo;
}
