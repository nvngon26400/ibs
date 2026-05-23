import Logger from '@/utils/ifaLog.js'
export class IfaFxOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.accountType = obj.accountType ? obj.accountType : '' // 口座分類（為替取引）
    this.quantity = obj.exchangeOrderAmount ? obj.exchangeOrderAmount : '' // 数量
    this.fxRateAdditionalType = obj.fxRateAdditionalType ? obj.fxRateAdditionalType : '' // 上乗せ区分
    this.additionalPrice = obj.additionalPrice ? obj.additionalPrice : '' // 上乗せ金額
    this.saleMethod = obj.saleMethod ? obj.saleMethod : '' // 売却方法
    this.warningMessage = obj.warningMessage ? '1' : '' // アラート内容確認.注文金額上限超過
    this.warningThreshold = obj.warningThreshold ? obj.warningThreshold : '' // 注文ワーニングしきい値
    this.fxTrade = obj.fxTrade ? obj.fxTrade : '' // 為替取引
    this.quantityDesignationMethod = obj.quantityDesignationMethod ? obj.quantityDesignationMethod : '' // 数量の指定方法
    this.currency = obj.currency ? obj.currency : '' // 通貨名
    this.orderWarningexceedingThreshold = obj.orderWarningexceedingThreshold ? obj.orderWarningexceedingThreshold : '' // 注文ワーニングしきい値超過メッセージ
    this.tradeDateTime = obj.tradeDateTime ? obj.tradeDateTime : '' // 約定日時
    this.decimalLength = obj.decimalLength ? obj.decimalLength : '' // 小数部有効桁数
    this.denominator = obj.denominator ? obj.denominator : '' // デノミ
  }
}
