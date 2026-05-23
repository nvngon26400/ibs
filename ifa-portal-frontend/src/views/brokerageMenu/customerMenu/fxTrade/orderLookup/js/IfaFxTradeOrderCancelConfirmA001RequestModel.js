import Logger from '@/utils/ifaLog.js'
export class IfaFxTradeOrderCancelConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.currency = obj.currency ? obj.currency : '' // 通貨名
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.cycleNumber = obj.cycleNumber ? obj.cycleNumber : '' // サイクル番号
    this.orderEventId = obj.orderEventId ? obj.orderEventId : '' // 注文イベントID
    this.businessDay = obj.businessDay ? obj.businessDay : '' // 営業日
  }
}
