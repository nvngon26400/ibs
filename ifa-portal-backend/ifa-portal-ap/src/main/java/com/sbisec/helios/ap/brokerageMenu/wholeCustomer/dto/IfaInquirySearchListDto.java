package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import java.util.List;
import lombok.Data;

/**
 * 画面ID:SUB020304-01
 * 画面名:接触履歴（入力）検索
 *
 * @author SBI大連 夏
 * @date   2025/08/18
 */

@Data
public class IfaInquirySearchListDto {

    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 顧客名_姓名(漢字). */
    private String nameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String nameKana;
    
    /** 営業員コード. */
    private String brokerChargeCode;
    
    /** 営業員名. */
    private String brokerChargeName;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 支店コード. */
    private String branchCode;
    
    /** 支店名 */
    private String branchName;
    
    /** カテゴリ. */
    private String toiawaseMei;
    
    /** クレーム/リクエスト. */
    private String cr;
    
    /** 重要度. */
    private String juuyoudo;
    
    /** 入電方向. */
    private String houkou;
    
    /** 対応ステータス. */
    private String taiouSts;
    
    /** 内容. */
    private List<IfaInquiryDetailListDto> inquiryDetailList;
    
    /** IFA問合せNO */
    private String ifaToiawaseNo;
    
    /** IFA回答NO */
    private String ifaKaitouNo;
    
    /** 登録グループ */
    private String tourokuGroup;
    
}
