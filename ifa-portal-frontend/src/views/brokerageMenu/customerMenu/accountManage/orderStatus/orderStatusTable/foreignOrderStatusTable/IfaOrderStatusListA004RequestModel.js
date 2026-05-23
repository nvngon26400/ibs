import Logger from '@/utils/ifaLog.js'
export class IfaOrderStatusListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.manageNumber = obj.manageNumber ? obj.manageNumber : '' // 管理番号
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
  }
}
