import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.buySellTypeName = obj.buySellTypeName ? obj.buySellTypeName : '' // 取引種別
  }
}
