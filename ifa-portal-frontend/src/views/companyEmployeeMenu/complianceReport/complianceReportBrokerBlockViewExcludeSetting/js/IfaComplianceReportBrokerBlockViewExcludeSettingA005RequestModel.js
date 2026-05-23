import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportBrokerBlockViewExcludeSettingA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
  }
}
