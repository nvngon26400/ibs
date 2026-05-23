import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeRepayOrderInputA012RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marketCode = obj.marketCode ? obj.marketCode : '' // 市場コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.closeOrderQuantity = obj.closeOrderQuantity ? obj.closeOrderQuantity : '' // 注文数量：一括指定の場合
    this.total = obj.closeOrderQuantity ? obj.closeOrderQuantity : '' // 注文数量：個別指定の場合
    this.orderPriceKindList = obj.orderPriceKindList === '3' ? obj.stopOrderPriceKindList : obj.orderPriceKindList // 価格条件
    this.limitPrice2 = obj.limitPrice2 ? obj.limitPrice2 : '' // 注文単価：指値
    this.stopOrderExecutePrice2 = obj.stopOrderExecutePrice2 ? obj.stopOrderExecutePrice2 : '' // 注文単価：逆指値
    this.stopOrderPrice = obj.stopOrderPrice ? obj.stopOrderPrice : '' // 発火条件価格
    this.periodRadio = obj.periodRadio ? '1' : '0' // 期間条件
    this.period = obj.period ? obj.period : '' // 期間(日付指定)
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.marginDueDate = obj.foreignStockMarginPositionSummary.marginDueDate ? obj.foreignStockMarginPositionSummary.marginDueDate : '' // 信用期日
    this.kessaiHoho = obj.kessaiHoho ? obj.kessaiHoho : '' // 決済方法
    this.repayPositionDesignateMethod = obj.repayPositionDesignateMethod ? obj.repayPositionDesignateMethod : '' // 返済建玉指定方法
    this.repaySelectOrder = obj.repaySelectOrder ? obj.repaySelectOrder : '' // 返済選択順序
    this.trade = obj.trade ? obj.trade : '' // 建区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.stockTicket = obj.stockTicket ? obj.stockTicket : '' // 株価チケット
    this.unitPrice = obj.unitPrice ? obj.unitPrice : '' // 建単価
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.individualOrderCount = obj.repayPositionDesignateMethod !== '0' && obj.individualOrderCount ? obj.individualOrderCount : '' // TODO 注文数量(個別)
    this.checkInsider = obj.checkInsider[0] ? obj.checkInsider[0] : '' // 確認項目.インサイダー確認
    this.tradeLimitUrl = obj.tradeLimitUrl ? obj.tradeLimitUrl : '' // 本日の注意銘柄URL
    this.closedDayUrl = obj.closedDayUrl ? obj.closedDayUrl : ''// 休場日URL
    this.yenClosedUrl = obj.yenClosedUrl ? obj.yenClosedUrl : ''// 円貨決済停止日URL
    this.usequityListUrl = obj.usequityListUrl ? obj.usequityListUrl : ''// 取扱銘柄一覧URL
    this.tradingAttentionUrl = obj.tradingAttentionUrl ? obj.tradingAttentionUrl : ''// お取引注意事項URL
    this.positionDesignationAreaIndividualPositionInfoList = obj.foreignStockMarginPositionDetailList ? obj.foreignStockMarginPositionDetailList : [] // 個別建玉情報一覧
  }
}
