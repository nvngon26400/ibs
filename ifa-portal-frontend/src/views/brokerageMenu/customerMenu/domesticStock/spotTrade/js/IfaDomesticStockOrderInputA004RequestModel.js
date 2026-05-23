import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.market = obj.selectedMarket ? obj.selectedMarket : '' // 市場
  }
}
