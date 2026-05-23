export class IfaForeignSpotTradeOrderConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0301-01_2'
    this.screenTitle = '外国現物取引注文確認'
    this.orderNumber = '' // 注文番号（数字）.
    this.orderSubNumber = '' // 注文Sub番号（数字）.
    this.brandInformation = { // 銘柄情報（全角半角）.
      productCode: '', // 商品コード
      countryCode: '', // 国コード
      securitiesCode: '', // 銘柄コード
      securitiesName: '', // 銘柄名
      securitiesShortName: '', // 銘柄略名
      securitiesEnglishName: '', // 銘柄英語名
      ric: '' // RIC
    }
    this.limitPriceText = '' // 取引通貨.
    this.marketInformation = { // 市場情報（全角半角）.
      countryCode: '', // 国コード
      marketCode: '', // 市場コード
      marketName: '', // 市場名
      marketShortName: '', // 市場略名
      marketEnglishName: '', // 市場英語名
      timeZone: '', // 現地タイムゾーン
      timeZoneId: '', // タイムゾーンID
      timeZoneShortName: '' // タイムゾーン略名
    }
    this.tradeKbn = '' // 売買区分（全角半角）.
    this.automaticBuyingCategory = '' // 自動買付区分.
    this.orderQuantity = '' // 注文数量.
    this.priceCondition = '' // 価格条件.
    this.hiddenOrderPrice = '' // 注文単価（数値(小数)）.
    this.triggerPrice = '' // 発火条件価格.
    this.trailStopWidth = '' // トレールストップ幅.
    this.executeBasePrice = '' // 成行基準価格（全角半角）.
    this.periodRadio = '' // 期間条件.
    this.period = '' // 期間.
    this.depositType = '' // 預り区分（全角半角）.
    this.settlementType = '' // 決済区分（全角半角）.
    this.settlementCurrency = '' // 決済通貨.
    this.fxRate = '' // 為替レート（数値(小数)）.
    this.averageTradePrice = '' // 平均約定単価（数値(小数)）.
    this.unTradeQuantity = '' // 未約定数量（数値(整数)）.
    this.tradeQuantity = '' // 約定数量（数値(整数)）.
    this.contractAmountForeign = '' // 約定金額（外貨）（数値(小数)）.
    this.contractAmountYen = '' // 約定金額（円貨）.
    this.foreignDeliveryAmount = '' // 受渡金額（外貨）（数値(小数)）.
    this.yenDeliveryAmount = '' // 受渡金額（円貨）（数値(小数)）.
    this.deliveryAmount = '' // 受渡金額（約定分）.
    this.domesticCommForeign = '' // 国内手数料（外貨）.
    this.domesticCommJpAmount = '' // 国内手数料（円貨）.
    this.domesticConsumptionTaxForeign = '' // 国内消費税（外貨）.
    this.domesticConsumptionTaxYen = '' // 国内消費税（円貨）.
    this.foreignFee = '' // 現地手数料等（外貨）.
    this.yenFee = '' // 現地手数料等（円貨）.
    this.localSettlementAmountForeign = '' // 現地精算金額（外貨）.
    this.localSettlementAmountYen = '' // 現地精算金額（円貨）.
    this.nisaFrameRestrictionAmount = '' // NISA枠拘束金額.
    this.approximateCapitalGainsTax = '' // 概算譲渡益税（数値(整数)）.
    this.orderStatus = '' // 注文状況（全角半角）.
    this.tradeStatus = '' // 約定状況.
    this.orderStopSituation = '' // 発火状況.
    this.domesticTradeDate = '' // 国内約定日.
    this.domesticSettlementDate = '' // 国内受渡日.
    this.localContractDate = '' // 現地約定日.
    this.localDeliveryDate = '' // 現地受渡日.
    this.orderDate = '' // 注文日時.
    this.tradeDateTime = '' // 約定日時.
    this.expirationDateandTime = '' // 失効日時.
    this.quotePrice = '' // 見積価格（数値(小数)）.
    this.deliveryAmountExecuted = '' // 買付余力（注文後）.
    this.nisaInvestableQuote = '' // NISA投資可能枠（注文後）.
    this.acPositionAfter = '' // 売却可能数（注文後）.
    this.priceLimitCheckText = '' // 約定代金ソフトリミット上限超過メッセージ.
    this.methodCheckText = '' // 逆指値注文即時発火メッセージ.
    this.stockPriceInformation = { // 株価情報.
      last: '', // 現在値
      lastDatetime: '', // 現在値日時
      tick: '', // ティック矢印(アップorダウン)
      change: '', // 前日比
      changePercent: '', // 前日比(%)
      open: '', // 始値
      openDatetime: '', // 始値日時
      high: '', // 高値
      highDatetime: '', // 高値日時
      low: '', // 安値
      lowDatetime: '', // 安値日時
      prevClose: '', // 前日終値
      prevCloseDate: '', // 前日終値日付
      lastToPrevClose: '', // 現在値or前日終値
      volume: '', // 出来高
      vwap: '' // 出来高加重平均価格
    }
    this.tradeLimit = '' // 注意銘柄.
    this.marketInfo = { // 市場情報
      marketAbbreviation: '', // 市場略名.
      countryCode: '', // 国コード（全角半角）.
      marketCode: '', // 市場コード（全角半角）.
      timeZoneAbbreviation: '' // タイムゾーン略名.
    }
    this.currencyCode = '' // 通貨コード（全角半角）.
    this.brandClass = '' // 銘柄種別（全角半角）.
    this.brandInfo = { // 銘柄情報
      brandName: '', // 銘柄名（全角半角）.
      brandCode: '' // 銘柄コード（半角英数字）.
    }
    this.priceBasicInfo = { // 価格基本情報
      currentPrice: '', // 現在値（数値(小数)）.
      currentPriceDate: '', // 現在値日時.
      tick: '', // ティック矢印(アップorダウン).
      diff: '', // 前日比（数値(小数)）.
      ratio: '', // 前日比(%).
      start: '', // 始値（数値(小数)）.
      startPriceDate: '', // 始値日時.
      high: '', // 高値（数値(小数)）.
      highPriceDate: '', // 高値日時.
      low: '', // 安値（数値(小数)）.
      lowPriceDate: '', // 安値日時.
      volume: '', // 出来高（数値(整数)）.
      last: '', // 前日終値（数値(小数)）.
      lastDate: '' // 前日終値日付.
    }
    this.buySellTypeName = '' // 取引種別
    this.kanyuKbn = '' // 勧誘区分（全角半角）.
    this.receiveOrderType = '' // 受注方法.
    this.importantMatterType = '' // 重要事項の説明.
    this.solicitationEtf = '' // 乗換え勧誘(ETF).
    this.engPubCheckbox = '' // 英文開示銘柄.
    this.confirmItemList = [{ // 確認項目リスト.
      checkInsider: '' // インサイダー確認.
    }]
    this.complianceCheckResult = '' // コンプラチェック_判定結果（全角半角）.
    this.complianceCheckList = [{ // コンプラチェックリスト.
      complianceCheckMsg: '', // メッセージ.
      chkBoxLabel: '' // チェックボックス文言（半角英数字）.
    }]
    this.localTradeDateLimitMsg = '' // 現地約定日超過メッセージ.
    this.noticeInfoAlert = '' // 注意情報アラート（全角半角）.
    this.noticeAlert = '' // お知らせアラート（全角半角）.
    this.tradeNoticeInfoBrandMsg = '' // 取引注意情報（銘柄）メッセージ（全角半角）.
    this.overseasEtfAlert = '' // 海外ETFアラート.
    this.todayTradeLimitUrl = '' // 本日の注意銘柄URL.
    this.closedDay = '' // 休場日URL.
    this.yenClosedDateUrl = '' // 円貨決済停止日URL.
    this.listofHandledStocksUrl = '' // 取扱銘柄一覧URL.
    this.noticeofTransactionPrecautionsUrl = '' // お取引注意事項URL.
    // アラート内容確認
    this.AlertContentsConfirm = {
      // アラート内容確認.取引注意情報（銘柄）確認
      tradingCautionInformation: '0',
      // アラート内容確認.コンプラランクチェック確認
      invitationCheck: '0',
      // アラート内容確認.注意情報アラート確認
      noteInfoCheckbox: '0',
      // アラート内容確認.お知らせアラート確認
      noteLimitCheck: '0',
      // アラート内容確認.約定代金の上限超過
      priceLimitCheck: '0',
      // アラート内容確認.逆指値注文即時発火
      methodCheck: '0',
      // アラート内容確認.翌営業日向け注文
      nextDayCheck: '0',
      // アラート内容確認.海外ETFアラート確認
      overseasEtfCheck: '0'
    }
    this.newMainSiteParamList = [] // 新メインサイト用パラメータ
    this.linkUrl = '' // リンクURL
    this.postRequest = {} // POSTリクエスト
  }
}
