import Logger from '@/utils/ifaLog.js'
export class IfaBrandPositionListA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.openTradeKbn ? obj.openTradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.newOpenMarket = obj.newOpenMarket ? obj.newOpenMarket : '' // 新規市場
    this.parentStockTradeDate = obj.hiddenItemParentStockTradeDate ? obj.hiddenItemParentStockTradeDate : '' // 親株新規約定日
    this.newTradeDate = obj.constructionDate ? obj.constructionDate : '' // 新規約定日
    this.newPrice = obj.newPrice ? obj.newPrice : '' // 新規単価
  }
}
