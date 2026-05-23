import Logger from '@/utils/ifaLog.js'
export class IfaDistributionReceiveMethodChangeInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.times = obj.times ? obj.times : '' // 銘柄コード.回数
    this.issue1 = obj.issue1 ? obj.issue1 : '' // 銘柄コード.号1
    this.issue2 = obj.issue2 ? obj.issue2 : '' // 銘柄コード.号2
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
  }
}
