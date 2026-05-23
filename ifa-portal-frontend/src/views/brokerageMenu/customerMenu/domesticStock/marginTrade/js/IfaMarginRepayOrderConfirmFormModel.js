export class IfaMarginRepayOrderConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-04_2',
      name: '信用返済注文確認'
    }
    /** 銘柄名 */
    this.brandName = ''
    /** 種別 */
    this.shubetu = ''
    /** エラーコード */
    this.code = ''
    /** エラーメッセージ */
    this.errMessage = ''
    /** エラー明細(1-999) */
    this.errorDetail1To999 = ''
    /** 注意情報アラート */
    this.noticeInfoAlert = ''
    /** お知らせアラート */
    this.noticeAlert = ''
    /** 内部者確認メッセージ */
    this.insiderConfirmMsg = ''
    /** 取引注意情報（銘柄）メッセージ */
    this.tradeNoticeInfoBrandMsg = ''
    /** 見積単価 */
    this.quoteUnitPrice = ''
    /** 約定金額 */
    this.contractAmount = ''
    /** 手数料/諸費用 */
    this.charge = ''
    /** 消費税 */
    this.consumptionTax = ''
    /** 讓渡益税 */
    this.yieldTax = ''
    /** 精算金額 */
    this.settlementAmount = ''
    /** 約定予定日 */
    this.contractDate = ''
    /** 受渡予定日 */
    this.deliveryDate = ''
    /** 受注日時 */
    this.orderDayTime = ''
    /** リクエスト内容 */
    this.request = {
      /** 銘柄コード */
      brandCode: '',
      /** 発注市場 */
      orderMarket: '',
      /** 取引種別 */
      tradeCd: '',
      /** 弁済期限 */
      paymentDeadline: '',
      /** 数量 */
      quantity: '',
      /** 期間.期間条件. */
      periodTerms: '',
      /** 期間.日付（全角半角）. */
      limit: '',
      /** 返済方法 */
      repayMethod: '',
      /** 返済順序 */
      repaymentOrder: '',
      /** 返済建玉カウント */
      repayPositionCount: '',
      /** 返済建玉明細 */
      repayPositionDetail: [{
        /** 建市場 */
        builtMarket: '',
        /** 新規建日（新規約定日） */
        constructionDate: '',
        /** 親株新規約定日 */
        parentStockTradeDate: '',
        /** 新規単価 */
        newPrice: '',
        /** 建玉No */
        positionNo: '',
        /** 残高数量 */
        contPosition: '',
        /** 諸経費 */
        cost: '',
        /** 返済注文済未出来数量 */
        unactualQuantity: '',
        /** 注文株数 */
        orderQuantity: '',
        /** 評価損益（リアル） */
        profitAndLossReal: '',
        /** 特定建玉区分 */
        specificPositionType: '',
        /** 権利区分 */
        rightType: ''
      }],
      /** 注文種別 */
      orderKind: '',
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
      /** 勧誘区分 */
      kanyuKbn: '',
      /** 受注方法 */
      orderMethod: '',
      /** 確認項目.インサイダー確認 */
      checkInsider: '',
      /** 確認項目.SOR確認 */
      checkSor: '',
      /** 新規売買区分 */
      openTradeKbn: '',
      /** 弁済期限（算出） */
      paymentDeadlineCalculation: ''
    }
    /** アラート内容確認.取引注意情報（銘柄）確認 */
    this.tradeNoticeInfoBrandConfirm = '0'
    /** アラート内容確認.注意情報アラート確認 */
    this.noticeInfoAlertConfirm = '0'
    /** アラート内容確認.お知らせアラート確認 */
    this.noticeAlertConfirm = '0'
    /** アラート内容確認.内部者エラー区分確認 */
    this.insiderErrorConfirm = '0'
    this.warningList = []
  }
}
