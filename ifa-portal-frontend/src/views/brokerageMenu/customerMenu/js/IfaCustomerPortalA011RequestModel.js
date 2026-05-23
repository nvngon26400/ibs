import Logger from '@/utils/ifaLog.js'
export class IfaCustomerPortalA011RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ifaMemoContent = obj.ifaMemoContent ? obj.ifaMemoContent : '' // メモ(IFA専用)内容
    this.ifaMemoUpdateDateTime = obj.updateTime ? obj.updateTime : '' // メモ(IFA専用)更新日時
  }
}
