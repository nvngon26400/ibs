import Logger from '@/utils/ifaLog.js'
export class IfaContactDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ifaToiawaseNo = obj.ifaToiawaseNo ? obj.ifaToiawaseNo : '' // 問合せNO
    this.screenId = obj.screenId ? obj.screenId : '' // screenId
  }
}
