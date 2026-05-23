export class IfaJointSubscriptTrustFeeFormModel {
  constructor() {
    this.title = {
      id: 'SUB0206_03-01',
      name: '共同募集　信託報酬'
    }
    this.chkBrokerCodeExclude = '' // 仲介業者除外
    this.brokerCode = '' // 仲介業者コード
    this.jointBranchCode = '' // 共募支店コード
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
    this.securityClassList = [] // 証券種別_入力 【初期値】全選択
    this.brandCode = '' // 銘柄コード_入力 【初期値】""
    // 信託報酬一覧
    this.trustFeeList = [
      {
        brokerCode: '', // 共募信託報酬一覧.仲介業者コード
        brokerName: '', // 共募共募信託報酬一覧.仲介業者名
        empCode: '', // 共募共募信託報酬一覧.営業員コード
        brokerChargeName: '', // 共募信託報酬一覧.営業員名
        butenCode: '', // 共募信託報酬一覧.部店
        accountNumber: '', // 共募信託報酬一覧.口座番号
        course: '', // 共募信託報酬一覧.取引コース
        customerNameKanji: '', // 共募信託報酬一覧.顧客名(漢字)
        customerNameKana: '', // 共募信託報酬一覧.顧客名(カナ)
        securityClass: '', // 共募信託報酬一覧.証券種別
        baseDate: '', // 共募信託報酬一覧.保有日
        baseMonth: '', // 共募信託報酬一覧.保有月
        brandCode: '', // 共募信託報酬一覧.銘柄コード
        brandName: '', // 共募信託報酬一覧.銘柄名
        quantity: '', // 共募信託報酬一覧.数量
        referencePrice: '', // 共募信託報酬一覧.参考価額
        price: '', // 共募信託報酬一覧.基準価額
        valuation: '', // 共募信託報酬一覧.評価額
        trustFeeRate: '', // 共募信託報酬一覧.信託報酬率
        currency: '', // 共募信託報酬一覧.通貨
        fxRate: '', // 共募信託報酬一覧.為替レート
        trustFeeAmount: '', // 共募信託報酬一覧.信託報酬額
        jointBranchCode: '', // 共募信託報酬一覧.共募支店コード
        jointBranchName: '', // 共募信託報酬一覧.共募支店名
        feeBalance: '', // 共募信託報酬一覧. 残手数料
        jointRewardPrice: '' // 共募信託報酬一覧.支払額
      }
    ]
  }
}
