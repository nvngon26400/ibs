import Logger from '@/utils/ifaLog.js'
export class IfaRepAddA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.privId = obj.privId ? obj.privId : '' // 権限コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
  }
}
