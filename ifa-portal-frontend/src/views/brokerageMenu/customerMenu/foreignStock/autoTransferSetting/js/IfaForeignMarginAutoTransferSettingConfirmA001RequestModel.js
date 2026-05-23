import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginAutoTransferSettingConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marginBuyingPowerShortfallSecurities = obj.marginBuyingPowerShortfallSecurities[0] ? obj.marginBuyingPowerShortfallSecurities[0] : '0' // 建余力不足.米国株式
    this.marginBuyingPowerShortfallCash = obj.marginBuyingPowerShortfallCash[0] ? obj.marginBuyingPowerShortfallCash[0] : '0' // 建余力不足.米ドル
    this.marginShortfallSecurities = obj.marginShortfallSecurities[0] ? obj.marginShortfallSecurities[0] : '0' // 保証金不足.米国株式
    this.marginShortfallCash = obj.marginShortfallCash[0] ? obj.marginShortfallCash[0] : '0' // 保証金不足.米ドル
    this.depositType = obj.depositType ? obj.depositType : '' // 株式自動振替先設定.振替先
  }
}
