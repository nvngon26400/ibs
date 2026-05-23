import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderInputA012RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marketCode = obj.marketCode ? obj.marketCode : '' // 市場コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.foreignQuantity = obj.foreignQuantity ? obj.foreignQuantity : '' // 注文数量
    this.orderPriceKindList = obj.orderPriceKindList ? obj.orderPriceKindList : '' // 価格条件
    this.orderPriceKindListReversePriceLimit = obj.orderPriceKindListReversePriceLimit ? obj.orderPriceKindListReversePriceLimit : '' // 価格条件（逆指値）
    this.hiddenOrderPrice = obj.hiddenOrderPrice ? obj.hiddenOrderPrice : '' // 注文単価（指値）
    this.hiddenOrderPriceReversePriceLimit = obj.hiddenOrderPriceReversePriceLimit ? obj.hiddenOrderPriceReversePriceLimit : '' // 注文単価(逆指値)
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice = obj.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice ? obj.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice : '' // 発火条件価格
    this.periodRadio = obj.periodRadio ? obj.periodRadio : '' // 期間条件
    this.period = obj.period ? obj.period : '' // 期間 "yyyy-MM-dd"形式
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.marginDueDate = obj.marginDueDate ? obj.marginDueDate : '' // 信用期日
    this.kessaiHoho = obj.kessaiHoho ? obj.kessaiHoho : '' // 決済方法
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.engPubText = obj.engPubText ? obj.engPubText : '' // 英文開示銘柄
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認
    this.tradeLimit = obj.tradeLimitUrl ? obj.tradeLimitUrl : '' // 本日の注意銘柄URL
    this.closedDay = obj.closedDay ? obj.closedDay : '' // 休場日URL
    this.yenClosed = obj.yenClosed ? obj.yenClosed : '' // 円貨決済停止日URL
    this.usequityList = obj.usequityList ? obj.usequityList : '' // 取扱銘柄一覧URL
    this.tradingAttention = obj.tradingAttention ? obj.tradingAttention : '' // お取引注意事項URL
  }
}
