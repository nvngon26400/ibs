export class IfaJointSubscriptTradeListFormModel {
  constructor() {
    this.pageTitle = {
      id: 'SUB0206_02-01',
      name: '共同募集　取引検索'
    }
    this.commComment = ''
    this.count = ''
    this.brokerCode = '' // 仲介業者コード
    this.chkBrokerCodeExclude = false // 仲介業者除外
    this.jointBranchCode = '' // 共募支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.periodDateFrom = ''
    this.periodDateTo = ''
    this.securityClass = [] // 証券種別 【初期値】全選択
    this.brandCode12 = '' // 銘柄コード 【初期値】""
    this.periodDate = [] // 期間指定 【初期値】前営業日（From、To両方）
    this.periodAlert = '' // 期間指定メッセージ
    this.customerNameKanjiKana = '' // 顧客名
    this.customerNameKanjiKanaTerms = '3' // 顧客名検索オプション
    this.courseSelected = '' // 取引コース

    // 共同募集　取引一覧
    this.jointSubscriptTradeHistoryList = [{ // 取引一覧
      brokerCode: '', // 仲介業者コード
      brokerName: '', // 仲介業者名
      butenCode: '', // 部店
      accountNumber: '', // 口座番号
      customerAttributeName: '', // コース名
      nameKanji: '', // 顧客名
      jointBranchCode: '', // 共募支店コード
      jointBranchName: '', // 共募支店名
      securityClass: '', // 証券種別
      brandCode: '', // 銘柄コード
      brandName: '', // 銘柄名
      productRank: '', // 商品ランク
      tradeTypeName: '', // 取引種別
      depositName: '', // 預り区分
      tradeDate: '', // 約定日
      settlementDate: '', // 受渡日
      price: '', // 単価
      quantity: '', // 数量
      contractAmountJpyAmount: '', // 約定金額（円貨）
      yenFee: '', // 手数料（円貨）
      yenDeliveryAmount: '', // 受渡金額（円貨）
      currency: '', // 通貨
      fxRate: '', // 為替レート
      contractAmountForeign: '', // 約定金額（外貨）
      foreignDeliveryAmount: '', // 受渡金額（外貨）
      feeBalance: '', // 残手数料
      jointRewardPrice: '', // 支払額
      empCode: '', // 営業員コード
      brokerChargeName: '', // 営業員名
      bondIntermediary: '', // 債券　媒介/非媒介
      usStockStoreEntrust: '', // 米国株　店頭/委託
      visibleButenCode: '' // 閲覧可能部店
    }]
  }
}
