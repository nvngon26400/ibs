export class IfaDomesticPositionListFormModel {
  constructor() {
    this.butenCode = ''
    this.accountNumber = ''
    this.screenId = 'SUB0202_010202-01'
    this.screenTitle = '国内建玉一覧'
    this.searchResultCount = '' // 検索結果件数
    this.totalPrice = '' // 建玉金額合計
    this.valuationTotalPreviousDay = '' // 評価額合計（前日）
    this.valuationTotalReal = '' // 評価額合計（リアル）
    this.costTotalYen15 = '' // 諸経費合計
    this.domesticPositionValuationTotalPreviousDay = '' // 評価損益合計（前日）
    this.domesticPositionValuationTotalReal = '' // 評価損益合計（リアル）
    this.noDetailMsg = '建玉明細はありません。' // 建玉明細なしメッセージ
    this.tradeSuspendFlag = '' // 取引停止フラグ
    this.intermediaryValueList = [{
      tradeClass: '', // 取引種別
      intermediaryValue: '' // 媒介可否
    }]
    this.positionList = [{ // エラー: 「国内建玉一覧」が、項目辞書に存在しません。
      brandCode: '', // 国内建玉一覧.銘柄コード
      brandName: '', // 国内建玉一覧.銘柄名
      tradeKbn: '', // 国内建玉一覧.取引種別.売買区分
      paymentDeadline: '', // 国内建玉一覧.取引種別.弁済期限
      market: '', // 国内建玉一覧.市場
      constructionDate: '', // 国内建玉一覧.新規建日
      repayPeriodShorter: '', // 国内建玉一覧.返済期限短縮
      lastTradeDate: '', // 国内建玉一覧.返済期限
      parentStockTradeDate: '', // 国内建玉一覧.親株新規約定日
      accountType: '', // 国内建玉一覧.特定・一般
      domesticCollateral: '', // 国内建玉一覧.担保
      collateralRegulations: '', // 国内建玉一覧.担保規制内容
      positionCount: '', // 国内建玉一覧.建玉件数
      contPositionTotal: '', // 国内建玉一覧.建株数
      unactualQuantity: '', // 国内建玉一覧.注文中
      newPrice: '', // 国内建玉一覧.新規単価
      dayBeforeValuationPrice: '', // 国内建玉一覧.評価単価（前日）
      currentPrice: '', // 国内建玉一覧.現在値（リアル）
      totalSmallPrice: '', // 国内建玉一覧.建玉金額計
      previousDayValueTotal: '', // 国内建玉一覧.評価額計（前日）
      realtimeValueTotal: '', // 国内建玉一覧.評価額計（リアル）
      costSmallTotalYen: '', // 国内建玉一覧.諸経費計
      domesticPositionValuationTotalPreviousDay: '', // 国内建玉一覧.評価損益（前日）
      domesticPositionValuationTotalReal: '', // 国内建玉一覧.評価損益（リアル）
      cashBond: '', // 国内建玉一覧.現金拘束金
      newCreditOrderType: '', // 国内建玉一覧.新規売買区分
      newOpenMarket: '', // 国内建玉一覧.新規市場
      newTradeDate: '', // 国内建玉一覧.新規約定日
      newOpenInterestNumber: '', // 国内建玉一覧.新規建玉指定番号
      repaySellButtonDisplaylassification: '', // 建玉一覧リスト.返済売ボタン表示区分
      receiptButtonDisplaylassification: '', // 建玉一覧リスト.現引ボタン表示区分
      repayBuyButtonDisplaylassification: '', // 建玉一覧リスト.返済買ボタン表示区分
      deliveryButtonDisplaylassification: '', // 建玉一覧リスト.現渡ボタン表示区分
      positionDetailButtonDisplaylassification: '', // 建玉一覧リスト.建玉詳細ボタン表示区分
      massRepayButtonDisplaylassification: '', // 建玉一覧リスト.一括返済ボタン表示区分
      activationDeactivationFlag: '' // 建玉一覧リスト.活性非活性フラグ
    }]
  }
}
