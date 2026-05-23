import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.mutualFundSellBuyType = obj.mutualFundSellBuyType ? obj.mutualFundSellBuyType : '' // 売買区分（投信）
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
  }
}
