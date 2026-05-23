import Logger from '@/utils/ifaLog.js'
export class IfaForeignBondBuyAmountInputInfoRegisterA005Model {
  constructor(obj) {
    Logger.debug(obj)
    if (obj.butenCode) { this.butenCode = obj.butenCode ? obj.butenCode : '' } // 部店コード
    if (obj.accountNumber) { this.accountNumber = obj.accountNumber ? obj.accountNumber : '' } // 口座番号
    if (obj.amount) { this.amount = obj.amount ? obj.amount : '' } // 金額（USD）
  }
}
