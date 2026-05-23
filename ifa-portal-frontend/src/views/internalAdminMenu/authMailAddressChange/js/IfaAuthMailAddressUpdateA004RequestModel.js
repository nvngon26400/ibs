import Logger from '@/utils/ifaLog.js'
export class IfaAuthMailAddressUpdateA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
    this.mailAddr = obj.mailAddress ? obj.mailAddress : '' // 認証用メールアドレス
  }
}
