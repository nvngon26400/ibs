import Logger from '@/utils/ifaLog.js'
import { getFormattedDateValue } from '@/components/Date/js/IfaDatePickerFunction.js'
export class IfaPortalNotificationUpdateA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.displayPeriodFrom = obj.period[0] ? getFormattedDateValue(obj.period[0], 'date8') : '' // 表示期間From
    this.displayPeriodTo = obj.period[1] ? getFormattedDateValue(obj.period[1], 'date8') : '' // 表示期間To
    this.notificationContent = obj.notificationContent ? obj.notificationContent : '' // ご連絡内容
  }
}
