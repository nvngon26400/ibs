import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderInputA014RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.countryCodeText = obj.countryCodeText ? obj.countryCodeText : '' // 国籍
  }
}
