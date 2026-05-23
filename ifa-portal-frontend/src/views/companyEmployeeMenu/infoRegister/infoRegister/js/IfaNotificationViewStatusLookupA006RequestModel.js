import Logger from '@/utils/ifaLog.js'
export class IfaNotificationViewStatusLookupA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせリスト.お知らせID
    this.corReferenceCondition = obj.corReferenceCondition ? obj.corReferenceCondition : '' // お知らせリスト.参照範囲
  }
}
