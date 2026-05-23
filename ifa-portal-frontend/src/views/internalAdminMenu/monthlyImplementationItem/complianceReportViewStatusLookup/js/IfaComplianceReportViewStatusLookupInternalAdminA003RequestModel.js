import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportViewStatusLookupInternalAdminA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.brokerChargeName = obj.employeeName ? obj.employeeName : '' // 営業員名
    this.title = obj.title ? obj.title : '' // タイトル
    this.viewStatus = obj.viewStatusType ? obj.viewStatusType : '' // 閲覧状況
    this.viewTarget = obj.viewTarget ? obj.viewTarget : '' // 閲覧対象
  }
}
