import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxCommonDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.sbcNo = obj.sbcNo ? obj.sbcNo : '' // 皆様からの要望No
  }
}

