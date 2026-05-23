import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockCounterOrderInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeKbn = obj.tradeClassification ? obj.tradeClassification : '' // 売買区分
  }
}
