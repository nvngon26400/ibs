import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportInfoRegisterManagerA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.lectureId = obj.lectureId ? obj.lectureId : '' // LECTURE_ID
  }
}
