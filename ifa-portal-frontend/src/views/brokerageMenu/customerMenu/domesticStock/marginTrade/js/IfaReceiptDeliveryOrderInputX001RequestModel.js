import Logger from '@/utils/ifaLog.js'
export class IfaReceiptDeliveryOrderInputX001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.openTradeKbn ? obj.openTradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.newOpenMarket = obj.newOpenMarket ? obj.newOpenMarket : '' // 新規市場
    this.parentStockTradeDate = obj.parentStockTradeDate ? obj.parentStockTradeDate : '' // 親株新規約定日
    this.newTradeDate = obj.newTradeDate ? obj.newTradeDate : '' // 新規約定日
    this.newPrice = obj.newPrice ? obj.newPrice : '' // 新規単価
  }
}
