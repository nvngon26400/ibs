import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteUpdateA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.contentItemList = obj.contentItemList ? obj.contentItemList : '' // 内容一覧
  }
}
