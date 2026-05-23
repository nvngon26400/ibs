import Logger from '@/utils/ifaLog.js'
export class IfaCustomerSelectA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCodeSelected ? obj.butenCodeSelected : '' // 部店
    this.accountNumber = obj.accountNumberSelected ? obj.accountNumberSelected : '' // 口座番号
  }
}
