import Logger from '@/utils/ifaLog.js'
export class IfaMutualFundAccumulateSettingInputA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.kyoukaiCd = obj.kyoukaiCd ? obj.kyoukaiCd : '' // 協会コード
    this.listFlag = obj.listFlag ? obj.listFlag : false
  }
}
