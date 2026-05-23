import Logger from '@/utils/ifaLog.js'
export class IfaWithdrawInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.payDate = obj.payDate ? obj.payDate : '' // 出金日
  }
}
