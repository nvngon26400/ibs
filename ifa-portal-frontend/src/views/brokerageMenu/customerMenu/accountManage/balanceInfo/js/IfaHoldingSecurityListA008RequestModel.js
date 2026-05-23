import Logger from '@/utils/ifaLog.js'
export class IfaHoldingSecurityListA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
  }
}
