export class IfaBbApplyCorrectCompleteFormModel {
  constructor() {
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店コード
    this.accountNumber = '' // 口座番号
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.quantityBeforeCorrect = '' // 訂正前数量
    this.marketOrderBeforeCorrect = '' // 訂正前成行
    this.priceBeforeCorrect = '' // 訂正前価格
    this.discountRateBeforeCorrect = '' // 訂正前ディスカント率
    this.investorAttributeValueBeforeCorrect = '' // 訂正前投資家属性順序
    this.investorAttributeNameBeforeCorrect = '' // 訂正前投資家属性名
    this.discretionQuantityBeforeCorrect = '' // 訂正前裁量希望数量
    this.selectReasonBeforeCorrect = '' // 訂正前裁量選定理由
    this.bbRemarkBeforeCorrect = '' // 訂正前備考
    this.marketOrder = '' // 成行
    this.price = '' // 価格（数値(小数)）
    this.discountRate = '' // ディスカウント率（数値(小数)）
    this.investorAttributeValue = '' // 投資家属性順序（全角半角）
    this.investorAttributeName = '' // 投資家属性名
    this.bbRemark = '' // 備考（全角半角）
    this.discretionQuantity = '' // 裁量希望数量
    this.selectReason = '' // 選定理由
    this.quantity = '' // 数量（数値(整数)）
    this.kanyuKbn = '' // 勧誘区分
    this.orderMethod = '' // 受注方法
    this.bbRemark = '' // 備考
    this.complianceRankCheckChkBoxLabel = '' // コンプラランクチェック確認見出し
    this.invitationCheck = '' // コンプラランクチェック確認
    this.discretionAllocateTimesOverFive = '' // 裁量配分割当回数5回以上ワーニング確認
    this.financialAssetLessThanThirtyMillionYenDiscretionApply = '' // 金融資産3000万円未満の裁量申込ワーニング確認
    // 裁量配分割当回数5回以上ワーニングメッセージ
    this.discretionAllocateTimesOverFiveWarningMsg = ''
    // 金融資産3000万円未満の裁量申込ワーニングメッセージ
    this.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg = ''
    this.customerCode = ''
  }
}
