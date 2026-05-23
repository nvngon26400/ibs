import Logger from '@/utils/ifaLog.js'
export class IfaBrandPositionListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.openTradeKbn ? obj.openTradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.contPositionTotal = obj.maxOrderableQuantity ? obj.maxOrderableQuantity : '' // 建株数に注文可能数量を格納
    this.positionDetailList = [{ // 返済建玉明細
      parentStockTradeDate: obj.hiddenItemParentStockTradeDate ? obj.hiddenItemParentStockTradeDate : '', // 返済建玉明細.親株新規約定日
      constructionDate: obj.constructionDate ? obj.constructionDate : '', // 返済建玉明細.新規約定日
      newPrice: obj.newPrice ? obj.newPrice : '', // 返済建玉明細.新規単価
      market: obj.market ? obj.market : '' // 返済建玉明細.建市場
    }]
    this.repayMethod = '2' // 返済方法
  }
}
