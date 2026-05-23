import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderInputA020RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marketCode = obj.marketCode ? obj.marketCode : '' // 市場コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.foreignQuantity = obj.foreignQuantity ? obj.foreignQuantity : '' // 「注文数量」が、項目辞書に存在しません。
    this.orderPriceKindList = obj.orderPriceKindList ? obj.orderPriceKindList : '' // 価格条件
    this.orderPriceKindListReversePriceLimit = obj.orderPriceKindListReversePriceLimit ? obj.orderPriceKindListReversePriceLimit : '' // 価格条件（逆指値）
    this.hiddenOrderPrice = obj.hiddenOrderPrice ? obj.hiddenOrderPrice : '' // 注文単価（指値）
    this.hiddenOrderPriceReversePriceLimit = obj.hiddenOrderPriceReversePriceLimit ? obj.hiddenOrderPriceReversePriceLimit : '' // 注文単価(逆指値)
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice = obj.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice ? obj.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice : '' // 「発火条件価格」が、項目辞書に存在しません。
    this.periodRadio = obj.periodRadio ? obj.periodRadio : '' // 期間条件
    this.period = obj.period ? obj.period : '' // 「期間 "yyyy-MM-dd"形式」が、項目辞書に存在しません。
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.marginDueDate = obj.marginDueDate ? obj.marginDueDate : '' // 「信用期日」が、項目辞書に存在しません。
    this.kessaiHoho = obj.kessaiHoho ? obj.kessaiHoho : '' // 決済方法
  }
}
