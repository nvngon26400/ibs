package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * SQL002用レスポンスモデル
 * 画面ID:SUB0202_0106-03
 * 画面名:接触履歴入力
 * 2025/04/24 新規作成
 *
 * @author 大連 王永宝
 */
@Data
public class IfaContactInputSql002ResponseModel {
    /** 問合せカテゴリコード（大） */
    private String toiawaseDCd;
    /** 問合せカテゴリコード（中） */
    private String toiawaseCd;
    /** 問合せカテゴリコード（小） */
    private String toiawaseSCd;
    /** 問合せ内容 */
    private String toiawaseNaiyou;
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
    /** 接触経路 */
    private String sessyokuKeiro;
    /** 問合せNO */
    private String toiawaseNo;
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    /** 登録日時 */
    private String tourokuNichiji;
    /** IFA入力フラグ */
    private String ifaNyuuryokuFlg;
}
