import Logger from '@/utils/ifaLog.js'
export class IfaComplianceReportA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.filename = obj.filename ? obj.filename : '' // ファイル名
    this.directory = obj.directory ? obj.directory : '' // ファイルパス
    this.confirmFlg = obj.confirmFlg ? obj.confirmFlg : '' // 確認フラグ
  }
}
