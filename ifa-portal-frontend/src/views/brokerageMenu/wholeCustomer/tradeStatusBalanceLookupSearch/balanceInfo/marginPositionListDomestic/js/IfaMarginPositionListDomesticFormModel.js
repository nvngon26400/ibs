export class IfaMarginPositionListDomesticFormModel {
  constructor() {
    this.title = '信用建玉一覧（国内）'
    // 検索項目
    this.brokerCode = '' // 仲介業者コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）条件
    this.course = [] // 取引コース（A002aリクエスト用）
    this.courseSelected = [] // 取引コース(共通検索部品) 【初期値】１個選択（IFAコース(プランA)）
    this.brandCode = '' // 銘柄コード
    this.chkBrokerCodeExclude = false // 仲介業者除外

    this.marginPositionListDomesticList = [ // 信用建玉一覧
      {
        butenCode: '', // 部店
        accountNumber: '', // 口座
        course: '', // 取引コース
        customerNameKanji: '', // 顧客名（漢字）
        customerNameKana: '', // 顧客名(カナ)
        domesticMarginPositionListActualGrntRate: '', // 維持率（%）
        brandName: '', // 銘柄名
        brandCode: '', // 銘柄コード
        market: '', // 市場
        openTradeKbn: '', // 取引
        openTradeDate: '', // 建日
        lastTradeDate: '', // 返済期限
        depositFullHalf13: '', // 預り
        contPositionTotal: '', // 建株数
        unactualQuantity: '', // 注文中
        unitPriceForeign: '', // 建単価
        currentPrice: '', // 現在値
        openAmount: '', // 建代金
        cost: '', // 諸経費等合計
        domesticPositionValuation: '', // 評価損益
        brokerCode: '', // 仲介業者コード
        brokerName: '', // 仲介業者名
        branchCode: '', // 支店コード
        branchName: '', // 支店名
        empCode: '', // 営業員コード
        brokerChargeName: '', // 営業員名
        newCreditOrderType: '', // 新規売買区分
        newOpenInterestNumber: '', // 新規建玉指定番号
        paymentDeadline: '', // 弁済期限
        parentStockTradeDate: '', // 親株新規約定日
        newTradeDate: '', // 新規約定日
        newOpenMarket: '' // 新規市場
      }
    ]
  }
}
