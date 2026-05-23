export class IfaDeliverInOutDetailFormModel {
  constructor() {
    this.screenId = 'SUB020302_0204-01' // 画面ID
    this.title = '入出庫明細' // 画面名
    this.brokerName = '' // 仲介業者名
    this.empCode = '' // 営業員コード
    this.brokerChargeName = '' // 営業員名
    this.buten = '' // 部店
    this.accountNumber = '' // 口座番号
    this.course = '' // 取引コース
    this.customerNameKanji = '' // 顧客名(漢字)
    this.customerNameKana = '' // 顧客名(カナ)
    this.brandName = '' // 銘柄名
    this.securityType = '' // 商品区分
    this.deliverInOutClassification = '' // 入出庫区分
    this.quantity = '' // 数量
    this.checkInOutPrice = '' // 入庫日時価
    this.totalAmount = '' // 合計金額
    this.checkInOutDay = '' // 入出庫日
    this.brokerCode = '' // 仲介業者コード
    this.branchCode = '' // 支店コード
    this.branchName = '' // 支店名
    this.period = []

    this.chkBrokerCodeExclude = ''
    this.customerNameKanjiKanaTerms = '3' // 顧客名（漢字／カナ）_条件
  }
}
