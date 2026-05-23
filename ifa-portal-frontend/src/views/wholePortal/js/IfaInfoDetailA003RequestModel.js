import Logger from '@/utils/ifaLog.js'
export class IfaInfoDetailA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.disclaimer = obj.disclaimer ? obj.disclaimer : '' // ディスクレーマー
    this.attachFile = obj.attachFile ? obj.attachFile : '' // 添付ファイル
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : '' // ファイルディレクトリ"
  }
}
