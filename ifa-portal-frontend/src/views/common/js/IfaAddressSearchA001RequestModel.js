import Logger from '@/utils/ifaLog.js'
export class IfaAddressSearchA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.yuusouNumber = obj.yuusouNumber ? obj.yuusouNumber : '' // 郵便番号
  }
}
