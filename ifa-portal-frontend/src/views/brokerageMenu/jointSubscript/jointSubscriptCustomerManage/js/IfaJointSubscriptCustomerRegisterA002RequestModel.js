// 共同募集　顧客管理 新規登録 A002登録確認(新規登録入力)のリクエストモデル
export class IfaJointSubscriptCustomerRegisterA002RequestModel {
  constructor(obj) {
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.jointBranchCode = obj.jointBranchCode ? obj.jointBranchCode : '' // 共募支店コード
    this.contractDate = obj.contractDate ? obj.contractDate : '' // 契約締結日
    this.startDate = obj.startDate ? obj.startDate : '' // 同意日
    this.endDate = obj.endDate ? obj.endDate : '' // 終了日
    this.jointRewardRate = obj.jointRewardRate ? obj.jointRewardRate : '' // 支払率
  }
}
