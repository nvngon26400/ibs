import Logger from '@/utils/ifaLog.js'
export class IfaNotificationViewStatusLookupA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせリスト.お知らせID
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
  }
}
