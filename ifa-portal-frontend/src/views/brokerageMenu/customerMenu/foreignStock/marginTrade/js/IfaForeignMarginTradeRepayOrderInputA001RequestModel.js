import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeRepayOrderInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.trade = obj.trade ? obj.trade : '' // 建区分
  }
}
