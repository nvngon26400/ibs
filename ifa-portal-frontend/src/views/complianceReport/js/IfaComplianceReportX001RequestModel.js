import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportX001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.lecId = obj.lecId ? obj.lecId : '' // 表示年月
  }
}
