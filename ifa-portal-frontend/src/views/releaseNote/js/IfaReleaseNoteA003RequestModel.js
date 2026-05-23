import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.nextDispFlg = obj.nextDispFlg ? obj.nextDispFlg : '1' // 次回表示フラグ
  }
}
