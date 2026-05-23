import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteUpdateA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.releaseNoteNo = obj.releaseNoteNo ? obj.releaseNoteNo : '' // リリースノートNo
  }
}
