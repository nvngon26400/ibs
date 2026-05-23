import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportViewStatusLookupManagerA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.userId = obj.userId ? obj.userId : '' // ユーザー_ID
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.empCode = obj.empCode ? obj.empCode : '' // 営業員コード
    this.viewExcludeSetting = obj.viewExcludeSetting ? obj.viewExcludeSetting : '' // 閲覧不要設定
  }
}

