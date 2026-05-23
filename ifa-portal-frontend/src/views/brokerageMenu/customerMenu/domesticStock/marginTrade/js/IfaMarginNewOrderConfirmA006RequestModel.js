import Logger from '@/utils/ifaLog.js'
export class IfaMarginNewOrderConfirmA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentsNo = obj.contentsNo ? obj.contentsNo : '' // コンテンツNo
  }
}
