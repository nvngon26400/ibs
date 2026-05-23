import Logger from '@/utils/ifaLog.js'
export class IfaCurrencyDealtListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 選択行通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
  }
}
