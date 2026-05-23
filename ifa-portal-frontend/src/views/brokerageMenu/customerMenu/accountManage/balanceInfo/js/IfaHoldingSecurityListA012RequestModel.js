import Logger from '@/utils/ifaLog.js'
export class IfaHoldingSecurityListA012RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.accountClassification = obj.accountClassification ? obj.accountClassification : '' // 口座区分
    this.securitySelect = obj.securitySelect ? obj.securitySelect : '' // 商品選択
  }
}
