package com.sbisec.helios.ap.brokerageMenu.commFee.dto;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaLevelFeeA004aRequestDto {

    /** 仲介業者コード（数字） */
    private List<String> brokerCodeArray;

    /** 仲介業者除外（半角英数字） */
    private String chkBrokerCodeExclude;

    /** 支店コード（数字） */
    private String branchCode;

    /** 営業員コード（半角英数字）. */
    private String empCode;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;

    /** 顧客名（漢字／カナ）（全角半角）. */
    private String customerNameKanjiKana;

    /** 顧客名（漢字／カナ）_条件. */
    private String customerNameKanjiKanaTerms;

    /** 集計単位(日次/月次) */
    private String dailyMonthlyCountingUnit;

    /** 集計単位(仲介業者/営業員/支店) */
    private String brokerChargeBranchCountingUnit;
    
    /** 期間指定FROM */
    private String periodFrom;

    /** 期間指定TO */
    private String periodTo;

}
