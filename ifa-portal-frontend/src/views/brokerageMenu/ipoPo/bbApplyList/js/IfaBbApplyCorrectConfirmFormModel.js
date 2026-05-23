export class IfaBbApplyCorrectConfirmFormModel {
  constructor() {
    // 部店コード（半角英数字）
    this.butenCode = ''
    // 口座番号（数字）
    this.accountNumber = ''
    // 銘柄コード（半角英数字）
    this.brandCode = ''
    // ブックビルディング申込期間（開始）（全角半角）
    this.bookBuildingPresentationFrom = ''
    // 数量（数値(整数)）
    this.quantity = ''
    // 申込日時
    this.bbCreateDate = ''
    // 成行
    this.marketOrder = ''
    // 価格（数値(小数)）
    this.price = ''
    // ディスカウント率（数値(小数)）
    this.discountRate = ''
    // 投資家属性順序（全角半角）
    this.investorAttributeValue = ''
    // 投資家属性名
    this.investorAttributeName = ''
    // 備考（全角半角）
    this.bbRemark = ''
    // 裁量希望数量
    this.discretionQuantity = ''
    // 選定理由
    this.selectReason = ''
    // 顧客名（漢字）（全角半角）
    this.customerNameKanji = ''
    // 顧客名（カナ）（全角半角）
    this.customerNameKana = ''
    // 銘柄名（全角半角）
    this.brandName = ''
    // 売買単位区分（全角半角）
    this.sellBuyUnitType = ''
    // 発行価格区分
    this.issuePriceType = ''
    // 勧誘区分（全角半角）
    this.kanyuKbn = ''
    // 受注方法
    this.receiveMethod = ''
    // コンプラランクチェック.メッセージ
    this.complianceRankCheckMsg = ''
    // コンプラランクチェック.チェックボックス文言
    this.complianceRankCheckChkBoxLabel = ''
    // アラート内容確認.コンプラランクチェック確認
    this.invitationCheck = ''
    // 裁量配分割当回数5回以上ワーニングメッセージ
    this.discretionAllocateTimesOverFiveWarningMsg = ''
    // 金融資産3000万円未満の裁量申込ワーニングメッセージ
    this.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg = ''
    // 裁量配分割当回数5回以上ワーニング確認
    this.discretionAllocateTimesOverFive = ''
    // 金融資産3000万円未満の裁量申込ワーニング確認
    this.financialAssetLessThanThirtyMillionYenDiscretionApply = ''
    // 注意情報レベル
    this.noticeInfoLevel = ''
    // 訂正前数量
    this.quantityBeforeCorrect = ''
    // 訂正前成行
    this.marketOrderBeforeCorrect = ''
    // 訂正前価格
    this.priceBeforeCorrect = ''
    // 訂正前ディスカント率
    this.discountRateBeforeCorrect = ''
    // 訂正前投資家属性順序
    this.investorAttributeValueBeforeCorrect = ''
    // 訂正前投資家属性名
    this.investorAttributeNameBeforeCorrect = ''
    // 訂正前裁量希望数量
    this.discretionQuantityBeforeCorrect = ''
    // 訂正前裁量選定理由
    this.selectReasonBeforeCorrect = ''
    // 訂正前備考
    this.bbRemarkBeforeCorrect = ''
    this.warningMessage = ''
    this.corporationType = ''
    this.customerCode = ''
  }
}
