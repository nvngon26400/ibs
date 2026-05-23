export class IfaForeignMarginTradeNewOrderConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0303-01_2'
    this.screenTitle = '米株信用取引新規注文確認'

    this.tradeCd = '' // 取引種別

    this.orderNumber = '' // 注文番号（数字）
    this.orderSubNumber = '' // 注文Sub番号（数字）
    this.brandInformation = {
      productCode: '', // 商品コード
      countryCode: '', // 国コード
      securitiesCode: '', // 銘柄コード
      securitiesName: '', // 銘柄名
      securitiesShortName: '', // 銘柄略名
      securitiesEnglishName: '', // 銘柄英語名
      ric: '' // RIC
    } // 銘柄情報（全角半角）
    this.tradeCurrency = '' // 取引通貨
    this.marketInformation = {
      countryCode: '', // 国コード
      marketCode: '', // 市場コード
      marketName: '', // 市場名
      marketShortName: '', // 市場略名
      marketEnglishName: '', // 市場英語名
      timeZone: '', // 現地タイムゾーン
      timeZoneId: '', // タイムゾーンID
      timeZoneShortName: '' // タイムゾーン略名
    } // 市場情報
    this.tradeKbn = '' // 売買区分
    this.autobuyKbn = '' // 自動買付区分
    this.foreignQuantity = '' // 注文数量
    this.orderPriceKindList = '' // 価格条件
    this.hiddenOrderPrice = '' // 注文単価（数値(小数)）
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice = '' // 発火条件価格
    this.trailStopWidth = '' // トレールストップ幅
    this.executeBasePrice = '' // 成行基準価格
    this.periodRadio = '' // 期間条件
    this.period = '' // 期間
    this.depositType = '' // 預り区分（全角半角）
    this.settlementType = '' // 決済区分
    this.settlementCurrencyCode = '' // 決済通貨
    this.fxRate = '' // 為替レート（数値(小数)）
    this.averageTradePrice = '' // 平均約定単価（数値(小数)）
    this.unTradeQuantity = '' // 未約定数量（数値(整数)）
    this.tradeQuantity = '' // 約定数量（数値(整数)）
    this.approximatePositionAmount = '' // 約定金額（外貨）（数値(小数)）
    this.tradeAmountYen = '' // 約定金額（円貨）
    this.deliveryAmountForeign = '' // 受渡金額（外貨）（数値(小数)）
    this.deliveryAmountYen = '' // 受渡金額（円貨）（数値(小数)）
    this.contractDeliveryAmount = '' // 受渡金額（約定分
    this.domesticCommForeign = '' // 国内手数料（外貨）
    this.domesticCommJpAmount = '' // 国内手数料（円貨）
    this.domesticConsumptionTaxForeign = '' // 国内消費税（外貨）
    this.domesticConsumptionTaxJpAmount = '' // 国内消費税（円貨）
    this.localCommForeign = '' // 現地手数料等（外貨）
    this.localCommJpAmount = '' // 現地手数料等（円貨）
    this.localSettlementAmountForeign = '' // 現地精算金額（外貨）
    this.localSettlementJpAmoun = '' // 現地精算金額（円貨）
    this.approximateCapitalGainsTax = '' // 概算譲渡益税（数値(整数)）
    this.orderStatus = '' // 注文状況
    this.tradeStatus = '' // 約定状況
    this.stopOrderStatus = '' // 発火状況
    this.domesticTradeDate = '' // 国内約定
    this.domesticSettlementDate = '' // 国内受渡日
    this.localTradeDate = '' // 現地約定日
    this.foreignSettlementDate = '' // 現地受渡日
    this.orderDate = '' // 注文日時
    this.tradeDateTime = '' // 約定日時
    this.revocationDataTime = '' // 失効日時
    this.stockTradeType = '' // 株取引区分
    this.marginDueDate = '' // 信用期日
    this.settlement = '' // 決済損益
    this.depositDeficientAmount = '' // 保証金不足金額
    this.transferDepositAmount = '' // 振替預り金額
    this.transferValuableSecurityValuation = '' // 振替有価証券評価額
    this.quotePrice = '' // 見積価格
    this.applicableInterestRate = '' // 適用金利率
    this.applicableStockLendingFeeRate = '' // 適用貸株料率
    this.foreignMarginPositionPower = '' // 信用建余力(注文後)
    this.marginDepositRateOrderAfter = '' // 委託保証金率(注文後預託率)
    this.methodCheckMessage = '' // 逆指値注文即時発火メッセージ
    this.additionalCollateralRegulationBrandTradeMsg = '' // 増し担保規制銘柄取引メッセージ
    this.tradeLimit = '' // 適用金利率
    this.brand = {
      brandName: '', // 銘柄名
      brandCode: '' // 銘柄コード
    } // 銘柄情報
    this.currencyCode = '' // 通貨コード
    this.maketList = {
      marketCode: '', // 市場コード
      listedMarket: '', // 市場略名
      countryCode: '', // 国コード
      timeZoneAbbreviatedName: '' // タイムゾーン略名
    } // 市場リスト
    this.brandClass = '' // 銘柄種別
    this.priceBasicInfo = [{
      currentPrice: '', // 現在値
      currentDateTime: '', // 現在値日時
      tick: '', // ティック矢印(アップorダウン)
      diff: '', // 前日比
      ratio: '', // 前日比(%)
      start: '', // 始値
      startDate: '', // 始値日時
      high: '', // 高値
      highDate: '', // 高値日時
      low: '', // 安値
      lowDate: '', // 安値日時
      volume: '', // 出来高
      last: '', // 前日終値
      lastDate: '' // 前日終値日付
    }] // 価格基本情報
    this.kanyuKbn = '' // 勧誘区分
    this.receiveOrderType = '' // 受注方法
    this.importantMatterType = '' // 重要事項の説明
    this.engPubText = '' // 英文開示銘柄
    this.checkInsider = '' // 確認項目
    this.localTradeDateLimitMsg = '' // 現地約定日超過メッセージ
    this.noticeInfoAlert = '' // 注意情報アラート
    this.noticeAlert = '' // お知らせアラート
    this.tradeNoticeInformationBrand = '' // 取引注意情報（銘柄）メッセージ
    this.tradeLimitUrl = '' // 本日の注意銘柄URL
    this.closedDay = '' // 休場日URL
    this.yenClosed = '' // 円貨決済停止日URL
    this.usequityList = '' // 取扱銘柄一覧URL
    this.tradingAttention = '' // お取引注意事項URL
    this.AlertContentsConfirm = {
      tradingCautionInformation: false,
      noteInfoCheckbox: false,
      noteLimitCheck: false,
      additionalCollateralRegulationsConfirm: false,
      methodCheck: false,
      nextDayCheck: false
    }
    this.newMainSiteParamList = [] // 新メインサイト用パラメータ
    this.linkUrl = '' // リンクURL
    this.postRequest = {} // POSTリクエスト
  }
}
