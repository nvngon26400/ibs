import Logger from '@/utils/ifaLog.js'
export class IfaIfaFxOrderInputA008RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.currencyCode = obj.currencyCode ? obj.currencyCode : '' // 通貨コード
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.saleMethod = obj.saleMethod ? obj.saleMethod : '' // 売却方法
    this.orderAmount = obj.orderAmount ? obj.orderAmount : '' // 為替注文金額
    this.warningThreshold = obj.warningThreshold ? obj.warningThreshold : '' // 注文ワーニングしきい値
    this.quantityDesignationMethod = obj.quantityDesignationMethod ? obj.quantityDesignationMethod : '' // 数量の指定方法
    this.foreignVolume = obj.foreignVolume ? obj.foreignVolume : '' // 数量入力（外貨）
    this.approximateForeignCount = obj.approximateForeignCount ? obj.approximateForeignCount : '' // 概算外貨数量
    this.exchangeGroup = obj.exchangeGroup ? obj.exchangeGroup : '' // 為替グループ
  }
}
