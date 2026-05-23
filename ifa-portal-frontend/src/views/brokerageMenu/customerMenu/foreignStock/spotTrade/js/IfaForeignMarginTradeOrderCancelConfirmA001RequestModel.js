import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeOrderCancelConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
  }
}
