import Logger from '@/utils/ifaLog.js'
export class IfaByYearAccountQuantityFeeAmountLookupA004RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.settleYearList = obj.settleYearList ? obj.settleYearList : [] // 決算年リスト
    this.closingMonth = obj.closingMonth ? obj.closingMonth : '' // 決算月
    this.chkBrokerCodeExclude = obj.chkBrokerCodeExclude ? obj.chkBrokerCodeExclude : false // 仲介業者除外
  }
}
