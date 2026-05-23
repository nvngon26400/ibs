import Logger from '@/utils/ifaLog.js'
export class IfaTradeTrendSearchA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? '1' : '0'
    this.branchCode = obj.branchCode ? obj.branchCode : '' // 支店コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.course = obj.courseSelected ? obj.courseSelected : '' // 取引コース
    this.countingUnit = obj.countingUnit ? obj.countingUnit : '' // 集計単位
    this.visibleButenCode = obj.visibleButenCode ? obj.visibleButenCode : '' // 閲覧可能部店
    this.periodMonth = obj.periodMonth ? obj.periodMonth : '' // 期間指定
    this.securityClass = obj.securityClassList ? obj.securityClassList : '' // 証券種別
  }
}
