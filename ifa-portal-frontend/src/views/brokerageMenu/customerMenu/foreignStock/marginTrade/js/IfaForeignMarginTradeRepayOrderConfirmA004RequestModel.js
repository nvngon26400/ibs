import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeRepayOrderConfirmA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄検索
  }
}
