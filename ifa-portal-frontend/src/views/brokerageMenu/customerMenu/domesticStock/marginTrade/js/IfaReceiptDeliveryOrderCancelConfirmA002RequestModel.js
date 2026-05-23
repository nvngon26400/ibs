import Logger from '@/utils/ifaLog.js'
export class IfaReceiptDeliveryOrderCancelConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
    this.market = obj.market ? obj.market : '' // 市場
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.unTradeQuantity = obj.unTradeQuantity ? obj.unTradeQuantity : '' // 数量
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.marginTradeTypeText = obj.marginTradeTypeText ? obj.marginTradeTypeText : '' // 信用取引区分
    this.paymentDeadline = obj.paymentDeadline ? obj.paymentDeadline : '' // 弁済期限
    this.newPrice = obj.newPrice ? obj.newPrice : '' // 新規単価
    this.newTradeDate = obj.newTradeDate ? obj.newTradeDate : '' // 新規建日
    this.newPositionNumber = obj.newPositionNumber ? obj.newPositionNumber : '' // 建玉No
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.paymentDeadlineDate = obj.paymentDeadlineDate ? obj.paymentDeadlineDate : '' // 弁済期限年月日数
    this.dateKbn = obj.dateKbn ? obj.dateKbn : '' // 年月日区分
    this.comIdR = obj.comIdR ? obj.comIdR : '' // 手数料区分（採用）
    this.orderDayTime = obj.orderDayTime ? obj.orderDayTime : '' // 受注日時
  }
}
