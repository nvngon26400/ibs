import Logger from '@/utils/ifaLog.js'
export class IfaHoldingSecurityListA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    Logger.debug(obj)
    this.fundCodeTimes = obj.times ? obj.times : '' // 銘柄コード.回数
    this.issue1 = obj.issue1 ? obj.issue1 : '' // 銘柄コード.号1
    this.fundCodeIssues = obj.issue2 ? obj.issue2.padStart(3) : '' // 銘柄コード.号2
    this.mutualFundSellBuyType = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.dispatchId = obj.dispatchId ? obj.dispatchId : ' ' // 目論見書チェック区分
  }
}
