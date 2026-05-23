import Logger from '@/utils/ifaLog.js'
export class IfaForeignStockBrandSearchPopupA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
  }
}
