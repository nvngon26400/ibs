import Logger from '@/utils/ifaLog.js'
export class IfaCustomerTradeHistoryA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.customerCode = obj.customerCode ? obj.customerCode : '' // 顧客ID
    this.companyCode = obj.companyCode ? obj.companyCode : '' // 会社コード
    this.times = obj.times ? obj.times : '' // 回数
    this.issue = obj.issue ? obj.issue : '' // 号
    this.securityType = obj.securityType && obj.securityType !== '$NULL' ? obj.securityType : '' // 商品区分
    this.tradeType = obj.tradeType  && obj.tradeType !== '$NULL' ? obj.tradeType : '' // 取引区分
    this.sortOrderItem = obj.sortOrderItem ? obj.sortOrderItem : '' // 並び順指定【項目】
    this.sortOrderProfile = obj.sortOrderProfile ? obj.sortOrderProfile : '' // 並び順指定【属性】
    this.periodFrom = obj.period[0] ? obj.period[0] : '' // 期間指定（From）
    this.periodTo = obj.period[1] ? obj.period[1] : '' // 期間指定（To）
  }
}
