import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdUpdateRegisterA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.repNumber = obj.repNumber ? obj.repNumber : '' // 担当者数
    this.privId = obj.privId ? obj.privId : '' // 権限コード
    this.branchCode = obj.branchCode ? obj.branchCode : ''// 本支店コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.subBrokerId = obj.subBrokerId ? obj.subBrokerId : '' // 仲介業者支店コード
    this.employeeId = obj.employeeId ? obj.employeeId : '' // 仲介業者担当者コード
    this.employeeNameChargeName = obj.employeeNameChargeName ? obj.employeeNameChargeName : '' // 社員名担当者名
    this.menuList = obj.menuList ? obj.menuList : '' // メニューリスト
  }
}
