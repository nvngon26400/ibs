export class IfaReceiptDeliveryOrderConfirmFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-08_2',
      name: '現引現渡注文確認'
    }
    /** リクエスト内容 */
    this.req = {
      /** 銘柄コード */
      brandCode: '',
      /** 取引種別 */
      tradeCd: '',
      /** 数量 */
      quantity: '',
      /** 信用取引区分 */
      marginTradeTypeText: '',
      /** 特定・一般区分 */
      accountType: '',
      /** 建市場 */
      builtMarket: '',
      /** 新規建日 */
      constructionDate: '',
      /** 親株新規約定日 */
      parentStockTradeDate: '',
      /** 新規建玉指定番号 */
      newOpenInterestNumber: '',
      /** 新規単価 */
      newPrice: '',
      /** 勧誘区分 */
      kanyuKbn: '',
      /** 受注方法 */
      receiveOrderType: '',
      /** 取得単価 */
      openPrice: '',
      /** 確認項目の契約締結前交付書面の確認 */
      checkCustomerAttribute: '',
      /** 弁済期限（算出）*/
      paymentDeadlineCalculation: '10年',
      /** 弁済期限年月日数 */
      paymentDeadlineDate: '10',
      /** 年月日区分*/
      dateKbn: 'Y'
    }
    /** 残高数量 */
    this.contPosition = ''
    /** 返済注文済未出来数量 */
    this.unactualQuantity = ''
    /** 特定建玉区分 */
    this.tokuteiContractId = ''
    /** 約定金額 */
    this.contractAmount = ''
    /** 手数料 */
    this.charge = ''
    /** 消費税 */
    this.consumptionTax = ''
    /** 譲渡益税 */
    this.yieldTax = ''
    /** 精算金額 */
    this.settlementAmount = ''
    /** 約定予定日 */
    this.contractDate = ''
    /** 受渡予定日 */
    this.deliveryDate = ''
    /** 受注日 */
    this.orderDate = ''
    /** 受注時刻 */
    this.orderTime = ''
    /** 種別 */
    this.shubetu = ''
    /** エラーコード */
    this.code = ''
    /** エラーメッセージ */
    this.errMessage = ''
    /** 注意情報アラート */
    this.noticeInfoAlert = ''
    /** お知らせアラート */
    this.noticeAlert = ''
    /** 内部者確認メッセージ */
    this.insiderConfirmMsg = ''
    /** 取引注意情報（銘柄）メッセージ */
    this.tradeNoticeInfoBrandMsg = ''
    /** 銘柄名 */
    this.brandName = ''
    /** アラート内容確認.取引注意情報（銘柄）確認 */
    this.tradingCautionInformation = '0'
    /** アラート内容確認.注意情報アラート確認 */
    this.noteInfoCheck = '0'
    /** アラート内容確認.お知らせアラート確認 */
    this.noteLimitCheck = '0'
    /** アラート内容確認.内部者エラー区分確認 */
    this.insiderErrorConfirmationCheck = '0'
    this.warningList = []
  }
}
