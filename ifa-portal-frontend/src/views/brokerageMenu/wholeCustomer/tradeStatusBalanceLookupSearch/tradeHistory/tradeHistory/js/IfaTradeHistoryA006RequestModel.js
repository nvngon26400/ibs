import Logger from '@/utils/ifaLog.js'
export class IfaTradeHistoryA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode12 ? obj.brandCode12 : '' // 銘柄コード
  }
}
