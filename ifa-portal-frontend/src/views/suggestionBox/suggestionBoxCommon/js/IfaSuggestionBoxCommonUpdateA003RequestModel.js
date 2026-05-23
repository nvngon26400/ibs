import Logger from '@/utils/ifaLog.js'
export class IfaSuggestionBoxCommonUpdateA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.attachFile = obj.attachFile ? obj.attachFile : '' // 皆様からの要望No
  }
}

