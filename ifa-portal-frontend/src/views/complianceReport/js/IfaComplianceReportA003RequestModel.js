import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.lecId = obj.lecId ? obj.lecId : '' // LECTURE_ID
    this.confirmFlg = obj.confirmFlg ? obj.confirmFlg : '' // 確認フラグ
  }
}
