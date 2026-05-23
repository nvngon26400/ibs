import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteRegisterA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentItemList = obj.contentItemList ? obj.contentItemList : '' // 内容一覧
  }
}
