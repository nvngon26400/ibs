import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.selectAccountType = obj.selectAccountType ? obj.selectAccountType : '' // 選択口座
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.transfersPreferentialQuotaApplicationSelect = obj.transfersPreferentialQuotaApplicationSelect ? obj.transfersPreferentialQuotaApplicationSelect : '' // 乗換優遇枠適用
  }
}
