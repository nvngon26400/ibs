export class IfaMarginRepayOrderCorrectInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-06_1',
      name: '信用返済注文訂正入力'
    }
    /** 銘柄コード */
    this.brandCode = ''
    /** 銘柄名 */
    this.brandName = ''
    /** 発注市場 */
    this.orderMarket = ''
    /** 取引種別 */
    this.tradeCd = ''
    /** 注文数量 */
    this.quantity = ''
    /** 未約定数量 */
    this.unTradeQuantity = ''
    /** 期間 */
    this.period = ''
    /** 注文種別 */
    this.orderKind = ''
    /** 通常/逆指値.執行方法 */
    this.sasinariHouhou = ''
    /** 通常/逆指値.執行条件 */
    this.sasinariJyouken = ''
    /** 通常/逆指値.発火条件価格（逆指値） */
    this.triggerPrice = ''
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン */
    this.triggerPriceText = ''
    /** 通常/逆指値.執行方法（逆指値） */
    this.gyakusasiHouhou = ''
    /** 通常/逆指値.注文単価 */
    this.price = ''
    /** OCO1.執行方法 */
    this.oco1SasinariHouhou = ''
    /** OCO1.執行条件 */
    this.oco1SasinariJyouken = ''
    /** OCO1.注文単価 */
    this.oco1Price = ''
    /** OCO2.発火条件価格（逆指値） */
    this.oco2TriggerPrice = ''
    /** OCO2.発火条件価格（逆指値）ゾーン */
    this.oco2TriggerPriceText = ''
    /** OCO2.執行方法（逆指値） */
    this.oco2GyakusasiHouhou = ''
    /** OCO2.執行条件（逆指値） */
    this.oco2GyakusasiJyouken = ''
    /** OCO2.注文単価 */
    this.oco2Price = ''
    /** 発火状態 */
    this.workingStatus = ''
    /** 弁済期限 */
    this.paymentDeadline = ''
    /** 信用取引区分 */
    this.marginTradeTypeText = ''
    /** RBE注文ステータス */
    this.rbeOrderStatus = ''
    /** 新規単価 */
    this.newPrice = ''
    /** 新規建日（新規約定日） */
    this.constructionDate = ''
    /** 建玉No */
    this.positionNo = ''
    /** 手数料区分 */
    this.tesuuryouKbn = ''
    /** 受注日 */
    this.orderDate = ''
    /** 勧誘区分 */
    this.kanyuKbn = ''
    /** 受注方法 */
    this.orderMethod = ''
    /** 契約締結前交付書面確認 */
    this.checkInsider = ''

    /** 約定ステータス */
    this.tradeStatus = ''
    /** 直近市場 */
    this.latestMarketId = ''
    /** SOR注文区分 */
    this.sorOrderClassification = ''
    /** 訂正SOR注文結果区分 */
    this.correctSorOrderResultClassification = ''
    /** 訂正後市場 */
    this.afterCorrecMarket = ''
    /** 確認項目.SOR確認 */
    this.checkSor = ''
    /** 訂正SOR注文区分 */
    this.correctSorOrderClassification = ''
     /** SOR取扱区分 */
     this.sorServiceKbn = ''

    /** 訂正前価格 */
    this.beforeCorrectPrice = {
      /** 訂正前価格.通常/逆指値.執行方法 */
      sasinariHouhou: '',
      /** 訂正前価格.通常/逆指値.執行条件 */
      sasinariJyouken: '',
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
      triggerPrice: '',
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
      triggerPriceText: '',
      /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
      gyakusasiHouhou: '',
      /** 訂正前価格.通常/逆指値.注文単価 */
      price: '',
      /** 訂正前価格.OCO1.執行方法 */
      oco1SasinariHouhou: '',
      /** 訂正前価格.OCO1.執行条件 */
      oco1SasinariJyouken: '',
      /** 訂正前価格.OCO1.注文単価 */
      oco1Price: '',
      /** 訂正前価格.OCO2.発火条件価格（逆指値） */
      oco2TriggerPrice: '',
      /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
      oco2TriggerPriceText: '',
      /** 訂正前価格.OCO2.執行方法（逆指値） */
      oco2GyakusasiHouhou: '',
      /** 訂正前価格.OCO2.執行条件（逆指値） */
      oco2GyakusasiJyouken: '',
      /** 訂正前価格.OCO2.注文単価 */
      oco2Price: ''
    }
    /** EC受注番号 */
    this.ecOrderNo = ''
    /** 弁済期限年月日数. */
    this.paymentDeadlineDate = ''
    /** 年月日区分. */
    this.dateKbn = ''
    /** CC014 */
    this.cc014 = {
      /** CC014.市場 */
      market: '',
      /** CC014.銘柄コード */
      brandCode: ''
    }
  }
}
