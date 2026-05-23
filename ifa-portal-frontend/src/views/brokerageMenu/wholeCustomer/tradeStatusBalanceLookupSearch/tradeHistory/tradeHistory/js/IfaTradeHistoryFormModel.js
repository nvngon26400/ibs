export class IfaTradeHistoryFormModel {
  constructor() {
    this.title = {
      id: 'SUB020302_0201-01',
      name: '取引履歴'
    }

    this.comment = ''
    this.count = ''
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名
    this.customerNameKanjiKanaTerms = '3' // 顧客名検索オプション
    this.courseSelected = '' // 取引コース
    this.butenCodeArray = '' // 閲覧可能部店 【初期値】""
    this.periodDateFrom = ''
    this.periodDateTo = ''
    this.securityClass = [] // 証券種別 【初期値】全選択
    this.brandCode12 = '' // 銘柄コード 【初期値】""

    this.periodDate = [] // 期間指定 【初期値】前営業日（From、To両方）
    this.periodAlert = '' // 期間指定メッセージ
    this.tradeHistoryList = [{ // 取引履歴一覧
      brokerCode: '', // 仲介業者コード
      brokerName: '', // 仲介業者名
      empCode: '', // 営業員コード
      brokerChargeName: '', // 営業員名
      buten: '', // 部店
      accountNumber: '', // 口座番号
      tcCompRank: '', // Cランク
      dealerNumber: '', // 扱者コード
      courseName: '', // コース名
      customerNameKanji: '', // 顧客名(漢字)
      customerNameKana: '', // 顧客名(カナ)
      securityClass: '', // 証券種別
      brandCode: '', // 銘柄コード
      brandName: '', // 銘柄名
      productRank: '', // 商品ランク
      tradeCd: '', // 取引種別
      tradeDate: '', // 約定日
      settlementDate: '', // 受渡日
      price: '', // 単価
      quantity: '', // 数量
      contractAmountJpyAmount: '', // 約定金額（円貨）
      yenFee: '', // 手数料（円貨）
      yenDeliveryAmount: '', // 受渡金額（円貨）
      currency: '', // 通貨
      contractAmountForeign: '', // 約定金額（外貨）
      foreignDeliveryAmount: '', // 受渡金額（外貨）
      foreignFee: '', // 手数料（外貨）
      fxRate: '', // 為替レート
      branchCode: '', // 支店コード
      branchName: '', // 支店名
      bondIntermediary: '', // 債券　媒介/非媒介
      usStockStoreEntrust: '', // 米国株　店頭/委託
      butenCodeViewAble: '' // 閲覧可能部店
    }]
  }
}
