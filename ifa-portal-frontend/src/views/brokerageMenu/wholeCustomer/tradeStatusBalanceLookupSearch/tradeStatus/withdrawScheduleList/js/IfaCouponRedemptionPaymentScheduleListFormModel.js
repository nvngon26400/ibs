export class IfaCouponRedemptionPaymentScheduleListFormModel {
  constructor() {
    this.title = {
      name: '利金・償還金支払予定一覧',
      id: 'SUB020302_0104-01'
    }
    this.oneMonth10PercentDeclineBrandListComment = '' // 利金・償還金支払予定一覧コメント
    this.brokerCode = '' // 仲介業者コード_入力
    this.chkBrokerCodeExclude = '' // 仲介業者除外_入力
    this.branchCode = '' // 支店コード_入力
    this.empCode = '' // 営業員コード_入力
    this.butenCode = '' // 部店コード_入力
    this.accountNumber = '' // 口座番号_入力
    this.customerNameKanjiKana = '' // 顧客名(漢字/カナ)_入力
    this.customerNameKanjiKanaTerms = '' // 顧客名(漢字/カナ)_条件_入力
    this.courseSelected = [] // 取引コース_入力
    this.periodDate = [] // 期間指定 【初期値】今月末
    this.securityClassList = [] // 証券種別 【初期値】全選択
    this.brandCode = '' // 銘柄コード_入力 【初期値】""
    this.couponRedemptionDetailList = [ // 利金償還金支払予定一覧
      // {
      //   brokerCode: '' // 利金償還金支払予定一覧.仲介業者コード
      //   brokerName: '' // 利金償還金支払予定一覧.仲介業者名
      //   empCode: '' // 利金償還金支払予定一覧.営業員コード
      //   brokerChargeName: '' // 利金償還金支払予定一覧.営業員名
      //   buten: '' // 利金償還金支払予定一覧.部店
      //   accountNumber: '' // 利金償還金支払予定一覧.口座番号
      //   dealerNumber: '' // 利金償還金支払予定一覧.扱者コード
      //   courceName: '' // 利金償還金支払予定一覧.コース名
      //   customerNameKanji: '' // 利金償還金支払予定一覧.顧客名
      //   customerNameKana: '' // 利金償還金支払予定一覧.顧客名
      //   securiytClassName: '' // 利金償還金支払予定一覧.証券種別名
      //   brandCode: '' // 利金償還金支払予定一覧.銘柄コード
      //   brandName: '' // 利金償還金支払予定一覧.銘柄名
      //   contractStandardDeposit: '' // 利金償還金支払予定一覧.約定基準残高
      //   currency: '' // 利金償還金支払予定一覧.通貨
      //   paymentClass: '' // 利金償還金支払予定一覧.元利払種別
      //   scheduleDate: '' // 利金償還金支払予定一覧.予定日
      //   nextInterest: '' // 利金償還金支払予定一覧.予定利率
      //   schedulePrice: '' // 利金償還金支払予定一覧.予定概算金額
      //   couponDeterminationDate: '' // 利金償還金支払予定一覧.クーポン判定日
      //   kiCount: '' // 利金償還金支払予定一覧.KI件数
      //   branchCode: '' // 利金償還金支払予定一覧.支店コード
      //   branchName: '' // 利金償還金支払予定一覧.支店名
      // }, {...}
    ]
  }
}
