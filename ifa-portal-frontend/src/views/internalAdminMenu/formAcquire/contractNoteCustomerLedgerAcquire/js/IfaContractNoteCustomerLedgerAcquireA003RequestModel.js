import Logger from '@/utils/ifaLog.js'
export class IfaContractNoteCustomerLedgerAcquireA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : '' // ファイルディレクトリ
    this.ledgerName = obj.ledgerName ? obj.ledgerName : '' // 帳票名
    this.ledgerClass = obj.ledgerClass ? obj.ledgerClass : '' // 帳票種別
    this.createDate = obj.createDate ? obj.createDate : '' // 作成日
    this.downloadFileName = obj.downloadFileName ? obj.downloadFileName : '' // ダウンロードファイル名
  }
}
