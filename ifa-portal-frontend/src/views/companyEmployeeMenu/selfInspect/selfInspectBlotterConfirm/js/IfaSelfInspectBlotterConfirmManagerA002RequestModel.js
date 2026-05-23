import Logger from '@/utils/ifaLog.js'
export class IfaSelfInspectBlotterConfirmManagerA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerDate = obj.registerDate ? obj.registerDate : '' // 登録年月
    this.answerStatus = obj.answerStatus ? obj.answerStatus : '' // 回答状況
    this.answerResult = obj.answerResult ? obj.answerResult : '' // 回答結果
  }
}
