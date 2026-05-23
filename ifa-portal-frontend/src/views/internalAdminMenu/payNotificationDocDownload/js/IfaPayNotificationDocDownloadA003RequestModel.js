import Logger from '@/utils/ifaLog.js'
export class IfaPayNotificationDocDownloadA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.dateYm = obj.dateYm ? obj.dateYm : '' // 年月
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.versionNumber = obj.versionNumber ? obj.versionNumber : '' // バージョン番号
    this.fileDirectory = obj.fileDirectory ? obj.fileDirectory : '' // ファイルディレクトリ
    this.beforeSearchTargetDateYmFrom = obj.beforeSearchTargetDateYmFrom ? obj.beforeSearchTargetDateYmFrom : '' // 前回検索時の対象年月From
    this.beforeSearchTargetDateYmTo = obj.beforeSearchTargetDateYmTo ? obj.beforeSearchTargetDateYmTo : '' // 前回検索時の対象年月To
    this.beforeSearchBrokerCode = obj.beforeSearchBrokerCode ? obj.beforeSearchBrokerCode : '' // 前回検索時の仲介業者コード
    this.beforeSearchChkBrokerCodeExclude = obj.beforeSearchChkBrokerCodeExclude ? 'true' : 'false' // 前回検索時の仲介業者除外
  }
}
