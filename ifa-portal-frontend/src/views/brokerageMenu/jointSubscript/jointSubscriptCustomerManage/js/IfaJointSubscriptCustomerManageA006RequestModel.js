// 共同募集　顧客管理 A006CSV出力のリクエストモデル
export class IfaJointSubscriptCustomerManageA006RequestModel {
  constructor(obj) {
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : false // 仲介業者除外
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.jointBranchCode = obj.jointBranchCode ? obj.jointBranchCode : '' // 共募支店コード
    this.editStatus = obj.editStatus ? obj.editStatus : '' // 手続状況
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : '' // 仲介業者除外
  }
}
