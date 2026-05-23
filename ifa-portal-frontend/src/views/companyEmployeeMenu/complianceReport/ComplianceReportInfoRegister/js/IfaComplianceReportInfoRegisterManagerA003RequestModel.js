import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportInfoRegisterManagerA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.disclosureFlag = obj.disclosureFlag === '0' ? '1' : '0' // 公開フラグ
    this.lectureId = obj.lectureId ? obj.lectureId : '' // LECTURE_ID
  }
}
