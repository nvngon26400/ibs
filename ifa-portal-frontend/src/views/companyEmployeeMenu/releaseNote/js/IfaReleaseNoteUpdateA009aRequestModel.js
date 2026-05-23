import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteUpdateA009aRequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.releaseNoteNo = obj.releaseNoteNo ? obj.releaseNoteNo : '' // リリースノートNo
    this.detailedFile = obj.detailedFile ? obj.detailedFile : '' // 詳細ファイル
  }
}
