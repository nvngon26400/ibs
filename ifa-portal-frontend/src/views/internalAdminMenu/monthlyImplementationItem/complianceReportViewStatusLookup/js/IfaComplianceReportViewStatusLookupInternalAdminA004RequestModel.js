import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportViewStatusLookupInternalAdminA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.viewStatus = obj.viewStatusType ? obj.viewStatusType : '' // 閲覧状況
    this.viewTarget = obj.viewTarget ? obj.viewTarget : '' // 閲覧対象
    this.titleThisMonth = obj.titleThisMonth ? obj.titleThisMonth : '' // タイトル（当月）
    this.titleLastMonth = obj.titleLastMonth ? obj.titleLastMonth : '' // タイトル（過去月）
    // this.reportYearMonthThisMonth = obj.reportYearMonthThisMonth ? obj.reportYearMonthThisMonth : '' // 通信年月（当月）
    // this.reportYearMonthLastMonth = obj.reportYearMonthLastMonth ? obj.reportYearMonthLastMonth : '' // 通信年月（過去月）
  }
}
