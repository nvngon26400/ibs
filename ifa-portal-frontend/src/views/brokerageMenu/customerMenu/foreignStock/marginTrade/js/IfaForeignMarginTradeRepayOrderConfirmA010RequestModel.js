import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeRepayOrderConfirmA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marketCode = obj.marketCode ? obj.marketCode : '' // 市場コード
    this.marketAbbreviatedName = obj.marketAbbreviatedName ? obj.marketAbbreviatedName : '' // 市場情報.市場略名
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.orderCount = obj.orderQuantity ? obj.orderQuantity : '' // 注文数量
    this.orderPriceKindList = obj.orderPriceKindList ? obj.orderPriceKindList : '' // 価格条件
    this.hiddenOrderPrice = obj.orderPriceKindList === '1' ? obj.hiddenOrderPrice : obj.orderPriceKindList === '3' ? obj.stopOrderExecutePrice2 : '' // 注文単価
    this.stopOrderPrice2 = obj.stopOrderPrice ? obj.stopOrderPrice : '' // 発火条件価格
    this.executeBasePrice = obj.executeBasePrice ? obj.executeBasePrice : '' // 成行基準価格
    this.periodRadio = obj.periodRadio ? obj.periodRadio : '' // 期間条件
    this.period = obj.periodRadio === '0' ? obj.localTradeDate : obj.period // 期間
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.kessaiHoho = obj.kessaiHoho ? obj.kessaiHoho : '' // 決済方法
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderTypeName = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.lastTradeDate = obj.marginDueDate ? obj.marginDueDate : '' // 返済期限
    this.repayPositionDesignateMethod = obj.repayPositionDesignateMethod ? obj.repayPositionDesignateMethod : '' // 返済建玉指定方法
    this.repaySelectOrder = obj.repaySelectOrder ? obj.repaySelectOrder : '' // 返済選択順序
    this.trade = obj.trade ? obj.trade : '' // 建区分
    this.unitPrice = obj.unitPrice ? obj.unitPrice : '' // 建単価
    this.closeOrderQuantity = obj.closeOrderQuantity ? obj.closeOrderQuantity : '' // 数量（建株数）
    this.positionDesignationAreaIndividualPositionInfoList = obj.positionDesignationAreaIndividualPositionInfoList ? obj.positionDesignationAreaIndividualPositionInfoList : [] // 個別建玉情報一覧
    this.stockTicket = obj.stockTicket ? obj.stockTicket : '' // 株価チケット
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認
    this.tradeLimitUrl = obj.tradeLimitUrl ? obj.tradeLimitUrl : '' // 本日の注意銘柄URL
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 取引通貨
    this.orderDate = obj.orderDate ? obj.orderDate : '' // 注文日時
    this.businessDaysAfterOrder = obj.businessDaysAfterOrder ? obj.businessDaysAfterOrder : '' // 国内約定日
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : [] // 取引注意情報（銘柄）メッセージ
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : [] // 注意情報アラート
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : [] // お知らせアラート
    this.stopOrderInstantMessage = obj.methodCheckMessage ? obj.methodCheckMessage : [] // 逆指値注文即時発火メッセージ
    this.nextBusinessDayMessage = obj.localTradeDateLimitMsg ? obj.localTradeDateLimitMsg : [] // 現地約定日超過メッセージ
    this.tradingCautionInformation = obj.tradingCautionInformation[0] ? obj.tradingCautionInformation[0] : '' // アラート内容確認.取引注意情報（銘柄）確認
    this.noteInfoCheckbox = obj.noteInfoCheckbox[0] ? obj.noteInfoCheckbox[0] : '' // アラート内容確認.注意情報アラート確認
    this.noteLimitCheck = obj.noteLimitCheck[0] ? obj.noteLimitCheck[0] : '' // アラート内容確認.お知らせアラート確認
    this.methodCheck = obj.methodCheck[0] ? obj.methodCheck[0] : '' // アラート内容確認.逆指値注文即時発火
    this.nextDayCheck = obj.nextDayCheck[0] ? obj.nextDayCheck[0] : '' // アラート内容確認.翌営業日向け注文
  }
}
