package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dto;

import com.sbibits.earth.model.ModelBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 取引動向検索
 * 2025/04/10 新規作成
 *
 * @author 大連 苗
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class IfaTradeTrendSearchCsvItems extends ModelBase {

    /** シリアルバージョンID */
    private static final long serialVersionUID = 6651543506903761494L;

    /** 仲介業者コード */
    private String brokerCode;

    /** 仲介業者名 */
    private String brokerName;

    /** 営業員名 */
    private String brokerChargeName;

    /** 部店コード */
    private String butenCode;

    /** 口座番号 */
    private String accountNumber;

    /** 顧客名 */
    private String nameKanji;

    /** 年齢 */
    private String age;

    /** Cランク */
    private String tcCompRank;

    /** 取引コース */
    private String customerAttribute;

    /** 職業区分 */
    private String occupation;

    /** 投資方針 */
    private String investmentPlan;

    /** 資金性格 */
    private String fundCharacter;

    /** 運用期間 */
    private String emloymentPeriod;

    /** 収入区分 */
    private String incomeForm;

    /** 年収 */
    private String annualIncome;

    /** 金融資産 */
    private String financialAssets;

    /** 投資経験年数（株式現物） */
    private String uaiExpStock;

    /** 投資経験年数（信用） */
    private String uaiExpMargin;

    /** 投資経験年数（その他投信） */
    private String uaiExpOtherfund;

    /** 投資経験年数（外国証券） */
    private String uaiExpForeign;

    /** 営業員コード */
    private String empCode;

    /** 扱者コード */
    private String dealerNumber;

    /** 支店コード */
    private String brokerBranchCode;

    /** 支店名 */
    private String branchName;

    /** 閲覧可能部店 */
    private String visibleButenCode;

    /** 取引回数 */
    private String tradeCount;

    /** 調整後回数 */
    private String adjustmentTradeCount;

    /** 約定総金額（円貨） */
    private String grossAmountYen;

    /** 預り資産 */
    private String assets;

    /** 資金回転率 */
    private String grossAmountYenAssets;

    /** 手数料 */
    private String fee;

    /** 担当者手数料 */
    private String rewardPrice;

    /** 手数料依存率 */
    private String feeRewardPrice;

    /** 評価額 */
    private String currenctPrice;

    /** 評価損益 */
    private String profitAndLoss;

    /** 評価損益率 */
    private String profitAndLossCurrenctPrice;

    /** 前月比評価損 */
    private String currenctPriceMonth;

    /** 年間実現損益 */
    private String yearRealProfitAndLoss;

    /** 当月実現損益 */
    private String monthlyRealProfitAndLoss;

    /** 期間指定実現損益 */
    private String periodRealProfitAndLoss;

}
