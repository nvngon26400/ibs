import Logger from '@/utils/ifaLog.js'
export class IfaReleaseNoteA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.displayYear = obj.displayYear ? obj.displayYear : '' // 表示対象年
  }
}
