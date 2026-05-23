import Logger from '@/utils/ifaLog.js'
export class IfaPayNotificationDocDownloadA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.targetDateYmFrom = obj.dateYmRange[0] ? obj.dateYmRange[0] : '' // 対象年月From
    this.targetDateYmTo = obj.dateYmRange[1] ? obj.dateYmRange[1] : '' // 対象年月To
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? 'true' : 'false' // 仲介業者除外
  }
}
