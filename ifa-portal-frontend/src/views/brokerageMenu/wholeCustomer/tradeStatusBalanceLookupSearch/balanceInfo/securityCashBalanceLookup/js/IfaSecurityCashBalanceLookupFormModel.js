export class IfaSecurityCashBalanceLookupFormModel {
  constructor() {
    this.title = {
      id: 'SUB020302_0301-01',
      name: '証券・金銭・残高照会'
    }

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
    this.period = '' // 期間指定 【初期値】当月
    this.periodAlert = '※期間は過去12ヶ月以内を指定してください。' // 期間指定メッセージ
    this.securityTypeSecuritiesMoney = [] // 証券　金銭 【初期値】全選択
    this.securityTypeCreditFuturesOp = [] // 信用・先OP 【初期値】未選択
    this.brandCode = '' // 銘柄コード 【初期値】""

    this.brandCodeCurrency = '' // 選択明細行の銘柄コード/通貨

    // 証券・金銭・残高照会一覧
    this.securityCashBalanceLookupList = [
      {
        dateYmd: '', // 年月日
        brokerCode: '', // 仲介業者コード
        brokerName: '', // 仲介業者名
        empCode: '', // 営業員コード
        brokerChargeName: '', // 営業員名
        buten: '', // 部店
        accountNumber: '', // 口座番号
        dealerNumber: '', // 扱者コード
        courceName: '', // コース名
        customerNameKanji: '', // 顧客名(漢字)
        customerNameKana: '', // 顧客名(カナ)
        securiytClass: '', // 証券種別名
        tradeTypeName: '', // 取引種別名
        lastTradeDate: '', // 返済期限
        sbiSecurityLastTradeDate: '', // SBI証券の返済期限
        brandCodeCurrency: '', // 銘柄コード/通貨
        brandName: '', // 銘柄名
        depositType: '', // 預り区分
        contractStandardDeposit: '', // 約定基準残高
        currentPrice: '', // 現在値
        valuation: '', // 評価額（円貨）
        currency: '', // 通貨
        foreignValuation: '', // 評価額（外貨）
        fxRate: '', // 為替レート
        branchCode: '', // 支店コード
        branchName: '', // 支店名
        butenCodeViewAble: '', // 閲覧可能部店
        foreignStandardDate: '', // 外債評価基準日
        structuredBondClassification: '' // 仕組債区分
      }
    ]
  }
}
