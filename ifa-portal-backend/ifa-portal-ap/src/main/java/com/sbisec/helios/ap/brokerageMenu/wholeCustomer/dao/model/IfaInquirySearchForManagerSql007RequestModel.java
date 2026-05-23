package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import java.util.List;
import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;
import com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto.IfaInquirySearchForManagerRequestSelected;

import lombok.Data;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/09/12
 */

@Data
public class IfaInquirySearchForManagerSql007RequestModel {

    /** 仲介業者コード（数字）. */
    private List<String> brokerCodeList;

    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字）. */
    private List<String> branchCodeList;

    /** 営業員コード（半角英数字）. */
    private List<String> empCodeList;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（全角半角）. */
    private String customerName;

    /** 顧客名検索オプション. */
    private String customerNameSearchType;

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
    
    /** クレーム. */
    private String cream;
    
    /** リクエスト. */
    private String request;
    
    /** 重要度. */
    private List<IfaInquirySearchForManagerRequestSelected> juuyoudo;
    
    /** 対応ステータス. */
    private List<IfaInquirySearchForManagerRequestSelected> taiouSts;
    
    /** 入力者(ID). */
    private String nyuuryokuShaId;
    
    /** 入力者(氏名). */
    private String nyuuryokuShaName;

    /** 権限コード（全角半角）. */
    private String privId;

    /** FCT030.仲介業者営業員リスト. */
    private List<BrokerCharge> brokerChargeList;
    
}
