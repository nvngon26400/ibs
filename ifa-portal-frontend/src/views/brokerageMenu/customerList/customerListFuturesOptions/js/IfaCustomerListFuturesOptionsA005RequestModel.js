import Logger from '@/utils/ifaLog.js'
export class IfaCustomerListFuturesOptionsA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : '' // 仲介業者除外
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名(漢字/カナ)（条件リスト）
    this.course = obj.courseSelected ? obj.courseSelected : '' // 取引コース
    this.necessaryEntrustDepositFrom = obj.necessaryEntrustDepositFrom ? obj.necessaryEntrustDepositFrom : '' // 必要委託保証金（From）
    this.necessaryEntrustDepositTo = obj.necessaryEntrustDepositTo ? obj.necessaryEntrustDepositTo : '' // 必要委託保証金（To）
    this.marginMoneyFrom = obj.marginMoneyFrom ? obj.marginMoneyFrom : '' // 受入証拠金（From）
    this.marginMoneyTo = obj.marginMoneyTo ? obj.marginMoneyTo : '' // 受入証拠金（To）
    this.beforeProfitAndLossFrom = obj.beforeProfitAndLossFrom ? obj.beforeProfitAndLossFrom : '' // 前日評価損益（From）
    this.beforeProfitAndLossTo = obj.beforeProfitAndLossTo ? obj.beforeProfitAndLossTo : '' // 前日評価損益（To）
  }
}
