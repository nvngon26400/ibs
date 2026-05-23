export class IfaFxOrderCompleteFormModel {
  constructor() {
    this.title = '為替注文完了'
    this.tradeKbn = '' // 売買区分
    this.currencyCode = '' // 通貨コード
    this.currency = '' // 通貨名
    this.orderDate = '' // 注文日時
    this.orderNumber = '' // 注文番号
    this.quantity = '' // 買付数量／売却数量
    this.fxRate = '' // 約定レート／概算レート
    this.fxTrade = '' // 為替取引
    this.denominator = '' // デノミ
    this.decimalLength = '' // 小数部有効桁数
    this.approximateRateExchangeRateDateTime = '' // 約定レート／概算レートの為替レート日時
    this.deliveryAmount = '' // 受渡金額
    this.tradeDateTime = '' // 約定タイミング
    this.settlementDate = '' // 受渡日
    this.accountType = '' // 口座分類（為替取引）
    this.fxRateAdditionalType = '' // 上乗せ区分
    this.additionalPrice = '' // 上乗せ金額(金額)
    this.additionalPercentage = '' // 上乗せ金額(パーセント)
    this.warningThreshold = '' // 注文ワーニングしきい値
    this.orderWarningexceedingThreshold = '' // 注文ワーニングしきい値超過メッセージ
  }
}
