import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeRepayOrderInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.paymentDeadline = obj.marginDueDate ? obj.marginDueDate : '' // 弁済期限
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.openTradeKbn = obj.trade === '3' ? '0' : '1' // 新規売買区分
    this.individualBatchJudge = '0' // 個別一括判定
  }
}
