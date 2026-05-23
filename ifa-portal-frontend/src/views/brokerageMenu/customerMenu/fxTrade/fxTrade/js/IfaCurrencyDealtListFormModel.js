export class IfaCurrencyDealtListFormModel {
  constructor() {
    this.screenId = 'SUB0202_0502-01'
    this.screenTitle = '取扱通貨一覧'
    // 通貨リスト
    this.currencyList = [{
      currencyCode: '', // 通貨一覧.国旗
      currencyName: '', //
      fxTrade: '', // 為替取引
      deadlines1: '', // 締時間1
      deadlines2: '', // 締時間2
      purchaseReferenceRate: '', // 通貨一覧.買付参考レート
      referenceRateForSale: '', // 通貨一覧.売却参考レート
      decimalLength: '' // 小数部有効桁数（数値(整数)）
    }]
  }
}
