import Logger from '@/utils/ifaLog.js'
export class IfaReceiptDeliveryOrderInputA006RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    this.brandCode = obj.brandCode ? obj.brandCode : '' // 銘柄コード
    this.tradeCd = obj.openTradeKbn ? obj.openTradeKbn : '' // 取引種別
    this.quantity = obj.quantity ? obj.quantity : '' // 数量
    this.marginTradeTypeText = obj.paymentDeadline ? obj.paymentDeadline : '' // 信用取引区分
    this.accountType = obj.accountType ? obj.accountType : '' // 特定・一般区分
    this.builtMarket = obj.newOpenMarket ? obj.newOpenMarket : '' // 建市場
    this.constructionDate = obj.constructionDate ? obj.constructionDate : '' // 新規建日
    this.parentStockTradeDate = obj.parentStockConstructionDate ? obj.parentStockConstructionDate : '' // 親株新規約定日
    this.newOpenInterestNumber = obj.newOpenInterestNumber ? obj.newOpenInterestNumber : '' // 新規建玉指定番号
    this.newPrice = obj.newPrice ? obj.newPrice : '' // 新規単価
    this.kanyuKbn = obj.kanyuKbn ? obj.kanyuKbn : '' // 勧誘区分
    this.receiveOrderType = obj.receiveOrderType ? obj.receiveOrderType : '' // 受注方法
    this.openPrice = obj.openPrice ? obj.openPrice : '' // 取得単価
    this.paymentDeadlineDate = obj.paymentDeadlineDate ? obj.paymentDeadlineDate : '' // 弁済期限年月日数
    this.dateKbn = obj.dateKbn ? obj.dateKbn : '' // 年月日区分
    this.paymentDeadlineCalculation = obj.paymentDeadlineCalculation ? obj.paymentDeadlineCalculation : '' // 弁済期限（算出）
    this.checkCustomerAttribute = obj.insiderConfirmCheckBox ? obj.insiderConfirmCheckBox : '' // 確認項目の契約締結前交付書面の確認
  }
}
