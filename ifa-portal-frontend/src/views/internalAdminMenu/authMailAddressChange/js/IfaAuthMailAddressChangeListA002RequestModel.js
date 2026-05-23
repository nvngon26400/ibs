import Logger from '@/utils/ifaLog.js'
export class IfaAuthMailAddressChangeListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.brokerOrBranchName = obj.branchNameBrokerName ? obj.branchNameBrokerName : '' // 支店名/仲介業者名
    this.employeeName = obj.employeeNameChargeName ? obj.employeeNameChargeName : '' // 社員名/担当者名
  }
}
