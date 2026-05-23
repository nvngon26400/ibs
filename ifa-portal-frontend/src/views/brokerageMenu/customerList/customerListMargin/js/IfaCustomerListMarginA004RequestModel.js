import Logger from '@/utils/ifaLog.js'

export class IfaCustomerListMarginA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.transitionNextScreen = obj.transitionNextScreen ? obj.transitionNextScreen : '' // 遷移先画面ID
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
  }
}
