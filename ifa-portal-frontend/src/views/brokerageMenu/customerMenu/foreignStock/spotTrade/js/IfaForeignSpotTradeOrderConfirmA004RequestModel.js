import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderConfirmA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)

    /** 国コード（全角半角）. */
    this.countryCode = obj.marketInfo.countryCode ? obj.marketInfo.countryCode : ''

    /** 銘柄コード（半角英数字）. */
    this.brandCode = obj.brandInfo.brandCode ? obj.brandInfo.brandCode : ''

    /** 取引種別（全角半角）. */
    this.tradeCd = obj.buySellTypeName ? obj.buySellTypeName : ''
  }
}
