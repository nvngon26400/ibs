import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportViewStatusLookupManagerA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.userId = obj.userId ? obj.userId : '' // ユーザーID
    this.lectureId = obj.lectureId ? obj.lectureId : '' // LECTURE_ID
    this.viewReportFlag = obj.viewReportFlag ? obj.viewReportFlag : '' // 閲覧報告フラグ
  }
}
