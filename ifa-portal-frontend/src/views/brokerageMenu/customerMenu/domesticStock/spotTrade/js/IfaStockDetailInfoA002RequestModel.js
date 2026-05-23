import Logger from '@/utils/ifaLog.js'
export class IfaStockDetailInfoA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.market = obj.market ? obj.market : '' // 市場
  }
}
