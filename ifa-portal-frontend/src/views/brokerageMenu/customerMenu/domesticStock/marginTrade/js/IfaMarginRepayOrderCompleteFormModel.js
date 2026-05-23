export class IfaMarginRepayOrderCompleteFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-04_3',
      name: '信用返済注文完了'
    }
    /** EC受注番号（半角英数字）. */
    this.ecOrderNo = ''
    /** 見積単価（数値(小数)）. */
    this.quoteUnitPrice = ''
    /** 約定金額（数値(整数)）. */
    this.contractAmount = ''
    /** 手数料/諸費用（数値(整数)）. */
    this.charge = ''
    /** 消費税（数値(整数)）. */
    this.consumptionTax = ''
    /** 讓渡益税（数値(整数)）. */
    this.yieldTax = ''
    /** 精算金額（数値(整数)）. */
    this.settlementAmount = ''
    /** 約定予定日. */
    this.contractDate = ''
    /** 受渡予定日. */
    this.deliveryDate = ''
    /** 受注日時. */
    this.orderDayTime = ''
    /** リクエスト内容. */
    this.request = {
      /** 銘柄コード（半角英数字）. */
      brandCode: '',
      /** 市場（訂正前） */
      orderMarket: '',
      /** 市場（訂正後） */
      afterCorrecMarket: '',
      /** 取引種別（全角半角）. */
      tradeCd: '',
      /** 弁済期限（全角半角）. */
      paymentDeadline: '',
      /** 数量（数値(整数)）. */
      quantity: '',
      /** 期間.期間条件. */
      periodTerms: '',
      /** 期間.日付（全角半角）. */
      limit: '',
      /** 返済方法. */
      repayMethod: '',
      /** 返済順序. */
      repaymentOrder: '',
      /** 返済建玉カウント. */
      repayPositionCount: '',
      /** 返済建玉明細. */
      repayPositionDetail: [{
        /** 親株新規約定日. */
        parentStockTradeDate: '',
        /** 新規建日. */
        constructionDate: '',
        /** 新規単価（数値(小数)）. */
        newPrice: '',
        /** 注文株数（数値(整数)）. */
        orderQuantity: '',
        /** 建市場（全角半角）. */
        builtMarket: '',
        /** 建玉No（数字）. */
        positionNo: '',
        /** 残高数量（数値(整数)）. */
        contPosition: '',
        /** 諸経費（数値(整数)）. */
        cost: '',
        /** 返済注文済未出来数量（数値(整数)）. */
        unactualQuantity: '',
        /** 特定建玉区分（半角英数字）. */
        specificPositionType: ''
      }],
      /** 注文種別（半角英数字）. */
      orderKind: '',
      /** 通常/逆指値.執行方法（半角英数字）. */
      sasinariHouhou: '',
      /** 通常/逆指値.執行条件（半角英数字）. */
      sasinariJyouken: '',
      /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
      triggerPrice: '',
      /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
      gyakusasiHouhou: '',
      /** 通常/逆指値.注文単価（数値(整数)）. */
      price: '',
      /** OCO1.執行方法（半角英数字）. */
      oco1SasinariHouhou: '',
      /** OCO1.執行条件（半角英数字）. */
      oco1SasinariJyouken: '',
      /** OCO1.注文単価（数値(整数)）. */
      oco1Price: '',
      /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
      oco2TriggerPrice: '',
      /** OCO2.執行方法（逆指値）（半角英数字）. */
      oco2GyakusasiHouhou: '',
      /** OCO2.執行条件（逆指値）（半角英数字）. */
      oco2GyakusasiJyouken: '',
      /** OCO2.注文単価（数値(整数)）. */
      oco2Price: '',
      /** 勧誘区分（全角半角）. */
      kanyuKbn: '',
      /** 受注方法. */
      orderMethod: '',
      /** 確認項目.インサイダー確認（半角英数字）. */
      checkInsider: '',
      /** 確認項目.SOR確認（半角英数字）. */
      checkSor: '',
      /** アラート内容確認.取引注意情報（銘柄）確認. */
      tradeNoticeInfoBrandConfirm: '',
      /** アラート内容確認.注意情報アラート確認. */
      noticeInfoAlertConfirm: '',
      /** アラート内容確認.お知らせアラート確認. */
      noticeAlertConfirm: '',
      /** アラート内容確認.内部者エラー確認. */
      insiderErrorConfirm: '',
      /** 注意情報アラート（全角半角）. */
      noticeInfoAlert: '',
      /** お知らせアラート（全角半角）. */
      noticeAlert: '',
      /** 内部者確認メッセージ. */
      insiderConfirmMsg: '',
      /** 取引注意情報（銘柄）メッセージ（全角半角）. */
      tradeNoticeInfoBrandMsg: '',
      /** 銘柄名（全角半角）. */
      brandName: '',
      /** 新規売買区分 */
      openTradeKbn: '',
      /** 弁済期限（算出） */
      paymentDeadlineCalculation: ''
    }
  }
}
