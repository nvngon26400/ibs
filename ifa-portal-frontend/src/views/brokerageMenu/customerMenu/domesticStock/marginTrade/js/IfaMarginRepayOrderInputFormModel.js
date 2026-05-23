export class IfaMarginRepayOrderInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-04_1',
      name: '信用返済注文入力'
    }
    // 銘柄コード
    this.brandCode = ''
    // 発注市場
    this.orderMarket = ''
    // 取引種別
    this.tradeCd = ''
    // 弁済期限
    this.paymentDeadline = ''
    // 数量
    this.quantity = ''
    // 期間.期間条件.
    this.periodTerms = null
    // 期間.日付（全角半角）.
    this.limit = ''
    // 返済方法
    this.repayMethod = '0'
    // 返済順序
    this.repaymentOrder = '61'
    // 返済建玉カウント
    this.repayPositionCount = ''
    // 注文種別
    this.orderKind = '1'
    // 通常/逆指値.執行方法
    this.sasinariHouhou = '1'
    // 通常/逆指値.執行条件
    this.sasinariJyouken = ' '
    // 通常/逆指値.発火条件価格（逆指値）
    this.triggerPrice = ''
    // 通常/逆指値.執行方法（逆指値）
    this.gyakusasiHouhou = '1'
    // 通常/逆指値.注文単価
    this.price = ''
    // OCO1.執行方法
    this.oco1SasinariHouhou = ''
    // OCO1.執行条件
    this.oco1SasinariJyouken = ''
    // OCO1.注文単価
    this.oco1Price = ''
    // OCO2.発火条件価格（逆指値）
    this.oco2TriggerPrice = ''
    // OCO2.執行方法（逆指値）
    this.oco2GyakusasiHouhou = ''
    // OCO2.執行条件（逆指値）
    this.oco2GyakusasiJyouken = ''
    // OCO2.注文単価
    this.oco2Price = ''
    // 勧誘区分
    this.kanyuKbn = ''
    // 受注方法
    this.orderMethod = ''
    // 確認項目.インサイダー確認
    this.checkInsider = ''
    // 確認項目.SOR確認
    this.checkSor = ''
    // 新規売買区分
    this.openTradeKbn = ''
    // 合計数量
    this.totalQuantity = ''
    this.repayPositionDetailList = [{ // 返済建玉明細
      builtMarket: '', // 建市場
      constructionDate: '', // 新規建日（新規約定日）
      parentStockTradeDate: '', // 親株新規約定日
      newPrice: '', // 新規単価
      positionNo: '', // 建玉No
      contPosition: '', // 残高数量
      cost: '', // 諸経費
      unactualQuantity: '', // 返済注文済未出来数量
      orderQuantity: '', // 注文株数
      profitAndLossReal: '', // 評価損益（リアル
      specificPositionType: '', // 特定建玉区分
      rightType: '' // 権利区分
    }]
    this.businessDayList = [] // 営業日リスト
    this.selectedMarket = '' // 発注市場
    this.maxOrderableQuantity = '' // 注文可能数量
    this.domesticQuantityInput = '' // 数量 【初期値】空欄
    this.unit = '' // 売買単位
    this.builtMarket = '' // 建市場
    this.constructionDate = '' // 新規建日
    this.parentStockTradeDate = '' // 親株新規約定日
    this.newPrice = '' // 新規単価
    this.orderQuantity = '' // 注文株数
    this.profitAndLossReal = '' // 評価損益（リアル）
    this.orderExecuteMethodList = [] // 執行方法 【初期値】指値
    this.limitExecutionConditionList = [] // 執行条件（指値） 【初期値】条件なし
    this.domesticLimitPrice = '' // 注文単価（指値） 【初期値】空欄
    this.priceLimit = '' // 制限値幅（指値）
    this.priceLimitDate = '' // 制限値幅（指値）日付
    this.marketExecutionConditionList = [] // 執行条件（成行） 【初期値】条件なし
    this.stopOrderPriceText1 = '' // 発火条件価格（逆指値）前文言
    this.domesticStopOrderPrice = '' // 発火条件価格（逆指値） 【初期値】空欄
    this.stopOrderPriceText2 = '' // 発火条件価格（逆指値）後文言
    this.stopOrderExecuteMethodList = [] // 執行方法（逆指値） 【初期値】指値
    this.newPricestopOrderLimitExecutionConditionList = [] // 執行条件（逆指値/指値） 【初期値】条件なし
    this.stopOrderMarketExecutionConditionList = [] // 執行条件（逆指値/成行） 【初期値】条件なし
    this.domesticStopOrderExecutePrice = '' // 注文単価（逆指値/指値） 【初期値】空欄
    this.priceLimitStopOrder = '' // 制限値幅（逆指値）
    this.stopOrderPriceLimitDate = '' // 制限値幅（逆指値）日付
    this.stopOrderExecutePriceText2 = '' // 注文単価（逆指値）文言
    this.orderTypeName = [] // 注文種別 【初期値】通常/逆指値
    this.periodRadio = '' // 期間 【初期値】未選択
    this.warningMessage = '' // 注意喚起文言
    this.paymentDeadline = '' // 信用取引区分
    this.individualDesignationPositionInfoRepayMethod = '' // 返済方法
    this.individualDesignationPositionInfoBuiltMarket = '' // 建市場
    this.individualDesignationPositionInfoConstructionDate = '' // 新規建日
    this.individualDesignationPositionInfoParentStockTradeDate = '' // 親株新規約定日
    this.individualDesignationPositionInfoNewPrice = '' // 新規単価
    this.oco1SasinariHouhou = '1' // 執行方法 【初期値】指値
    this.oco1SasinariJyouken = ' ' // 執行条件（指値） 【初期値】条件なし
    this.oco2OrderExecuteMethodList = '3' // 執行方法 【初期値】逆指値
    this.oco2StopOrderPriceText2 = '' // 発火条件価格（逆指値）後文言
    this.oco2GyakusasiHouhou = '1' // 執行方法（逆指値） 【初期値】指値
    this.oco2GyakusasiJyouken = ' ' // 執行条件（逆指値/成行） 【初期値】条件なし
    this.tesuuryouKbn = '' // 手数料区分
    this.solicitTypeList = [] // 勧誘区分 【初期値】未選択
    this.insiderConfirmCheckBox = '' // インサイダー確認 【初期値】未選択
    this.sorConfirmationCheckbox = '' // SOR確認 【初期値】未選択
    this.hiddenParentStockTradeDate = '' // 親株新規約定日
    this.hiddenConstructionDate = '' // 新規建日
    this.hiddenNewPrice = '' // 新規単価
    this.hiddenOrderQuantity = '' // 注文株数
    this.hiddenBuiltMarket = '' // 建市場
    this.hiddenPositionNo = '' // 建玉No
    this.ctNightBatchFinishFlag = '' // CT夜間バッチ終了フラグ
    this.paymentDeadlineCalculation = '' // 弁済期限（算出）
  }
}
