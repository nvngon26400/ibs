import Logger from '@/utils/ifaLog.js'
export class IfaRepAddA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.privId = obj.privId ? obj.privId : '' // 権限コード
  }
}
