export class IfaFxOrderConfirmFormModel {
  constructor() {
    this.title = '為替注文確認'
    this.currencyCode = '' // 通貨コード
    this.currency = '' // 通貨名
    this.tradeKbn = '' // 売買区分
    this.exchangeOrderAmount = '' // 買付数量／売却数量
    this.tradeDateTime = '' // 約定日時
    this.settlementDate = '' // 受渡日
    this.fxTrade = '' // 為替取引
    this.fxRate = '' // 為替レート
    this.approximateRateExchangeRateDateTime = '' // 為替レート日時
    this.yenDeliveryAmount = '' // 受渡金額（円貨）
    this.accountType = '' // 口座分類（為替取引）
    this.warningMessage = false
    this.warningThreshold = '' // 注文ワーニングしきい値
    this.orderWarningexceedingThreshold = '' // 注文ワーニングしきい値超過メッセージ
    this.additionalPrice = '' // 上乗せ金額(金額)
    this.additionalPricePercent = '' // 上乗せ金額(パーセント)
    this.fxRateAdditionalType = '' // 上乗せ区分
    this.saleMethod = '' // 売却方法
    this.quantityDesignationMethod = '' // 数量の指定方法
    this.decimalLength = '' // 小数部有効桁数
    this.denominator = '' // デノミ
  }
}
