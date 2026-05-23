import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxPersonalDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.sbpNo = obj.sbpNo ? obj.sbpNo : '' // あなたの要望No
  }
}
