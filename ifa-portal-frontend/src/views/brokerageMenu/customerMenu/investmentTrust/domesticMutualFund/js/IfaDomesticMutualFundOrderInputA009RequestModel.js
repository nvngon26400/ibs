import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA009RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.selectAccountType = obj.selectAccountType ? obj.selectAccountType : '' // 選択口座
    this.selectDepositType = obj.selectDepositType ? obj.selectDepositType : '' // 預り区分固定 【初期値】選択預り区分
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.mutualFundSellBuyType = obj.mutualFundSellBuyType ? obj.mutualFundSellBuyType : '' // 売買区分（投信）
    this.fundType = obj.brand.fundType ? obj.brand.fundType : '' // ファンドタイプ
  }
}
