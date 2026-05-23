import Logger from '@/utils/ifaLog.js'
export class IfaDomesticMutualFundOrderCancelConfirmA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.tradeCd = obj.tradeCd ? obj.tradeCd : '' // 取引種別
    this.amount = obj.amount ? obj.amount : '' // 金額
    this.unit = obj.unit ? obj.unit : '' // 口数
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分
    this.point = obj.point ? obj.point : '' // 利用ポイント
    this.orderDayTime = obj.orderDayTime ? obj.orderDayTime : '' // 受注日時
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号
    this.fundType = obj.fundType ? obj.fundType : '' // ファンドタイプ
    this.fundCodeTimes = obj.fundCodeTimes ? obj.fundCodeTimes : '' // ファンドコード（回数）
    this.fundCodeIssues = obj.fundCodeIssues ? obj.fundCodeIssues : '' // ファンドコード（号）
    this.tradeKbn = obj.tradeKbn ? obj.tradeKbn : '' // 売買区分
    this.norikaeYuguKbn = obj.norikaeYuguKbn ? obj.norikaeYuguKbn : '' // 乗換優遇区分
    this.quoteUnitPrice = obj.quoteUnitPrice ? obj.quoteUnitPrice : '' // 見積単価
    this.contractAmount = obj.contractAmount ? obj.contractAmount : '' // 約定金額
    this.charge = obj.charge ? obj.charge : '' // 手数料/諸費用
    this.consumptionTax = obj.consumptionTax ? obj.consumptionTax : '' // 消費税
    this.yieldTax = obj.yieldTax ? obj.yieldTax : '' // 讓渡益税
    this.settlementAmount = obj.settlementAmount ? obj.settlementAmount : '' // 精算金額
    this.contractDate = obj.contractDate ? obj.contractDate : '' // 約定予定日
    this.deliveryDate = obj.deliveryDate ? obj.deliveryDate : '' // 受渡予定日
    this.orderDate = obj.orderDate ? obj.orderDate : '' // 受注日
    this.orderQuantity = obj.orderQuantity ? obj.orderQuantity : '' // 受注数量
    this.distributionReceiveMethod = obj.distributionReceiveMethod ? obj.distributionReceiveMethod : '' // 分配金受取方法
    this.pointType = obj.pointType ? obj.pointType : '' // ポイント種別
    this.callcenterClassification = obj.callcenterClassification ? obj.callcenterClassification : '' // 受付経路区分
    this.buyCancelMethod = obj.buyCancelMethod ? obj.buyCancelMethod : '' // 購入解約方法
  }
}
