export class IfaDepositBalanceDetailFormModel {
  constructor() {
    this.screenId = 'SUB0202_010201-04'
    this.screenTitle = '預り残高詳細'

    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.commodityAccountDeposit = '' // 商品口座預り
    this.domesticStockDepositList = [{ // 国内株式預り明細一覧
      holdingStock: '', // 国内株式預り明細一覧.保有株数
      getPriceReferencePrice: '', // 国内株式預り明細一覧.取得単価/参考単価
      currentPrice: '', // 国内株式預り明細一覧.現在値
      acquireAmountReferenceAmount: '', // 国内株式預り明細一覧.取得金額/参考金額
      valuation: '', // 国内株式預り明細一覧.評価額
      valuationProfitAndLoss: '', // 国内株式預り明細一覧.評価損益
      depositDate: '', // 国内株式預り明細一覧.預り年月日
      protectionSubstitutionClass: '', // 国内株式預り明細一覧.保護／代用区分
      storageReason: '' // 国内株式預り明細一覧.入庫理由
    }]
    this.mutualFundLotDepositList = [{ // 投資信託口数預り明細一覧」
      unitVolume: '', // 投資信託口数預り明細一覧.保有口数
      getPriceReferencePrice: '', // 投資信託口数預り明細一覧.取得単価/参考単価
      currentPrice: '', // 投資信託口数預り明細一覧.現在値
      acquireAmountReferenceAmount: '', // 投資信託口数預り明細一覧.取得金額/参考金額
      valuation: '', // 投資信託口数預り明細一覧.評価額
      valuationProfitAndLoss: '', // 投資信託口数預り明細一覧.評価損益
      protectionSubstitutionClass: '', // 投資信託口数預り明細一覧.保護／代用区分
      storageReason: '' // 投資信託口数預り明細一覧.入庫理由
    }]
    this.mutualFundAmountDepositList = [{ // 投資信託金額預り明細一覧」
      unitVolume: '', // 投資信託金額預り明細一覧.保有口数
      getPriceReferencePrice: '', // 投資信託金額預り明細一覧.取得単価/参考単価
      currentPrice: '', // 投資信託金額預り明細一覧.現在値
      depositDetailPriceUnit: '', // 投資信託金額預り明細一覧.基準価格単位
      acquireAmountReferenceAmount: '', // 投資信託金額預り明細一覧.取得金額/参考金額
      valuation: '', // 投資信託金額預り明細一覧.評価額
      valuationProfitAndLoss: '', // 投資信託金額預り明細一覧.評価損益
      protectionSubstitutionClass: '' // 投資信託金額預り明細一覧.保護／代用区分
    }]
    this.domesticClaimDepositList = [{ // 「国内債券預り明細一覧」
      holdingFaceValue: '', // 国内債券預り明細一覧.保有額面
      compoundInterest: '', // 国内債券預り明細一覧.利率(%)
      redemptionDate: '', // 国内債券預り明細一覧.償還日
      interestPaymentDate: '', // 国内債券預り明細一覧.利払日
      exchangeRate: '', // 国内債券預り明細一覧.参考為替
      getPriceReferencePrice: '', // 国内債券預り明細一覧.取得単価/参考単価
      contractExchange: '', // 国内債券預り明細一覧.約定為替
      contractAmount: '', // 国内債券預り明細一覧.約定金額
      depositDate: '', // 国内債券預り明細一覧.預り年月日
      valuation: '', // 国内債券預り明細一覧.評価額
      protectionSubstitutionClass: '', // 国内債券預り明細一覧.保護／代用区分
      storageReason: '' // 国内債券預り明細一覧.入庫理由
    }]
    this.foreignClaimDepositList = [{ // 「外国債券預り明細一覧」
      holdingFaceValue: '', // 外国債券預り明細一覧.保有額面
      compoundInterest: '', // 外国債券預り明細一覧.利率(%)
      redemptionDate: '', // 外国債券預り明細一覧.償還日
      interestPaymentDate: '', // 外国債券預り明細一覧.利払日
      exchangeRate: '', // 外国債券預り明細一覧.参考為替
      unitPrice: '', // 外国債券預り明細一覧.買付単価
      contractExchange: '', // 外国債券預り明細一覧.約定為替
      contractAmount: '', // 外国債券預り明細一覧.約定金額
      depositDate: '', // 外国債券預り明細一覧.預り年月日
      valuationJpAmount: '', // 外国債券預り明細一覧.評価額（円）
      protectionSubstitutionClass: '', // 外国債券預り明細一覧.保護／代用区分
      storageReason: '', // 外国債券預り明細一覧.入庫理由
      currencyCode: '' // 通貨コード
    }]
    this.foreignClaimForeignCurrencyDepositList = [{ // 「外国債券預り（外貨建）明細一覧」
      holdingFaceValue: '', // 外国債券預り明細一覧.保有額面
      exchangeRate: '', // 外国債券預り明細一覧.参考為替
      unitPrice: '', // 外国債券預り明細一覧.買付単価
      contractExchange: '', // 外国債券預り明細一覧.約定為替
      contractAmount: '', // 外国債券預り明細一覧.約定金額
      valuationJpAmount: '', // 外国債券預り明細一覧.評価額（円）
      protectionSubstitutionClass: '' // 外国債券預り明細一覧.保護／代用区分
    }]
    this.foreignStockDepositList = [{ // 「外国株式預り明細一覧」
      holdingStock: '', // 外国株式預り明細一覧.保有株数
      openPrice: '', // 外国株式預り明細一覧.取得単価
      currentPrice: '', // 外国株式預り明細一覧.現在値
      getAmount: '', // 外国株式預り明細一覧.取得金額
      valuation: '', // 外国株式預り明細一覧.評価額
      foreignProfitAndLoss: '', // 外国株式預り明細一覧.外貨建評価損益
      fxValuationRate: '', // 外国株式預り明細一覧.評価為替レート
      valuationJpAmount: '', // 外国株式預り明細一覧.評価額（円貨）
      yenProfitLoss: '', // 外国株式預り明細一覧.評価損益（円貨）
      protectionSubstitutionClass: '', // 外国株式預り明細一覧.保護／代用区分
      currencyCode: '' // 通貨コード
    }]
    this.foreignMmfDepositList = [{ // 「「外貨建MMF預り明細一覧」
      unitVolume: '', // 外貨建MMF預り明細一覧.保有口数
      foreignValuation: '', // 外貨建MMF預り明細一覧.評価額（外貨）
      fxValuationRate: '', // 外貨建MMF預り明細一覧.評価為替レート
      valuation: '', // 外貨建MMF預り明細一覧.評価額（円貨）
      yenProfitLoss: '', // 外貨建MMF預り明細一覧.評価損益（円貨）
      protectionSubstitutionClass: '', // 外貨建MMF預り明細一覧.保護／代用区分
      currencyCode: '' // 通貨コード
    }]
    this.productName = '' // 「商品名」
    this.brandCode = '' // 銘柄コード
    this.brandName = '' // 銘柄名
    this.securityType = '' // 商品区分
    this.kokunaiGaiKbn = '' // 国内外国区分
    this.securityClass1 = '' // 商品種別１
    this.securityClass2 = '' // 商品種別2
    this.companyCode = '' // 会社ｺｰﾄﾞ
    this.rightType = '' // 権利区分
    this.newOldType = '' // 新旧区分
    this.times = '' // 回数
    this.issue1 = '' // 号1
    this.issue2 = '' // 号2
    this.listedCountryCode = '' // 上場国ｺｰﾄﾞ
    this.nonSpecificDepositCategory = '' // 「非特定預り区分」
    this.getAccountCategory = '' // 「取得口座区分」
    this.securityClass = '' // 商品コード
    this.countryCode = '' // 国コード
    this.currencyCode = '' // 通貨コード
    this.depositType = '' // 預り区分
    this.noDetailMsg = '預り残高明細はありません。' // 明細無しメッセージ
  }
}
