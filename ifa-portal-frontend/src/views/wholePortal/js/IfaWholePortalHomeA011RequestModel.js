import Logger from '@/utils/ifaLog.js'
export class IfaWholePortalHomeA011RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.periodDesignationFrom = obj.periodDesignationFrom ? obj.periodDesignationFrom : '' // アラート通知日
  }
}
