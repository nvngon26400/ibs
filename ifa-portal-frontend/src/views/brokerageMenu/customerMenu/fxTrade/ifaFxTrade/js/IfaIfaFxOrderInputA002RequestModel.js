import Logger from '@/utils/ifaLog.js'
export class IfaIfaFxOrderInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.foreignVolume = obj.foreignVolume ? obj.foreignVolume : '' // 数量入力（外貨）
    this.domesticVolume = obj.domesticVolume ? obj.domesticVolume : '' // 数量入力（円）
    this.decimalLength = obj.decimalLength ? obj.decimalLength : '' // 小数部有効桁数
    this.exchangeGroup = obj.exchangeGroup ? obj.exchangeGroup : '' // 為替グループ
    this.closableQuantity = obj.closableQuantity ? obj.closableQuantity : '' // 取引下限
    this.tradeLimitMax = obj.tradeLimitMax ? obj.tradeLimitMax : '' // 取引上限
    this.tradeUnit = obj.tradeUnit ? obj.tradeUnit : '' // 取引単位
    this.computeExchangeRate = obj.computeExchangeRate ? obj.computeExchangeRate : '' // 概算用レート
    this.denominator = obj.denominator ? obj.denominator : '' // デノミ
  }
}
