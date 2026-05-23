import Logger from '@/utils/ifaLog.js'
export class IfaDomesticPositionListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.newCreditOrderType = obj.tradeKbn ? obj.tradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.market = obj.market ? obj.market : '' // 新規市場
  }
}
