import Logger from '@/utils/ifaLog.js'
export class IfaJointSubscriptSecurityCashBalanceLookupA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCodeCurrency ? obj.brandCodeCurrency : '' // 銘柄コード/通貨
  }
}
