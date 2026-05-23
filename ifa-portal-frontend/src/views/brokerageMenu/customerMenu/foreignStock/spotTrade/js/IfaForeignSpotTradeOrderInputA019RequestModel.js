import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderInputA019RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentNo = obj.contentNo ? obj.contentNo : '' // コンテンツNo
  }
}
