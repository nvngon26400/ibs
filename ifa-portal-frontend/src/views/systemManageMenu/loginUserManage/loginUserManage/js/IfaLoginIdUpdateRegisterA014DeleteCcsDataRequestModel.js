import Logger from '@/utils/ifaLog.js'
export class IfaLoginIdUpdateRegisterA014DeleteCcsDataRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.loginId = obj.loginId ? obj.loginId : '' // ログインID
  }
}
