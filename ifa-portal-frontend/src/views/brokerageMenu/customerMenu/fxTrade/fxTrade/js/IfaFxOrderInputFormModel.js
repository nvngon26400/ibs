export class IfaFxOrderInputFormModel {
  constructor() {
    this.title = '為替注文入力'
    this.referenceRate = '' // 参考レート
    this.denominator = '' // 参考レート_デノミ
    this.referenceRateCurrency = '' // 参考レート_通貨コード
    this.maxOrderableQuantity = '' // 注文可能数量
    this.approximatePurchaseAmount = '' // 概算買付可能金額
    this.currencyFrom = '' // 通貨名（元）
    this.saleMethod = '1' // 売却方法 【初期値】"指定した数量を売却"
    this.quantityDesignationMethod = '1' // 数量の指定方法 【初期値】"外貨で指定"
    this.closableQuantity = '' // 取引下限
    this.tradeLimitMax = '' // 取引上限
    this.tradeLimitMin = '' // 取引単位
    this.foreignVolume = '' // 数量入力（外貨）
    this.domesticVolume = '' // 数量入力（円）
    this.foreignApproximateDeliveryAmount = '-' // 概算外貨数量 【初期値】"-"
    this.approximateDeliveryAmount = '-' // 概算受渡金額 【初期値】"-円"
    this.currencyCode = '' // 通貨コード
    this.currency = '' // 通貨名
    this.tradeKbn = '' // 売買区分
    this.exchangeGroup = '' // 為替グループ
    this.decimalLength = '' // 小数部有効桁数
    this.foreignSpinButtonRange = '' // 外貨スピンボタン増減幅
    this.foreignCurrencyMax = '' // 外貨指定最大値
    this.yenCurrencyMin = '' // 円指定最小値
    this.yenCurrencyMax = '' // 円指定最大値
    this.approximationRate = '' // 概算用レート
    this.jrNisaAccountStatus = '' // ジュニアNISA口座状態
    this.accountType = '1' // 口座分類（為替取引）
    this.warningThreshold = '' // 注文ワーニングしきい値
  }
}
