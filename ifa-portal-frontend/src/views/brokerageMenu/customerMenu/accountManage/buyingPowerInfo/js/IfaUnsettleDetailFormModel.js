export class IfaUnsettleDetailFormModel {
  constructor() {
    this.title = '未精算明細'
    this.noDetailMsg = '未精算明細はありません。' // 未精算明細なしメッセージ
    this.customerName = '' // 顧客名
    this.accountNumber = '' // 口座番号
    this.classificationDisplay = '' // 分類表示
    this.searchResultCount = '' // 検索結果総数
    this.sumSettlementAmount = '' // 精算金額合計
    this.unsettleDetail = []
    // this.kouban = '' // 未精算明細.項番
    // this.settlementDate = '' // 未精算明細.受渡日
    // this.tradeDate = '' // 未精算明細.約定日
    // this.openTradeKbn = '' // 未精算明細.取引
    // this.dispAbstract = '' // 未精算明細.摘要
    // this.quantity = '' // 未精算明細.数量
    // this.price = '' // 未精算明細.単価
    // this.settlementAmount = '' // 未精算明細.精算金額
  }
}
