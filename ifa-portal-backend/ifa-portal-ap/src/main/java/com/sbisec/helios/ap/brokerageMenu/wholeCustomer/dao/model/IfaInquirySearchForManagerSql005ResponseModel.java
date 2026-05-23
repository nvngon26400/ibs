package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Data
public class IfaInquirySearchForManagerSql005ResponseModel {

    /** 総件数. */
    private int totalRow;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 顧客名_姓名(漢字). */
    private String nameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String nameKana;
    
    /** カテゴリー名称（大）. */
    private String toiawaseDMei;
    
    /** カテゴリー名称（中）. */
    private String toiawaseMei;
    
    /** カテゴリー名称（小）. */
    private String toiawaseSMei;
    
    /** クレーム. */
    private String cream;
    
    /** リクエスト. */
    private String request;
    
    /** 重要度. */
    private String juuyoudo;
    
    /** 入電方向. */
    private String houkou;
    
    /** 対応ステータス. */
    private String taiouSts;
    
    /** 問合せ日時. */
    private String toiawaseNichiji;
    
    /** 回答日時. */
    private String kaitouNichiji;
    
    /** 問合せ内容. */
    private String toiawaseNaiyou;
    
    /** 回答内容. */
    private String kaitouNaiyou; 
    
    /** 問合せユーザ名. */
    private String toiawaseUserName;
    
    /** 回答ユーザ名. */
    private String kaitouUserName;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 支店コード. */
    private String branchCode;
    
    /** 支店名 */
    private String branchName;
    
    /** 営業員コード. */
    private String brokerChargeCode;
    
    /** 営業員名. */
    private String brokerChargeName;
    
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    
    /** IFA回答NO */
    private String ifaKaitouNo;
    
    /** 登録グループ */
    private String tourokuGroup;
    
}
