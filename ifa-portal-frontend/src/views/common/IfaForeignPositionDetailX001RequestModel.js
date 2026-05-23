import Logger from '@/utils/ifaLog.js'
export class IfaForeignPositionDetailX001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.countryCode = obj.countryCode ? obj.countryCode : '' // 国コード
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.openTradeKbn = obj.openTradeKbn ? obj.openTradeKbn : '' // 新規売買区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.depositTypeName = obj.depositTypeName ? obj.depositTypeName : '' // 預り区分
    this.individualBatchJudge = obj.individualBatchJudge ? obj.individualBatchJudge : '' // 個別一括判定
    this.kokunaiyakuteibi = obj.kokunaiyakuteibi ? obj.kokunaiyakuteibi : '' // 国内約定日
    this.genchiyakuteibi = obj.genchiyakuteibi ? obj.genchiyakuteibi : '' // 現地約定日
    this.sinkidatetanka = obj.sinkidatetanka ? obj.sinkidatetanka : '' // 新規建単価（外貨）
    this.jpyAmountNewPositionPrice = obj.jpyAmountNewPositionPrice ? obj.jpyAmountNewPositionPrice : '' // 新規建単価（円貨）
  }
}
