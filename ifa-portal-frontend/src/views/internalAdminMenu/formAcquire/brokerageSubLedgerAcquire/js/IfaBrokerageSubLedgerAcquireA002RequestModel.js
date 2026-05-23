import Logger from '@/utils/ifaLog.js'
export class IfaBrokerageSubLedgerAcquireA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude // 仲介業者除外
    this.createDateFrom = obj.createDate[0] ? obj.createDate[0] : '' // 作成日From
    this.createDateTo = obj.createDate[1] ? obj.createDate[1] : '' // 作成日To
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : '' // ファイルディレクトリ
  }
}
