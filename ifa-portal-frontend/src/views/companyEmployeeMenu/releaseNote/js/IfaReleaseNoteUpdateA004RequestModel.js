import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteUpdateA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.downloadFileName = obj.downloadFileName ? obj.downloadFileName : '' // 詳細ファイル
  }
}
