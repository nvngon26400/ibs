import Logger from '@/utils/ifaLog.js'
export class IfaCustomerListA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.accountNumber = obj.accountNumberSelected ? obj.accountNumberSelected : '' // 口座番号
    this.butenCode = obj.butenCodeSelected ? obj.butenCodeSelected : '' // 部店コード
    this.brokerCode = obj.brokerCodeSelected ? obj.brokerCodeSelected : '' // 仲介業者コード
    this.empCode = obj.empCodeSelected ? obj.empCodeSelected : '' // 営業員コード
  }
}
