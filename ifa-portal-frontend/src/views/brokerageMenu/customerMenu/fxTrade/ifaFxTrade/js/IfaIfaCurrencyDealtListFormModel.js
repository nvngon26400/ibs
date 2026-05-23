export class IfaIfaCurrencyDealtListFormModel {
  constructor() {
    this.screenId = 'SUB0202_0503-01' // 画面ID
    this.screenTitle = '【IFA】取扱通貨一覧' // 画面タイトル
    this.dataList = [{
      currencyList: [
        {
          currencyCode: '', // 通貨コード
          currencyName: '', // 通貨名
          closeTime1: null, // 締時間1
          closeTime2: null, // 締時間2
          decimalLength: null, // 小数部有効桁数
          purchaseReferenceRate: null, // 買付参考レート
          referenceRateForSale: null // 売却参考レート
        }
      ]
    }]
    this.tradeKbn = '0' // 売買区分
    this.currencyCodeSelected = '' // 選択行通貨コード
  }
}
