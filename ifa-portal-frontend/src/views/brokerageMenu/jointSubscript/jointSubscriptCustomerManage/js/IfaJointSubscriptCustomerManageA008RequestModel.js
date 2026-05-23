// 共同募集　顧客管理 A008顧客情報詳細のリクエストモデル
export class IfaJointSubscriptCustomerManageA008RequestModel {
  constructor(obj) {
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
  }
}
