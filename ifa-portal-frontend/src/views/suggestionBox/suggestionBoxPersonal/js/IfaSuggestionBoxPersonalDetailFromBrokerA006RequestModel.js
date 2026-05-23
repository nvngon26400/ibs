import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxPersonalDetailFromBrokerA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.sbpNo = obj.sbpNo ? obj.sbpNo : '' // あなたの要望No
    this.status = obj.status ? obj.status : '' // ステータス
    this.registeredAnswerList = obj.registeredAnswerList ? obj.registeredAnswerList : [] // 登録済回答一覧
    this.newAnswerList = obj.newAnswerList ? obj.newAnswerList : [] // 新規回答一覧
  }
}
