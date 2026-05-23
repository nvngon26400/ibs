export class IfaCustomerListFuturesOptionsFormModel {
  constructor() {
    this.screenId = 'SUB0201_03-01' // 画面ID
    this.screenTitle = '顧客一覧・先OP' // 画面タイトル
    // ifa-common-serch
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = '' // 仲介業者除外チェックボックス
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字/カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名検索条件（初期値：'3'）
    this.courseSelected = [
      { id: 'J', isSelected: true },
      { id: 'A', isSelected: true },
      { id: 'B', isSelected: true },
      { id: 'C', isSelected: true },
      { id: 'D', isSelected: true },
      { id: 'E', isSelected: true },
      { id: 'F', isSelected: true },
      { id: 'G', isSelected: true },
      { id: 'H', isSelected: true },
      { id: 'I', isSelected: true }
    ] // 取引コース
    // 詳細検索条件
    this.necessaryEntrustDepositFrom = '' // 必要委託保証金（From）
    this.necessaryEntrustDepositTo = '' // 必要委託保証金（To）
    this.marginMoneyFrom = '' // 受入証拠金（From）
    this.marginMoneyTo = '' // 受入証拠金（To）
    this.beforeProfitAndLossFrom = '' // 前日評価損益（From）
    this.beforeProfitAndLossTo = '' // 前日評価損益（To）
  }
}
