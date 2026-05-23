export class IfaStructuredBondBrandInfoFormModel {
  constructor() {
    this.bondCode = '' // 債券コード
    this.isinCode = '' // ISINコード
    this.brandName = '' // 銘柄名
    this.issueDate = '' // 発行日
    this.maturityRedemptionDate = '' // 満期償還日
    this.publicPrivateOfferingName = '' // 公募/私募
    this.knockInEventTypeName = '' // ノックイン有無
    this.earlyRedemptionEventTypeName = '' // 早期償還有無
    this.businessDayCalendar = '' // 参照営業日カレンダー
    this.exchangeCalendar = '' // 参照取引所カレンダー
    this.exchangeJudgmentDays = '' // 判定日数
    this.nonCallPeriod = '' // ノンコール期間
    this.appInterest = '' // 利率(%)
    this.couponKind = '' // クーポン種類
    this.coupon = '' // 固定クーポン
    this.couponCount = '' // 固定クーポン回数
    this.highJudgmentLevel = '' // ハイ判定水準
    this.highCouponFractionCalculation = '' // ハイクーポン端数計算
    this.highCouponRounding = '' // ハイクーポン端数処理
    this.highCoupon = '' // ハイクーポン
    this.midJudgmentLevel = '' // ミッド判定水準
    this.midCouponFractionCalculation = '' // ミッドクーポン端数計算
    this.midCouponRounding = '' // ミッドクーポン端数処理
    this.midCoupon = '' // ミッドクーポン
    this.lowJudgmentLevel = '' // ロー判定水準
    this.lowCouponFractionCalculation = '' // ロークーポン端数計算
    this.lowCouponRounding = '' // ロークーポン端数処理
    this.lowCoupon = '' // ロークーポン
    // 早期償還判定日/利率判定日一覧
    this.earlyStageRedemptionJudgmentDateRateJudgmentDateList = [
      {
        number: '', // 早期償還判定日/利率判定日一覧.番号
        judgmentDate: '' // 早期償還判定日/利率判定日一覧.判定日
      }
    ]
    this.maturityRedemptionJudgmentDate = '' // 満期償還判定日
    this.initiallyPriceSettingJudgmentDate = '' // 当初価格決定日
    this.observationPeriodFrom = '' // 観察期間From
    this.observationPeriodTo = '' // 観察期間To
    this.referenceValue = '' // 参照値
    this.currencyPair = '' // 通貨ペア
    this.useRate = '' // 使用レート
    this.leverage = '' // レバレッジ
    this.knockInJudgmentLevel = '' // ノックイン判定水準
    this.knockInJudgmentFractionCalculation = '' // ノックイン判定水準端数計算
    this.knockInJudgmentRounding = '' // ノックイン判定水準端数処理
    this.earlyRedemptionJudgmentLevel = '' // 早期償還判定水準
    this.earlyRedemptionJudgmentFractionCalculation = '' // 早期償還判定端数計算
    this.earlyRedemptionJudgmentRounding = '' // 早期償還判定端数処理
    this.earlyRedemptionJudgmentStepDown = '' // 早期償還判定水準ステップダウン有無
    this.stepDownStartCount = '' // 早期償還判定水準ステップダウン開始回目
    this.earlyRedemptionJudgmentStepDownSettingValue = '' // 早期償還判定水準ステップダウン設定値
    this.earlyRedemptionJudgmentStepDownLowerLimit = '' // 早期償還判定水準ステップダウン下限
    this.earlyRedemptionJudgmentStepDownLowerLimitFractionCalculation = '' // 早期償還判定水準ステップダウン下限端数計算
    this.earlyRedemptionJudgmentStepDownLowerLimitRounding = '' // 早期償還判定水準ステップダウン下限端数処理
    this.earlyRedemptionPriceRatio = '' // 早期償還価格
    this.usePriceRatio = '' // 行使価格
    this.usePriceFractionCalculation = '' // 行使価格端数計算
    this.usePriceRounding = '' // 行使価格端数処理
    this.maturityRedemptionJudgmentLevel = '' // 満期償還判定水準
    this.maturityRedemptionFractionCalculation = '' // 満期償還端数計算
    this.maturityRedemptionRounding = '' // 満期償還端数処理
    this.maturityRedemptionPriceRatio = '' // 満期償還価格
    this.knockInExemptExistence = '' // ノックイン免除
    this.knockInExemptobservationPeriodFrom = '' // ノックイン免除観察期間From
    this.knockInExemptobservationPeriodTo = '' // ノックイン免除観察期間To
    this.knockInExemptJudgmentLevel = '' // ノックイン免除判定水準
    this.knockInExemptFractionCalculation = '' // ノックイン免除判定端数計算
    this.knockInExemptRounding = '' // ノックイン免除判定端数処理
    this.earlyRedemptionMemoryExistence = '' // 早期償還メモリ
    // 参照銘柄一覧
    this.referenceBrandList = [
      {
        disporder: '', // 参照銘柄一覧.表示順
        brandClass: '', // 参照銘柄一覧.銘柄種別
        marketCode: '', // 参照銘柄一覧.判定市場
        judgmentBrand: '', // 参照銘柄一覧.判定銘柄
        brandName: '', // 参照銘柄一覧.銘柄名
        unit: '', // 参照銘柄一覧.単位
        initiallyPrice: '', // 参照銘柄一覧.当初価格
        knockInLevelPrice: '', // 参照銘柄一覧.ノックイン水準価格
        earlyRedemptionLevelPrice: '', // 参照銘柄一覧.早期償還水準価格
        knockInAccuralDate: '', // 参照銘柄一覧.ノックイン発生日
        lastPrice: '' // 参照銘柄一覧.終値
      }
    ]
    this.noJudgmentDayMsg = '' // 判定日なしメッセージ
    this.noReferenceBrandMsg = '' // 参照銘柄なしメッセージ
  }
}
