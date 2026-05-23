export class IfaDomesticMutualFundOrderConfirmFormModel {
  constructor() {
    this.screenId = 'SUB0202_0401-02_2' // 画面ID
    this.Title = '国内投信注文確認' // 画面名
    this.customerNameKanji = '' // 顧客名（漢字）
    this.customerNameKana = '' // 顧客名（カナ）
    this.butenCode = '' // 部店
    this.accountNumber = '' // 口座番号
    this.shubetu = '' // 種別
    this.code = '' // エラーコード
    this.errMessage = '' // エラーメッセージ
    this.noticeInfoAlert = '' // 注意情報アラート
    this.noticeAlert = '' // お知らせアラート
    this.complianceRankCheck = {
      message: '', // メッセージ
      invitationCheck: '', // チェックボックス文言
      fundCharacter: '', // コンプラチェック用資金性格
      startCriteriaConfirmMsg: '' // コンプラランクチェック開始基準確認メッセージ
    } // コンプラランクチェック
    this.shortTermSellConfirmMsg = '' // 短期売却確認アラートメッセージ
    this.preRedemptionSellConfirmAlertMsg = '' // 償還前売却確認アラートメッセージ
    this.tradeCd = '' // 取引種別
    this.quoteUnitPrice = '' // 見積単価
    this.contractAmount = '' // 約定金額
    this.charge = '' // 手数料/諸費用
    this.nisaInvestableQuote = '' // 注文後のNISA投資可能枠(YYYY年)
    this.consumptionTax = '' // 消費税
    this.yieldTax = '' // 讓渡益税
    this.settlementAmount = '' // 精算金額
    this.contractDate = '' // 約定予定日
    this.deliveryDate = '' // 受渡予定日
    this.orderDate = '' // 受注日
    this.orderDayTime = '' // 受注時刻
    this.orderQuantity = '' // 受注数量
    this.amountOrderQuantity = '' // 受注数量（金額指定）
    this.unitOrderQuantity = '' // 受注数量（口数指定）
    this.point = '' // 利用ポイント
    this.a010ApiRequest = {
      accountType: '', // 口座
      depositType: '', // 預り区分
      tradeCd: '', // 取引種別
      fundCodeTimes: '', // ファンドコード（回数）
      fundCodeIssues: '', // ファンドコード（号）
      fundType: '', // ファンドタイプ
      brandCode: '', // 銘柄コード
      brandName: '', // 銘柄名
      douitsuTukaKuniKbn: '', // 同一通貨/同一国の乗換
      tashaNorikaeKbn: '', // 他社間投信乗換勧誘
      kanyuKbn: '', // 勧誘区分
      receiveOrderType: '', // 受注方法
      unit: '', // 口数
      saleMethod: '', // 売却方法
      sellDesignated: '', // 売却指定
      amount: '', // 金額
      tankiSellKbn: '', // 短期売却確認
      shokanMaeKbn: '', // 償還前売却確認
      pointFlg: '', // ポイント利用
      point: '', // ポイント
      pointType: '', // ポイント種別
      distributionReceiveMethodWord: '', // 分配金受取方法
      mokuromiKoufuKbn: '', // 目論見書の交付方法
      norikaeYuguKbn: '', // 乗換優遇区分
      leverageInvestTrust: '', // レバレッジ投資信託
      norikaeKanyuKbn: '', // 乗換勧誘
      conflictOfInterestExplain: '', // 利益相反可能性の説明
      checkMokuromi: '', // 確認項目.目論見書補完書面の確認
      checkMadoAki: '', // 確認項目.窓空きファンドの注意事項に同意
      checkHiyou: '', // "確認項目.費用について説明済
      checkFukusu: '', // 確認項目.複数取引業者での手数料等明示済
      tradeKbn: '', // 売買区分
      shortTermSaleConfirm: '', // "短期売却確認期間
      preRedemptionSellConfirmSelect: '', // 償還前売却確認期間
      brandSpecialClassification: '', // 銘柄情報.特殊区分
      dispatchId: '' // 目論見書チェック区分
    } // リクエスト内容
    this.alert = {
      // アラート内容確認.コンプラランクチェック確認
      complianceRankCheckConfirmCheck: false,
      // アラート内容確認.コンプラランクチェック開始基準確認
      startCriteriaConfirmCheck: '0',
      // アラート内容確認.短期売却確認アラート確認
      shortTermSaleConfirmCheck: '0',
      // アラート内容確認.償還前売却確認アラート確認
      preRedemptionSellConfirmSelectCheck: '0',
      // アラート内容確認.注意情報アラート確認
      noticeInfoAlertCheck: '0',
      // アラート内容確認.お知らせアラート確認
      noticeAlertCheck: '0'
    } // アラート内容確認
    this.selectAccountType = '' // 選択口座
    this.afterOrderNisaInvestAbleLabel = '' // 注文後のNISA投資可能枠見出し
    this.prospectusSupplementaryDocConfirm = '' // 目論見書補完書面の確認
    this.windowSpaceFundPrecautionsConsent = '' // 窓空きファンドの注意事項に同意
    this.costExplained = '' // 費用について説明済
    this.multipleTradeClearlyCommStated = '' // 複数取引業者での手数料等明示済
    this.complaRankCheckConfirmLabel = '' // コンプラランクチェック確認見出し
    this.invitationCheck = '' // コンプラランクチェック確認 【初期値】未確認
    this.complaRankCheckStartCriteriaConfirm = '' // コンプラランクチェック開始基準確認 【初期値】未確認
    this.shortTermSaleConfirmName = '' // 短期売却確認アラート確認 【初期値】未確認
    this.preRedemptionSellConfirmSelect = '' // 償還前売却確認アラート確認 【初期値】未確認
    this.noteInfoCheckbox = '' // 注意情報アラート確認 【初期値】未確認
    this.noteLimitCheck = '' // お知らせアラート確認 【初期値】未確認
    this.mutualFundSellBuyType = '' // 売買区分
    this.fundCharacter = '' // コンプラランクチェック.コンプラチェック用資金性格
  }
}
