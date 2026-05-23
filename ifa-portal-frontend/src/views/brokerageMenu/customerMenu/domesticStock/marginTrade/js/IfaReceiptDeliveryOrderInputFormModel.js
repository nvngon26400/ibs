export class IfaReceiptDeliveryOrderInputFormModel {
  constructor() {
    this.title = {
      id: 'SUB0202_0212-08_1',
      name: '現引現渡注文入力'
    }
    this.deliveryDateT2 = '' // 現引可能額（2営業日後）のラベル
    this.cashOnDeliveryT2 = '' // 現引可能額（2営業日後）の金額
    this.deliveryDateT3 = '' // 現引可能額（3営業日後）のラベル
    this.cashOnDeliveryT3 = '' // 現引可能額（3営業日後）の金額
    this.openTradeKbn = '' // 選択行_新規売買区分
    this.tradeCd = '' // 取引種別
    this.maxOrderableQuantity = '' // 注文可能数量
    this.quantity = '' // 数量 【初期値】空欄
    this.quantityTradeUnit = '' // 数量の売買単位
    this.paymentDeadline = '' // 弁済期限（選択行_弁済期限）
    this.domesticDepositType = '' // 特定・一般区分 【初期値】未選択
    this.newOpenMarket = '' // 建市場
    this.constructionDate = '' // 新規建日
    this.kanyuKbn = '' // 勧誘区分 【初期値】未選択
    this.insiderConfirmCheckBox = '' // 確認項目の契約締結前交付書面の確認 【初期値】未チェック
    this.receiveOrderType = '' // 受注方法 【初期値】未選択
    /** 選択行_銘柄コード */
    this.brandCode = ''
    /** 新規建玉指定番号 */
    this.newOpenInterestNumber = ''
    /** 親株新規建日 */
    this.parentStockConstructionDate = ''
    /** 取得単価 */
    this.openPrice = ''
    /** 新規単価 */
    this.newPrice = ''
    /** 信用取引区分（弁済期限（算出）） */
    this.paymentDeadlineCalculation = ''
    /** 弁済期限年月日数 */
    this.paymentDeadlineDate = ''
    /** 年月日区分 */
    this.dateKbn = ''
  }
}
