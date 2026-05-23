import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportBrokerBlockViewExcludeSettingA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
  }
}
