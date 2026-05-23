// SUB0206_01-01共同募集　顧客管理のフォームモデル
export class IfaJointSubscriptCustomerManageFormModel {
  constructor() {
    this.screenId = 'SUB0206_01-01' // 画面ID
    this.screenTitle = '共同募集　顧客管理' // 画面タイトル
    this.commComment = ''// コメント
    this.brokerCode = '' // 仲介業者コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.jointBranchCode = '' // 共募支店コード
    this.editStatus = '' // 手続状況 【初期値】全て
    this.chkBrokerCodeExclude = '' // 仲介業者コード除外フラグ
    this.jointUserDispKbn = '1' // 共通募集顧客表示内容
    this.jointSubscriptCustomerManageList = [ // 共同募集 顧客管理一覧
      {
        brokerCode: '', // 仲介業者コード
        brokerName: '', // 仲介業者名
        butenCode: '', // 部店
        accountNumber: '', // 口座番号
        customerAttributeName: '', // コース名
        nameKanji: '', // 顧客名
        jointBranchCode: '', // 共募支店コード
        jointBranchName: '', // 共募支店名
        contractDate: '', // 契約締結日
        startDate: '', // 同意日
        endDate: '', // 終了日
        jointRewardRate: '', // 支払率
        editStatus: '', // 手続状況
        editStatusName: '', // 手続状況名
        brokerChargeCode: '', // 営業員コード
        brokerChargeName: '', // 営業名
        autoCancellationReason: '' // 自動解約理由
      }
    ]
  }
}
