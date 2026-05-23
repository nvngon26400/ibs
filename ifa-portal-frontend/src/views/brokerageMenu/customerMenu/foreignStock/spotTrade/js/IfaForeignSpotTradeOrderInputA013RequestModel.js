import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderInputA013RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.marketCode = obj.marketCode ? obj.marketCode : '' // 市場コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.buySellTypeName = obj.buySellTypeName ? obj.buySellTypeName : '' // 取引種別
    this.orderQuantity = obj.foreignQuantity ? obj.foreignQuantity : '' // 注文数量
    this.orderPriceKindList = obj.orderPriceKind === '3' ? (obj.orderPriceKindListReversePriceLimit ? obj.orderPriceKindListReversePriceLimit : '') : (obj.orderPriceKind ? obj.orderPriceKind : '') // 価格条件
    this.limitOrderPrice = obj.limitOrderPrice ? obj.limitOrderPrice : '' // 注文単価
    this.stopOrderExecutePrice = obj.stopOrderExecutePrice ? obj.stopOrderExecutePrice : '' // 注文単価（逆指値）
    this.stopOrderPrice = obj.stopOrderPrice ? obj.stopOrderPrice : '' // 発火条件価格
    this.periodRadio = obj.periodRadio ? obj.periodRadio : '' // 期間条件
    this.period = obj.period ? obj.period : '' // 期間
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.currencyTypeName = obj.currencyTypeName ? obj.currencyTypeName : '' // 決済方法
    this.solicitTypeList = obj.solicitTypeList ? obj.solicitTypeList : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.solicitationEtf = obj.solicitationEtf ? obj.solicitationEtf : '' // 乗換え勧誘(ETF)
    this.engPubCheckbox = (obj.buySellTypeName === '0' && obj.engPubCheck === '1') ? obj.engPubCheckbox : '' // 英文開示銘柄
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認
    this.todayTradeLimitUrl = obj.todayTradeLimitUrl ? obj.todayTradeLimitUrl : '' // 本日の注意銘柄URL
    this.closedDay = obj.closedDay ? obj.closedDay : '' // 休場日URL
    this.yenClosedDateUrl = obj.yenClosed ? obj.yenClosed : '' // 円貨決済停止日URL
    this.handledStockListUrl = obj.handledStockListUrl ? obj.handledStockListUrl : '' // 取扱銘柄一覧URL
    this.noticeofTransactionPrecautionsUrl = obj.tradingAttention ? obj.tradingAttention : '' // お取引注意事項URL
  }
}
