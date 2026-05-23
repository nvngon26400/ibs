import Logger from '@/utils/ifaLog.js'
export class IfaRepAddA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.privId = obj.privId ? obj.privId : '' // 権限コード
    this.sbiSecurityCode = obj.sbiSecurityCode ? obj.sbiSecurityCode : '' // SBI証券支店コード
  }
}
