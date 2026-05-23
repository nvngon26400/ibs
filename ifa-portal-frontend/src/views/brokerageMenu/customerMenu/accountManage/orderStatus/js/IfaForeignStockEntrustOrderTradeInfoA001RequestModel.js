import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockEntrustOrderTradeInfoA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
    this.tradeCd = obj.buySellTypeName ? obj.buySellTypeName : '' // 取引種別
  }
}
