import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.shoruiSeikyuuNo = obj.shoruiSeikyuuNo ? obj.shoruiSeikyuuNo : '' // 書類請求NO
    this.edaban = obj.edaban ? obj.edaban : '' // 枝番
  }
}
