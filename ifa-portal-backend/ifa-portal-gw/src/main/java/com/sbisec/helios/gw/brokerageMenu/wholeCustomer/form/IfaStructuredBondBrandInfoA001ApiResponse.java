package com.sbisec.helios.gw.brokerageMenu.wholeCustomer.form;

import java.util.List;
import lombok.Data;

/**
 * 仕組債銘柄情報　A001　レスポンス

 * @author SCSK川崎
 */
@Data
public class IfaStructuredBondBrandInfoA001ApiResponse {
    
    /** 債券コード（半角英数字）. */
    private String bondCode;
    
    /** ISINコード（半角英数字）. */
    private String isinCode;
    
    /** 債券名. */
    private String bondName;
    
    /** 発行日. */
    private String issueDate;
    
    /** 満期償還日. */
    private String maturityRedemptionDate;
    
    /** 公募私募区分. */
    private String publicPrivateOfferingName;
    
    /** ノックインイベント区分. */
    private String knockInEventType;
    
    /** 早期償還イベント区分. */
    private String earlyRedemptionEventType;
    
    /** 参照営業日カレンダー（全角半角）. */
    private String businessDayCalendar;
    
    /** 参照取引所カレンダー（全角半角）. */
    private String exchangeCalendar;
    
    /** 取引所判断日数（全角半角）. */
    private String exchangeJudgmentDays;
    
    /** ノンコール期間（数値(整数)）. */
    private String nonCallPeriod;
    
    /** 適用利率（数値(小数)）. */
    private String appInterest;
    
    /** クーポン種類（全角半角）. */
    private String couponKind;
    
    /** 固定クーポン. */
    private String coupon;
    
    /** 固定クーポン回数（数値(整数)）. */
    private String couponCount;
    
    /** ハイ判定水準. */
    private String highJudgmentLevel;
    
    /** ハイクーポン端数計算. */
    private String highCouponFractionCalculation;
    
    /** ハイクーポン端数処理. */
    private String highCouponRounding;
    
    /** ハイクーポン. */
    private String highCoupon;
    
    /** ミッド判定水準. */
    private String midJudgmentLevel;
    
    /** ミッドクーポン端数計算. */
    private String midCouponFractionCalculation;
    
    /** ミッドクーポン端数処理. */
    private String midCouponRounding;
    
    /** ミッドクーポン. */
    private String midCoupon;
    
    /** ロー判定水準. */
    private String lowJudgmentLevel;
    
    /** ロークーポン端数計算. */
    private String lowCouponFractionCalculation;
    
    /** ロークーポン端数処理. */
    private String lowCouponRounding;
    
    /** ロークーポン. */
    private String lowCoupon;
    
    /** 早期償還判定日/利率判定日リスト. */
    private List<IfaStructuredBondBrandInfoApiResponseEarlyStageRedemptionJudgmentDateRateJudgmentDate> earlyStageRedemptionJudgmentDateRateJudgmentDateList;
    
    /** 満期償還判定日. */
    private String maturityRedemptionJudgmentDate;
    
    /** 当初価格設定判定日. */
    private String initiallyPriceSettingJudgmentDate;
    
    /** 観察期間from. */
    private String observationPeriodFrom;
    
    /** 観察期間to. */
    private String observationPeriodTo;
    
    /** 参照値（全角半角）. */
    private String referenceValue;
    
    /** 通貨ペア（全角半角）. */
    private String currencyPair;
    
    /** 使用レート（全角半角）. */
    private String useRate;
    
    /** レバレッジ（数字）. */
    private String leverage;
    
    /** ノックイン判定水準. */
    private String knockInJudgmentLevel;
    
    /** ノックイン判定端数計算. */
    private String knockInJudgmentFractionCalculation;
    
    /** ノックイン判定端数処理. */
    private String knockInJudgmentRounding;
    
    /** 早期償還判定水準. */
    private String earlyRedemptionJudgmentLevel;
    
    /** 早期償還判定端数計算. */
    private String earlyRedemptionJudgmentFractionCalculation;
    
    /** 早期償還判定端数処理. */
    private String earlyRedemptionJudgmentRounding;
    
    /** 早期償還判定水準ステップダウン有無（全角半角）. */
    private String earlyRedemptionJudgmentStepDown;
    
    /** 早期償還判定水準ステップダウン開始回目（数値(整数)）. */
    private String stepDownStartCount;
    
    /** 早期償還判定水準ステップダウン設定値. */
    private String earlyRedemptionJudgmentStepDownSettingValue;
    
    /** 早期償還判定水準ステップダウン下限. */
    private String earlyRedemptionJudgmentStepDownLowerLimit;
    
    /** 早期償還判定水準ステップダウン下限端数計算. */
    private String earlyRedemptionJudgmentStepDownLowerLimitFractionCalculation;
    
    /** 早期償還判定水準ステップダウン下限端数処理. */
    private String earlyRedemptionJudgmentStepDownLowerLimitRounding;
    
    /** 早期償還価格比率. */
    private String earlyRedemptionPriceRatio;
    
    /** 行使価格比率. */
    private String usePriceRatio;
    
    /** 行使価格端数計算. */
    private String usePriceFractionCalculation;
    
    /** 行使価格端数処理. */
    private String usePriceRounding;
    
    /** 満期償還判定水準. */
    private String maturityRedemptionJudgmentLevel;
    
    /** 満期償還端数計算. */
    private String maturityRedemptionFractionCalculation;
    
    /** 満期償還端数処理. */
    private String maturityRedemptionRounding;
    
    /** 満期償還価格比率. */
    private String maturityRedemptionPriceRatio;
    
    /** ノックイン免除有無. */
    private String knockInExemptExistence;
    
    /** ノックイン免除観察期間From. */
    private String knockInExemptobservationPeriodFrom;
    
    /** ノックイン免除観察期間To. */
    private String knockInExemptobservationPeriodTo;
    
    /** ノックイン免除判定水準　. */
    private String knockInExemptJudgmentLevel;
    
    /** ノックイン免除判定端数計算. */
    private String knockInExemptFractionCalculation;
    
    /** ノックイン免除判定端数処理. */
    private String knockInExemptRounding;
    
    /** 早期償還メモリ有無. */
    private String earlyRedemptionMemoryExistence;
    
    /** 参照銘柄リスト. */
    private List<IfaStructuredBondBrandInfoApiResponseReferenceBrand> referenceBrandList;
    
}
