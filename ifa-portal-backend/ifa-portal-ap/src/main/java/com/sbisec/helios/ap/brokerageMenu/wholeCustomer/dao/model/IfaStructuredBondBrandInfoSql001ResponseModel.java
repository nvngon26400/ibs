package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 * 仕組債銘柄情報　SQL001　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoSql001ResponseModel {
    
    /** STAR債券コード（全角半角）. */
    private String sbmBondCode;
    
    /** ISINコード（半角英数字）. */
    private String sbmIsinCode;
    
    /** 公募私募区分. */
    private String sbmSubscriptionWay;
    
    /** 債券名. */
    private String sbmBondName;
    
    /** 発行日. */
    private String sbmIssueDate;
    
    /** 満期償還日. */
    private String sbmPaymentDate;
    
    /** ノックインイベント区分. */
    private String sbmNockinEventKbn;
    
    /** 早期償還イベント区分. */
    private String sbmEarlyPaymentKbn;
    
    /** 取引所判断日数（全角半角）. */
    private String sbmBeforeTradeDays;
    
    /** ノンコール期間（数値(整数)）. */
    private String sbmNocallPeriod;
    
    /** 適用利率（数値(小数)）. */
    private String sbmCurrentRate;
    
    /** クーポン種類（全角半角）. */
    private String sbmCouponType;
    
    /** 固定クーポン. */
    private String sbmFixCoupon;
    
    /** 固定クーポン回数（数値(整数)）. */
    private String sbmFixCouponTimes;
    
    /** ハイ判定水準. */
    private String sbmHighCouponLevel;
    
    /** ハイクーポン端数計算. */
    private String sbmHighCouponCalc;
    
    /** ハイクーポン端数処理. */
    private String sbmHighCouponRound;
    
    /** ハイクーポン. */
    private String sbmHighCoupon;
    
    /** ミッド判定水準. */
    private String sbmMidCouponLevel;
    
    /** ミッドクーポン端数計算. */
    private String sbmMidCouponCalc;
    
    /** ミッドクーポン端数処理. */
    private String sbmMidCouponRound;
    
    /** ミッドクーポン. */
    private String sbmMidCoupon;
    
    /** ロー判定水準. */
    private String sbmLowCouponLevel;
    
    /** ロークーポン端数計算. */
    private String sbmLowCouponCalc;
    
    /** ロークーポン端数処理. */
    private String sbmLowCouponRound;
    
    /** ロークーポン. */
    private String sbmLowCoupon;
    
    /** 満期償還判定日. */
    private String sbmPaymentJudgementDate;
    
    /** 当初価格設定判定日. */
    private String sbmFirstJudgementDate;
    
    /** 観察期間from. */
    private String sbmObservationPeriodFrom;
    
    /** 観察期間to. */
    private String sbmObservationPeriodTo;
    
    /** 参照値（全角半角）. */
    private String sbmValueType;
    
    /** 通貨ペア（全角半角）. */
    private String sbmCurrencyPair;
    
    /** 使用レート（全角半角）. */
    private String sbmUseRate;
    
    /** レバレッジ（数字）. */
    private String sbmLeverage;
    
    /** ノックイン免除有無. */
    private String sbmNockinExemptionKbn;
    
    /** ノックイン免除観察期間From. */
    private String sbmNockinExemptionFrom;
    
    /** ノックイン免除観察期間To. */
    private String sbmNockinExemptionTo;
    
    /** 早期償還メモリ有無. */
    private String sbmKoMemoryKbn;
    
}
