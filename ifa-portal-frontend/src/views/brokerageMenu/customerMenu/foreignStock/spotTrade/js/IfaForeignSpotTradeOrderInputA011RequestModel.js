import Logger from '@/utils/ifaLog.js'
export class IfaForeignSpotTradeOrderInputA011RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
  }
}
