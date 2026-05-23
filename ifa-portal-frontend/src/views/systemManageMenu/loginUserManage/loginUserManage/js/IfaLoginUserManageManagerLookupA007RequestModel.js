import Logger from '@/utils/ifaLog.js'
export class IfaLoginUserManageManagerLookupA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.sbiSecurityCode = obj.sbiSecurityCode ? obj.sbiSecurityCode : '' // SBI証券支店コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.subBrokerId = obj.subBrokerId ? obj.subBrokerId : '' // 仲介業者支店コード
    this.employeeId = obj.employeeId ? obj.employeeId : '' // 仲介業者担当者コード
    this.managerCount = obj.managerCount ? obj.managerCount : '' // 担当数
  }
}
