import Logger from '@/utils/ifaLog.js'
export class IfaDocRequestInputA014RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.bmDeliveryNo = obj.bmDeliveryNo ? obj.bmDeliveryNo : '' // BM交付番号
  }
}
