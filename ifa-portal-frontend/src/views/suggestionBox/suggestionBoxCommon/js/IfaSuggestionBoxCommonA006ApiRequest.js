import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxCommonA006ApiRequest {
  constructor(obj) {
    Logger.debug(obj)
    this.status = obj.status ? obj.status : '' // ステータス
    this.registerDateFrom = obj.registerDate[0] ? obj.registerDate[0] : '' // 登録日（From)
    this.registerDateTo = obj.registerDate[1] ? obj.registerDate[1] : '' // 登録日（To)
    this.title = obj.title ? obj.title : '' // タイトル
  }
}
