import Logger from '@/utils/ifaLog.js'
export class IfaDomesticStockOrderInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
  }
}
