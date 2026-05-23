import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterConfirmManagerA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.registerDate = obj.registerDate ? obj.registerDate : '' // 登録年月
    this.answerStatus = obj.answerStatus ? obj.answerStatus : '' // 回答状況
    this.answerResult = obj.answerResult ? obj.answerResult : '' // 回答結果
  }
}
