import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportBrokerBlockViewExcludeSettingA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
  }
}
