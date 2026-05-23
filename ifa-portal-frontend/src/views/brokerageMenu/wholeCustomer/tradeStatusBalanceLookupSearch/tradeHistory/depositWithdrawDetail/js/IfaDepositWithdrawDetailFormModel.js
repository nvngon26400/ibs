export class IfaDepositWithdrawDetailFormModel {
  constructor() {
    this.title = '入出金明細'
    this.depositWithdrawDetailType = '0' // 区分 【初期値】全て
    this.withdrawTotalAmount = '0' // 出金額合計 合計額 【初期値】0
    this.withdrawTotalCount = '0' // 出金額合計 件数 【初期値】0
    this.depositTotalAmount = '0' // 入金額合計 合計額 【初期値】0
    this.depositTotalCount = '0' // 入金額合計 件数 【初期値】0
    this.transferWithdrawTotalAmount = '0' // 振替出金額合計 合計額 【初期値】0
    this.transferWithdrawTotalCount = '0' // 振替出金額合計 件数 【初期値】0
    this.transferDepositTotalAmount = '0' // 振替入金額合計 合計額 【初期値】0
    this.transferDepositTotalCount = '0' // 振替入金額合計 件数 【初期値】0
    this.depositWithdrawDetail = [] // 入出金明細
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.course = '' // 取引コース
    this.customerNameKanji = '' // 顧客名(漢字)
    this.customerNameKana = '' // 顧客名(カナ)
    this.depositsAndWithdrawalsDate = '' // 入出金日
    this.openTradeKbn = '' // 取引
    this.dispAbstract = '' // 摘要
    this.deliveryAmount = '' // 受渡金額
    this.empCode = '' // 営業員コード
    this.brokerChargeName = '' // 営業員名
    this.brokerCode = '' // 仲介業者コード
    this.brokerName = '' // 仲介業者名
    this.branchCode = '' // 支店コード
    this.branchName = '' // 支店名
    this.period = []

    this.chkBrokerCodeExclude = ''
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
  }
}
