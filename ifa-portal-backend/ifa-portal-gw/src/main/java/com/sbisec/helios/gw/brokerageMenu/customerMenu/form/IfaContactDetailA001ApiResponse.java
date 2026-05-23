package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import lombok.Data;

/**
 * レスポンス
 * 画面ID:SUB0202_0106-06
 * 画面名:接触履歴詳細
 * アクション：A001 初期化
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactDetailA001ApiResponse {

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** 問合せNO */
    private String toiawaseNo;

    /** 問合せ日時 */
    private String toiawaseNichiji;
    
    /** 入力者 */
    private String userNm;
    
    /** カテゴリー名称（大） */
    private String toiawaseDMei;

    /** カテゴリー名称（中） */
    private String toiawaseMei;

    /** カテゴリー名称（小） */
    private String toiawaseSMei;

    /** 接触経路 */
    private String sessyokuKeiro;

    /** クレーム */
    private String cream;

    /** リクエスト */

    private String request;
    /** 重要度 */

    private String juuyoudo;

    /** 対応ステータス */
    private String taiouSts;

    /** 入電方向 */
    private String houkou;

    /** 訪問日 */
    private String houmonbi;

    /** 次回訪問予定日 */
    private String nextHoumonbi;

    /** 問合せ内容 */
    private String toiawaseNaiyou;

    /** 登録日時 */
    private String tourokuNichiji;

    /** IFA専用フラグ */
    private String ifaSenyouFlg;

    /** 回答内容情報リスト */
    private List<AnswerList> answerList;

    /**
     * 回答内容情報リストのレスポンスクラス
     */
    @Data
    public static class AnswerList {
        /** IFA問合せNO */
        private String ifaToiawaseNo;

        /** 問合せNO */
        private String toiawaseNo;

        /** IFA回答NO */
        private String ifaKaitouNo;

        /** 回答NO */
        private String kaitouNo;

        /** 入力者 */
        private String userNm;

        /** 回答内容 */
        private String kaitouNaiyou;

        /** 登録日時 */
        private String tourokuNichiji;
    }
}
