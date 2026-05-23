import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
  }
}
