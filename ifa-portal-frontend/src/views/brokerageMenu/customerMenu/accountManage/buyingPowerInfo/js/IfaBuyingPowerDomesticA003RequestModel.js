import Logger from '@/utils/ifaLog.js'
export class IfaBuyingPowerDomesticA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.accountType = obj.accountTypeRadio ? obj.accountTypeRadio : '' // 口座区分
  }
}
