import Logger from '@/utils/ifaLog.js'
export class IfaReceiptDeliveryOrderInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.openTradeKbn ? (obj.openTradeKbn === '8' ? '0' : '1') : '' // 新規売買区分
    this.newOpenMarket = obj.newOpenMarket ? obj.newOpenMarket : '' // 新規市場
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.parentStockTradeDate = obj.parentStockConstructionDate ? obj.parentStockConstructionDate : '' // 親株新規約定日
    this.newTradeDate = obj.constructionDate ? obj.constructionDate : '' // 新規約定日
    this.newPrice = obj.newPrice ? obj.newPrice : '' // 新規単価
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
  }
}
