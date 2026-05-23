package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * レスポンス
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * アクション：A002 登録
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactConfirmA002ApiResponse {

    /** 問合せカテゴリコード */
    private String toiawaseCd;

    /** カテゴリー名称 */
    private String toiawaseMei;

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
