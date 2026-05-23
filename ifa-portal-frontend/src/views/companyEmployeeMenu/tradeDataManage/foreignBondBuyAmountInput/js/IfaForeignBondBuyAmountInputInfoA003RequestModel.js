import Logger from '@/utils/ifaLog.js'
export class IfaForeignBondBuyAmountInputInfoA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    if (obj.butenCode) { this.butenCode = obj.butenCode }// 部店コード
    if (obj.accountNumber) { this.accountNumber = obj.accountNumber } // 口座番号
  }
}
