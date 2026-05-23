import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderInputA001RequestModel {
  constructor(obj, displayFlag, btnFlag) {
    Logger.debug(obj)
    let mutualFundSellBuyTypeTrans = ''
    // 国内投信購入可能一覧から遷移した時
    if (displayFlag) {
      mutualFundSellBuyTypeTrans = '1'
    } else {
      if (btnFlag) {
        mutualFundSellBuyTypeTrans = '1'
      } else {
        mutualFundSellBuyTypeTrans = '2'
      }
    }
    this.mutualFundSellBuyType = mutualFundSellBuyTypeTrans || '' // 売買区分（投信）
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.dispatchId = obj.dispatchId ? obj.dispatchId : ' ' // 目論見書チェック区分
  }
}
