import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMarginCollateralDeficientAlertListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.transitionNextScreen = obj.transitionNextScreen ? obj.transitionNextScreen : '' // 遷移先画面ID
  }
}
