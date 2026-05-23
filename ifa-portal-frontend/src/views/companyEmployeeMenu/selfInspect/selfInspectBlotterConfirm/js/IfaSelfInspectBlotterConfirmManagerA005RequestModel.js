import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterConfirmManagerA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerDate = obj.registerDate ? obj.registerDate : '' // 登録年月
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
  }
}
