import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA023RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    // カテゴリIDリスト
    this.categoryIdList = [{
      category: obj.category ? obj.category : '' // カテゴリIDリスト.カテゴリ
    }]
  }
}
