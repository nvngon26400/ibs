// 共同募集　顧客管理 新規登録 A002登録確認(新規登録入力)のレスポンスモデル
export class IfaJointSubscriptCustomerRegisterA002ResponseModel {
  constructor() {
    this.brokerCode // 仲介業者コード
    this.brokerName // 仲介業者名
    this.butenCode // 部店
    this.accountNumber // 口座番号
    this.jointBranchCode // 共募支店コード
    this.jointBranchName // 共募支店名
    this.contractDate // 契約締結日
    this.startDate // 同意日
    this.endDate // 終了日
    this.jointRewardRate // 支払率
  }
}
