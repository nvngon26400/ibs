import Logger from '@/utils/ifaLog.js'
export class IfaFxOrderInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
  }
}
