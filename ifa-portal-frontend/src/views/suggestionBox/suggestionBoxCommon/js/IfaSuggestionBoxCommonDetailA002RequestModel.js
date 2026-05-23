import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxCommonDetailA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.attachFile = obj.attachFile ? obj.attachFile : '' // 添付ファイル
  }
}

