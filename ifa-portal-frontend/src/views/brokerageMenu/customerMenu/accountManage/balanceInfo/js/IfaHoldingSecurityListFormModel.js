export class IfaHoldingSecurityListFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_010201-01',
      name: '保有商品一覧'
    }
    this.accountTypeList = [] // 口座区分リスト
    this.accountClassification = '2' // 口座区分 【初期値】全て
    this.securitySelect = '全て' // 商品選択 【初期値】全て
    this.sellUnableDetailDisplayClassification = '' // 売却不可明細表示区分
    this.getTotalAssessedValueAll = '' // 評価額総合計
    this.getTotalProfitAll = '' // 評価損益総合計
    this.noDetailMsg = '預り明細はありません。' // 保有商品一覧明細なしメッセージ

    // 国内株式
    this.domesticStockList = [
      {
        numberOfDepositedIssues: '', // 国内株式一覧.預り銘柄数
        valuationTotal: '', // 国内株式一覧.評価額合計
        getProfitAll: '', // 国内株式一覧.評価損益合計
        depositBalanceAccountTypeName: '', // 国内株式一覧.口座区分
        depositType: '', // 国内株式一覧.預り区分
        depositDetailList: [ // 「国内株式一覧.預り明細一覧」
          {
            brandCode: '', // 国内株式一覧.預り明細一覧.銘柄コード
            brandName: '', // 国内株式一覧.預り明細一覧.銘柄名
            holdingStock: '', // 国内株式一覧.預り明細一覧.保有株数
            sellingVolume: '', // 国内株式一覧.預り明細一覧.売却注文中
            acquirePriceReferencePrice: '', // 国内株式一覧.預り明細一覧.取得単価/参考単価
            currentPrice: '', // 国内株式一覧.預り明細一覧.現在値
            acquireAmountReferenceAmount: '', // 国内株式一覧.預り明細一覧.取得金額/参考金額
            valuation: '', // 国内株式一覧.預り明細一覧.評価額
            domesticStockListValuation: '', // 国内株式一覧.預り明細一覧.評価損益
            buyDisplayClassification: '', // 国内株式一覧.預り明細一覧.現買表示区分
            saleDisplayClassification: '', // 国内株式一覧.預り明細一覧.現売表示区分
            securityType: '', // 国内株式一覧.預り明細一覧.商品区分
            kokunaiGaiKbn: '', // 国内株式一覧.預り明細一覧.国内外国区分
            securityClass1: '', // 国内株式一覧.預り明細一覧.商品種別１
            securityClass2: '', // 国内株式一覧.預り明細一覧.商品種別2
            companyCode: '', // 国内株式一覧.預り明細一覧.会社ｺｰﾄﾞ
            rightType: '', // 国内株式一覧.預り明細一覧.権利区分
            newOldType: '', // 国内株式一覧.預り明細一覧.新旧区分
            times: '', // 国内株式一覧.預り明細一覧.回数
            issue1: '', // 国内株式一覧.預り明細一覧.号1
            issue2: '', // 国内株式一覧.預り明細一覧.号2
            listedCountryCode: '', // 国内株式一覧.預り明細一覧.上場国ｺｰﾄﾞ
            depositBalanceListSpecificDepositType: '' // 国内株式一覧.預り明細一覧.非特定預り区分
          }
        ]
      }
    ]

    // 投資信託
    this.investmentTrustList = [
      {
        numberOfDepositedIssues: '', // 投資信託一覧.預り銘柄数
        valuationTotal: '', // 投資信託一覧.評価額合計
        getProfitAll: '', // 投資信託一覧.評価損益合計
        course: '', // 投資信託一覧.コース
        reinvest: '', // 投資信託一覧.分配金受取方法区分（全角半角）
        depositBalanceAccountTypeName: '', // 投資信託一覧.口座区分
        depositType: '', // 投資信託一覧.預り区分（全角半角）
        depositDetailList: [ // エラー: 「投資信託一覧.預り明細一覧」が、項目辞書に存在しません。
          {
            brandCode: '', // 投資信託一覧.預り明細一覧.銘柄コード
            fundName: '', // 投資信託一覧.預り明細一覧.ファンド名
            unitVolume: '', //  投資信託一覧.預り明細一覧.保有口数
            sellingVolume: '', // 投資信託一覧.預り明細一覧.売却注文中
            acquirePriceReferencePrice: '', // 投資信託一覧.預り明細一覧.取得単価/参考単価
            basePrice8: '', // 投資信託一覧.預り明細一覧.基準価格
            acquireAmountReferenceAmount: '', // 投資信託一覧.預り明細一覧.取得金額/参考金額
            valuation: '', // 投資信託一覧.預り明細一覧.評価額
            mutualFundListProfitLossTotal: '', // 投資信託一覧.預り明細一覧.評価損益
            individualPrincipal: '', // 投資信託一覧.預り明細一覧.個別元本
            buyDisplayClassification: '', // 投資信託一覧.預り明細一覧.買付表示区分
            saleDisplayClassification: '', // 投資信託一覧.預り明細一覧.売却表示区分
            distributionReceiveMethod: '', // 投資信託一覧.預り明細一覧.分配金受取方法
            distributionreceiveMethodchangedisplayclassification: '', // 投資信託一覧.預り明細一覧.分配金受取方法変更表示区分
            depositBalanceListSpecificDepositType: '', // 投資信託一覧.預り明細一覧.非特定預り区分
            times: '', // 投資信託一覧.預り明細一覧.銘柄コード.回数
            issue1: '', // 投資信託一覧.預り明細一覧.銘柄コード.号1
            issue2: '', // 投資信託一覧.預り明細一覧.銘柄コード.号2
            securityType: '', // 投資信託一覧.預り明細一覧.商品区分
            kokunaiGaiKbn: '', // 投資信託一覧.預り明細一覧.国内外国区分
            securityClass1: '', // 投資信託一覧.預り明細一覧.商品種別１
            securityClass2: '', // 投資信託一覧.預り明細一覧.商品種別2
            companyCode: '', // 投資信託一覧.預り明細一覧.会社ｺｰﾄﾞ
            rightType: '', // 投資信託一覧.預り明細一覧.権利区分
            newOldType: '', // 投資信託一覧.預り明細一覧.新旧区分
            listedCountryCode: '', // 投資信託一覧.預り明細一覧.上場国ｺｰﾄﾞ
            kyoukaiCd: '', // 投資信託一覧.預り明細一覧.協会コード（全角半角）
            accumulationLink: '' // 投資信託一覧.預り明細一覧.積立表示区分
          }
        ]
      }
    ]

    // 国内債券
    this.domesticBondsList = [
      {
        numberOfDepositedIssues: '', // 国内債券一覧.預り銘柄数
        valuationTotal: '', // 国内債券一覧.評価額合計
        depositBalanceAccountTypeName: '', // 国内債券一覧.口座区分
        depositType: '', // 国内債券一覧.預り区分（全角半角)
        depositDetailList: [ // エラー: 「国内債券一覧.預り明細一覧」が、項目辞書に存在しません。
          {
            brandCode: '', // 国内債券一覧.預り明細一覧.銘柄コード
            brandName: '', // 国内債券一覧.預り明細一覧.銘柄名
            compoundInterestPercent: '', // 国内債券一覧.預り明細一覧.利率(%)
            redemptionDate: '', // 国内債券一覧.預り明細一覧.償還日
            interestPaymentDate: '', // 国内債券一覧.預り明細一覧.利払日
            volumeYen: '', // 国内債券一覧.預り明細一覧.保有額面
            acquirePriceReferencePrice: '', // 国内債券一覧.預り明細一覧.取得単価/参考単価
            contractExchange: '', // 国内債券一覧.預り明細一覧.約定為替
            exchangeRate: '', // 国内債券一覧.預り明細一覧.参考為替
            valuation: '', // 国内債券一覧.預り明細一覧.評価額
            securityType: '', // 国内債券一覧.預り明細一覧.商品区分
            kokunaiGaiKbn: '', // 国内債券一覧.預り明細一覧.国内外国区分
            securityClass1: '', // 国内債券一覧.預り明細一覧.商品種別１
            securityClass2: '', // 国内債券一覧.預り明細一覧.商品種別2
            companyCode: '', // 国内債券一覧.預り明細一覧.会社ｺｰﾄﾞ
            rightType: '', // 国内債券一覧.預り明細一覧.権利区分
            newOldType: '', // 国内債券一覧.預り明細一覧.新旧区分
            times: '', // 国内債券一覧.預り明細一覧.回数
            issue1: '', // 国内債券一覧.預り明細一覧.号1
            issue2: '', // 国内債券一覧.預り明細一覧.号2
            listedCountryCode: '', // 国内債券一覧.預り明細一覧.上場国ｺｰﾄﾞ
            depositBalanceListSpecificDepositType: '' // 国内債券一覧.預り明細一覧.非特定預り区分
          }
        ]
      }
    ]

    // 外国債券
    this.foreignBondsList = [
      {
        numberOfDepositedIssues: '', // 外国債券一覧.預り銘柄数
        valuationTotal: '', // 外国債券一覧.評価額合計
        depositBalanceAccountTypeName: '', // 外国債券一覧.口座区分
        depositType: '', // 外国債券一覧.預り区分（全角半角）
        depositDetailList: [ // エラー: 「外国債券一覧.預り明細一覧」が、項目辞書に存在しません。
          {
            brandCode: '', // 外国債券一覧.預り明細一覧.銘柄コード
            brandName: '', // 外国債券一覧.預り明細一覧.銘柄名
            volumeForeign: '', // 外国債券一覧.預り明細一覧.保有額面
            unitPrice: '', // 外国債券一覧.預り明細一覧.買付単価
            foreignValuation17: '', // 外国債券一覧.預り明細一覧.外貨建評価額
            exchangeRate: '', // 外国債券一覧.預り明細一覧.参考為替
            yenConversionValuation: '', // 外国債券一覧.預り明細一覧.円換算評価額
            currencyCode: '', // 外国債券一覧.預り明細一覧.通貨コード
            securityType: '', // 外国債券一覧.預り明細一覧.商品区分
            kokunaiGaiKbn: '', // 外国債券一覧.預り明細一覧.国内外国区分
            securityClass1: '', // 外国債券一覧.預り明細一覧.商品種別１
            securityClass2: '', // 外国債券一覧.預り明細一覧.商品種別2
            companyCode: '', // 外国債券一覧.預り明細一覧.会社ｺｰﾄﾞ
            rightType: '', // 外国債券一覧.預り明細一覧.権利区分
            newOldType: '', // 外国債券一覧.預り明細一覧.新旧区分
            times: '', // 外国債券一覧.預り明細一覧.回数
            issue1: '', // 外国債券一覧.預り明細一覧.号1
            issue2: '', // 外国債券一覧.預り明細一覧.号2
            listedCountryCode: '', // 外国債券一覧.預り明細一覧.上場国ｺｰﾄﾞ
            depositBalanceListSpecificDepositType: '', // 外国債券一覧.預り明細一覧.非特定預り区分
            depositType: '', // 預り区分
            countryCode: '', // 国コード
            securityClass: '' // 商品コード
          }
        ]
      }
    ]

    // 外国株式
    this.foreignStocksList = [
      {
        numberOfDepositedIssues: '', // 外国株式一覧.預り銘柄数
        valuationTotal: '', // 外国株式一覧.評価額合計
        getProfitAll: '', // 外国株式一覧.評価損益合計
        depositBalanceAccountTypeName: '', // 外国株式一覧.口座区分
        depositType: '', // 外国株式一覧.預り区分（全角半角）
        depositDetailList: [ // エラー: 「外国株式一覧.預り明細一覧」が、項目辞書に存在しません。
          {
            brandCode: '', // 外国株式一覧.預り明細一覧.ティッカー／銘柄コード
            brandName: '', // 外国株式一覧.預り明細一覧.銘柄名
            holdingQuantity: '', // 外国株式一覧.預り明細一覧.保有数量
            unactualQuantity: '', // 外国株式一覧.預り明細一覧.注文中
            protectType: '', // 外国株式一覧.預り明細一覧.保護区分
            openPrice: '', // 外国株式一覧.預り明細一覧.取得単価
            currencyCode: '', // 外国株式一覧.預り明細一覧.通貨コード
            currentPrice: '', // 外国株式一覧.預り明細一覧.現在値
            foreignProfitAndLoss: '', // 外国株式一覧.預り明細一覧.外貨建評価損益
            fxValuationRate: '', // 外国株式一覧.預り明細一覧.評価為替レート
            valuation: '', // 外国株式一覧.預り明細一覧.評価額（円貨）
            yenProfitLoss: '', // 外国株式一覧.預り明細一覧.評価損益（円貨）
            buyDisplayClassification: '', // 外国株式一覧.預り明細一覧.買付表示区分
            saleDisplayClassification: '', // 外国株式一覧.預り明細一覧.売却表示区分
            securityClass: '', // 外国株式一覧.預り明細一覧.商品コード
            countryCode: '', // 外国株式一覧.預り明細一覧.国コード
            depositType: '' // 外国株式一覧.預り明細一覧.預り区分
          }
        ]
      }
    ]

    // 外貨建
    this.foreignCurrencyMmfList = [
      {
        numberOfDepositedIssues: '', // 外貨建MMF一覧.預り銘柄数
        valuationTotal: '', // 外貨建MMF一覧.評価額合計
        getProfitAll: '', // 外貨建MMF一覧.評価損益合計
        depositBalanceAccountTypeName: '', // 外貨建MMF一覧.口座区分
        depositType: '', // 外貨建MMF一覧.預り区分（全角半角）
        depositDetailList: [ // エラー: 「外貨建MMF一覧.預り明細一覧」が、項目辞書に存在しません。
          {
            brandCode: '', // 外貨建MMF一覧.預り明細一覧.銘柄コード
            fundName: '', // 外貨建MMF一覧.預り明細一覧.ファンド名
            unitVolume: '', // 外貨建MMF一覧.預り明細一覧.保有口数
            unactualQuantity: '', // 外貨建MMF一覧.預り明細一覧.注文中
            foreignValuation: '', // 外貨建MMF一覧.預り明細一覧.評価額（外貨）
            currencyCode: '', // 外貨建MMF一覧.預り明細一覧.通貨コード
            fxValuationRate: '', // 外貨建MMF一覧.預り明細一覧.評価為替レート
            valuation: '', // 外貨建MMF一覧.預り明細一覧.評価額（円貨）
            yenProfitLoss: '', // 外貨建MMF一覧.預り明細一覧.評価損益（円貨）
            securityClass: '', // 外貨建MMF一覧.預り明細一覧.商品コード
            countryCode: '', // 外貨建MMF一覧.預り明細一覧.国コード
            depositType: '' // 外貨建MMF一覧.預り明細一覧.預り区分
          }
        ]
      }
    ]

    // ST
    this.securityTokenList = [
      {
        numberOfDepositedIssues: '',
        depositBalanceAccountTypeName: '',
        depositType: '',
        valuationTotal: '',
        getProfitAll: '',
        depositDetailList: [
          {
            brandCode: '',
            brandName: '',
            contractStandardDeposit: '',
            openPrice: '',
            price: '',
            valuation: '',
            yenProfitLoss: ''
          }
        ]
      }
    ]

    // その他
    this.otherSecurityList = [
      {
        numberOfDepositedIssues: '', // その他商品一覧.預り銘柄数
        depositBalanceAccountTypeName: '', // その他商品一覧.口座区分
        depositType: '', // その他商品一覧.預り区分（全角半角）
        depositDetailList: [ // エラー: 「その他商品一覧.預り明細一覧」が、項目辞書に存在しません。
          {
            securityClass: '', // その他商品一覧.預り明細一覧.商品分類
            brandNameFundName: '', // その他商品一覧.預り明細一覧.銘柄名/ファンド名
            quantity: '' // その他商品一覧.預り明細一覧.数量
          }
        ]
      }
    ]
  }
}
