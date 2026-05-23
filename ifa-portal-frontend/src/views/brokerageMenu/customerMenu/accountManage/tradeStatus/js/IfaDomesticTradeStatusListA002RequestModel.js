import Logger from '@/utils/ifaLog.js'
export class IfaDomesticTradeStatusListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.tradeDate = obj.tradeDate ? obj.tradeDate : '' // 約定日
    this.settlementDate = obj.settlementDate ? obj.settlementDate : '' // 受渡予定日
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.tradeClassification = obj.tradeClassification ? obj.tradeClassification : '' // 取引区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.marginTradeClassification = obj.marginTradeClassification ? obj.marginTradeClassification : '' // 信用取引区分
    this.depositType = obj.depositType ? obj.depositType : '' // 非特定預り売買区分
  }
}
