export class IfaForeignMarginTradeNewOrderInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0303-01_1',
      name: '米株信用取引新規注文入力'
    }
    this.quantityAvailableForSaleMsg = '注文可能な数量が不足しているため、注文できません。' // メッセージ（売建可能数量0）
    this.tradeCdList = [] // 取引種別リスト
    this.tradeCd = '' // 取引種別 【初期値】未選択 (買:2, 売:3)
    this.countryCode = 'US'
    this.tickerBrandCode = '' // ティッカー／銘柄コード 【初期値】空欄
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名称
    this.listedMarket = '' // 上場市場
    this.timeZoneAbbreviatedName = '' // タイムゾーン略名
    this.tradeLimitUrl = '' // 本日の注意銘柄URL
    this.closedDay = '' // 休場日URL
    this.yenClosed = '' // 円貨決済停止日URL
    this.usequityList = '' // 取扱銘柄一覧URL
    this.tradingAttention = '' // お取引注意事項URL
    this.tradeCurrency = '' // 取引通貨
    this.foreignMarginPositionPower = '' // 信用建余力
    this.referenceMarginPower = '' // 参考信用建余力
    this.collateralTransferMarginDepositRateAfter = '' // 委託保証金率（預託率）
    this.quantityAvailableForSale = '' // 売建可能数量
    this.tradeUnit = '' // 取引単位
    this.tradeLimit = null

    // 価格基本情報
    this.priceBasicInfo = [{
      currentPrice: '', // 現在値 【初期値】"-"
      currentDateTime: '--/--/-- --:--', // 現在値日時 【初期値】"(--/--/-- --:--)"
      tick: '', // 現在値（上がり／下がり） 【初期値】空欄
      diff: '-', // 前日比 【初期値】"-"
      ratio: '-', // 前日比率（％） 【初期値】"(-%)"
      start: '-', // 始値 【初期値】"-"
      high: '-', // 高値 【初期値】"-"
      low: '-', // 安値 【初期値】"-"
      last: '-', // 前日終値 【初期値】"-"
      lastDate: '--/--/--', // 前日終値日付 【初期値】"(--/--/--)"
      volume: '-' // 出来高 【初期値】"-"
    }]

    // 入力エリア
    this.foreignQuantity = '' // エラー: 「数量」の項目IDが、項目辞書に存在しません。
    this.orderPriceKindList = '' // 価格条件(リクエスト用)
    this.orderPriceKind = '' // 価格条件
    this.hiddenOrderPrice = '' // 注文単価（指値） 【初期値】空欄
    this.orderInputAreaPriceTermsreversePriceLimitStopOrderPrice = '' // 発火条件価格（逆指値） 【初期値】空欄
    this.orderPriceKindListReversePriceLimit = '' // 価格条件（逆指値） 【初期値】指値
    this.hiddenOrderPriceReversePriceLimit = '' // 注文単価（逆指値） 【初期値】空欄
    this.approximatePositionAmount = '' // 概算建代金
    this.periodRadio = '' // 期間条件 【初期値】未選択
    this.periodType = null
    this.periodDate = [] // 期間リスト
    this.period = '' // 期間（日付指定）
    this.depositType = '' // 預り区分 【初期値】空白
    this.kessaiHoho = '' // 選択可能決済方法
    this.marginDueDate = '' // 期限 【初期値】空白
    this.solicitTypeList = [] // 勧誘区分リスト
    this.kanyuKbn = '' // 勧誘区分 【初期値】空白
    this.receiveOrderTypeList = [] // 受注方法リスト
    this.receiveOrderType = '' // 受注方法 【初期値】空白
    this.importantMatterTypeList = [] // 重要事項の説明リスト
    this.importantMatterType = '1' // 重要事項の説明 【初期値】説明不要を確認（属性登録済）
    this.engPubText = '1' // 英文開示銘柄リスト 【初期値】未選択
    this.checkInsider = '0' // 確認項目 【初期値】未選択

    // hidden項目
    this.countryCode = 'US' // 国コード
    this.marketCode = '' // 市場コード
    this.brandListedStatus = '' // 銘柄上場ステータス
    this.tradeLowerLimitQuantity = '' // 取引下限数量
    this.tradeUpperLimitQuantity = '' // 取引上限数量
    this.engJudge = '' // 英文開示銘柄判定
    this.newMainSiteParamList = [] // 新メインサイト用パラメータ
    this.linkUrl = '' // リンクURL
    this.postRequest = {} // POSTリクエスト
  }
}
