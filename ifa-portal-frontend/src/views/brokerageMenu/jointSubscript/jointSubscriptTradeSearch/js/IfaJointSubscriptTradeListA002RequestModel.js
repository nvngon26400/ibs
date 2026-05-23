import Logger from '@/utils/ifaLog.js'
export class IfaJointSubscriptTradeListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : false // 仲介業者除外
    this.jointBranchCode = obj.jointBranchCode ? obj.jointBranchCode : '' // 共募支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.periodDateFrom = obj.periodDateFrom ? obj.periodDateFrom : '' // 期間指定（From)
    this.periodDateTo = obj.periodDateTo ? obj.periodDateTo : '' // 期間指定（To)
    this.securityClass = obj.securityClass ? obj.securityClass : '' // 証券種別
    this.brandCode12 = obj.brandCode12 ? obj.brandCode12 : '' // 銘柄コード
    this.customerName = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名
    this.customerNameSearchType = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名検索オプション
    this.course = obj.courseSelected ? obj.courseSelected : '' // 取引コース
  }
}
