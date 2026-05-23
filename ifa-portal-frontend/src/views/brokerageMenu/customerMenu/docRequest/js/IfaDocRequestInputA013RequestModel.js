import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA013RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bmDeliveryNo = obj.bmDeliveryNo ? obj.bmDeliveryNo : '' // BM交付番号
  }
}
