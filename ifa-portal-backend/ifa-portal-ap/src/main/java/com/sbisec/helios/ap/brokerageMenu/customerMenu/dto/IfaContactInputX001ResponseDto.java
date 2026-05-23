package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import java.util.List;

import lombok.Data;

/**
 * サービス用レスポンスモデル
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * アクション：X001 追加入力/管理項目修正
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputX001ResponseDto {

    /** 問合せカテゴリリスト（大） */
    private List<IfaContactToiawaseListDto> toiawaseDList;
    
    /** 問合せカテゴリリスト（中） */
    private List<IfaContactToiawaseListDto> toiawaseList;
    
    /** 問合せカテゴリリスト（小） */
    private List<IfaContactToiawaseListDto> toiawaseSList;

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

    /** 方向 */
    private String houkou;

    /** 対応ステータス */
    private String taiouSts;

    /** 訪問日 */
    private String houmonbi;

    /** 次回訪問予定日 */
    private String nextHoumonbi;

    /** 問合せ内容 */
    private String toiawaseNaiyou;

    /** 問合せNO */
    private String toiawaseNo;

    /** IFA問合せNO */
    private String ifaToiawaseNo;
    
    /** 回答情報リスト */
    private List<IfaContactAnswerListDto> answerList;
    
    /** IFA入力フラグ */
    private String ifaNyuuryokuFlg;
}
