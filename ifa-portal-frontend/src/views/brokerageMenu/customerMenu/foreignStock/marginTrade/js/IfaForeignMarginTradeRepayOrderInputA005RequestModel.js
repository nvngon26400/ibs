import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeRepayOrderInputA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.period = obj.foreignStockMarginPositionSummary.length < 0 ? obj.obj.foreignStockMarginPositionSummary.marginDueDate : '' // 期限
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.repayPositionDesignateMethod = obj.repayPositionDesignateMethod ? obj.repayPositionDesignateMethod : '' // 返済建玉指定方法
    this.trade = obj.trade ? obj.trade : '' // 建区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
  }
}
