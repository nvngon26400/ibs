export class IfaForeignSpotTradeOrderCompleteFormModel {
  constructor() {
    this.screenId = 'SUB0202_0301-01_3'
    this.screenTitle = '外国現物取引注文完了'
    this.orderList = [{ // 注文情報.
      orderNumber: '', // 注文番号（数字）.
      orderSubNumber: '', // 注文Sub番号（数字）.
      limitPriceText: '', // 取引通貨.
      tradeKbn: '', // 売買区分（全角半角）.
      automaticBuyingCategory: '', // 自動買付区分.
      orderQuantity: '', // 注文数量.
      priceCondition: '', // 価格条件.
      hiddenOrderPrice: '', // 注文単価（数値(小数)）.
      triggerPrice: '', // 発火条件価格.
      trailStopWidth: '', // トレールストップ幅.
      executeBasePrice: '', // 成行基準価格（全角半角）.
      periodRadio: '', // 期間条件.
      period: '', // 期間.
      depositType: '', // 預り区分（全角半角）.
      settlementType: '', // 決済方法（半角英数字）.
      settlementCurrency: '', // 決済通貨.
      fxRate: '', // 為替レート（数値(小数)）.
      averageTradePrice: '', // 平均約定単価（数値(小数)）.
      unTradeQuantity: '', // 未約定数量（数値(整数)）.
      tradeQuantity: '', // 約定数量（数値(整数)）.
      contractAmountForeign: '', // 約定金額（外貨）（数値(小数)）.
      contractAmountYen: '', // 約定金額（円貨）.
      foreignDeliveryAmount: '', // 受渡金額（外貨）（数値(小数)）.
      yenDeliveryAmount: '', // 受渡金額（円貨）（数値(小数)）.
      deliveryAmount: '', // 受渡金額（約定分）.
      domesticforeignFee: '', // 国内手数料（外貨）.
      domesticyenFee: '', // 国内手数料（円貨）.
      domesticConsumptionTaxForeign: '', // 国内消費税（外貨）.
      domesticConsumptionTaxYen: '', // 国内消費税（円貨）.
      foreignFee: '', // 現地手数料等（外貨）.
      yenFee: '', // 現地手数料等（円貨）.
      localSettlementAmountForeign: '', // 現地精算金額（外貨）.
      localSettlementAmountYen: '', // 現地精算金額（円貨）.
      nisaFrameRestrictionAmount: '', // NISA枠拘束金額.
      approximateCapitalGainsTax: '', // 概算譲渡益税（数値(整数)）.
      orderStatus: '', // 注文状況（全角半角）.
      tradeStatus: '', // 約定状況.
      orderStopSituation: '', // 発火状況.
      domesticTradeDate: '', // 国内約定日.
      domesticSettlementDate: '', // 国内受渡日.
      localContractDate: '', // 現地約定日.
      localDeliveryDate: '', // 現地受渡日.
      orderDate: '', // 注文日時.
      tradeDateTime: '', // 約定日時.
      expirationDateandTime: '' // 失効日時.
    }]
    this.brandClass = '' // 銘柄種別（全角半角）.
    this.tradeLimit = '' // 注意銘柄.
    this.kanyuKbn = '' // 勧誘区分（全角半角）.
    this.receiveOrderType = '' // 受注方法.
    this.importantMatterType = '' // 重要事項の説明.
    this.solicitationEtf = '' // 乗換え勧誘(ETF).
    this.engPubCheckbox = '' // 英文開示銘柄.
    this.checkList = [{ // 確認項目.
      checkInsider: ''
    }]
    this.alertCheckList = [{ // アラート内容確認.
      tradingCautionInformation: '', // 取引注意情報（銘柄）確認.
      invitationCheck: '', // コンプラランクチェック確認.
      noteInfoCheckbox: '', // 注意情報アラート確認.
      noteLimitCheck: '', // お知らせアラート確認.
      priceLimitCheck: '', // 約定代金の上限超過.
      methodCheck: '', // 逆指値注文即時発火.
      nextDayCheck: '', // 翌営業日向け注文.
      overseasEtfAlertConfirm: '' // 海外ETFアラート確認.
    }]
    this.noticeInfoAlert = '' // 注意情報アラート（全角半角）.
    this.noticeAlert = '' // お知らせアラート（全角半角）.
    this.complianceCheckList = [{ // コンプラランクチェック.
      complianceCheckMsg: '', // メッセージ.
      chkBoxLabel: '' // チェックボックス文言（半角英数字）.
    }]
    this.tradeNoticeInfoBrandMsg = '' // 取引注意情報（銘柄）メッセージ（全角半角）.
    this.contractAmountOverMessage = '' // 約定代金の条件超過確認（全角半角）.
    this.stopOrderInstantMessage = '' // 逆指値注文即時発火確認.
    this.nextBusinessDayMessage = '' // 翌営業日向け注文確認.
    this.overseasEtfAlertMessage = '' // 海外ETFアラートメッセージ（全角半角）.
    this.brandCode = '' // 銘柄コード（半角英数字）.
    this.brandName = '' // 銘柄名（全角半角）.
    this.countryCode = '' // 国コード（全角半角）.
    this.marketAbbreviation = '' // 市場略名.
    this.todayTradeLimitUrl = '' // 本日の注意銘柄URL.
    this.tradeCd = '' // 取引種別
  }
}
