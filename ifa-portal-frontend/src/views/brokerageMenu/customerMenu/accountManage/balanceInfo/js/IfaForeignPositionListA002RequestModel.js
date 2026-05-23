import Logger from '@/utils/ifaLog.js'
export class IfaForeignPositionListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeKbn === '0' ? '5' : '4' // 取引種別
    this.paymentDeadline = obj.marginDueDate ? obj.marginDueDate : '' // 弁済期限
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.trade = obj.tradeKbn === '0' ? '3' : '1' // 新規売買区分
  }
}
