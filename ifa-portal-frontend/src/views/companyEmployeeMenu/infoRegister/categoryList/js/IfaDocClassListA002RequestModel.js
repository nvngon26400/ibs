import Logger from '@/utils/ifaLog.js'
export class IfaDocClassListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    Logger.debug(obj)
    this.category = obj.category ? obj.category : '' // カテゴリ
  }
}
