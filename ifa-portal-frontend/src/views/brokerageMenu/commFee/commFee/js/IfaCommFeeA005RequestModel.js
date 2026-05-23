import Logger from '@/utils/ifaLog.js'
export class IfaCommFeeA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.targetDateYm = obj.targetDateYm ? obj.targetDateYm : '' // 対象年月
  }
}
