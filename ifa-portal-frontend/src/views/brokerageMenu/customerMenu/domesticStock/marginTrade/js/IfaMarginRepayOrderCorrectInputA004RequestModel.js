import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderCorrectInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
  }
}
