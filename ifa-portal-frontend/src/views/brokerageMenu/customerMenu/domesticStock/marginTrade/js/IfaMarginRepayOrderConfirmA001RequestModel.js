import Logger from '@/utils/ifaLog.js'
export class IfaMarginRepayOrderConfirmA001RequestModel {
  constructor(obj) {
    Logger.debug(obj)
    /** 銘柄コード（半角英数字）. */
    this.brandCode = obj.request.brandCode ? obj.request.brandCode : ''
    /** 発注市場（全角半角）. */
    this.orderMarket = obj.request.orderMarket ? obj.request.orderMarket : ''
    /** 取引種別（全角半角）. */
    this.tradeCd = obj.request.tradeCd ? obj.request.tradeCd : ''
    /** 弁済期限（全角半角）. */
    this.paymentDeadline = obj.request.paymentDeadline ? obj.request.paymentDeadline : ''
    /** 数量（数値(整数)）. */
    this.quantity = obj.request.quantity ? obj.request.quantity : ''
    /** 期間.期間条件. */
    this.periodTerms = obj.request.periodTerms ? obj.request.periodTerms : ''
    /** 期間.日付（全角半角）. */
    this.limit = obj.request.limit ? obj.request.limit : ''
    /** 返済方法. */
    this.repayMethod = obj.request.repayMethod ? obj.request.repayMethod : ''
    /** 返済順序. */
    this.repaymentOrder = obj.request.repaymentOrder ? obj.request.repaymentOrder : ''
    /** 返済建玉カウント. */
    this.repayPositionCount = obj.request.repayPositionCount ? obj.request.repayPositionCount : ''
    /** 返済建玉明細. */
    this.repayPositionDetail = [] // 返済建玉明細
    if (obj.request.repayPositionDetail) {
      for (let i = 0; i < obj.request.repayPositionDetail.length; i++) {
        this.repayPositionDetail.push({
        /** 親株新規約定日. */
          parentStockTradeDate: obj.request.repayPositionDetail[i].parentStockTradeDate,
          /** 新規建日. */
          constructionDate: obj.request.repayPositionDetail[i].constructionDate,
          /** 新規単価（数値(小数)）. */
          newPrice: obj.request.repayPositionDetail[i].newPrice,
          /** 注文株数（数値(整数)）. */
          orderQuantity: obj.request.repayPositionDetail[i].orderQuantity,
          /** 建市場（全角半角）. */
          builtMarket: obj.request.repayPositionDetail[i].builtMarket,
          /** 建玉No（数字）. */
          positionNo: obj.request.repayPositionDetail[i].positionNo,
          /** 残高数量（数値(整数)）. */
          contPosition: obj.request.repayPositionDetail[i].contPosition,
          /** 諸経費（数値(整数)）. */
          cost: obj.request.repayPositionDetail[i].cost,
          /** 返済注文済未出来数量（数値(整数)）. */
          unactualQuantity: obj.request.repayPositionDetail[i].unactualQuantity,
          /** 特定建玉区分（半角英数字）. */
          specificPositionType: obj.request.repayPositionDetail[i].specificPositionType
        })
      }
    }
    /** 注文種別（半角英数字）. */
    this.orderKind = obj.request.orderKind ? obj.request.orderKind : ''
    /** 通常/逆指値.執行方法（半角英数字）. */
    this.sasinariHouhou = obj.request.sasinariHouhou ? obj.request.sasinariHouhou : ''
    /** 通常/逆指値.執行条件（半角英数字）. */
    this.sasinariJyouken = obj.request.sasinariJyouken ? obj.request.sasinariJyouken : ''
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    this.triggerPrice = obj.request.triggerPrice ? obj.request.triggerPrice : ''
    /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
    this.gyakusasiHouhou = obj.request.gyakusasiHouhou ? obj.request.gyakusasiHouhou : ''
    /** 通常/逆指値.注文単価（数値(整数)）. */
    this.price = obj.request.price ? obj.request.price : ''
    /** OCO1.執行方法（半角英数字）. */
    this.oco1SasinariHouhou = obj.request.oco1SasinariHouhou ? obj.request.oco1SasinariHouhou : ''
    /** OCO1.執行条件（半角英数字）. */
    this.oco1SasinariJyouken = obj.request.oco1SasinariJyouken ? obj.request.oco1SasinariJyouken : ''
    /** OCO1.注文単価（数値(整数)）. */
    this.oco1Price = obj.request.oco1Price ? obj.request.oco1Price : ''
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    this.oco2TriggerPrice = obj.request.oco2TriggerPrice ? obj.request.oco2TriggerPrice : ''
    /** OCO2.執行方法（逆指値）（半角英数字）. */
    this.oco2GyakusasiHouhou = obj.request.oco2GyakusasiHouhou ? obj.request.oco2GyakusasiHouhou : ''
    /** OCO2.執行条件（逆指値）（半角英数字）. */
    this.oco2GyakusasiJyouken = obj.request.oco2GyakusasiJyouken ? obj.request.oco2GyakusasiJyouken : ''
    /** OCO2.注文単価（数値(整数)）. */
    this.oco2Price = obj.request.oco2Price ? obj.request.oco2Price : ''
    /** 勧誘区分（全角半角）. */
    this.kanyuKbn = obj.request.kanyuKbn ? obj.request.kanyuKbn : ''
    /** 受注方法. */
    this.orderMethod = obj.request.orderMethod ? obj.request.orderMethod : ''
    /** 確認項目.インサイダー確認（半角英数字）. */
    this.checkInsider = obj.request.checkInsider ? obj.request.checkInsider : ''
    /** 確認項目.SOR確認（半角英数字）. */
    this.checkSor = obj.request.checkSor ? obj.request.checkSor : ''
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    this.tradeNoticeInfoBrandConfirm = obj.tradeNoticeInfoBrandConfirm ? obj.tradeNoticeInfoBrandConfirm : ''
    /** アラート内容確認.注意情報アラート確認. */
    this.noticeInfoAlertConfirm = obj.noticeInfoAlertConfirm ? obj.noticeInfoAlertConfirm : ''
    /** アラート内容確認.お知らせアラート確認. */
    this.noticeAlertConfirm = obj.noticeAlertConfirm ? obj.noticeAlertConfirm : ''
    /** アラート内容確認.内部者エラー確認. */
    this.insiderErrorConfirm = obj.insiderErrorConfirm ? obj.insiderErrorConfirm : ''
    /** 注意情報アラート（全角半角）. */
    this.noticeInfoAlert = obj.noticeInfoAlert ? obj.noticeInfoAlert : ''
    /** お知らせアラート（全角半角）. */
    this.noticeAlert = obj.noticeAlert ? obj.noticeAlert : ''
    /** 内部者確認メッセージ. */
    this.insiderConfirmMsg = obj.insiderConfirmMsg ? obj.insiderConfirmMsg : ''
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    this.tradeNoticeInfoBrandMsg = obj.tradeNoticeInfoBrandMsg ? obj.tradeNoticeInfoBrandMsg : ''
    /** 銘柄名（全角半角）. */
    this.brandName = obj.brandName ? obj.brandName : ''
    /** 新規売買区分 */
    this.openTradeKbn = obj.request.openTradeKbn ? obj.request.openTradeKbn : ''
    /** 弁済期限（算出） */
    this.paymentDeadlineCalculation = obj.request.paymentDeadlineCalculation ? obj.request.paymentDeadlineCalculation : ''
  }
}
