import Logger from '@/utils/ifaLog.js'
export class IfaLoginA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.mailAddress = obj.mailAddress ? obj.mailAddress : '' // メールアドレス
    this.userId = obj.userId ? obj.userId : '' // ログインID
    this.password = obj.password ? obj.password : '' // パスワード
  }
}
