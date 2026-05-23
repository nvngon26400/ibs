export class IfaFxTradeOrderLookupA003RequestModel {
  constructor(obj) {
    this.refinementPeriodFrom = obj.refinementPeriod[0] ? obj.refinementPeriod[0] : '' // 絞込期間from
    this.refinementPeriodTo = obj.refinementPeriod[1] ? obj.refinementPeriod[1] : '' // 絞込期間to
    this.orderStatus = obj.orderStatus ? obj.orderStatus : '' // 注文状況
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
  }
}
