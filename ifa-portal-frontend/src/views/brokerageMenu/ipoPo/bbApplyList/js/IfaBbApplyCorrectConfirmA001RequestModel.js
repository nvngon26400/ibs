import Logger from '@/utils/ifaLog.js'
export class IfaBbApplyCorrectConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.butenCode = obj.butenCode ? obj.butenCode : '' // 部店コード
    this.accountNumber = obj.accountNumber ? obj.accountNumber : '' // 口座番号
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.bookBuildingPresentationFrom = obj.bookBuildingPresentationFrom ? obj.bookBuildingPresentationFrom : '' // ブックビルディング申込期間（開始）
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.bbCreateDate = obj.bbCreateDate ? obj.bbCreateDate : '' // 申込日時
    this.marketOrder = obj.marketOrder ? obj.marketOrder : '' // 成行
    this.price = obj.price ? obj.price : '' // 価格
    this.discountRate = obj.discountRate ? obj.discountRate : '' // ディスカウント率
    this.investorAttributeValue = obj.investorAttributeValue ? obj.investorAttributeValue : '' // 投資家属性順序
    this.investorAttributeName = obj.investorAttributeName ? obj.investorAttributeName : '' // 投資家属性名
    this.bbRemark = obj.bbRemark ? obj.bbRemark : '' // 備考
    this.discretionQuantity = obj.discretionQuantity ? obj.discretionQuantity : '' // 裁量希望数量
    this.selectReason = obj.selectReason ? obj.selectReason : '' // 選定理由
    this.customerNameKanji = obj.customerNameKanji ? obj.customerNameKanji : '' // 顧客名（漢字）
    this.customerNameKana = obj.customerNameKana ? obj.customerNameKana : '' // 顧客名（カナ）
    this.brandName = obj.brandName ? obj.brandName : '' // 銘柄名
    this.sellBuyUnitType = obj.sellBuyUnitType ? obj.sellBuyUnitType : '' // 売買単位区分
    this.issuePriceType = obj.issuePriceType ? obj.issuePriceType : '' // 発行価格区分
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveMethod = obj.receiveMethod ? obj.receiveMethod : '' // 受注方法
    this.complianceRankCheckMsg = obj.complianceRankCheckMsg ? obj.complianceRankCheckMsg : '' // コンプラランクチェック.メッセージ
    this.complianceRankCheckChkBoxLabel = obj.complianceRankCheckChkBoxLabel ? obj.complianceRankCheckChkBoxLabel : '' // コンプラランクチェック.チェックボックス文言
    this.invitationCheck = obj.invitationCheck ? obj.invitationCheck : '' // アラート内容確認.コンプラランクチェック確認
    this.discretionAllocateTimesOverFiveWarningMsg = obj.discretionAllocateTimesOverFiveWarningMsg ? obj.discretionAllocateTimesOverFiveWarningMsg : '' // 裁量配分割当回数5回以上ワーニングメッセージ
    this.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg = obj.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg ? obj.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg : '' // 金融資産3000万円未満の裁量申込ワーニングメッセージ
    this.discretionAllocateTimesOverFive = obj.discretionAllocateTimesOverFive ? obj.discretionAllocateTimesOverFive : '' // 裁量配分割当回数5回以上ワーニング確認
    this.financialAssetLessThanThirtyMillionYenDiscretionApply = obj.financialAssetLessThanThirtyMillionYenDiscretionApply ? obj.financialAssetLessThanThirtyMillionYenDiscretionApply : '' // 金融資産3000万円未満の裁量申込ワーニング確認
    this.noticeInfoLevel = obj.noticeInfoLevel ? obj.noticeInfoLevel : '' // 注意情報レベル
    this.quantityBeforeCorrect = obj.quantityBeforeCorrect ? obj.quantityBeforeCorrect : '' // 訂正前数量
    this.marketOrderBeforeCorrect = obj.marketOrderBeforeCorrect ? obj.marketOrderBeforeCorrect : '' // 訂正前成行
    this.priceBeforeCorrect = obj.priceBeforeCorrect ? obj.priceBeforeCorrect : '' // 訂正前価格
    this.discountRateBeforeCorrect = obj.discountRateBeforeCorrect ? obj.discountRateBeforeCorrect : '' // 訂正前ディスカント率
    this.investorAttributeValueBeforeCorrect = obj.investorAttributeValueBeforeCorrect ? obj.investorAttributeValueBeforeCorrect : '' // 訂正前投資家属性順序
    this.investorAttributeNameBeforeCorrect = obj.investorAttributeNameBeforeCorrect ? obj.investorAttributeNameBeforeCorrect : '' // 訂正前投資家属性名
    this.discretionQuantityBeforeCorrect = obj.discretionQuantityBeforeCorrect ? obj.discretionQuantityBeforeCorrect : '' // 訂正前裁量希望数量
    this.selectReasonBeforeCorrect = obj.selectReasonBeforeCorrect ? obj.selectReasonBeforeCorrect : '' // 訂正前裁量選定理由
    this.bbRemarkBeforeCorrect = obj.bbRemarkBeforeCorrect ? obj.bbRemarkBeforeCorrect : '' // 訂正前備考
    this.customerCode = obj.customerCode ? obj.customerCode : ''
  }
}
