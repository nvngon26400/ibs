export class IfaForeignStockEntrustOrderTradeInfoFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0104-02',
      name: '委託注文約定情報'
    }
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    // 委託注文約定情報一覧
    this.entrustOrderTradeinfoList = [
      {
        tradeDateTime: '', // 委託注文約定情報一覧.約定日時
        tradeQuantity: '', // 委託注文約定情報一覧.約定数量
        tradePrice: '', // 委託注文約定情報一覧.約定単価
        tradePriceCurrencyCode: '' // 委託注文約定情報一覧.約定単価通貨コード
      }
    ]
    this.noDetailMsg = '委託注文約定情報明細はありません。' // 委託注文約定情報明細なしメッセージ
  }
}
