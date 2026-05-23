import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderCorrectInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
  }
}
