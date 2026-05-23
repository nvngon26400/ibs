import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockCounterOrderInputA011RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentsNo = obj.contentsNo ? obj.contentsNo : '' // コンテンツNo
  }
}
