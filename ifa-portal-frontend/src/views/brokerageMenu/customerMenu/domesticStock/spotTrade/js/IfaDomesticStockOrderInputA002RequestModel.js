import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderInputA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.market = obj.market ? obj.market : '' // 市場
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
  }
}
