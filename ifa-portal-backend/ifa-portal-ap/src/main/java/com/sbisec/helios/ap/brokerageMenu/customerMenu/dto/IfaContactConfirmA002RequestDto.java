package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * サービス用リクエストモデル
 * 画面ID:SUB0202_0106-04
 * 画面名:接触履歴入力確認
 * アクション：A002 登録
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactConfirmA002RequestDto {

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

    /** 問合せ内容 */
    private String toiawaseNaiyou;

    /** 回答内容 */
    private String kaitouNaiyou;

    /** 処理区分 */
    private String operationType;

    /** IFA問合せNO */
    private String ifaToiawaseNo;

    /** 問合せNO */
    private String toiawaseNo;

    /** IFA回答NO */
    private String ifaKaitouNo;

    /** 回答NO */
    private String kaitouNo;
    
    /** IFA入力フラグ */
    private String ifaNyuuryokuFlg;

}
