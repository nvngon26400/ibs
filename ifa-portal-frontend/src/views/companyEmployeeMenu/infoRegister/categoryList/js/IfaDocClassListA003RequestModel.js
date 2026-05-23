import Logger from '@/utils/ifaLog.js'
export class IfaDocClassListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    Logger.debug(obj)
    this.category = obj.category ? obj.category : '' // カテゴリ
    this.t9nInfoCat = obj.t9nInfoCat ? obj.t9nInfoCat : '' // カテゴリID
  }
}
