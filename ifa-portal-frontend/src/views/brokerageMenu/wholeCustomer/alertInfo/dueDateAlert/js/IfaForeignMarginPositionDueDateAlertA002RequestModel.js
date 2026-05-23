import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginPositionDueDateAlertA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude // 仲介業者除外
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.customerNameKanjiKana = obj.customerNameKanjiKana ? obj.customerNameKanjiKana : '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = obj.customerNameKanjiKanaTerms ? obj.customerNameKanjiKanaTerms : '' // 顧客名（漢字／カナ）_条件
    this.course = obj.courseSelected.filter(e => e.isSelected).map(e => e.id).join(',')// 取引コース
  }
}
