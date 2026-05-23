import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportBrokerBlockViewExcludeSettingA003ReSearchRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerName = obj.branchNameOfBrokerLastSearchWord ? obj.branchNameOfBrokerLastSearchWord : '' // 検索条件
  }
}
