import Logger from '@/utils/ifaLog.js'
export class IfaDepositWithdrawDetailA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? 'true' : 'false' // 仲介業者除外
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名(漢字/カナ)
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名(漢字/カナ)
    this.course = obj.courseSelected ? obj.courseSelected : '' // 取引コース
    this.depositWithdrawDetailType = obj.depositWithdrawDetailType ? obj.depositWithdrawDetailType : '' // 区分
    this.periodYmFrom = obj.period[0] ? obj.period[0] : '' // 期間指定（From)
    this.periodYmTo = obj.period[1] ? obj.period[1] : '' // 期間指定（To)
  }
}
