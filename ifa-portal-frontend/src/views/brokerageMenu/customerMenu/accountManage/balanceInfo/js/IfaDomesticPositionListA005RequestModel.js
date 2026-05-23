import Logger from '@/utils/ifaLog.js'
export class IfaDomesticPositionListA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.contPositionTotal = obj.contPositionTotal ? obj.contPositionTotal : '' // 建株数
    this.positionDetailList = [{ // 返済建玉明細
      parentStockTradeDate: obj.parentStockTradeDate ? obj.parentStockTradeDate : '', // 返済建玉明細.親株新規約定日
      constructionDate: obj.newTradeDate ? obj.newTradeDate : '', // 返済建玉明細.新規約定日
      newPrice: obj.newPrice ? obj.newPrice : '', // 返済建玉明細.新規単価
      market: obj.market ? obj.market : '' // 返済建玉明細.建市場
    }]
    this.repayMethod = '2' // 返済方法
  }
}
