import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportViewStatusLookupManagerA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.brokerChargeName = obj.brokerChargeName ? obj.brokerChargeName : '' // 営業員名
    this.title = obj.complianceReportTitle ? obj.complianceReportTitle : '' // タイトル
    this.viewStatus = obj.viewStatus ? obj.viewStatus : '' // 閲覧状況
    this.viewNecessity = obj.viewNecessity ? obj.viewNecessity : '' // 閲覧要否
  }
}
