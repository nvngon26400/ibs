export class IfaForeignBondBuyAmountInputListFormModel {
  constructor() {
    this.title = {
      id: 'SUB0504_04-01',
      name: '外債買付代金入力一覧'
    }
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.nameKanji = '' // 顧客名
    this.amount = '' // 金額（USD）
    this.orderTime = '' // 注文時間
    this.ifaAccountSeqNo = ''// IFAシーケンス
    this.processDayTime = '' // 処理日
    this.createTime = '' // 作成日時
    this.updateTime = '' // 更新日時
  }
}
