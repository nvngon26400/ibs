import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.releaseNoteNo = obj.releaseNoteNo ? obj.releaseNoteNo : '' // リリースノートNo
  }
}
