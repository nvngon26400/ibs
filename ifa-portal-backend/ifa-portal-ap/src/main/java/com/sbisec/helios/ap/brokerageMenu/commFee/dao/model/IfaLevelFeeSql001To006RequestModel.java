package com.sbisec.helios.ap.brokerageMenu.commFee.dao.model;

import java.util.List;

import com.sbisec.helios.ap.bizcommon.model.OutputFct030Dto.BrokerCharge;

import lombok.Data;

/**
 * 画面ID：SUB020505-01
 * 画面名：残高連動手数料・報酬
 * 2025/06/02 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaLevelFeeSql001To006RequestModel {

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

    /** FCT030.仲介業者営業員リスト */
    private List<BrokerCharge> brokerChargeList;

    /** 最大件数 */
    private int maxRowNum;

    /** ユーザ共通情報.権限コード  */
    private String privId;

}
