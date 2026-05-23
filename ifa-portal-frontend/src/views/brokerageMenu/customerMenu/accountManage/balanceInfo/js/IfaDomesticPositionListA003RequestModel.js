import Logger from '@/utils/ifaLog.js'
export class IfaDomesticPositionListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.openTradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.sortOrder = '61' // 並替順序
  }
}
