import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxPersonalDetailA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.pdfFileName = obj.attachFile ? obj.attachFile : '' // 添付ファイル
  }
}
