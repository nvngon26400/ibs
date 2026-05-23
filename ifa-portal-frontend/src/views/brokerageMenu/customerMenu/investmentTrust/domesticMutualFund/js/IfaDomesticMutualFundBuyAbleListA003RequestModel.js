import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundBuyAbleListA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.mutualFundSellBuyType = '1' // 売買区分
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.depositType = '' // 預り区分
    this.dispatchId = ' ' // 目論見書チェック区分
  }
}
