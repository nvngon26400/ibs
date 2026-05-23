import Logger from '@/utils/ifaLog.js'
export class IfaHoldingSecurityListA019RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fundCode = obj.fundCode // 協会コード
  }
}
