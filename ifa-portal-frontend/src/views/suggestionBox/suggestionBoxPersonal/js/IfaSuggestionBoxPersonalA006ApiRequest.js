import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxPersonalA006ApiRequest {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.status = obj.status ? obj.status : '' // ステータス
    this.registerDateFrom = obj.registerDateFrom ? obj.registerDateFrom : '' // 登録日_開始
    this.registerDateTo = obj.registerDateTo ? obj.registerDateTo : '' // 登録日_終了
    this.title = obj.title ? obj.title : '' // タイトル
  }
}
