package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 * A004リクエストDto
 *
 * @author SBI大連 夏
 * @date   2025/08/15
 */

@Data
public class IfaInquirySearchForManagerA004RequestDto {

    /** 仲介業者コード（数字）. */
    private String brokerCode;

    /** 仲介業者除外（半角英数字）. */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（全角半角）. */
    private String customerNameKanjiKana;

    /** 顧客名検索オプション. */
    private String customerNameKanjiKanaTerms;

    /** 問合せ日From. */
    private String inquiryDateYmFrom;

    /** 問合せ日To. */
    private String inquiryDateYmTo;

    /** 問合せカテゴリコード（大）. */
    private String toiawaseDCd;
    
    /** 問合せカテゴリコード（中） */
    private String toiawaseCd;
    
    /** 問合せカテゴリコード（小） */
    private String toiawaseSCd;
    
    /** クレーム/リクエスト. */
    private String cr;
    
    /** 重要度. */
    private List<IfaInquirySearchForManagerRequestSelected> juuyoudo;
    
    /** 対応ステータス. */
    private List<IfaInquirySearchForManagerRequestSelected> taiouSts;
    
    /** 入力者(ID). */
    private String nyuuryokuShaId;
    
    /** 入力者(氏名). */
    private String nyuuryokuShaName;
    
}
