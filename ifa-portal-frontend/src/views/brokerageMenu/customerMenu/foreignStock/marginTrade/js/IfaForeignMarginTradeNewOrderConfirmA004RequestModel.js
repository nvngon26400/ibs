import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginTradeNewOrderConfirmA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.brandCode = obj.brandInformation.securitiesCode ? obj.brandInformation.securitiesCode : '' // 銘柄コード
  }
}
