import Logger from '@/utils/ifaLog.js'
export class IfaCollateralSecurityListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.settlementDate = obj.settlementDate ? obj.settlementDate : '' // 受渡日
    this.displayBaseDate = obj.displayBaseDate ? obj.displayBaseDate : '' // 表示基準日（受渡日）不要？？
    this.deliverInOutClassification = obj.deliverInOutClassification ? obj.deliverInOutClassification : '' // 入出庫区分
  }
}
