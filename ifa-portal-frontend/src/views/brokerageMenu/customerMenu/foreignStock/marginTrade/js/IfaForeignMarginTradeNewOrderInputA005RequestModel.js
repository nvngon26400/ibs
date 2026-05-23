import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderInputA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
  }
}
