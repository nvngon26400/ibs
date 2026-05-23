export class IfaCouponRedemptionPaymentScheduleListFormModel {
  constructor() {
    this.periodDate = '' // 期間指定 【初期値】今月末
    this.periodAlert = '' // 期間指定_メッセージ
    this.securiytClassList = [] // 証券種別 【初期値】全選択
    this.brandCode = '' // 銘柄コード_入力 【初期値】""
    // 利金償還金支払予定一覧
    this.couponRedemptionPayScheduleList = [
      {
        brokerCode: '', // 利金償還金支払予定一覧.仲介業者コード
        brokerName: '', // 利金償還金支払予定一覧.仲介業者名
        empCode: '', // 利金償還金支払予定一覧.営業員コード
        brokerChargeName: '', // 利金償還金支払予定一覧.営業員名
        buten: '', // 利金償還金支払予定一覧.部店
        accountNumber: '', // 利金償還金支払予定一覧.口座番号
        dealerNumber: '', // 利金償還金支払予定一覧.扱者コード
        courceName: '', // 利金償還金支払予定一覧.コース名
        customerNameKanji: '', // 利金償還金支払予定一覧.顧客名
        customerNameKana: '', // 利金償還金支払予定一覧.顧客名
        securiytClass: '', // 利金償還金支払予定一覧.証券種別名
        brandCode: '', // 利金償還金支払予定一覧.銘柄コード
        brandName: '', // 利金償還金支払予定一覧.銘柄名
        contractStandardDeposit: '', // 利金償還金支払予定一覧.約定基準残高
        currency: '', // 利金償還金支払予定一覧.通貨
        paymentClass: '', // 利金償還金支払予定一覧.元利払種別
        scheduleDate: '', // 利金償還金支払予定一覧.予定日
        nextInterest: '', // 利金償還金支払予定一覧.予定利率
        schedulePrice: '', // 利金償還金支払予定一覧.予定概算金額
        ki: '', // 利金償還金支払予定一覧.KI有無
        branchCode: '', // 利金償還金支払予定一覧.支店コード
        branchName: '' // 利金償還金支払予定一覧.支店名
      }
    ]
  }
}
