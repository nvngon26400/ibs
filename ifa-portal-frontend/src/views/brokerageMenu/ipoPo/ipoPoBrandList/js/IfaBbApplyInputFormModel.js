export class IfaBbApplyInputFormModel {
  constructor() {
    this.titleModel = {
      id: 'SUB0204_01-02_1',
      name: 'BB申込入力'
    }
    // 銘柄コード（半角英数字）
    this.brandCode = ''
    // 銘柄（全角半角）
    this.brand = ''
    // 申込単位
    this.applyUnit = ''
    // 売買単位（数値(整数)）
    this.unit = ''
    // 売買単位区分（半角英数字）
    this.sellBuyUnitType = ''
    // 仮条件（全角半角）
    this.assumeCondition = ''
    // ブックビルディング申込期間
    this.bookBuildingApplyPeriod = ''
    // 抽選日
    this.lotDate = ''
    // ブックビルディング申込期間（開始）（YYYMMDD）
    this.bookBuildingPresentationFrom = ''
    // 発行価格区分
    this.issuePriceType = ''
    // 価格表示（開始）（数値(整数)）
    this.startPriceDisplay = ''
    // 価格表示（刻み）
    this.priceDisplayTick = ''
    // 価格表示（終了）（数値(整数)）
    this.finishPriceDisplay = ''
    // ディスカウント率（開始）（数値(小数)）
    this.startDiscountRate = ''
    // ディスカウント率（刻み）
    this.discountRateTick = ''
    // ディスカウント率（終了）（数値(小数)）
    this.finishDiscountRate = ''
    // 投資家属性プルダウンリスト
    this.investorAttributePullDownList = []
    // 遷移元画面（全角半角）
    this.transitionSourceScreen = ''
    // 部店コード
    this.butenCode = ''
    // 口座番号
    this.accountNumber = ''
    // 顧客名
    this.customerName = ''
    // 口座開設年月日
    this.openAcctDate = ''
    // 投資方針
    this.investmentPolicyType = ''
    // 職業
    this.profession = ''
    // 投資経験年数(株式現物)
    this.stockSpotInvestExperienceYears = ''
    // 勤務先名
    this.officeName = ''
    // 金融資産
    this.financialAssets = ''
    // コンプラランク
    this.complianceRank = ''
    // 電子交付同意
    this.edelivAgreementDate = ''
    // 法人区分
    this.corporationType = ''
    // 金融資産区分
    this.financialAssetsType = ''
    // 本年の年間裁量配分割当回数
    this.discretionAlloCount = ''
    // 本年の年間裁量配分可能回数
    this.discretionAllocateAbleTimes = ''
    // 預り資産額
    this.depositAssetAmount = ''
    // 銘柄プルダウンリスト
    this.brandPullDownList = []
    // 銘柄コードリスト
    this.brandCodeList = []
    // 買付余力
    this.buyingPower = ''
    // 投資家属性順序リスト
    this.investorAttributeValueList = []
    // 目論見書閲覧
    this.readTime = ''
    // 数量
    this.quantity = ''
    // 成行
    this.marketOrder = ''
    // 価格
    this.price = ''
    // ディスカウント率
    this.discountRate = ''
    // 投資家属性順序
    this.investorAttributeValue = ''
    // 投資家属性名
    this.investorAttributeName = ''
    // 備考
    this.bbRemark = ''
    // 裁量希望数量
    this.discretionQuantity = ''
    // 選定理由
    this.selectReason = ''
    // 銘柄名
    this.brandName = ''
    // 顧客名（漢字）
    this.customerNameKanji = ''
    // 顧客名（カナ）
    this.customerNameKana = ''
    // コンプラランクチェック.メッセージ
    this.complianceRankCheckMsg = ''
    // コンプラランクチェック.チェックボックス文言
    this.complianceRankCheckChkBoxLabel = ''
    // 裁量配分割当回数5回以上ワーニングメッセージ
    this.discretionAllocateTimesOverFiveWarningMsg = ''
    // 金融資産3000万円未満の裁量申込ワーニングメッセージ
    this.financialAssetLessThanThirtyMillionYenDiscretionApplyWarningMsg = ''
    // 注意情報レベル
    this.noticeInfoLevel = ''
    // 勧誘区分
    this.kanyuKbn = ''
    // 受注方法
    this.receiveMethod = ''
    this.periodUpdateMessage = ''
    this.priceUpdateMessage = ''
    this.marketOrderStrikePrice = ''
    this.customerCode = ''
  }
}
