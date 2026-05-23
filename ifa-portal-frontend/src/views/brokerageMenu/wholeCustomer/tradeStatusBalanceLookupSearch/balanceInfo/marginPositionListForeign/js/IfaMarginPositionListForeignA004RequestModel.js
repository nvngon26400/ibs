import Logger from '@/utils/ifaLog.js'
export class IfaMarginPositionListForeignA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.buten ? obj.buten : '' // 部店コード
    this.accountNumber = obj.account ? obj.account : '' // 口座番号
  }
}
