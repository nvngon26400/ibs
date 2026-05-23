import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockCounterOrderConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
  }
}
