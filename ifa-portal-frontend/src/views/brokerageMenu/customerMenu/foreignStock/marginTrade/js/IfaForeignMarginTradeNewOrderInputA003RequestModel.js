import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.tickerBrandCode ? obj.tickerBrandCode : '' // ティッカー／銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
  }
}
