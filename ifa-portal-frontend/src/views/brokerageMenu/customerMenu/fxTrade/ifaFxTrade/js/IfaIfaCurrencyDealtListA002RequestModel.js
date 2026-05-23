export class IfaIfaCurrencyDealtListA002RequestModel {
  constructor(obj) {
    this.currencyCodeSelected = obj.currencyCodeSelected ? obj.currencyCodeSelected : '' // 選択行通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
  }
}
