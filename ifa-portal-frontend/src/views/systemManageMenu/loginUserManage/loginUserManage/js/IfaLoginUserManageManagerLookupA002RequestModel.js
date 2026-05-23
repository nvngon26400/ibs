import Logger from '@/utils/ifaLog.js'
export class IfaLoginUserManageManagerLookupA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.branchNameBrokerName = obj.branchNameBrokerName ? obj.branchNameBrokerName : '' // 支店名仲介業者名
    this.employeeNameChargeName = obj.employeeNameChargeName ? obj.employeeNameChargeName : '' // 社員名担当者名
  }
}
