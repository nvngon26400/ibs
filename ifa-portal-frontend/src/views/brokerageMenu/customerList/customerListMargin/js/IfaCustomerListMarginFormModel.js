export class IfaCustomerListMarginFormModel {
  constructor() {
    this.title = {
      id: 'SUB0201_02-01',
      name: '顧客一覧・信用'
    }
    this.customerNameKanjiKanaTerms = '3'
    this.beforeProfitAndLossFrom = '' // 前日評価損益（From）
    this.beforeProfitAndLossTo = '' // 前日評価損益（To）
    this.beforeDepositBalanceFrom = '' // 前日保証金残高（From）
    this.beforeDepositBalanceTo = '' // 前日保証金残高（To）
    this.beforeEntrustDepositRateFrom = '' // 前日委託保証金率（From）
    this.beforeEntrustDepositRateTo = '' // 前日委託保証金率（To）
    this.marginCallCheck = '' // 追証（チェック） 【初期値】OFF
    this.withdrawalDeficientCheck = '' // 引出金不足（チェック） 【初期値】OFF
    this.brokerName = '' // 仲介業者名
    this.empCode = '' // 営業員コード
    this.brokerChargeName = '' // 営業員名
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.customerListCourse = '' // 取引コース
    this.customerNameKanji = '' // 顧客名(漢字)
    this.customerNameKana = '' // 顧客名(カナ)
    this.tcCompRank = '' // Cランク
    this.beforeProfitAndLoss = '' // 前日評価損益
    this.beforeDepositBalance = '' // 前日保証金残高
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.branchName = '' // 支店名
    this.pattern = ''
  }
}
