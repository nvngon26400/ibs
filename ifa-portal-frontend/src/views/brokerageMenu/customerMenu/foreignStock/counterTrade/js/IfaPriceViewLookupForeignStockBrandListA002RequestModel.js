import Logger from '@/utils/ifaLog.js'
export class IfaPriceViewLookupForeignStockBrandListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tickerSelectFlag = obj.tickerSelectFlag ? obj.tickerSelectFlag : '' // 検索条件
    this.brandCodeTicker = obj.brandCodeTicker ? obj.brandCodeTicker : '' // ティッカーキー
    this.brandName = obj.brandName ? obj.brandName : '' // 名称キー
  }
}
