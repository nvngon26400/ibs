import Logger from '@/utils/ifaLog.js'
export class IfaFxTradeOrderCancelConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.cycleNumber = obj.cycleNumber ? obj.cycleNumber : ''// サイクル番号
    this.orderEventId = obj.orderEventId ? obj.orderEventId : ''// 注文イベントID
    this.currency = obj.currency ? obj.currency : '' // 通貨名
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.tradingAccount = obj.tradingAccount ? obj.tradingAccount : '' // 取引口座
    this.businessDay = obj.businessDay ? obj.businessDay : '' // 営業日
  }
}
