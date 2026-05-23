export class IfaStructuredBondBrandInfoFormModel {
  constructor() {
    this.title = {
      id: 'SUB020302_0104-02',
      name: '仕組債銘柄情報'
    }
    this.bondCode = '' // 債券コード
    this.isinCode = '' // ISINコード
    this.bondName = '' // 債券名
    this.issueDate = '' // 発行日
    this.maturityRedemptionDate = '' // 満期償還日
    this.publicPrivateOfferingName = '' // 公募私募区分
    this.knockInEventType = '' // ノックインイベント区分
    this.earlyRedemptionEventType = '' // 早期償還イベント区分
    this.businessDayCalendar = '' // 参照営業日カレンダー
    this.exchangeCalendar = '' // 参照取引所カレンダー
    this.exchangeJudgmentDays = '' // 取引所判断日数
    this.nonCallPeriod = '' // ノンコール期間
    this.appInterest = '' // 適用利率
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
    this.earlyStageRedemptionJudgmentDateRateJudgmentDateList = [ // 早期償還判定日/利率判定日リスト
      // {
      //   number: '', // 番号
      //   judgmentDate: '' // 判定日
      // }, {...}
    ]
    this.maturityRedemptionJudgmentDate = '' // 満期償還判定日
    this.initiallyPriceSettingJudgmentDate = '' // 当初価格設定判定日
    this.observationPeriodFrom = '' // 観察期間from
    this.observationPeriodTo = '' // 観察期間to
    this.referenceValue = '' // 参照値
    this.currencyPair = '' // 通貨ペア
    this.useRate = '' // 使用レート
    this.leverage = '' // レバレッジ
    this.knockInJudgmentLevel = '' // ノックイン判定水準
    this.knockInJudgmentFractionCalculation = '' // ノックイン判定端数計算
    this.knockInJudgmentRounding = '' // ノックイン判定端数処理
    this.earlyRedemptionJudgmentLevel = '' // 早期償還判定水準
    this.earlyRedemptionJudgmentFractionCalculation = '' // 早期償還判定端数計算
    this.earlyRedemptionJudgmentRounding = '' // 早期償還判定端数処理
    this.earlyRedemptionJudgmentStepDown = '' // 早期償還判定水準ステップダウン有無
    this.stepDownStartCount = '' // 早期償還判定水準ステップダウン開始回目
    this.earlyRedemptionJudgmentStepDownSettingValue = '' // 早期償還判定水準ステップダウン設定値
    this.earlyRedemptionJudgmentStepDownLowerLimit = '' // 早期償還判定水準ステップダウン下限
    this.earlyRedemptionJudgmentStepDownLowerLimitFractionCalculation = '' // 早期償還判定水準ステップダウン下限端数計算
    this.earlyRedemptionJudgmentStepDownLowerLimitRounding = '' // 早期償還判定水準ステップダウン下限端数処理
    this.earlyRedemptionPriceRatio = '' // 早期償還価格比率
    this.usePriceRatio = '' // 行使価格比率
    this.usePriceFractionCalculation = '' // 行使価格端数計算
    this.usePriceRounding = '' // 行使価格端数処理
    this.maturityRedemptionJudgmentLevel = '' // 満期償還判定水準
    this.maturityRedemptionFractionCalculation = '' // 満期償還端数計算
    this.maturityRedemptionRounding = '' // 満期償還端数処理
    this.maturityRedemptionPriceRatio = '' // 満期償還価格比率
    this.knockInExemptExistence = '' // ノックイン免除有無
    this.knockInExemptobservationPeriodFrom = '' // ノックイン免除観察期間From
    this.knockInExemptobservationPeriodTo = '' // ノックイン免除観察期間To
    this.knockInExemptJudgmentLevel = '' // ノックイン免除判定水準
    this.knockInExemptFractionCalculation = '' // ノックイン免除判定端数計算
    this.knockInExemptRounding = '' // ノックイン免除判定端数処理
    this.earlyRedemptionMemoryExistence = '' // 早期償還メモリ有無
    this.referenceBrandList = [ // 参照銘柄リスト
      // {
      //   disporder: '', // 表示順
      //   judgmentBrandClass: '', // 判定銘柄種別
      //   judgmentMarketCode: '', // 判定市場コード
      //   judgmentBrandCode: '', // 判定銘柄コード
      //   judgmentBrandName: '', // 判定銘柄名
      //   unit: '', // 単位
      //   initiallyPrice: '', // 当初価格
      //   knockInLevelPrice: '', // ノックイン水準価格
      //   earlyRedemptionLevelPrice: '', // 早期償還水準価格
      //   knockInAccuralDate: '', // ノックイン発生日
      //   knockInAccuralTimePrice: '' // ノックイン発生時価格
      // }, {...}
    ]
  }
}
