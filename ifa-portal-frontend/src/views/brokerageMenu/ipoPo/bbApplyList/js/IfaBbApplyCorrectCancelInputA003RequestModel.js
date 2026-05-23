import Logger from '@/utils/ifaLog.js'
export class IfaBbApplyCorrectCancelInputA003RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.quantityBeforeCorrect = obj.quantityBeforeCorrect ? obj.quantityBeforeCorrect : '' // 訂正前数量
    this.marketOrderBeforeCorrect = obj.marketOrderBeforeCorrect ? obj.marketOrderBeforeCorrect : '' // 訂正前成行
    this.priceBeforeCorrect = obj.priceBeforeCorrect ? obj.priceBeforeCorrect : '' // 訂正前価格
    this.discountRateBeforeCorrect = obj.discountRateBeforeCorrect ? obj.discountRateBeforeCorrect : '' // 訂正前ディスカウント率
    this.investorAttributeValueBeforeCorrect = obj.investorAttributeValueBeforeCorrect ? obj.investorAttributeValueBeforeCorrect : '' // 訂正前投資家属性順序
    this.investorAttributeNameBeforeCorrect = obj.investorAttributeNameBeforeCorrect ? obj.investorAttributeNameBeforeCorrect : '' // 訂正前投資家属性名
    this.discretionQuantityBeforeCorrect = obj.discretionQuantityBeforeCorrect ? obj.discretionQuantityBeforeCorrect : '' // 訂正前裁量希望数量
    this.selectReasonBeforeCorrect = obj.selectReasonBeforeCorrect ? obj.selectReasonBeforeCorrect : '' // 訂正前裁量選定理由
    this.bbRemarkBeforeCorrect = obj.bbRemarkBeforeCorrect ? obj.bbRemarkBeforeCorrect : '' // 訂正前備考
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.sellBuyUnitType = obj.sellBuyUnitType ? obj.sellBuyUnitType : '' // 売買単位区分
    this.issuePriceType = obj.issuePriceType ? obj.issuePriceType : '' // 発行価格区分
    this.customerNameKanji = obj.customerName ? obj.customerName : '' // 顧客名（漢字）
    this.customerNameKana = obj.customerNameKana ? obj.customerNameKana : '' // 顧客名（カナ）
    this.customerCode = obj.customerCode ? obj.customerCode : ''
  }
}
