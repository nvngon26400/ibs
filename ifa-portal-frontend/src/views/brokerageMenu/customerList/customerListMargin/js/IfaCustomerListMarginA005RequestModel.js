import Logger from '@/utils/ifaLog.js'
export class IfaCustomerListMarginA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude // 仲介業者除外
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaConditionList = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名(漢字/カナ)（条件リスト）
    this.course = obj.courseSelected ? obj.courseSelected.filter(e => e.isSelected).map(e => e.id).join(',') : '' // 取引コース
    this.beforeProfitAndLossFrom = obj.beforeProfitAndLossFrom ? obj.beforeProfitAndLossFrom : '' // 前日評価損益（From）
    this.beforeProfitAndLossTo = obj.beforeProfitAndLossTo ? obj.beforeProfitAndLossTo : '' // 前日評価損益（To）
    this.beforeDepositBalanceFrom = obj.beforeDepositBalanceFrom ? obj.beforeDepositBalanceFrom : '' // 前日保証金残高（From）
    this.beforeDepositBalanceTo = obj.beforeDepositBalanceTo ? obj.beforeDepositBalanceTo : '' // 前日保証金残高（To）
    this.beforeEntrustDepositRateFrom = obj.beforeEntrustDepositRateFrom ? obj.beforeEntrustDepositRateFrom : '' // 前日委託保証金率（From）
    this.beforeEntrustDepositRateTo = obj.beforeEntrustDepositRateTo ? obj.beforeEntrustDepositRateTo : '' // 前日委託保証金率（To）
    this.marginCallCheck = obj.marginCallCheck // 追証（チェック）
    this.withdrawalDeficientCheck = obj.withdrawalDeficientCheck // 引出金不足（チェック）
    this.pattern = obj.pattern ? obj.pattern : ''
  }
}
