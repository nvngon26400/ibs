import Logger from '@/utils/ifaLog.js'
export class IfaTodayTradeListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.buten ? obj.buten : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
  }
}
