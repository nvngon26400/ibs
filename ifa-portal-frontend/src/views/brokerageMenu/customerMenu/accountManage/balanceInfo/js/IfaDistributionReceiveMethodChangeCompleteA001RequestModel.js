import Logger from '@/utils/ifaLog.js'
export class IfaDistributionReceiveMethodChangeCompleteA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fundName = obj.fundName ? obj.fundName : '' // ファンド名
    this.unitVolume = obj.unitVolume ? obj.unitVolume : '' // 保有口数
    this.sellingVolume = obj.sellingVolume ? obj.sellingVolume : '' // 売却注文中
    this.times = obj.times ? obj.times : '' // ファンドコード・回数（4桁）
    this.issue1 = obj.issue1 ? obj.issue1 : '' // ファンドコード・号1
    this.issue2 = obj.issue2 ? obj.issue2 : '' // ファンドコード・号2
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
  }
}
