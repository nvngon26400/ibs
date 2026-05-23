import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundBuyAbleListA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.kyoukaiCd = obj.kyoukaiCd ? obj.kyoukaiCd : '' // 協会コード
  }
}
