import Logger from '@/utils/ifaLog.js'
export class IfaNotificationViewStatusLookupA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせリスト.お知らせID
    this.corReferenceCondition = obj.corReferenceCondition ? obj.corReferenceCondition : '' // お知らせリスト.参照範囲
    this.title = obj.title ? obj.title : '' // お知らせリスト.タイトル
    this.registerDayTime = obj.registerDayTime ? obj.registerDayTime : '' // お知らせリスト.登録日時
  }
}
