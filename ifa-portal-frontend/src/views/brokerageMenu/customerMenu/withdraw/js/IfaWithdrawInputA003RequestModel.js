import Logger from '@/utils/ifaLog.js'
export class IfaWithdrawInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.payDate = obj.payDate ? obj.payDate : '' // 出金日
    this.payAmount = obj.payAmount ? obj.payAmount : '' // 出金額
  }
}
