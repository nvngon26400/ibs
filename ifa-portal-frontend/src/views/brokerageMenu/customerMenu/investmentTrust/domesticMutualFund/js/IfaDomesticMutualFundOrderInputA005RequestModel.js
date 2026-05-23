import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.mutualFundSellBuyType = obj.mutualFundSellBuyType ? obj.mutualFundSellBuyType : '' // 売買区分（投信）
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.depositType = obj.selectAccountType === ' ' ? ' ' : '5' // 預り区分 （総合口座に切替 → △（特定/一般）, ジュニアNISA口座に切替 → 5（Jr特定/Jr一般））
  }
}
