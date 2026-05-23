import Logger from '@/utils/ifaLog.js'
export class IfaDocClassListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    Logger.debug(obj)
    this.registerCategoryList = obj.updateCategoryList ? obj.updateCategoryList : []
  }
}
