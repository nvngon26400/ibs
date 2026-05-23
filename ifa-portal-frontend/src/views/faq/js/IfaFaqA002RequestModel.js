import Logger from '@/utils/ifaLog.js'
export class IfaFaqA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.searchWord = obj.searchWord ? obj.searchWord : '' // 検索ワード
  }
}
