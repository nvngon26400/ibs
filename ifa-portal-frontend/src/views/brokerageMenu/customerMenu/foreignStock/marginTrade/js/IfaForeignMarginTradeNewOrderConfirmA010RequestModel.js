import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderConfirmA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marketCode = obj.marketInformation.marketCode ? obj.marketInformation.marketCode : '' // 市場コード
    this.listedMarket = obj.marketInformation.marketShortName ? obj.marketInformation.marketShortName : '' // 市場略名
    this.brandCode = obj.brandInformation.securitiesCode ? obj.brandInformation.securitiesCode : '' // 銘柄コード
    this.brandName = obj.brandInformation.securitiesName ? obj.brandInformation.securitiesName : '' // 銘柄名
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.foreignQuantity = obj.foreignQuantity ? obj.foreignQuantity : '' // 注文数量
    this.orderPriceKindList = obj.orderPriceKindList ? obj.orderPriceKindList : '' // 価格条件
    this.hiddenOrderPrice = obj.hiddenOrderPrice ? obj.hiddenOrderPrice : '' // 注文単価
    this.stopOrderPrice2 = obj.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice ? obj.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice : '' // 発火条件価格
    this.executeBasePrice = obj.executeBasePrice ? obj.executeBasePrice : '' // 成行基準価格
    this.periodDate = obj.periodRadio ? obj.periodRadio : '' // 期間条件
    this.period = obj.period ? obj.period : '' // 期間
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.settlementType = obj.settlementType ? obj.settlementType : '' // 決済区分
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderTypeName = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.importantMatterType = obj.importantMatterType ? obj.importantMatterType : '' // 重要事項の説明
    this.engPubText = obj.engPubText ? obj.engPubText : '' // 英文開示銘柄
    this.checkInsider = obj.checkInsider ? obj.checkInsider : '' // 確認項目.インサイダー確認
    this.tradingCautionInformation = obj.AlertContentsConfirm.tradingCautionInformation ? obj.AlertContentsConfirm.tradingCautionInformation : '' // アラート内容確認.取引注意情報（銘柄）確認
    this.noteInfoCheckbox = obj.AlertContentsConfirm.noteInfoCheckbox ? obj.AlertContentsConfirm.noteInfoCheckbox : '' // アラート内容確認.注意情報アラート確認
    this.noteLimitCheck = obj.AlertContentsConfirm.noteLimitCheck ? obj.AlertContentsConfirm.noteLimitCheck : '' // アラート内容確認.お知らせアラート確認
    this.additionalCollateralRegulationsConfirm = obj.AlertContentsConfirm.additionalCollateralRegulationsConfirm ? obj.AlertContentsConfirm.additionalCollateralRegulationsConfirm : '' // アラート内容確認.増し担保規制確認
    this.methodCheck = obj.AlertContentsConfirm.methodCheck ? obj.AlertContentsConfirm.methodCheck : '' // アラート内容確認.逆指値注文即時発火
    this.nextDayCheck = obj.AlertContentsConfirm.nextDayCheck ? obj.AlertContentsConfirm.nextDayCheck : '' // アラート内容確認.翌営業日向け注文
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : '' // 注意情報アラート
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : '' // お知らせアラート
    this.additionalCollateralRegulationConfirmMsg = obj.additionalCollateralRegulationBrandTradeMsg ? obj.additionalCollateralRegulationBrandTradeMsg : '' // 増し担保規制銘柄取引メッセージ
    this.stopOrderInstantMessage = obj.methodCheckMessage ? obj.methodCheckMessage : '' // 逆指値注文即時発火メッセージ
    this.nextBusinessDayMessage = obj.localTradeDateLimitMsg ? obj.localTradeDateLimitMsg : '' // 現地約定日超過メッセージ
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrand ? obj.tradeNoticeInfoBrand : '' // 取引注意情報（銘柄）メッセージ
    this.marginDueDate = obj.marginDueDate ? obj.marginDueDate : '' // 信用期日
    this.tradeLimit = obj.tradeLimitUrl ? obj.tradeLimitUrl : '' // 本日の注意銘柄URL
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文Sub番号
    this.tradeCurrency = obj.tradeCurrency ? obj.tradeCurrency : '' // 取引通貨
    this.orderDate = obj.orderDate ? obj.orderDate : '' // 注文日時
    this.domesticTradeDate = obj.domesticTradeDate ? obj.domesticTradeDate : '' // 国内約定日
    this.localTradeDate = obj.localTradeDate ? obj.localTradeDate : '' // 現地約定日
  }
}
