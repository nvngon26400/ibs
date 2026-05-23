import Logger from '@/utils/ifaLog.js'
export class IfaStarUploadFileOutputListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.sysDate = obj.sysDate ? obj.sysDate : '' // システム日付
    this.minKey = obj.minKey ? obj.minKey : '' // MIN_KEY
    this.maxKey = obj.maxKey ? obj.maxKey : '' // MAX_KEY
    this.dataCnt = obj.dataCnt ? obj.dataCnt : '' // 画面表示明細件数
  }
}
