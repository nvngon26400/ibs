export class IfaTrustFeeFormModel {
  constructor() {
    this.title = {
      id: 'SUB020503-01',
      name: '信託報酬'
    }
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.empCode = '' // 営業員コード
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanjiKana = '' // 顧客名（漢字／カナ）
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
    this.courseSelected = [] // selected取引コース

    this.dailyMonthlyCountingUnit = '0' // 集計単位(日次/月次累計)_入力 【初期値】'0':日次
    this.detailCustomerCurrencyCountingUnit = '0' // 集計単位(明細/顧客/通貨毎)_入力 【初期値】'0':明細
    this.periodDate = [] // 期間指定（日次指定）_入力 【初期値】前営業日の日付（From,Toどちらも）
    this.periodMonth = [] // 期間指定（月次指定）_入力 【初期値】当月（From,Toどちらも）
    this.periodAlert = '' // 期間指定_メッセージ
    this.securityClassList = [] // 証券種別_入力 【初期値】全選択
    this.brandCode = '' // 銘柄コード_入力 【初期値】""
    // 信託報酬一覧
    this.trustFeeList = [
      {
        brokerCode: '', // 信託報酬一覧.仲介業者コード
        brokerName: '', // 信託報酬一覧.仲介業者名
        empCode: '', // 信託報酬一覧.営業員コード
        brokerChargeName: '', // 信託報酬一覧.営業員名
        buten: '', // 信託報酬一覧.部店
        accountNumber: '', // 信託報酬一覧.口座番号
        dealerNumber: '', // 信託報酬一覧.扱者コード
        course: '', // 信託報酬一覧.取引コース
        customerNameKanji: '', // 信託報酬一覧.顧客名(漢字)
        customerNameKana: '', // 信託報酬一覧.顧客名(カナ)
        securityClass: '', // 信託報酬一覧.証券種別
        holdingcDate: '', // 信託報酬一覧.保有日
        baseMonth: '', // 信託報酬一覧.保有月
        brandCode: '', // 信託報酬一覧.銘柄コード
        brandName: '', // 信託報酬一覧.銘柄名
        quantity: '', // 信託報酬一覧.数量
        referencePrice: '', // 信託報酬一覧.参考価額
        price: '', // 信託報酬一覧.基準価額
        valuation: '', // 信託報酬一覧.評価額
        trustFeeRate: '', // 信託報酬一覧.信託報酬率
        currency: '', // 信託報酬一覧.通貨
        fxRate: '', // 信託報酬一覧.為替レート
        trustFeeAmount: '', // 信託報酬一覧.信託報酬額
        branchCode: '', // 信託報酬一覧.支店コード
        branchName: '' // 信託報酬一覧.支店名
      }
    ]
  }
}
