import Logger from '@/utils/ifaLog.js'
export class IfaPriceViewLookupForeignStockBrandListA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tickerSelectFlag = obj.tickerSelectFlag ? obj.tickerSelectFlag : '' // ティッカー選択フラグ
    this.brandCodeTicker = obj.brandCodeTicker ? obj.brandCodeTicker : '' // 銘柄コード（ティッカー）
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
  }
}
