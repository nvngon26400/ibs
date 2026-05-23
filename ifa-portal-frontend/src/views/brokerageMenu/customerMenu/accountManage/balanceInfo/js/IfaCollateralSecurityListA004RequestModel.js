import Logger from '@/utils/ifaLog.js'
export class IfaCollateralSecurityListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.displayBaseDate = obj.displayBaseDate ? obj.displayBaseDate : '' // 表示基準日（受渡日）
    this.collateralClass = obj.collateralClass ? obj.collateralClass : '' // 代用種別
  }
}
