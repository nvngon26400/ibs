import Logger from '@/utils/ifaLog.js'
export class IfaOrderStatusListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
    this.buySellTypeName = obj.buySellTypeName ? obj.buySellTypeName : '' // 取引種別
  }
}
