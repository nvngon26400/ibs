import Logger from '@/utils/ifaLog.js'
export class IfaForeignMarginAutoTransferSettingInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.marginBuyingPowerShortfallSecurities = obj.marginBuyingPowerShortfallSecurities ? obj.marginBuyingPowerShortfallSecurities : '0' // 建余力不足.米国株式
    this.marginBuyingPowerShortfallCash = obj.marginBuyingPowerShortfallCash ? obj.marginBuyingPowerShortfallCash : '0' // 建余力不足.米ドル
    this.marginShortfallSecurities = obj.marginShortfallSecurities ? obj.marginShortfallSecurities : '0' // 保証金不足.米国株式
    this.marginShortfallCash = obj.marginShortfallCash ? obj.marginShortfallCash : '0' // 保証金不足.米ドル
    this.depositType = obj.depositType ? obj.depositType : '' // 株式自動振替先設定.振替先
  }
}
