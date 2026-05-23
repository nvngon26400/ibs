export class IfaForeignSpotTradeOrderInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0301-01_1',
      name: '外国現物取引注文入力'
    }
    this.warningMessage = '' // メッセージ（注文可能数量0）
    this.countryCodeText = [] // 国籍 【初期値】米国
    this.brandSearchCountryCodeList = [] // 国籍
    this.brandCode = '' // 銘柄コード
    this.brandClass = '' // 銘柄種別
    this.brandFixationCountryCodeText = '' // 国籍
    this.brandFixationBrandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名称
    this.marketAbbreviation = '' // 市場略名
    this.timeZoneAbbreviation = '' /** タイムゾーン略名. */
    this.stockListingStatus = '' /** 銘柄上場ステータス. */
    this.minimumTradingQuantity = '' // 取引下限数量
    this.maximumTradingQuantity = '' // 取引上限数量
    this.tradingUnit = '' // 取引単位
    this.stockType = '' // 銘柄種別
    this.tradeLimit = '' // 注意銘柄
    // 価格基本情報
    this.priceBasicInfo = {
      currentPrice: '', // 現在値 【初期値】"-"
      currentPriceDate: '', /** 現在値日時. */
      tick: '', // 現在値（上がり／下がり） 【初期値】空欄
      diff: '', // 前日比 【初期値】"-"
      ratio: '', // 前日比率（％） 【初期値】"(-%)"
      start: '', // 始値 【初期値】"-"
      startPriceDate: '', /** 始値日時. */
      high: '', // 高値 【初期値】"-"
      highPriceDate: '', /** 高値日時. */
      low: '', // 安値 【初期値】"-"
      lowPriceDate: '', /** 安値日時. */
      volume: '', // /** 出来高（数値(整数)）. */
      last: '', // 前日終値 【初期値】"-"
      lastDate: '' // 前日終値日付 【初期値】"(--/--/--)"
    }
    // 呼値リスト
    this.callValue = [{
      basePriceFrom: '',
      basePriceTo: '',
      tickSize: '',
      basePriceType: ''
    }]
    this.russianCallValue = '' /** ロシア呼値. */

    this.tradeLimitTitle = '' // 注意銘柄見出し
    this.buyingPowerTitile = '' // 総合口座.買付余力.外貨［ラベル］ 【初期値】空欄
    /** 円貨余力リスト. */
    this.buyingPowerDomesticList = [{
      yenBuyingPowerGeneralAccount: '', /** 買付余力（総合）. */
      nisaBuyLimitList: [{
        availableBuyingLimit: '', /** 買付可能枠（総合）. */
        annualAvailableBuyingLimit: '', /** 買付可能枠年（総合）. */
        accountClassification: '' /** 口座分類（総合）. */
      }],
      yenBuyingPowerJrNisa: '', /** 買付余力（JrNISA）. */
      accountClassificationJrNisa: ''/** 口座分類（JrNISA）. */
    }]
    /** 外貨余力リスト. */
    this.buyingPowerForeignList = [{
      foreignBuyingPower: '', /** 買付余力（総合）. */
      currencyCode: '', /** 通貨コード（総合）. */
      foreignBuyingPowerJrNisa: '', /** 買付余力（JrNISA）. */
      currencyCodeJrNisa: '' /** 通貨コード（JrNISA）. */
    }]
    this.maxOrderableQuantity = '' // 注文可能数量
    this.acPosition = '' // 売却可能数
    this.foreignQuantity = '' // 数量 【初期値】空欄
    this.orderPriceKindList = [] // 価格条件 【初期値】指値
    this.orderPriceKind = '' // 価格条件
    this.limitOrderPrice = '' // 注文単価（指値） 【初期値】空欄
    this.limitPriceText = '' // 取引通貨
    this.stopOrderPrice = '' // 発火条件価格（逆指値）
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice1 = '' // 発火条件価格（逆指値） 【初期値】空欄
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderPriceText22 = '' // 発火条件価格（逆指値）
    this.stopOrderPriceKindList = [] // 価格条件（逆指値） 【初期値】指値 TODO: サーバ項目にない
    this.stopOrderExecutePrice = '' // 注文単価（逆指値）
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderExecutePrice1 = '' // 注文単価（逆指値） 【初期値】空欄
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderExecutePriceText22 = '' // 注文単価（逆指値）
    this.periodRadio = '' // 期間条件 【初期値】未選択
    this.period = '' /** 期間 */
    this.selectAblePeriodTermsList = [] // 選択可能期間条件リスト
    this.validPeriodList = [] // 有効期間リスト
    this.orderInputAreaPriceTermsreversePriceLimitForeignDepositTypeRadio1 = '' // 預り区分 【初期値】空欄  TODO：いる？
    this.orderInputAreaPriceTermsreversePriceLimitDepositTypeName2 = '' // 預り区分 【初期値】空欄  TODO：いる？
    this.buySellTypeName = '0' // 取引種別
    this.kanyuKbn = '' // 勧誘区分 【初期値】空欄
    this.receiveOrderType = '' // 受注方法 【初期値】空欄
    this.importantMatterType = '' // 重要事項の説明 【初期値】説明不要を確認（属性登録済）
    this.solicitationEtf = '' // 乗換え勧誘(ETF) 【初期値】未選択
    this.engPubCheckbox = '1' // 英文開示銘柄 【初期値】未選択 1:未説明, 0:説明済
    this.engPubCheck = '' // 英文開示銘柄判定
    this.engPubCheckUrl = '' // よくある質問一覧の英文開示銘柄 URL  TODO: どこから取得する？
    this.insiderConfirmCheckBox = '' // 確認項目 【初期値】未選択　TODO: いる？
    this.checkInsider = '0' // 確認項目.インサイダー確認 0:未確認, 1:確認済み
    this.countryCode = 'US' // 国コード
    this.marketCode = '' // 市場コード
    this.marketHash = '' // マーケット価格ハッシュ
    this.marketRics = '' // マーケット価格rics
    this.depositType = '' // 預り区分(サーバリクエスト項目)
    this.depositTypeRadio = '' // 預り区分（ラジオボタンの表示に用いる項目）
    this.depositTypeList = [] // 選択可能預り区分リスト
    this.currencyTypeName = '' // 決済方法(サーバリクエスト項目)
    this.stockPriceKindList = [] // 選択可能決済方法リスト
    this.sellableProtectionCategory = '' // 売却可能保護区分
    this.yenClosed = '' // 円貨決済停止日URL
    this.tradingAttention = '' // お取引注意事項URL
    this.tradeLimitUrl = '' // 本日の注意銘柄URL
    this.closedDay = '' // 休場日URL
    this.usequityList = '' // 取扱銘柄一覧URL
    this.sellableCustodyCategory = '' // 売却可能預り区分
    this.priceRangeNoLimit = '' /** 値幅制限なし. */
    this.priceRangeLimitMin = '' /** 値幅下限. */
    this.priceRangeLimitMax = '' /** 値幅上限. */
    this.newMainSiteParamList = [] // 新メインサイト用パラメータ
    this.linkUrl = '' // リンクURL
    this.postRequest = {} // POSTリクエスト
  }
}
