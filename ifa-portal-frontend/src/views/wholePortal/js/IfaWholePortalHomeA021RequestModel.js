import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA021RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    // カテゴリIDリスト
    this.categoryIdList = [{
      categoryId: '', // カテゴリIDリスト.カテゴリID
      requiredFlag: '', // カテゴリIDリスト.必須フラグ
      category: '' // カテゴリIDリスト.カテゴリ
    }]
  }
}
