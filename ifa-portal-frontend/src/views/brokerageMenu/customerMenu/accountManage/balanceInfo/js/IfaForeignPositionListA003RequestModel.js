import Logger from '@/utils/ifaLog.js'
export class IfaForeignPositionListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.marginDueDate ? obj.marginDueDate : '' // 弁済期限
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.individualBatchJudge = '1' // 個別一括判定
    this.businessDaysAfterOrder = obj.domesticTradeDate ? obj.domesticTradeDate : '' // 国内約定日
    this.localTradeDate = obj.localTradeDate ? obj.localTradeDate : '' // 現地約定日
    this.previousDayValue = obj.newPositionPriceForeign ? obj.newPositionPriceForeign : '' // 新規建単価（外貨）
    this.jpyAmountNewPositionPrice = obj.jpyAmountNewPositionPrice ? obj.jpyAmountNewPositionPrice : '' // 新規建単価（円貨）
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
  }
}
