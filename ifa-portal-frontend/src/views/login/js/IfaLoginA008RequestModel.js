import Logger from '@/utils/ifaLog.js'
export class IfaLoginA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.userId = obj.userId ? obj.userId : '' // ログインID
    this.password = obj.password ? obj.password : '' // パスワード
    this.verifyCode = obj.verifyCode ? obj.verifyCode : '' // 認証コード
  }
}
