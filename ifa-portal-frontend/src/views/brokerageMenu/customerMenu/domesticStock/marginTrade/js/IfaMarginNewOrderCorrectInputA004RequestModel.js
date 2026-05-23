import Logger from '@/utils/ifaLog.js'
export class IfaMarginNewOrderCorrectInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
  }
}
