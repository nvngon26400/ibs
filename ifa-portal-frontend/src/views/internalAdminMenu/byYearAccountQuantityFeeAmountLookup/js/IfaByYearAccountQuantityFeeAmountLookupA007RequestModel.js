import Logger from '@/utils/ifaLog.js'
export class IfaByYearAccountQuantityFeeAmountLookupA007RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brokerCode = obj.brokerCode ? obj.brokerCode : '' // 仲介業者コード
    this.closingYearMonth = obj.closingYearMonth ? obj.closingYearMonth : '' // 決算年月
    this.brokerName = obj.brokerName ? obj.brokerName : '' // 仲介業者名
    this.mediateProprietyKBN = obj.mediateProprietyKBN ? obj.mediateProprietyKBN : '' // 媒介可否区分
  }
}
