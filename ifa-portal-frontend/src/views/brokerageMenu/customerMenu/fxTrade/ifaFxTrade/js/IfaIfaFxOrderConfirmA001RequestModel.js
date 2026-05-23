import Logger from '@/utils/ifaLog.js'
export class IfaIfaFxOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.quantity = obj.orderAmount ? obj.orderAmount : '' // 数量
    this.selectedCurrencyInfo = obj.selectedCurrencyInfo ? obj.selectedCurrencyInfo : '' // 適用スプレッド
    this.fxRateAdditionalType = obj.fxRateAdditionalType ? obj.fxRateAdditionalType : '' // 上乗せ区分
    this.additionalPrice = obj.additionalPrice ? obj.additionalPrice : '' // additionalPrice上乗せ金額
    this.saleMethod = obj.saleMethod ? obj.saleMethod : '' // 売却方法
    this.exceedingOrderAmountLimit = obj.exceedingOrderAmountLimitCheckbox ? obj.exceedingOrderAmountLimitCheckbox : '' // exceedingOrderAmountLimitアラート内容確認.注文金額上限超過
    this.orderWarningexceedingThreshold = obj.orderWarningexceedingThreshold ? obj.orderWarningexceedingThreshold : '' // 注文ワーニングしきい値超過メッセージ
    this.warningThreshold = obj.warningThreshold ? obj.warningThreshold : '' // 注文ワーニングしきい値
    this.quantityDesignationMethod = obj.quantityDesignationMethod ? obj.quantityDesignationMethod : '' // 数量の指定方法
    this.currency = obj.currency ? obj.currency : '' // 通貨名
    this.accountClass = obj.accountClass ? obj.accountClass : '' // 口座分類（為替取引）
    this.addLinkAttention = obj.addLinkAttention ? obj.addLinkAttention : '' // リンク付注意事項URLID
    this.decimalLength = obj.decimalLength ? obj.decimalLength : '' // 小数部有効桁数
  }
}
