import Logger from '@/utils/ifaLog.js'
export class IfaReceiptDeliveryOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** 銘柄コード */
    this.brandCode = obj.req.brandCode ? obj.req.brandCode : ''
    /** 銘柄名 */
    this.brandName = obj.brandName ? obj.brandName : ''
    /** 取引種別 */
    this.tradeCd = obj.req.tradeCd ? obj.req.tradeCd : ''
    /** 受注数量 */
    this.quantity = obj.req.quantity ? obj.req.quantity : ''
    /** 弁済期限 */
    this.paymentDeadline = obj.req.marginTradeTypeText ? obj.req.marginTradeTypeText : ''
    /** 特定・一般区分 */
    this.accountType = obj.req.accountType ? obj.req.accountType : ''
    /** 返済建玉明細.建市場 */
    this.builtMarket = obj.req.builtMarket ? obj.req.builtMarket : ''
    /** 返済建玉明細.新規建日 */
    this.constructionDate = obj.req.constructionDate ? obj.req.constructionDate : ''
    /** 親株新規約定日 */
    this.parentStockTradeDate = obj.req.parentStockTradeDate ? obj.req.parentStockTradeDate : ''
    /** 新規建玉指定番号 */
    this.newOpenInterestNumber = obj.req.newOpenInterestNumber ? obj.req.newOpenInterestNumber : ''
    /** 新規単価 */
    this.newPrice = obj.req.newPrice ? obj.req.newPrice : ''
    /** 勧誘区分（全角半角） */
    this.kanyuKbn = obj.req.kanyuKbn ? obj.req.kanyuKbn : ''
    /** 受注方法 */
    this.receiveOrderType = obj.req.receiveOrderType ? obj.req.receiveOrderType : ''
    /** 取得単価 */
    this.openPrice = obj.req.openPrice ? obj.req.openPrice : ''
    /** 残高数量 */
    this.contPosition = obj.contPosition ? obj.contPosition : ''
    /** 返済注文済未出来数量 */
    this.unactualQuantity = obj.unactualQuantity ? obj.unactualQuantity : ''
    /** 特定建玉区分 */
    this.tokuteiContractId = obj.tokuteiContractId ? obj.tokuteiContractId : ''
    /** 確認項目の契約締結前交付書面の確認 */
    this.checkCustomerAttribute = obj.req.checkCustomerAttribute ? obj.req.checkCustomerAttribute : ''
    /** 弁済期限（算出）. */
    this.paymentDeadlineCalculation = obj.req.paymentDeadlineCalculation ? obj.req.paymentDeadlineCalculation : ''
    /** 弁済期限年月日数. */
    this.paymentDeadlineDate = obj.req.paymentDeadlineDate ? obj.req.paymentDeadlineDate : ''
    /** 年月日区分. */
    this.dateKbn = obj.req.dateKbn ? obj.req.dateKbn : ''
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    this.tradingCautionInformation = obj.tradingCautionInformation ? obj.tradingCautionInformation : ''
    /** アラート内容確認.注意情報アラート確認 */
    this.noteInfoCheck = obj.noteInfoCheck ? obj.noteInfoCheck : ''
    /** アラート内容確認.お知らせアラート確認 */
    this.noteLimitCheck = obj.noteLimitCheck ? obj.noteLimitCheck : ''
    /** アラート内容確認.内部者エラー確認 */
    this.insiderErrorConfirmationCheck = obj.insiderErrorConfirmationCheck ? obj.insiderErrorConfirmationCheck : ''
    /** 注意情報アラート（全角半角） */
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : ''
    /** お知らせアラート（全角半角） */
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : ''
    /** 内部者確認メッセージ */
    this.insiderErrorMsg = obj.insiderConfirmMsg ? obj.insiderConfirmMsg : ''
    /** 取引注意情報（銘柄）メッセージ（全角半角） */
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : ''
  }
}
