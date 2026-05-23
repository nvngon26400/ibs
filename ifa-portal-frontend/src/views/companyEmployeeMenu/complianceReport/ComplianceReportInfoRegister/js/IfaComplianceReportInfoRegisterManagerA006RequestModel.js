import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportInfoRegisterManagerA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.lectureId = obj.lectureId ? obj.lectureId : '' // LECTURE_ID
  }
}
