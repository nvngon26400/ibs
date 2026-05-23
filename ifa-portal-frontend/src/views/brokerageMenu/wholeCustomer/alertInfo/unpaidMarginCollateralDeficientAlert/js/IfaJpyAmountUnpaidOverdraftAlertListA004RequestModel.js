import Logger from '@/utils/ifaLog.js'
export class IfaJpyAmountUnpaidOverdraftAlertListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
  }
}
