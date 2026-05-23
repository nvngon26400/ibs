export class IfaDomesticMutualFundOrderInputFormModel {
  constructor() {
    this.screenId = 'SUB0202_0401-02_1' // 画面ID
    this.screenTitle = '国内投信注文入力' // 画面タイトル
    this.mutualFundSellBuyType = '' // リクエスト.売買区分（投信）
    this.fundCodeTimes = '' // リクエスト.ファンドコード（回数）
    this.fundCodeIssues = '' // リクエスト.ファンドコード（号）
    this.depositType = '' // リクエスト.預り区分
    this.dispatchId = ''// リクエスト.目論見書チェック区分
    this.brandCode = '' // 銘柄コード
    this.selectAccountType = '' // 口座選択 【初期値】選択口座
    this.selectDepositType = '' // 預り区分固定 【初期値】選択預り区分
    this.depositTypeList = [] // 預り区分リスト
    this.brand = {
      brandName: '', // 銘柄名
      priceUnit: '', // 基準価額単位
      minSalesLot: '', // 販売最低口数
      minSalesUnitAmount: '', // 販売最小単位金額(2回目以降)
      salesUnitLot: '', // 販売単位口数
      salesTradeUnitAmount: '', // 販売売買単位金額(2回目以降)
      cancelTradeUnitLot: '', // 解約売買単位口数
      cancelTradeUnitAmount: '', // 解約売買単位金額(2回目以降)
      deadlines: '', // 締切時刻
      buyMethodSelect: '', // 売却方法選択 【初期値】未選択
      fundType: '', // 銘柄情報.ファンドタイプ
      reInvestmentClassification: '', // 再投資区分
      brandSpecialClassification: '', // 特殊区分
      basePrice: '', // 基準価格
      priceDate: '' // 基準価額日付
    } // 銘柄情報
    this.commRateList = [
      {
        commRateConditions: '', // 手数料率条件n
        commRate: '' // 手数料率n
      }
    ] // 手数料率リストn
    this.switchingFavorableTreatmentRate = '' // 乗換優遇率
    this.handlerIndividualDataExistence = '' // 扱者個別データ有無
    this.switchingFavorableTreatmentLimit = {
      wholeAccountThisMonth: '', // 乗換優遇限度額（総合口座）（今月残）
      wholeAccountNextMonth: '', // 乗換優遇限度額（総合口座）（来月残）
      jrNisaAccountThisMonth: '', // 乗換優遇限度額（ジュニアNISA口座）（今月残）
      jrNisaAccountNextMonth: '' // 乗換優遇限度額（ジュニアNISA口座）（来月残）
    } // 乗換優遇限度額
    this.buyingPower = {
      wholeAccount: '', // 買付余力（総合口座）
      jrNisaAccount: '' // 買付余力（ジュニアNISA口座）
    } // 買付余力
    this.depositInfo = {
      sellAbleLot: '', // 売却可能（口数）
      sellAbelAmount: '', // 売却可能（金額）
      approximateValuation: '', // 概算評価金額
      approximateValuationDate: '' // 概算評価金額日付
    } // 預り情報
    this.nisaBuy = '' // NISA買付可能最大額
    this.distributionReceiveMethodWord = '' // 分配金受取方法文言
    this.point = {
      pointClass: '', // ポイント.ポイント種別
      pointCount: '', // ポイント数
      fixedPeriodPoint: '', // うち期間固定ポイント
      shortestExpirationDate: '', // 最短有効期限
      minUsePointCount: '', // 最低利用ポイント数
      usePointUnit: '', // ポイント.利用ポイント単位
      pointShowAreaFlag: '1', // ポイント表示エリア表示可否
      pointNameDisplayFlag: '', // ポイント名表示可否
      pointCountDisplayFlag: '', // ポイント数表示可否
      fixedPeriodPointDisplayFlag: '', // うち期間固定ポイント表示可否
      shortestExpirationDateDisplayFlag: '', // 最短有効期限表示可否
      pointUseAreaFlag: '' // ポイント利用エリア表示可否
    } // ポイント
    this.tpointBalance = '' // ポイント残高
    this.pointBalanceUnit = '' // ポイント残高単位
    this.fixedPeriodTpoint = '' // 期間固定ポイント残高
    this.periodFixationPointBalanceUnit = '' // 期間固定ポイント残高単位
    this.sharesAvailableForSale = '' // 売却可能（解約（一般））
    this.accountType = '' // 口座固定
    this.tradeCd = '' // 取引種別
    this.transfersPreferentialQuotaApplication = '' // 乗換優遇枠適用 【初期値】未選択
    this.transfersPreferentialQuotaApplicationSelect = '' // 乗換優遇枠適用 選択値
    this.shortTermSaleConfirm = '' // 短期売却確認 【初期値】未選択
    this.shortTermSaleConfirmSelect = ''
    this.preRedemptionSellConfirm = ''
    this.preRedemptionSellConfirmSelect = '' // 償還前売却確認選択 【初期値】未選択
    this.sellDesignatedWord = '' // 売却指定 【初期値】未選択
    this.amount = '' // 金額入力 【初期値】空欄
    this.buyAmountWord = '' // 金額文言（購入（累投））
    this.sellAmountWord = '' // 金額文言（解約（累投））
    this.unit = '' // 口数入力 【初期値】空欄
    this.buyUnitWord = '' // 口数文言（購入（一般））
    this.sellSharesWord = '' // 口数文言（解約（一般）、解約（累投））
    this.useOfPointsSelect = [] // ポイント使用有無 【初期値】使用しない
    this.pointInput = '' // ポイント入力 【初期値】空欄
    this.solicitType = '' // 勧誘区分 【初期値】未選択
    this.receiveOrderType = '' // 受注方法 【初期値】未選択
    this.leverageInvestTrust = '' // レバレッジ投資信託 【初期値】未選択
    this.solicitingTransfers = '' // 乗換勧誘 【初期値】未選択
    this.sameCurrencySameCountryTransfers = '' // 同一通貨/同一国の乗換 【初期値】未選択
    this.intercompanyMutualFundTransferSolicitation = '' // 他社間投信乗換勧誘 【初期値】未選択
    this.distributionReceivingMethod = '' // 分配金受取方法選択 【初期値】指定しない
    this.prospectusIssueMethod = '' // 目論見書の交付方法 【初期値】未選択
    this.conflictOfInterestExplain = '0' // 利益相反可能性の説明 【初期値】未選択
    this.prospectusSupplementaryDocConfirm = '0' // 目論見書補完書面の確認 【初期値】未選択
    this.windowSpaceFundPrecautionsConsent = '0' // 窓空きファンドの注意事項に同意 【初期値】未選択
    this.costExplained = '0' // 費用について説明済 【初期値】未選択
    this.multipleTradeClearlyCommStated = '0' // 複数取引業者での手数料等明示済 【初期値】未選択
    this.tradeType = ''
  }
}
