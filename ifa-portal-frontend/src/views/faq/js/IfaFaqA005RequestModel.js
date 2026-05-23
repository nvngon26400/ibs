import Logger from '@/utils/ifaLog.js'
export class IfaFaqA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentsNo = obj.contentsNo ? obj.contentsNo : '' // コンテンツNo
  }
}
