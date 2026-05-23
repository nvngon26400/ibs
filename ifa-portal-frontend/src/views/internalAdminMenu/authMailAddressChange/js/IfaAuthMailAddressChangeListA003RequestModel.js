import Logger from '@/utils/ifaLog.js'
export class IfaAuthMailAddressChangeListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.employeeName = obj.userName ? obj.userName : '' // 社員名/担当者名
    this.mailAddr = obj.mailAddress ? obj.mailAddress : '' // 認証用メールアドレス
  }
}
