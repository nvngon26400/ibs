import Logger from '@/utils/ifaLog.js'
export class IfaBbApplyCorrectCancelInputA005RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.marketOrder = obj.marketOrder ? obj.marketOrder : '' // 成行
    this.price = obj.price ? obj.price : '' // 価格
    this.discountRate = obj.discountRate ? obj.discountRate : '' // ディスカウント率
    this.investorAttributeValue = obj.investorAttributeValue ? obj.investorAttributeValue : '' // 投資家属性順序
    this.investorAttributeName = obj.investorAttributeName ? obj.investorAttributeName : '' // 投資家属性名
    this.bbRemark = obj.bbRemark ? obj.bbRemark : '' // 備考
    this.discretionQuantity = obj.discretionQuantity ? obj.discretionQuantity : '' // 裁量希望数量
    this.selectReason = obj.selectReason ? obj.selectReason : '' // 選定理由
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveMethod = obj.receiveMethod ? obj.receiveMethod : '' // 受付方法
  }
}
