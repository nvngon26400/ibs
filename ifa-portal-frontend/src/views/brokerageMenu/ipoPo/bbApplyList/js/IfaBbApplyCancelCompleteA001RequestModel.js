import Logger from '@/utils/ifaLog.js'
export class IfaBbApplyCancelCompleteA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.buten = obj.buten ? obj.buten : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
  }
}
