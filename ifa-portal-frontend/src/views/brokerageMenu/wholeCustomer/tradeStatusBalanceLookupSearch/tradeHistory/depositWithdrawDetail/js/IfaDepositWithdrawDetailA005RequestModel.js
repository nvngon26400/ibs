import Logger from '@/utils/ifaLog.js'
export class IfaDepositWithdrawDetailA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.transitionNextScreen = 'SUB0202_010201' // 遷移先画面ID
  }
}
