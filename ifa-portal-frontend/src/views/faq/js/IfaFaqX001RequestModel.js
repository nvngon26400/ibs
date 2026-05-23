import Logger from '@/utils/ifaLog.js'
export class IfaFaqX001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentsNo = obj.contentsNo ? obj.contentsNo : '' // コンテンツNo
  }
}
