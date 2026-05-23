// 共同募集　顧客管理 A008顧客情報詳細ののレスポンスモデル
export class IfaJointSubscriptCustomerManageA008ResponseModel {
  constructor() {
    this.screenId = 'SUB0206_01-04' // 画面ID
    this.screenTitle = '顧客情報詳細' // 画面タイトル
    this.butenCode // 部店
    this.accountNumber // 口座番号
    this.brokerCode // 仲介業者コード
    this.brokerName // 仲介業者名
    this.brokerChargeCode // 仲介業者営業員コード
    this.brokerChargeName // 仲介業者営業員名
    this.nameKanji // 顧客名(漢字)
    this.nameKana // 顧客名(カナ)
    this.addressKanji1 // 住所(漢字)
    this.phoneNumber // 電話番号
    this.phoneFlg // 自宅電話不可フラグ
    this.sex // 性別
    this.age // 年齢
    this.openAcctDate // 口座開設年月日
    this.tcCompRank // コンプラランク
  }
}
