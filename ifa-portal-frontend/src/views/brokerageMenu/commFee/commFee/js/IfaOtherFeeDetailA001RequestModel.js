import Logger from '@/utils/ifaLog.js'
export class IfaOtherFeeDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.targetDateYm = obj.targetDateYm ? obj.targetDateYm : '' // 対象年月
  }
}
