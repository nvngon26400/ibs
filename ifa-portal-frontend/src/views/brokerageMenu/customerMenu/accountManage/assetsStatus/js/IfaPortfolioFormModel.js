export class IfaPortfolioFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0101-01',
      name: 'ポートフォリオ'
    }
    this.createTime = '' // 作成日時 (システム共通情報.システム日時)
    this.noDetailMsg = '残高明細はありません。' // 残高なしメッセージ
    this.jobRunningMsg = '集計処理中です。集計処理後表示します。' // ジョブ稼働中メッセージ

    // 資産状況サマリリスト: 初期値が空の配列
    this.portfolioSummaryList = [
      // {
      //   securityClass: '', // 資産状況.証券種別
      //   valuation: '', // 資産状況.評価額
      //   profitAndLoss: '', // 資産状況.評価損益
      //   byProductAssetsRatio: '' // 資産状況.資産比率
      // }
    ]
    this.portfolioSummaryValuationTotal = '' // 資産状況サマリ評価額合計
    this.portfolioSummaryProfitAndLossTotal = '' // 資産状況サマリ評価損益合計

    // 国内株式リスト: 初期値が空の配列
    this.holdingSecurityDomesticStock = [
      // {
      //   brandCode: '', // 国内株式.銘柄コード
      //   brandName: '', // 国内株式.銘柄名
      //   depositType: '', // 国内株式.預り区分（全角半角）
      //   contractStandardDeposit: '', // 国内株式. 約定基準残高 ・ 保有株数
      //   openPrice: '', // 国内株式.取得単価 ・ 平均取得価額「円」
      //   price: '', // 国内株式.時価・ 前日終値「円」
      //   valuation: '', // 国内株式.評価額（円貨）・ 評価額「円」
      //   profitAndLoss: '' // 国内株式.評価損益「円」
      // }
    ]
    this.domesticStockProfitAndLossTotal = '' // 国内株式評価損益合計 ・ 評価損益合計

    // 国内債券リスト: 初期値が空の配列
    this.holdingSecurityListDomesticClaim = [
      // {
      //   brandCode: '', // 国内債券.銘柄コード
      //   brandName: '', // 国内債券.銘柄名
      //   depositType: '', // 国内債券.預り区分名
      //   compoundInterest: '', // 国内債券.利率(%)
      //   currency: '', // 国内債券.通貨
      //   fxRate: '', // 国内債券.為替レート
      //   redemptionDate: '', // 国内債券.償還日
      //   interestPaymentDate: '', // 国内債券.利払日
      //   openPrice: '', // 国内債券.取得単価  ・ 平均取得価額「円」
      //   acquireRate: '', // 国内債券.取得レート
      //   price: '', // 国内債券.時価 ・ 前日終値「円」
      //   contractStandardDeposit: '', // 国内債券.約定基準残高 ・ 保有額面
      //   valuation: '', // 国内債券.評価額 ・ 評価額「円」
      //   profitAndLoss: '' // 国内債券.評価損益「円」
      // }
    ]
    this.domesticClaimProfitAndLossTotal = '' // 国内債券評価損益合計

    // 投資信託リスト: 初期値が空の配列
    this.holdingSecurityListMutualFund = [
      // {
      //   brandCode: '', // 投資信託.銘柄コード
      //   brandName: '', // 投資信託.銘柄名
      //   depositType: '', // 投資信託.預り区分名
      //   contractStandardDeposit: '', // 投資信託.約定基準残高・保有口数
      //   openPrice: '', // 投資信託.取得単価・平均取得価額
      //   price: '', // 投資信託.時価・基準価額
      //   valuation: '', // 投資信託.評価額
      //   profitAndLoss: '' // 投資信託.評価損益
      // }
    ]
    this.mutualFundProfitAndLossTotal = '' // 投資信託評価損益合計

    // 外国債券(円建)リスト: 初期値が空の配列
    this.holdingSecurityListForeignClaimYenBase = [
      // {
      //   brandCode: '', // 外国債券(円建).銘柄コード
      //   brandName: '', // 外国債券(円建).銘柄名
      //   depositType: '', // 外国債券(円建).預り区分名
      //   compoundInterest: '', // 外国債券(円建).利率(%)
      //   currency: '', // 外国債券(円建).通貨
      //   fxRate: '', // 外国債券(円建).為替レート・参考為替
      //   redemptionDate: '', // 外国債券(円建).償還日
      //   interestPaymentDate: '', // 外国債券(円建).利払日
      //   openPrice: '', // 外国債券(円建).取得単価・買付単価
      //   contractStandardDeposit: '', // 外国債券(円建).約定基準残高・保有額面
      //   valuation: '', // 外国債券(円建).評価額
      //   profitAndLoss: '' // 外国債券(円建).評価損益
      // }
    ]
    this.foreignClaimYenBaseProfitAndLossTotal = '' // 外国債券(円建)評価損益合計

    // 外国株式リスト: 初期値が空の配列
    this.holdingSecurityListForeignStock = [
      // {
      //   brandCode: '', // 外国株式.銘柄コード
      //   brandName: '', // 外国株式.銘柄名
      //   depositType: '', // 外国株式.預り区分名
      //   contractStandardDeposit: '', // 外国株式.約定基準残高・保有株数
      //   currency: '', // 外国株式.通貨
      //   fxRate: '', // 外国株式.為替レート・参考為替
      //   openPrice: '', // 外国株式.取得単価
      //   marketValueForeign: '', // 外国株式.時価
      //   valuation: '', // 外国株式.評価額
      //   profitAndLoss: '' // 外国株式.評価損益
      // }
    ]
    this.foreignStockProfitAndLossTotal = '' // 外国株式評価損益合計

    // 外貨建MMFリスト: 初期値が空の配列
    this.holdingSecurityListForeignMmf = [
      // {
      //   brandCode: '', // 外貨建MMF.銘柄コード
      //   brandName: '', // 外貨建MMF.銘柄名
      //   contractStandardDeposit: '', // 外貨建MMF.約定基準残高・保有口数
      //   currency: '', // 外貨建MMF.通貨
      //   fxRate: '', // 外貨建MMF.為替レート・参考為替
      //   valuation: '', // 外貨建MMF.評価額
      //   profitAndLoss: '' // 外貨建MMF.評価損益
      // }
    ]
    this.foreignMmfProfitAndLossTotal = '' // 外貨建MMF評価損益合計

    // 外国債券(外貨建)リスト: 初期値が空の配列
    this.holdingSecurityListForeignClaimForeign = [
      // {
      //   brandCode: '', // 外国債券(外貨建).銘柄コード
      //   brandName: '', // 外国債券(外貨建).銘柄名
      //   contractStandardDeposit: '', // 外国債券(外貨建).約定基準残高
      //   openPrice: '', // 外国債券(外貨建).取得単価
      //   currency: '', // 外国債券(外貨建).通貨
      //   fxRate: '', // 外国債券(外貨建).為替レート
      //   valuation: '', // 外国債券(外貨建).評価額
      //   profitAndLoss: '' // 外国債券(外貨建).評価損益
      // }
    ]
    this.foreignClaimForeignProfitAndLossTotal = '' // 外国債券(外貨建)評価損益合計

    // 外国債券(外貨建仕組債)リスト: 初期値が空の配列
    this.holdingSecurityListForeignClaimForeignStructuredBond = [
      // {
      //   brandCode: '', // 外国債券(外貨建仕組債).銘柄コード
      //   brandName: '', // 外国債券(外貨建仕組債).銘柄名
      //   contractStandardDeposit: '', // 外国債券(外貨建仕組債).約定基準残高
      //   openPrice: '', // 外国債券(外貨建仕組債).取得単価
      //   currency: '', // 外国債券(外貨建仕組債).通貨
      //   fxRate: '', // 外国債券(外貨建仕組債).為替レート
      //   valuation: '', // 外国債券(外貨建仕組債).評価額
      //   profitAndLoss: '' // 外国債券(外貨建仕組債).評価損益
      // }
    ]
    this.foreignClaimForeignStructuredBondProfitAndLossTotal = '' // 外国債券(外貨建仕組債)評価損益合計

    // 現金リスト: 初期値が空の配列
    this.holdingSecurityListCash = [
      // {
      //   name: '', // 現金.名称・種類
      //   fxRate: '', // 現金.為替レート・参考為替
      //   foreignValuation: '', // 現金.評価額（外貨）・残高「現地通貨」
      //   valuationTotal: '', // 現金.評価額合計・残高「円」
      //   profitAndLoss: '' // 現金.評価損益
      // }
    ]
    // SBIラップ口座現金リスト: 初期値が空の配列
    this.holdingSecurityListSbiRapAccountCash = [
      // {
      //   securityClassCode: '', // SBIラップ口座現金.証券種別コード
      //   fxRate: '', // SBIラップ口座現金.為替レート
      //   foreignValuation: '', // SBIラップ口座現金.評価額（外貨）
      //   valuation: '', // SBIラップ口座現金.評価額（円貨）
      //   profitAndLoss: '' // SBIラップ口座現金.評価損益
      // }
    ]
    // 外貨預金リスト: 初期値が空の配列
    this.holdingSecurityListForeignDeposit = [
      // {
      //   currency: '', // 外貨預金.通貨
      //   fxRate: '', // 外貨預金.為替レート
      //   foreignValuation: '', // 外貨預金.評価額（外貨）
      //   valuation: '', // 外貨預金.評価額（円貨）
      //   profitAndLoss: '' // 外貨預金.評価損益
      // }
    ]
    this.cashProfitAndLossTotal = '' // 現金評価損益合計

    // STリスト(セキュリティ・トークン): 初期値が空の配列
    this.holdingSecurityListSecurityToken = [
      // {
      //   brandCode: '', // ST.銘柄コード
      //   brandName: '', // ST.銘柄名
      //   depositType: '', // ST.預り区分名
      //   contractStandardDeposit: '', // ST.約定基準残高・保有口数
      //   openPrice: '', // ST.取得単価・平均取得価額
      //   price: '', // ST.時価・現在値
      //   valuation: '', // ST.評価額
      //   profitAndLoss: '' // ST.評価損益
      // }
    ]
    this.securityTokenProfitAndLossTotal = '' // ST評価損益合計

    this.domesticMarginActualGrntRate = '' // 維持率（国内信用）
    // 信用建玉リスト: 初期値が空の配列
    this.holdingSecurityListMarginPosition = [
      // {
      //   brandCode: '', // 信用建玉.銘柄コード
      //   brandName: '', // 信用建玉.銘柄名
      //   tradeTypeName: '', // 信用建玉.取引種別名
      //   designationDealtClassification: '', // 信用建玉.指定扱区分
      //   lastTradeDate: '', // 信用建玉.返済期限
      //   contractStandardDeposit: '', // 信用建玉.約定基準残高・建株数
      //   openPrice: '', // 信用建玉.取得単価・建単価
      //   price: '', // 信用建玉.時価
      //   valuation: '', // 信用建玉.評価額
      //   profitAndLoss: '' // 信用建玉.評価損益
      // }
    ]
    this.marginPositionProfitAndLossTotal = '' // 信用建玉評価損益合計

    this.americaMarginActualGrntRate = '' // 維持率（米株信用）
    // 米株信用建玉リスト: 初期値が空の配列
    this.holdingSecurityListUsStockMarginPositionList = [
      // {
      //   brandCode: '', // 米株信用建玉.銘柄コード
      //   brandName: '', // 米株信用建玉.銘柄名
      //   tradeTypeName: '', // 米株信用建玉.取引種別名
      //   designationDealtClassification: '', // 米株信用建玉.指定扱区分
      //   lastTradeDate: '', // 米株信用建玉.返済期限
      //   contractStandardDeposit: '', // 米株信用建玉.約定基準残高
      //   currency: '', // 米株信用建玉.通貨
      //   fxRate: '', // 米株信用建玉.為替レート
      //   openPrice: '', // 米株信用建玉.取得単価
      //   price: '', // 米株信用建玉.時価
      //   valuation: '', // 米株信用建玉.評価額
      //   profitAndLoss: '' // 米株信用建玉.評価損益
      // }
    ]
    this.usStockMarginPositionProfitAndLossTotal = '' // 米株信用建玉評価損益合計

    // 投資信託トータルリターンリスト: 初期値が空の配列
    this.holdingSecurityListMutualFundTotalReturnList = [
      // {
      //   holdingStatus: '', // 投資信託トータルリターン.保有状況
      //   nriCd: '', // 投資信託トータルリターン.NRIコード
      //   fundName: '', // 投資信託トータルリターン.ファンド名
      //   nonSpecificDepositCategory: '', // 投資信託トータルリターン.非特定預り区分
      //   depositTransferMarketValueToday: '', // 投資信託トータルリターン.預り移送_時価評価金額（当日）
      //   amountSellRedemptionDeliverOutTotal: '', // 投資信託トータルリターン.金額(売却・償還・出庫)合計
      //   couponRevenueTotal: '', // 投資信託トータルリターン.利金収益金合計
      //   amountBuyReinvestSubscriptDeliverInTotal: '', // 投資信託トータルリターン.金額(買付・再投資・募集・入庫)合計
      //   totalReturnYen: '', // 投資信託トータルリターン.トータルリターン「円」
      //   totalReturnRate: '' // 投資信託トータルリターン.トータルリターン（率）
      // }
    ]
    this.mutualFundTotalReturnValuationTotal = '' // 投資信託トータルリターン評価金額「円」
    this.sellPriceTotal = '' // 投資信託トータルリターン売却金額「円」
    this.dividendTotal = '' // 投資信託トータルリターン分配金額「円」
    this.buyPriceTotal = '' // 投資信託トータルリターン買付金額「円」合計
    this.totalReturnYenTotal = '' // 投資信託トータルリターントータルリターン「円」合計
    this.totalReturnRateTotal = '' // 投資信託トータルリターントータルリターン「率」
    this.jobStatus = '' // バッチジョブステータス
  }
}
