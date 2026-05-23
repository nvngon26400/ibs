import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderConfirmA010RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** 国コード（全角半角）. */
    this.countryCode = obj.marketInfo.countryCode ? obj.marketInfo.countryCode : ''

    /** 市場コード（全角半角）. */
    this.marketCode = obj.marketInfo.marketCode ? obj.marketInfo.marketCode : ''

    /** 市場略名. */
    this.marketAbbreviation = obj.marketInfo.marketAbbreviation ? obj.marketInfo.marketAbbreviation : ''

    /** 銘柄コード（半角英数字）. */
    this.brandCode = obj.brandInfo.brandCode ? obj.brandInfo.brandCode : ''

    /** 銘柄名（全角半角）. */
    this.brandName = obj.brandInfo.brandName ? obj.brandInfo.brandName : ''

    /** 取引種別（全角半角）. */
    this.tradeCd = obj.buySellTypeName ? obj.buySellTypeName : ''

    /** 注文数量. */
    this.orderQuantity = obj.orderQuantity ? obj.orderQuantity : ''

    /** 価格条件. */
    this.orderPriceKindText = obj.priceCondition ? obj.priceCondition : ''

    /** 注文単価（数値(小数)）. */
    this.hiddenOrderPrice = obj.hiddenOrderPrice ? obj.hiddenOrderPrice : ''

    /** 発火条件価格. */
    this.stopOrderPrice = obj.triggerPrice ? obj.triggerPrice : ''

    /** 成行基準価格（全角半角）. */
    this.executeBasePrice = obj.executeBasePrice ? obj.executeBasePrice : ''

    /** 期間条件. */
    this.periodDate = obj.periodRadio ? obj.periodRadio : ''

    /** 期間. */
    this.period = obj.period ? obj.period : ''

    /** 預り区分（全角半角）. */
    this.depositTypeName = obj.depositType ? obj.depositType : ''

    /** 決済方法（半角英数字）. */
    this.currencyTypeName = obj.settlementType ? obj.settlementType : ''

    /** 勧誘区分（全角半角）. */
    this.solicitTypeName = obj.kanyuKbn ? obj.kanyuKbn : ''

    /** 受注方法. */
    this.receiveOrderTypeName = obj.receiveOrderType ? obj.receiveOrderType : ''

    /** 重要事項の説明. */
    this.importantMatterTypeText = obj.importantMatterType ? obj.importantMatterType : ''

    /** 乗換え勧誘(ETF). */
    this.solicitationEtfText = obj.solicitationEtf ? obj.solicitationEtf : ''

    /** 英文開示銘柄. */
    this.engPubText = obj.engPubCheckbox ? obj.engPubCheckbox : ''

    /** 確認項目.インサイダー確認（半角英数字）. */
    this.checkInsider = obj.confirmItemList[0].checkInsider ? obj.confirmItemList[0].checkInsider : ''

    /** アラート内容確認.取引注意情報（銘柄）確認. */
    this.tradingCautionInformation = obj.AlertContentsConfirm.tradingCautionInformation ? obj.AlertContentsConfirm.tradingCautionInformation : ''

    /** アラート内容確認.コンプラランクチェック確認. */
    this.invitationCheck = obj.AlertContentsConfirm.invitationCheck ? '1' : ''

    /** アラート内容確認.注意情報アラート確認. */
    this.noteInfoCheckbox = obj.AlertContentsConfirm.noteInfoCheckbox ? obj.AlertContentsConfirm.noteInfoCheckbox : ''

    /** アラート内容確認.お知らせアラート確認. */
    this.noteLimitCheck = obj.AlertContentsConfirm.noteLimitCheck ? obj.AlertContentsConfirm.noteLimitCheck : ''

    /** アラート内容確認.約定代金の上限超過. */
    this.priceLimitCheck = obj.AlertContentsConfirm.priceLimitCheck ? obj.AlertContentsConfirm.priceLimitCheck : ''

    /** アラート内容確認.逆指値注文即時発火. */
    this.methodCheck = obj.AlertContentsConfirm.methodCheck ? obj.AlertContentsConfirm.methodCheck : ''

    /** アラート内容確認.翌営業日向け注文. */
    this.nextDayCheck = obj.AlertContentsConfirm.nextDayCheck ? obj.AlertContentsConfirm.nextDayCheck : ''

    /** アラート内容確認.海外ETFアラート確認. */
    this.overseasEtfCheck = obj.AlertContentsConfirm.overseasEtfCheck ? obj.AlertContentsConfirm.overseasEtfCheck : ''

    /** 注意情報アラート（全角半角）. */
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : ''

    /** お知らせアラート（全角半角）. */
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : ''

    /** コンプラチェック.メッセージ. */
    this.complianceCheckMessage = obj.complianceCheckList ? (Array.isArray(obj.complianceCheckList) ? (Boolean(obj.complianceCheckList.length) && 'complianceCheckMsg' in obj.complianceCheckList[0] ? obj.complianceCheckList[0].complianceCheckMsg : '') : obj.complianceCheckList) : ''

    /** コンプラチェック.メッセージID. */
    this.complianceCheckMessageId = obj.complianceCheckList ? (Array.isArray(obj.complianceCheckList) ? (Boolean(obj.complianceCheckList.length) && 'complianceCheckMsg' in obj.complianceCheckList[0] ? (obj.complianceCheckList[0].complianceCheckMsg ? obj.complianceCheckList[0].messageId : '') : '') : (obj.complianceCheckList ? obj.complianceCheckList[0].messageId : '')) : ''

    /** コンプラチェック.チェックボックス文言. */
    this.complianceCheckWording = obj.complianceCheckList ? (Array.isArray(obj.complianceCheckList) ? (Boolean(obj.complianceCheckList.length) && 'complianceCheckMsg' in obj.complianceCheckList[0] ? (obj.complianceCheckList[0].complianceCheckMsg ? obj.complianceCheckList[0].chkBoxLabel : '') : '') : (obj.complianceCheckList ? obj.complianceCheckList[0].chkBoxLabel : '')) : ''

    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : ''

    /** 約定代金の条件超過確認（全角半角）. */
    this.contractAmountOverMessage = obj.priceLimitCheckText ? obj.priceLimitCheckText : ''

    /** 逆指値注文即時発火確認. */
    this.stopOrderInstantMessage = obj.methodCheckText ? obj.methodCheckText : ''

    /** 翌営業日向け注文確認. */
    this.nextBusinessDayMessage = obj.localTradeDateLimitMsg ? obj.localTradeDateLimitMsg : ''

    /** 海外ETFアラートメッセージ（全角半角）. */
    this.overseasEtfAlertMessage = obj.overseasEtfAlert ? obj.overseasEtfAlert : ''

    /** 本日の注意銘柄URL. */
    this.todayTradeLimitUrl = obj.todayTradeLimitUrl ? obj.todayTradeLimitUrl : ''

    /** 注文番号（数字）. */
    this.orderNumber = obj.orderNumber ? obj.orderNumber : ''

    /** 注文Sub番号（数字）. */
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : ''

    /** 売買区分（全角半角）. */
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : ''

    /** 取引通貨. */
    this.limitPriceText = obj.limitPriceText ? obj.limitPriceText : ''

    /** 注文日時. */
    this.orderDate = obj.orderDate ? obj.orderDate : ''

    /** 国内約定日. */
    this.domesticTradeDate = obj.domesticTradeDate ? obj.domesticTradeDate : ''

    /** 現地約定日. */
    this.localContractDate = obj.localContractDate ? obj.localContractDate : ''

    /** 概算受渡金額（外貨）. */
    this.approximateDeliveryForeignAmount = obj.foreignDeliveryAmount ? obj.foreignDeliveryAmount : ''
  }
}
