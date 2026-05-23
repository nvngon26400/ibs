import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockCounterOrderInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.selectTicker ? obj.selectTicker : '' // 銘柄コード
    this.tradeClassification = obj.tradeClassification ? obj.tradeClassification : '' // 取引区分
    this.returnTickerSelectFlag = obj.tickerSelectFlag ? obj.tickerSelectFlag : '' // 返却用ティッカー選択フラグ
    this.forReturnBrandCode = obj.brandCodeTicker ? obj.brandCodeTicker : '' // 返却用銘柄コード
    this.forReturnBrandName = obj.brandName ? obj.brandName : '' // 返却用銘柄名
  }
}
