import Logger from '@/utils/ifaLog.js'
export class IfaInfoDetailA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.notificationId = obj.notificationId ? obj.notificationId : ''// お知らせID
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : ''// ファイルディレクトリ
  }
}
