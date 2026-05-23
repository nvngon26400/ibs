import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderInputA011RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.countryCode = 'US' // 国コード
  }
}
