import Logger from '@/utils/ifaLog.js'
export class IfaCustomerListA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.accountNumber = obj.accountNumberSelected ? obj.accountNumberSelected : '' // 口座番号
    this.butenCode = obj.butenCodeSelected ? obj.butenCodeSelected : '' // 部店コード
  }
}
