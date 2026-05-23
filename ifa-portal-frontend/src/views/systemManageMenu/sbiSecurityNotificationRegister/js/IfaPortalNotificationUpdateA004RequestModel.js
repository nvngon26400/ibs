import Logger from '@/utils/ifaLog.js'
export class IfaPortalNotificationUpdateA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : '' // お知らせID
  }
}
