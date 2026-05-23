export class IfaMarginRepayOrderCorrectConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-06_2',
      name: '信用返済注文訂正確認'
    }
    /** 銘柄名 */
    this.brandName = ''
    /** 注意情報アラート */
    this.noticeInfoAlert = ''
    /** お知らせアラート */
    this.noticeAlert = ''
    /** 取引注意情報（銘柄）メッセージ */
    this.tradeNoticeInfoBrandMsg = ''
    /** 種別 */
    this.shubetu = ''
    /** エラーコード */
    this.code = ''
    /** エラーメッセージ */
    this.errMessage = ''
    /** 約定予定日 */
    this.contractDate = ''
    /** 受渡予定日 */
    this.deliveryDate = ''
    /** 受注日時 */
    this.orderDayTime = ''
    /** 訂正後建玉余力 */
    this.positionPower = ''
    /** 訂正SOR注文結果区分 */
    this.correctSorOrderResultClassification = ''
    /** 有効期限変更フラグ */
    this.yukoKigenChange = ''
    /** 有効期限 */
    this.yukoKigen = ''
    /** リクエスト内容 */
    this.request = {
      /** EC受注番号 */
      ecOrderNo: '',
      /** 市場（訂正前） */
      orderMarket: '',
      /** 市場（訂正後） */
      afterCorrecMarket: '',
      /** 銘柄コード */
      brandCode: '',
      /** 数量 */
      quantity: '',
      /** 未約定数量 */
      unTradeQuantity: '',
      /** 注文種別 */
      orderKind: '',
      /** 取引種別 */
      tradeCd: '',
      /** 期間 */
      period: '',
      /** 信用取引区分 */
      marginTradeTypeText: '',
      /** 発火状態 */
      workingStatus: '',
      /** 弁済期限 */
      paymentDeadline: '',
      /** RBE注文ステータス */
      rbeOrderStatus: '',
      /** 通常/逆指値.執行方法 */
      sasinariHouhou: '',
      /** 通常/逆指値.執行条件 */
      sasinariJyouken: '',
      /** 通常/逆指値.発火条件価格（逆指値） */
      triggerPrice: '',
      /** 通常/逆指値.執行方法（逆指値） */
      gyakusasiHouhou: '',
      /** 通常/逆指値.注文単価 */
      price: '',
      /** OCO1.執行方法 */
      oco1SasinariHouhou: '',
      /** OCO1.執行条件 */
      oco1SasinariJyouken: '',
      /** OCO1.注文単価 */
      oco1Price: '',
      /** OCO2.発火条件価格（逆指値） */
      oco2TriggerPrice: '',
      /** OCO2.執行方法（逆指値） */
      oco2GyakusasiHouhou: '',
      /** OCO2.執行条件（逆指値） */
      oco2GyakusasiJyouken: '',
      /** OCO2.注文単価 */
      oco2Price: '',
      /** 訂正前価格.通常/逆指値.執行方法 */
      correctBeforePriceSasinariHouhou: '',
      /** 訂正前価格.通常/逆指値.執行条件 */
      correctBeforePriceSasinariJyouken: '',
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
      correctBeforePriceTriggerPrice: '',
      /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
      correctBeforePriceStopOrderPriceText2: '',
      /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
      correctBeforePriceGyakusasiHouhou: '',
      /** 訂正前価格.通常/逆指値.注文単価 */
      correctBeforePricePrice: '',
      /** 訂正前価格.OCO1.執行方法 */
      correctBeforePriceOco1SasinariHouhou: '',
      /** 訂正前価格.OCO1.執行条件 */
      correctBeforePriceOco1SasinariJyouken: '',
      /** 訂正前価格.OCO1.注文単価 */
      correctBeforePriceOco1Price: '',
      /** 訂正前価格.OCO2.発火条件価格（逆指値） */
      correctBeforePriceOco2TriggerPrice: '',
      /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
      correctBeforePriceOco2StopOrderPriceText2: '',
      /** 訂正前価格.OCO2.執行方法（逆指値） */
      correctBeforePriceOco2GyakusasiHouhou: '',
      /** 訂正前価格.OCO2.執行条件（逆指値） */
      correctBeforePriceOco2GyakusasiJyouken: '',
      /** 訂正前価格.OCO2.注文単価 */
      correctBeforePriceOco2Price: '',
      /** 勧誘区分 */
      kanyuKbn: '',
      /** 受注方法 */
      orderMethod: '',
      /** 確認項目.契約締結前交付書面確認 */
      checkInsider: '',
      /** 確認項目.SOR確認 */
      checkSor: '',
      /** 手数料区分 */
      tesuuryouKbn: '',
      /** 受注日 */
      orderDate: '',
      /** 訂正SOR注文区分 */
      correctSorOrderClassification: '',
      /** 弁済期限年月日数. */
      aymentDeadlineDate: '',
      /** 年月日区分. */
      dateKbn: ''
    }
    /** アラート内容確認.取引注意情報（銘柄）確認 */
    this.tradeNoticeInfoBrandConfirm = '0'
    /** アラート内容確認.注意情報アラート確認 */
    this.noticeInfoAlertConfirm = '0'
    /** アラート内容確認.お知らせアラート確認 */
    this.noticeAlertConfirm = '0'
  }
}
