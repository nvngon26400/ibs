package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;
import lombok.Data;

/**
 * レスポンス
 * 画面ID：SUB0202_0106-03
 * 画面名：接触履歴入力
 * アクション：X001 追加入力/管理項目修正
 * 2025/04/22 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputX001ApiResponse {

    /** 問合せカテゴリリスト（大） */
    private List<IfaContactToiawaseList> toiawaseDList;
    
    /** 問合せカテゴリリスト（中） */
    private List<IfaContactToiawaseList> toiawaseList;
    
    /** 問合せカテゴリリスト（小） */
    private List<IfaContactToiawaseList> toiawaseSList;

    /** 処理区分 */
    private String operationType;

    /** 問合せカテゴリコード（大） */
    private String toiawaseDCd;
    
    /** 問合せカテゴリコード（中） */
    private String toiawaseCd;
    
    /** 問合せカテゴリコード（小） */
    private String toiawaseSCd;

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

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** IFA回答NO */
    private String ifaKaitouNo;
    
    /** IFA入力フラグ */
    private String ifaNyuuryokuFlg;
    
    /** 回答情報リスト */
    private List<IfaContactAnswerList> answerList;
}
