import Logger from '@/utils/ifaLog.js'
export class IfaContractNoteCustomerLedgerAcquireA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? 'true' : 'false' // 仲介業者除外
    this.createDateFrom = obj.createDateFrom ? obj.createDateFrom : '' // 作成日 From
    this.createDateTo = obj.createDateTo ? obj.createDateTo : '' // 作成日 To
    this.ledgerClass = obj.ledgerClass ? obj.ledgerClass : '' // 帳票種別
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : '' // ファイルディレクトリ
  }
}
