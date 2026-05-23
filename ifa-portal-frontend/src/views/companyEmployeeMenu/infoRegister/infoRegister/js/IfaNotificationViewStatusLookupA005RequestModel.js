import Logger from '@/utils/ifaLog.js'
export class IfaNotificationViewStatusLookupA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId.length ? obj.notificationId : '' // お知らせリスト.お知らせID
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
  }
}
