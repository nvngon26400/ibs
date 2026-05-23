// 共同募集　顧客管理 新規登録 A004登録(新規登録確認)のリクエストモデル
export class IfaJointSubscriptCustomerRegisterA004RequestModel {
  constructor(obj) {
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.jointBranchCode = obj.jointBranchCode ? obj.jointBranchCode : '' // 共募支店コード
    this.jointBranchName = obj.jointBranchName ? obj.jointBranchName : '' // 共募支店名
    this.contractDate = obj.contractDate ? obj.contractDate.replace(/\//g, '') : '' // 契約締結日
    this.startDate = obj.startDate ? obj.startDate.replace(/\//g, '') : '' // 同意日
    this.endDate = obj.endDate ? obj.endDate.replace(/\//g, '') : '' // 終了日
    this.jointRewardRate = obj.jointRewardRate ? obj.jointRewardRate : '' // 支払率
  }
}
