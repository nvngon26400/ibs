import Logger from '@/utils/ifaLog.js'
export class IfaInfoRegisterLookupX001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationCategoryList = obj.notificationCategoryList ? obj.notificationCategoryList : [] // お知らせカテゴリリスト
  }
}
