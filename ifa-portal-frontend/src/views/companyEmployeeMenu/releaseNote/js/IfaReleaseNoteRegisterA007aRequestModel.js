import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteRegisterA007aRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.detailedFile = obj.detailedFile ? obj.detailedFile : '' // 詳細ファイル
  }
}
