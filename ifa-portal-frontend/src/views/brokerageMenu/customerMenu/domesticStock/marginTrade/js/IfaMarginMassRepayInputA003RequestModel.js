import Logger from '@/utils/ifaLog.js'
export class IfaMarginMassRepayInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.newCreditOrderType = obj.newCreditOrderType ? obj.newCreditOrderType : '' // 新規建売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.repaymentOrder = obj.repaymentOrder ? obj.repaymentOrder : '' // 返済順序
  }
}
