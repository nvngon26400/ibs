export class IfaBrandPositionListFormModel {
  constructor() {
    this.butenCode = ''
    this.accountNumber = ''
    this.screenId = 'SUB0202_010202-03銘柄別建玉一覧'
    this.screenTitle = '銘柄別建玉一覧'
    this.noDetailMsg = '建玉明細はありません。' // 建玉明細なしメッセージ
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.newCreditOrderType = '' // 建玉区分（新規売買区分）
    this.specificPositionTypePaymentDeadline = '' // 建玉区分（弁済期限）
    this.openTradeKbn = '' // 新規売買区分
    this.paymentDeadline = '' // 弁済期限
    this.searchResultCount = '' // 検索結果件数（建玉件数）
    this.tradeSuspendFlag = '' // 取引停止フラグ
    this.intermediaryValueList = [{
      tradeClass: '', // 取引種別
      intermediaryValue: '' // 媒介可否
    }]
    this.tokyoSecurityFlag = ''
    this.ptsFlag = ''
    // 「銘柄別建玉一覧合計部」
    this.tokyoSecurityList = [{
      market: '', // 東証.市場
      totalPrice: '', // 東証.建玉金額合計
      valuationTotalPreviousDay: '', // 東証.評価額合計（前日）
      valuationTotalReal: '', // 東証.評価額合計（リアル）
      expensesTotal: '', // 東証.諸費用合計
      domesticPositionValuationTotalPreviousDay: '', // 東証.評価損益合計（前日）
      domesticPositionValuationTotalReal: '' // 東証.評価損益合計（リアル）
    }]
    this.ptsList = [{
      market: '', // PTS.市場
      totalPrice: '', // PTS.建玉金額合計
      valuationTotalPreviousDay: '', // PTS.評価額合計（前日）
      valuationTotalReal: '', // PTS.評価額合計（リアル）
      expensesTotal: '', // PTS.諸費用合計
      domesticPositionValuationTotalPreviousDay: '', // PTS.評価損益合計（前日）
      domesticPositionValuationTotalReal: '' // PTS.評価損益合計（リアル）
    }]
    this.sortOrder = '' // 並替順序 【初期値】評価益率順
    this.brandPositionDetailList = [{ // 「銘柄別建玉一覧」
      market: '', // 銘柄別建玉一覧.市場
      constructionDate: '', // 銘柄別建玉一覧.新規建日
      dueDateShortenClassification: '', // 銘柄別建玉一覧.期日短縮区分
      lastTradeDate: '', // 銘柄別建玉一覧.返済期限
      parentStockTradeDate: '', // 銘柄別建玉一覧.親株新規約定日
      accountType: '', // 銘柄別建玉一覧.特定・一般
      security: '', // 銘柄別建玉一覧.担保
      collateralRegulations: '', // 銘柄別建玉一覧.担保規制内容
      contPositionTotal: '', // 銘柄別建玉一覧.建株数
      unactualQuantity: '', // 銘柄別建玉一覧.注文中
      newPrice: '', // 銘柄別建玉一覧.新規単価
      dayBeforeValuationPrice: '', // 銘柄別建玉一覧.評価単価（前日）
      latestPrice: '', // 銘柄別建玉一覧.現在値（リアル）
      openInterestAmount: '', // 銘柄別建玉一覧.建玉金額
      valuationPreviousDay: '', // 銘柄別建玉一覧.評価額（前日）
      valuationReal: '', // 銘柄別建玉一覧.評価額（リアル）
      charge: '', // 銘柄別建玉一覧.諸費用
      domesticPositionValuationTotalPreviousDay: '', // 銘柄別建玉一覧.評価損益（前日）
      domesticPositionValuationTotalReal: '', // 銘柄別建玉一覧.評価損益（リアル）
      cashBond: '', // 銘柄別建玉一覧.現金拘束金
      maxOrderableQuantity: '', // 銘柄別建玉一覧.注文可能数量
      newOpenInterestNumber: '', // 銘柄別建玉一覧.新規建玉指定番号
      hiddenItemParentStockTradeDate: '' // 銘柄別建玉一覧.親株新規約定日２
    }]
  }
}
