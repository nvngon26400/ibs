import Logger from '@/utils/ifaLog.js'
export class IfaOrderStatusListA002RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.securityType = obj.securityType ? obj.securityType : '' // 商品区分
    this.ecOrderNo = obj.ecOrderNo ? obj.ecOrderNo : '' // EC受注番号（国内株式注文）
    this.hattyuuKbn = obj.hattyuuKbn ? obj.hattyuuKbn : '' // 発注区分（国内株式注文）
    this.domesticMutualFundOrderStatusEcOrderNo = obj.domesticMutualFundOrderStatusEcOrderNo ? obj.domesticMutualFundOrderStatusEcOrderNo : '' // EC受注番号（国内投信注文）
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード（募集注文）
    this.depositType = obj.depositType ? obj.depositType : '' // 預り区分（募集注文）
    this.orderNumber = obj.orderNumber ? obj.orderNumber : '' // 注文番号（外株委託注文）
    this.orderSubNumber = obj.orderSubNumber ? obj.orderSubNumber : '' // 注文SUB番号（外株委託注文）
    this.manageNumber = obj.manageNumber ? obj.manageNumber : '' // 管理番号（店頭取引注文）
    this.orderType = obj.orderType ? obj.orderType : '' // 商品区分（国内投信）
  }
}
