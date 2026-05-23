import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA017RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.registerYearMonthThisMonth = obj.registerYearMonthThisMonth ? obj.registerYearMonthThisMonth : '' // 登録年月（当月）
    this.registerYearMonthLastMonth = obj.registerYearMonthLastMonth ? obj.registerYearMonthLastMonth : '' // 登録年月（過去月）
  }
}
