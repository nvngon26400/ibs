import Logger from '@/utils/ifaLog.js'
export class IfaForeignBondBuyAmountInputDeleteA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    if (obj.ifaAccountSeqNo) { this.ifaAccountSeqNo = obj.ifaAccountSeqNo ? obj.ifaAccountSeqNo : '' }
  }
}
