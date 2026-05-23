import Logger from '@/utils/ifaLog.js'
export class IfaDomesticPositionDetailX001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.openTradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.newOpenMarket = obj.market ? obj.market : '' // 新規市場
    this.newOpenInterestNumber = obj.newOpenInterestNumber ? obj.newOpenInterestNumber : '' // 新規建玉指定番号
    this.parentStockTradeDate = obj.parentStockTradeDate ? obj.parentStockTradeDate : '' // 親株新規約定日
    this.newTradeDate = obj.newTradeDate ? obj.newTradeDate : '' // 新規約定日
    this.openPrice = obj.newPrice ? obj.newPrice : '' // 取得単価
    this.batchIndividualDisplayFlag = Number(obj.positionCount) === 1 ? '1' : '0' // 一括個別表示フラグ
  }
}
