export class IfaJointMarketTrustFeeFormModel {
  constructor() {
    this.title = {
      id: 'SUB0208_02-01',
      name: '共同店舗　信託報酬'
    }
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
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
    // 共同店舗　信託報酬一覧
    this.JointSroreTrustFeeList = [
      {
        brokerCode: '', // 共同店舗信託報酬一覧.仲介業者コード
        brokerName: '', // 共同店舗共同店舗信託報酬一覧.仲介業者名
        buten: '', // 共同店舗共同店舗信託報酬一覧.部店/accountOpenMenu/newAccountOpenImcompleteStatus
        accountNumber: '', // 共同店舗共同店舗信託報酬一覧.口座番号
        course: '', // 共同店舗共同店舗信託報酬一覧.取引コース
        customerNameKanji: '', // 共同店舗共同店舗信託報酬一覧.顧客名(漢字)
        customerNameKana: '', // 共同店舗共同店舗信託報酬一覧.顧客名(カナ)
        branchCode: '', // 共同店舗信託報酬一覧.支店コード
        branchName: '', // 共同店舗信託報酬一覧.支店名
        securityClass: '', // 共同店舗共同店舗信託報酬一覧.証券種別
        holdingcDate: '', // 共同店舗共同店舗信託報酬一覧.保有日
        baseMonth: '', // 共同店舗共同店舗信託報酬一覧.保有月
        brandCode: '', // 共同店舗共同店舗信託報酬一覧.銘柄コード
        brandName: '', // 共同店舗共同店舗信託報酬一覧.銘柄名
        quantity: '', // 共同店舗共同店舗信託報酬一覧.数量
        referencePrice: '', // 共同店舗共同店舗信託報酬一覧.参考価額
        price: '', // 共同店舗信託報酬一覧.基準価額
        valuation: '', // 共同店舗信託報酬一覧.評価額
        trustFeeRate: '', // 共同店舗信託報酬一覧.信託報酬率
        currency: '', // 共同店舗信託報酬一覧.通貨
        fxRate: '', // 共同店舗信託報酬一覧.為替レート
        trustFeeAmount: '', // 共同店舗信託報酬一覧.信託報酬額
        jointRate: '', // 共同店舗信託報酬一覧.共募報酬率
        rewardPrice: '', // 共同店舗信託報酬一覧.支払額
        empCode: '', // 共同店舗信託報酬一覧.営業員コード
        brokerChargeName: ''// 共同店舗信託報酬一覧.営業員名
      }
    ]
  }
}
