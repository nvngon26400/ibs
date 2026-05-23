import Logger from '@/utils/ifaLog.js'
export class IfaDocClassListA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    Logger.debug(obj)
    this.registerCategoryList = obj.registerCategoryList ? obj.registerCategoryList : []
  }
}
