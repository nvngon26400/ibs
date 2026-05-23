import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdUpdateRegisterA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.beforeLoginId ? obj.beforeLoginId : '' // ログインID
    this.privId = obj.beforePrivId ? obj.beforePrivId : '' // 権限コード
    this.branchCode = obj.beforeBranchCode ? obj.beforeBranchCode : '' // 本支店コード
    this.brokerCode = obj.beforeBrokerCode ? obj.beforeBrokerCode : '' // 仲介業者コード
    this.subBrokerId = obj.beforeSubBrokerId ? obj.beforeSubBrokerId : '' // 仲介業者支店コード
    this.employeeId = obj.beforeEmployeeId ? obj.beforeEmployeeId : '' // 仲介業者担当者コード
    this.employeeNameChargeName = obj.beforeEmployeeNameChargeName ? obj.beforeEmployeeNameChargeName : '' // 社員名担当者名
    this.menuList = obj.menuList ? obj.menuList : [] // メニューリスト
  }
}
