// 共同募集　顧客管理 更新 A002更新確認(更新入力)のリクエストモデル
export class IfaJointSubscriptCustomerCorrectA002RequestModel {
  constructor(obj) {
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.jointBranchCode = obj.jointBranchCode ? obj.jointBranchCode : '' // 共募支店コード
    this.contractDate = obj.contractDate ? obj.contractDate : '' // 契約締結日
    this.startDate = obj.startDate ? obj.startDate : '' // 同意日
    this.endDate = obj.endDate ? obj.endDate : '' // 終了日
    this.jointRewardRate = obj.jointRewardRate ? obj.jointRewardRate : '' // 支払率
  }
}
