import Logger from '@/utils/ifaLog.js'
export class IfaRepAddA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.userId = obj.userId ? obj.userId : '' // ユーザーID
    this.sbiSecurityCode = obj.sbiSecurityCode ? obj.sbiSecurityCode : '' // SBI証券支店コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.subBrokerId = obj.subBrokerId ? obj.subBrokerId : undefined // 仲介業者支店コード
    this.employeeId = obj.employeeId ? obj.employeeId : '' // 仲介業者担当者コード
    this.chargeName = obj.chargeName ? obj.chargeName : '' // 担当者名
  }
}
